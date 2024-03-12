import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Requirements:

// This shelter is in need of a user-friendly tool to accurately record and track the inflow and outflow of donations, and to generate insightful reports about their donation management.

// Here are the core functionalities your solution should address:

// DONE - Donation Registration: A feature that allows the shelter staff to record details of the donations, such as the donor's name, type of donation (money, food, clothing, etc.), quantity or amount donated, and the date of the donation.
// DONE - Donation Distribution: A feature to log when and how much of the donations are distributed, capturing the type of donation, quantity or amount distributed, and the date of distribution.
// PARTIALLY DONE - Donation Reports: Your solution should have the capacity to generate two types of reports: (1) An inventory report displaying the current status of donations, grouped by type. (2) A donator report, summarizing the total contributions received from each donor.

// TO DO:
// 
// Move instructions to README file for GitHub repository
// (do so before next checkin; but get some other ToDo's done first)
// 
// DONE - 0. Add JSON file data intake
//   (FUTURE: could eventually be REST API from a UI for query support)
// 
// DONE 1. Add business logic to support not distributing funds of a given type unless such quantity currently exists
//   (i.e. use query to verify present in Donations table first - already have query - just need to expand functionality)
//   DONE //ToDo: determine if amount expected of given DonationType is present in remaining/existing donations (not already distributed)
//   DONE //ToDo: add logic to determine if distribution data was successfully inserted or not
//   
// 2. Add unit testing
// 3. Add more logging
// 
// FUTURE: Augment reporting functionality with a UI to render the output if customer was wanting such.
// 

public class App {

    public static void main(String[] args) {

        // initial dev work
        // sqlTestConnection();

        LocalDateTime nowForTestUse = LocalDateTime.now();
        System.out.println("\nDateTime.now: " + nowForTestUse);

        // set the following to control amount of distribution
        boolean distributeFunds = true;
        LocalDateTime date = nowForTestUse;
        Donation.DonationType type = Donation.DonationType.CLOTHING;
        Double amount = 244.20;

        // #region input from JSON file

        // Values read from JSON input file:
        // {"date":"03\/01\/2024","name":"John S.
        // Dewey","donationType":"money","value":100}
        // {"date":"2-25-2024","name":"Michael
        // Jones","donationType":"Money","value":4000}
        // {"date":"1\/1\/2024","name":"Edward S
        // Sweeney","donationType":"Food","value":10000}
        // {"date":"02\/01\/2024","name":"Michelle
        // Sonnenberg","donationType":"clothing","value":750}
        // {"date":"01-01-2024","name":"Gertrude
        // Dux","donationType":"CLOTHING","value":3500}
        // {"date":"03\/14\/2024","name":"Jeremiah L.
        // Books","donationType":"food","value":2500}
        // {"date":"3\/7\/2024","name":"Ed Foote","donationType":"Clothing","value":999}
        // {"date":"1\/15\/2024","name":"Wylie
        // Coyote","donationType":"MONEY","value":1600}
        // {"date":"1-15-2024","name":"Jennifer
        // Dowling","donationType":"FOOD","value":3000}
        // {"date":"03\/02\/2024","name":"Scape E.
        // Goat","donationType":"money","value":150}
        // {"date":"3\/1\/2024","name":"Funny
        // Duckling","donationType":"clothing","value":1090}
        // {"date":"03\/01\/2024","name":"Earle S.
        // Grates","donationType":"Money","value":10000}
        // {"date":"02\/29\/2024","name":"Jerry
        // Smith","donationType":"Food","value":1843.5}
        // {"date":"01\/01\/2024","name":"Willie J.
        // Smoots","donationType":"Misc","value":649.55}
        // {"date":"2-28-2024","name":"Mike
        // Dewey","donationType":"miscellaneous","value":1000}

        // #endregion

        Donation donationInfo = new Donation();

        // read in donors.json file and process by calling donation.register for each
        JSONArray donorsList = new JSONArray();
        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonObj = (JSONObject) parser
                    .parse(new FileReader("C:\\Git\\Euler\\DAS\\src\\main\\resources\\donors.json"));
            donorsList = (JSONArray) jsonObj.get("Donors");

            for (Object obj : donorsList) {

                JSONObject donate = (JSONObject) obj;

                String donorName = (String) donate.get("name");
                Donation.DonorName jsonName = new Donation.DonorName();
                jsonName = jsonName.SetName(donorName);

                Donation.DonationType jsonType = Donation.DonationType.MISC;
                String donationType = ((String) donate.get("donationType")).toLowerCase();
                switch (donationType) {
                    case "money":
                        jsonType = Donation.DonationType.MONEY;
                        break;
                    case "clothing":
                        jsonType = Donation.DonationType.CLOTHING;
                        break;
                    case "food":
                        jsonType = Donation.DonationType.FOOD;
                        break;
                    case "misc":
                        jsonType = Donation.DonationType.MISC;
                        break;
                    default:
                        jsonType = Donation.DonationType.MISC;
                        break;
                }

                Long donationValue = (Long) donate.get("value");
                Double jsonValue = donationValue.doubleValue();

                // fix this below:
                // DEBUGGING: Exception hit... Text '03/01/2024' could not be parsed at index 2,
                // [Ljava.lang.StackTraceElement;@c7ba306

                String dateStr = (String) donate.get("date");
                dateStr = dateStr.replaceAll("-", "").replaceAll("/", "");
                if (dateStr.length() < 7) {
                    dateStr = "0" + dateStr.substring(0, 1) + "0" + dateStr.substring(1, dateStr.length());
                } else if (dateStr.length() < 8) {
                    dateStr = "0" + dateStr.substring(0, dateStr.length());
                }
                System.out.println(dateStr);
                LocalDate jsonDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MMddyyyy"));
                LocalTime time = LocalTime.of(0, 0, 0, 0);
                LocalDateTime dateTime = LocalDateTime.of(jsonDate, time);
                donationInfo.setDonorName(jsonName);
                donationInfo.setDonationType(jsonType);
                donationInfo.setDonationAmount(jsonValue);
                donationInfo.setDonationDate(dateTime);
                System.out.println("DEBUGGING: " + donationInfo.getDonorName() + ":" + donationInfo.getDonationType()
                        + ":" + donationInfo.getDonationAmount() + ":" + donationInfo.getDonationDate());

                donationInfo.register(donationInfo.getDonorName(), donationInfo.getDonationType(),
                        donationInfo.getDonationAmount(), donationInfo.getDonationDate());
            }

        } catch (Exception e) {
            System.out.println("DEBUGGING: Exception hit... " + e.getLocalizedMessage() + ", " + e.getStackTrace());
        }

