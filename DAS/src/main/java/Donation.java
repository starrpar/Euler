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

        logger.log(Level.INFO, "Determine if sufficient funds exist - ");
        if (logCandidates) {
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
            logger.log(Level.INFO, donorAndAmount.getKey() + ": " + donorAndAmount.getValue());
            if (Double.valueOf(donorAndAmount.getValue()) >= amountDonated) {
                selectedDonor = new KeyValuePair(donorAndAmount.getKey(), donorAndAmount.getValue());
                // logger.log(Level.INFO, "Winner: " + donorAndAmount.getKey() + ": " +
                // donorAndAmount.getValue());
                logger.log(Level.INFO, "Winner: " + selectedDonor);
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

            boolean updatedSuccessfully = verifyAmountsUpdatedInDonationsTable(kvp.getKey(),
                    (Double.valueOf(kvp.getValue())));
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

        logger.log(Level.INFO, "Distribute(): Running query: " + checkForResourcesQuery);
        List<Map<String, Object>> results = DataAccess.sqlConnectAndRunQuery(checkForResourcesQuery);

        return results;
    }

    private KeyValuePair registerDistribution(KeyValuePair donorAndAmount, DonationType type, Double amountDonated,
            LocalDateTime date) {
        String distributeQuery = "INSERT INTO donations_manager.distributions(DonationType,DollarValue,Date) values(\""
                + type + "\"," + amountDonated + ",\"" + date + "\")";

        Double newRemainingAmount = Double.valueOf(donorAndAmount.getValue()) - amountDonated;
        String reduceRemainingQuery = "UPDATE donations_manager.donations SET Remaining = " + newRemainingAmount
                + " WHERE ID = " + donorAndAmount.getKey();

        String getNameFromIDQuery = "SELECT DonorName FROM donations_manager.donations WHERE ID = "
                + donorAndAmount.getKey();

        // record such distribution was excersized...
        logger.log(Level.INFO, "Distribute(): Running query: " + distributeQuery);
        DataAccess.sqlConnectAndExecute(distributeQuery);

        logger.log(Level.INFO, "Distribute(): Running query: " + reduceRemainingQuery);
        DataAccess.sqlConnectAndExecute(reduceRemainingQuery);

        KeyValuePair donorAndAmountRemaining = null;

        List<String> names = DataAccess.sqlConnectAndRunSimpleQuery(getNameFromIDQuery);
        for (String name : names) {
            logger.log(Level.INFO, "name: " + name);
            donorAndAmountRemaining = new KeyValuePair(name, newRemainingAmount.toString());
            break;
        }

        return donorAndAmountRemaining;
    }

    private boolean verifyAmountsUpdatedInDonationsTable(String donorName, Double newAmountRemaining) {
        String verifyQuery = "SELECT * FROM donations_manager.donations WHERE DonorName=\"" + donorName
                + "\" AND Remaining =" + newAmountRemaining;

        logger.log(Level.INFO, "Distribute(): Running query: " + verifyQuery);
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
    public void generateReports(LocalDateTime date) {
        System.out.println();
        System.out.println("\nGenerateReports():");
        generateInventoryReport(date);
        generateDonatorReport();
    }

    public void addNotes(DonorName name) {
        // write notes to database; associate with donor by name (or DBID)

        // likely a desired functionality...
        System.out.println("notes");
    }

    public void trivialMethod() {
        // do nothing
        System.out.println(" ");
    }

    public void generateInventoryReport(LocalDateTime date) {
        // get all current donations status by donation type from DB - use date to
        // specify appropriate query
        // add DB connection
        // DB =
        // provide string as temp workaround for logging
        String DB = "MySQL_database";
        logger.log(Level.INFO, "GenerateInventoryReport(): Connecting to DB: " + DB);

        // add query
        String query = "";
        logger.log(Level.INFO, "GenerateInventoryReport(): query: " + query);

        // meanwhile simply print out data of the distribution
        logger.log(Level.INFO,
                "GenerateInventoryReport(): Creating a report for donation inventory by type for \"as of\" date of: "
                        + date);

        // output values by donation type
        System.out.println("\nInventory report by donation type:");
        for (DonationType type : DonationType.values()) {
            switch (type) {
                case MONEY:
                    generateTypeMONEYReport();
                    break;
                case FOOD:
                    generateTypeFOODReport();
                    break;
                case CLOTHING:
                    generateTypeCLOTHINGReport();
                    break;
                case MISC:
                    generateTypeMISCReport();
                    break;
            }
        }
    }

    public void generateTypeMONEYReport() {

        // use query against DB for all current funds of donation type MONEY
        System.out.println("MONEY donations report... (TBD)");
    }

    public void generateTypeFOODReport() {

        // use query against DB for all current funds of donation type FOOD
        System.out.println("FOOD donations report... (TBD)");
    }

    public void generateTypeCLOTHINGReport() {

        // use query against DB for all current funds of donation type CLOTHING
        System.out.println("CLOTHING donations report... (TBD)");
    }

    public void generateTypeMISCReport() {

        // use query against DB for all current funds of donation type MISC
        System.out.println("MISC donations report... (TBD)");
    }

    public void generateDonatorReport() {

        Map<DonorName, Double> donorAmountList = new HashMap<>();

        // test scenario
        DonorName name1 = new DonorName();
        DonorName name2 = new DonorName();
        DonorName name3 = new DonorName();
        DonorName name4 = new DonorName();
        DonorName name5 = new DonorName();

        name1.firstName = "Joe";
        name1.lastName = "Miller";
        name2.firstName = "Michael";
        name2.lastName = "Adams";
        name2.mI = "X";
        name3.firstName = "Susan";
        name3.lastName = "Smith";
        name4.firstName = "Able";
        name4.lastName = "Body";
        name4.mI = "S";
        name5.firstName = "Richie";
        name5.lastName = "Rich";

        donorAmountList.put(name1, 4450.00);
        donorAmountList.put(name2, 100000.00);
        donorAmountList.put(name3, 357.00);
        donorAmountList.put(name4, 22.50);
        donorAmountList.put(name5, 18500.00);

        // access data from DB using a query based to get list of all donors and all
        // amounts donated by each donor
        // add DB connection
        // DB =
        // provide string as temp workaround for logging
        String DB = "MySQL_database";
        logger.log(Level.INFO, "GenerateDonatorReport(): Connecting to DB: " + DB);

        // add query
        String query = "";
        logger.log(Level.INFO, "GenerateDonatorReport(): query: " + query);

        // meanwhile simply print out data of the distribution
        logger.log(Level.INFO, "GenerateDonatorReport(): Creating a report for donations summary by donor");

        // then summarize all dollar amounts for all donations per donor - place donor's
        // name and total amount in dictionary
        System.out.print("\nDonations report by donor:");

        outputListOfDonorsAndAmounts(donorAmountList);
    }

    public void outputListOfDonorsAndAmounts(Map<DonorName, Double> donorAmountList) {
        for (DonorName donor : donorAmountList.keySet()) {
            System.out.print("\nDonor: " + donor.toString() + ", \t$" + donorAmountList.get(donor));
        }
    }

    // #endregion
}
