import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.KeyValuePair;

public class Donation {

    public static class DonorName {
        private String lastName;
        private String firstName;
        private String mI;

        @Override
        public String toString() {
            if (mI == "") {
                return firstName + " " + lastName;
            } else {
                return firstName + " " + mI + " " + lastName;
            }
        }

        public DonorName SetName(String s) {
            String[] sArr = s.split(" ");
            String first = "";
            String MI = "";
            String last = "";

            if (sArr.length > 2) {
                MI = sArr[1];
                last = sArr[2];
            } else {
                last = sArr[1];
            }
            first = sArr[0];

            setLastName(last);
            setFirstName(first);
            setMI(MI);

            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMI() {
            return mI;
        }

        public void setLastName(String ln) {
            lastName = ln;
        }

        public void setFirstName(String fn) {
            firstName = fn;
        }

        public void setMI(String mi) {
            if (mi.contains(".")) {
                mi = mi.replace(".", "");
            }
            mI = mi;
        }
    }

    // this is just one design choice, but it works if the field is static enough
    // (not frequently changing)
    public enum DonationType {
        MONEY,
        FOOD,
        CLOTHING,
        MISC
        // add fields as necessary to expand supported donation type set
    }

    // #region private members and getter/setter methods
    private DonorName donorName;
    private DonationType donationType;
    private Double donationAmount;
    private LocalDateTime donationDate;

    public DonorName getDonorName() {
        return donorName;
    }

    public DonationType getDonationType() {
        return donationType;
    }

    public Double getDonationAmount() {
        return donationAmount;
    }

    public LocalDateTime getDonationDate() {
        return donationDate;
    }

    public void setDonorName(DonorName name) {
        donorName = name;
    }

    public void setDonationType(DonationType type) {
        donationType = type;
    }

    public void setDonationAmount(Double amount) {
        donationAmount = amount;
    }

    public void setDonationDate(LocalDateTime date) {
        donationDate = date;
    }
    // #endregion

    public Donation() {
        this.donorName = new DonorName();
        this.donorName.firstName = "";
        this.donorName.lastName = "";
        this.donorName.mI = "";
    }

    // #region externally exposed methods
    private final Logger logger = LogManager.getLogger(this);

    // A feature that allows the shelter staff to record details of the donations,
    // such as the donor's name, type of donation (money, food, clothing, etc.),
    // quantity or amount donated, and the date of the donation.
    public void register(DonorName name, DonationType type, Double amountDonated, LocalDateTime date,
            boolean logInputs) {
        // write data to database
        String sqlQuery = "INSERT INTO donations_manager.donations(DonorName,DonationType,DollarValue,Date,Remaining) values(\""
                + name + "\",\"" + type + "\"," + amountDonated + ",\"" + date + "\"," + amountDonated + ")";
        if (logInputs) {
            logger.log(Level.INFO, "Register(): Running query: " + sqlQuery);
        }
        boolean success = DataAccess.sqlConnectAndExecute(sqlQuery);

        if (logInputs) {
            // Just to note: this seems to consistently return 'false', yet succeed in
            // inserting data into the DB
            logger.log(Level.INFO, "Register query success? " + success);
        }
    }

    // A feature to log when and how much of the donations are distributed,
    // capturing
    // the type of donation, quantity or amount distributed, and the date of
    // distribution.
    public void distribute(DonationType type, Double amountDonated, LocalDateTime date, boolean logCandidates) {
        // make distribution adjustments in the database...

        // determine there is enough of given donationtype to distribute
        List<Map<String, Object>> availableDonations = determineSufficientDonationsExist(type, amountDonated, date);

        if (logCandidates) {
            logger.log(Level.INFO, "Determine if sufficient funds exist - ");
            logger.log(Level.INFO, "\tpotential donations to select from:");
        }

        List<KeyValuePair> amounts = new ArrayList<KeyValuePair>();
        Double amount = 0.00;
        Integer id = 0;

        // log out potentially available donations for visibility/debugging

        for (Map<String, Object> map : availableDonations) {
            if (logCandidates) {
                logger.log(Level.INFO, "Candidate:");
            }
            for (String str : map.keySet()) {

                if (str.contains("Remaining") && (Double.valueOf(map.get(str).toString())) >= amountDonated) {
                    amount = Double.valueOf(map.get(str).toString());
                }
                if (str.contains("ID")) {
                    id = (Integer) map.get(str);
                }
                if (logCandidates) {
                    logger.log(Level.INFO, "\t" + str + ": " + map.get(str));
                }
            }
            KeyValuePair kvp = new KeyValuePair((id.toString()), amount.toString());
            amounts.add(kvp);
        }

        KeyValuePair selectedDonor = null; // winner, winner, chicken dinner...

        for (KeyValuePair donorAndAmount : amounts) {
            if (Double.valueOf(donorAndAmount.getValue()) >= amountDonated) {
                selectedDonor = new KeyValuePair(donorAndAmount.getKey(), donorAndAmount.getValue());
                logger.log(Level.INFO, "Determine if sufficient funds exist... winner: " + selectedDonor);
                break;
            }
        }

        // for now - set properly once determeined above
        boolean sufficientFundsOfTypeExist = false;
        if (selectedDonor != null) {
            sufficientFundsOfTypeExist = true;
        }

        // if so, distribute those funds of the given type and verify those
        // distributions of the given type
        // were recorded in the Distributions table
        // a simple improvement in design would be to have the donations current state
        // (Donations - Distributions, so far)
        // were recorded in a 3rd CurrentBalance (by DonationType) table - for now are
        // doing this manually with these queries
        KeyValuePair kvp = null;
        if (sufficientFundsOfTypeExist) {
            kvp = registerDistribution(selectedDonor, type, amountDonated, date);

            String donorName = kvp.getKey();
            Double amountRemaining = (Double.valueOf(kvp.getValue()));

            // clean up value
            int temp = (int) (amountRemaining * 100.0);
            double amtRemaining = ((double) temp) / 100.0;

            boolean updatedSuccessfully = verifyAmountsUpdatedInDonationsTable(donorName, amtRemaining);
            if (updatedSuccessfully) {
                logger.log(Level.INFO, "updatedSuccessfully: " + updatedSuccessfully);
            }
        } else {
            logger.log(Level.ERROR,
                    "Unable to find any donations of sufficient value to satisfy expected donation distribution");
        }

    }
    // #endregion