        // Uncomment next line to trigger error in attempt to distribute too large an
        // amount
        // amount = 50000.00;

        // FYI: what it looks like in the log
        // 2024-03-12 12:18:00.756 [main] ERROR Donation - Unable to find any donations
        // of sufficient value to satisfy expected donation distribution
        if (distributeFunds) {
            donationInfo.distribute(type, amount, date);
        }

        // In a production app, I would back this functionality with exporting the data
        // in tabular form to some rendered UI page (ReactJS)
        // I'm still learning React currently, so am not ready to do that yet...
        // however, this method does attempt to output the data in an organized form
        // that could be employed by users in the interim
        donationInfo.generateReports(date);
    }

    // #region testing MySQL connection
    /*
     * //sqlTestConnection()
     * private static void sqlTestConnection(){
     * String sqlQuery = "SELECT * FROM donations_manager.donations";
     * String connectionUrl = "jdbc:mysql://localhost:3306/donations_manager";
     * 
     * try(Connection connection = DriverManager.getConnection(connectionUrl,
     * "starrp", "ABCD****1234");
     * PreparedStatement ps = connection.prepareStatement(sqlQuery);
     * ResultSet rs = ps.executeQuery()) {
     * while (rs.next()){
     * int id = rs.getInt("ID");
     * String donationType = rs.getString("DonationType");
     * Double dollarValue = rs.getDouble("DollarValue");
     * String date = rs.getString("Date");
     * 
     * System.out.println("DB data: " + id + "; " + donationType + "; " +
     * dollarValue + "; " + date);
     * }
     * 
     * }
     * catch(SQLException e){
     * //for now, log and swallow exception...
     * //or simply print out
     * System.out.println("\nSQLException: " + e.getErrorCode());
     * }
     * }
     */
    // #endregion
}