    // #region private data handling methods
    private List<Map<String, Object>> determineSufficientDonationsExist(DonationType type, Double amountDonated,
            LocalDateTime date) {
        String checkForResourcesQuery = "SELECT * FROM donations_manager.donations WHERE DonationType=\"" + type
                + "\" AND Remaining >=" + amountDonated;

        logger.log(Level.INFO, "Distribute(): Query: " + checkForResourcesQuery);
        List<Map<String, Object>> results = DataAccess.sqlConnectAndRunQuery(checkForResourcesQuery);

        return results;
    }

    private KeyValuePair registerDistribution(KeyValuePair donorAndAmount, DonationType type, Double amountDonated,
            LocalDateTime date) {

        Double newRemainingAmount = Double.valueOf(donorAndAmount.getValue()) - amountDonated;

        // clean up value
        int temp = (int) (newRemainingAmount * 100.0);
        double amtRemaining = ((double) temp) / 100.0;

        String getNameFromIDQuery = "SELECT DonorName FROM donations_manager.donations WHERE ID = "
                + donorAndAmount.getKey();

        KeyValuePair donorAndAmountRemaining = null;

        List<String> names = DataAccess.sqlConnectAndRunSimpleQuery(getNameFromIDQuery);
        for (String name : names) {
            donorAndAmountRemaining = new KeyValuePair(name, ((Double) amtRemaining).toString());
            break;
        }
        String donorName = donorAndAmountRemaining.getKey();

        String distributeQuery = "INSERT INTO donations_manager.distributions(DonationType,DollarValue,Date,Notes) values(\""
                + type + "\"," + amountDonated + ",\"" + date + "\",\"" + donorName + "\")";

        // record such distribution was excersized...
        logger.log(Level.INFO, "Distribute(): Query: " + distributeQuery);
        DataAccess.sqlConnectAndExecute(distributeQuery);

        String reduceRemainingQuery = "UPDATE donations_manager.donations SET Remaining = " + amtRemaining
                + " WHERE ID = " + donorAndAmount.getKey();

        logger.log(Level.INFO, "Distribute(): Query: " + reduceRemainingQuery);
        DataAccess.sqlConnectAndExecute(reduceRemainingQuery);

        return donorAndAmountRemaining;
    }

    private boolean verifyAmountsUpdatedInDonationsTable(String donorName, Double newAmountRemaining) {
        String verifyQuery = "SELECT * FROM donations_manager.donations WHERE DonorName=\"" + donorName
                + "\" AND Remaining =" + newAmountRemaining;

        logger.log(Level.INFO, "Distribute(): Query: " + verifyQuery);
        boolean result = DataAccess.sqlConnectAndExecute(verifyQuery);
        logger.log(Level.INFO, "Result of query execution: " + result);
        return result;
    }
    // #endregion

    // #region Reports generation

    // ToDo: use sql query methods in section above to generate more general reports

    // Your solution should have the capacity to generate two types of reports:
    // (1) An inventory report displaying the current status of donations, grouped
    // by type.
    // (2) A donator report, summarizing the total contributions received from each
    // donor.
    public void generateReports(LocalDateTime date, boolean logCandidates) {
        logger.log(Level.INFO, "\nGenerateReports():");

        List<List<Map<String, Object>>> inventory = new ArrayList<>();

        for (DonationType type : DonationType.values()) {
            inventory.add(generateInventoryReport(type, logCandidates));
        }

        for (List<Map<String, Object>> donationTypesList : inventory) {
            for (Map<String, Object> map : donationTypesList) {
                if (logCandidates) {
                    logger.log(Level.INFO, "Donation:");
                    for (String str : map.keySet()) {
                        logger.log(Level.INFO, "\t" + str + ": " + map.get(str));
                    }
                }
            }
        }

        DonorName name = new DonorName();
        name.firstName = "John";
        name.mI = "S";
        name.lastName = "Dewey";

        Map<String, List<KeyValuePair>> listOfDonationsByDonor = new HashMap<>();
        Map<String, List<Integer>> donorList = getDonorList();
        listOfDonationsByDonor = generateDonatorReport(donorList);

        for (String donorName : listOfDonationsByDonor.keySet()) {
            logger.log(Level.INFO,
                    "\n\nDonor: " + donorName + ", \nDonations: " + listOfDonationsByDonor.get(donorName));
        }
    }

    public List<Map<String, Object>> generateInventoryReport(DonationType type, boolean logCandidates) {
        // get all current donations status by donation type from DB - use date to

        // meanwhile simply print out data of the distribution
        logger.log(Level.INFO,
                "\n\nCreating a report for donation inventory by type for type: " + type);

        // output values by donation type
        // System.out.println("\nInventory report by donation type:");
        List<Map<String, Object>> availableDonations = new ArrayList<>();

        switch (type) {
            case MONEY:
                availableDonations = captureInventoryReportData(DonationType.MONEY);
                break;
            case FOOD:
                availableDonations = captureInventoryReportData(DonationType.FOOD);
                break;
            case CLOTHING:
                availableDonations = captureInventoryReportData(DonationType.CLOTHING);
                break;
            case MISC:
                availableDonations = captureInventoryReportData(DonationType.MISC);
                break;
        }

        return availableDonations;
    }

    private List<Map<String, Object>> captureInventoryReportData(DonationType type) {
        String getDonationsForGivenType = "SELECT * FROM donations_manager.donations WHERE DonationType=\"" + type
                + "\"";

        logger.log(Level.INFO, "Distribute(): Query: " + getDonationsForGivenType);
        List<Map<String, Object>> results = DataAccess.sqlConnectAndRunQuery(getDonationsForGivenType);

        return results;
    }

    public Map<String, List<Integer>> getDonorList() {

        String getAllDonations = "SELECT * FROM donations_manager.donations";

        logger.log(Level.INFO, "Distribute(): Query: " + getAllDonations);
        List<Map<String, Object>> results = DataAccess.sqlConnectAndRunQuery(getAllDonations);
        logger.log(Level.INFO, "results: " + results.size());
        Map<String, List<Integer>> donorIdList = new HashMap<>();

        Integer id = 0;
        String donor = "";

        for (Map<String, Object> map : results) {
            for (String str : map.keySet()) {
                if (str.contains("ID")) {
                    id = (Integer) map.get(str);
                }
                if (str.contains("DonorName")) {
                    donor = (String) map.get(str);
                }
                if (donorIdList.containsKey(donor)) {
                    donorIdList.get(donor).add(id);
                } else {
                    List<Integer> tmpList = new ArrayList<>();
                    tmpList.add(id);
                    if (donor != "") {
                        donorIdList.put(donor, tmpList);
                    }
                }
            }
        }
        return donorIdList;
    }

    public Map<String, List<KeyValuePair>> generateDonatorReport(Map<String, List<Integer>> donorList) {

        Map<String, List<KeyValuePair>> donorDonations = new HashMap<>();
        logger.log(Level.INFO, "Report of All Donations by Donor\n");

        for (String name : donorList.keySet()) {

            String getDonationsByDonor = "SELECT * FROM donations_manager.donations WHERE DonorName=\"" + name
                    + "\"";

            logger.log(Level.INFO, "\n\nGathering data for report for all donations by Donor: " + name);
            logger.log(Level.INFO, "Distribute(): Query: " + getDonationsByDonor);
            List<Map<String, Object>> results = DataAccess.sqlConnectAndRunQuery(getDonationsByDonor);

            Integer id = 0;
            String donor = "";
            String donationType = "";
            Double value = 0.00;
            KeyValuePair giftTypeAmountPairs = null;
            List<KeyValuePair> gifts = new ArrayList<>();

            for (Map<String, Object> map : results) {
                for (String str : map.keySet()) {
                    if (str.contains("ID")) {
                        id = (Integer) map.get(str);
                    }
                    if (str.contains("DonorName")) {
                        donor = (String) map.get(str);
                    }
                    if (str.contains("DonationType")) {
                        donationType = (String) map.get(str);
                    }
                    if (str.contains("DollarValue")) {
                        value = Double.valueOf(map.get(str).toString());
                    }
                }
                if (donor != null && donationType != "" && value > 0.00) {
                    giftTypeAmountPairs = new KeyValuePair(donationType, ((Double) value).toString());
                    if (donorDonations.keySet().contains(donor)) {
                        donorDonations.get(donor).add(giftTypeAmountPairs);
                    } else {
                        gifts.add(giftTypeAmountPairs);
                        if (donor != "") {
                            donorDonations.put(donor, gifts);
                        }
                    }
                }
            }
        }
        return donorDonations;
    }

    public void outputListOfDonorsAndAmounts(Map<DonorName, Double> donorAmountList) {
        for (DonorName donor : donorAmountList.keySet()) {
            System.out.print("\nDonor: " + donor.toString() + ", \t$" + donorAmountList.get(donor));
        }
    }

    // #endregion
}
