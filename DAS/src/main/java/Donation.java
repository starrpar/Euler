import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Donation {

    public class DonorName{
        String lastName;
        String firstName;
        String middleInitial = "NMN";

        @Override
        public String toString(){
            return firstName + " " + middleInitial + " " + lastName;
        }
    }

    //this is just one design choice, but it works if the field is static enough
    public enum DonationType{
        MONEY,
        FOOD,
        CLOTHING,
        MISC
        //add fields as necessary to expand supported donation type set
    }

    public DonorName Donor;

    public Donation(){
        this.Donor = new DonorName();
        this.Donor.firstName = "";
        this.Donor.lastName = "";
        this.Donor.middleInitial = "";
    }

    private final Logger logger = LogManager.getLogger(this);

    // A feature that allows the shelter staff to record details of the donations,
    // such as the donor's name, type of donation (money, food, clothing, etc.),
    // quantity or amount donated, and the date of the donation.
    public void register(DonorName name, DonationType type, Double amountDonated, LocalDateTime date) {
        //write data to database
        String sqlQuery = "INSERT INTO donations_manager.donations(DonorName,DonationType,DollarValue,Date) values(\""+name+"\",\""+type+"\","+amountDonated+",\""+date+"\")";
        logger.log(Level.INFO, "Register(): Running query: " + sqlQuery);

        sqlConnectAndExecute(sqlQuery);

        // logger.log(Level.INFO, "Distribute(): number of rows processed: " + numRows);
    }

    // A feature to log when and how much of the donations are distributed, capturing
    // the type of donation, quantity or amount distributed, and the date of distribution.
    public void distribute(DonationType type, Double amountDonated, LocalDateTime date) {
        //make adjustments in the database...

        //first off, determine there is enough of given donationtype to distribute
        String sqlQuery1 = "SELECT * FROM donations_manager.donations WHERE DonationType=\""+type+"\" AND DollarValue="+amountDonated;
        String sqlQuery2 = "INSERT INTO donations_manager.distributions(DonationType,DollarValue,Date) values(\""+type+"\","+amountDonated+",\""+date+"\")";
        String sqlQuery3 = "SELECT * FROM donations_manager.distributions WHERE DonationType=\""+type+"\" AND DollarValue="+amountDonated+" AND Date=\""+date+"\"";


        logger.log(Level.INFO, "Distribute(): Running query: " + sqlQuery1);
        List<Map<String, Object>> results = sqlConnectAndRunQuery(sqlQuery1);

        for (Map<String,Object> map : results) {
            for(String str : map.keySet()){
                System.out.println("\n" + str + ": " + map.get(str));
            }
        }

        //next, record such distribution was excersized
        logger.log(Level.INFO, "Distribute(): Running query: " + sqlQuery2);
        sqlConnectAndExecute(sqlQuery2);

        //then simply verify the data entered in the distributions table correctly
        logger.log(Level.INFO, "Distribute(): Running query: " + sqlQuery3);
        results = sqlConnectAndRunQuery(sqlQuery3);

        for (Map<String,Object> map : results) {
            for(String str : map.keySet()){
                System.out.println("\n" + str + ": " + map.get(str));
            }
        }
    }

    private static List<Map<String, Object>> sqlConnectAndRunQuery(String query) {
        String connectionUrl = "jdbc:mysql://localhost:3306/donations_manager";

        List<Map<String, Object>> resultList = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(connectionUrl, "starrp", "ABCD****1234");
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        }
        catch(SQLException e){
            //for now just print out SQL Exception contents, but in real app, do appropriate action...
            System.out.println("SQLException experienced: " + e.getErrorCode() + "; " + e.getLocalizedMessage());
        }

        return resultList;
    }

    private static void sqlConnectAndExecute(String query) {
        String connectionUrl = "jdbc:mysql://localhost:3306/donations_manager";

        try{
            Connection connection = DriverManager.getConnection(connectionUrl, "starrp", "ABCD****1234");
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
        }
        catch(SQLException e){
            //for now just print out SQL Exception contents, but in real app, do appropriate action...
            System.out.println("SQLException experienced: " + e.getErrorCode() + "; " + e.getLocalizedMessage());
        }
    }


    // Your solution should have the capacity to generate two types of reports:
    // (1) An inventory report displaying the current status of donations, grouped by type.
    // (2) A donator report, summarizing the total contributions received from each donor.
    public void generateReports(LocalDateTime date) {
        System.out.println();
        System.out.println("\nGenerateReports():");
        generateInventoryReport(date);
        generateDonatorReport();
    }

    public void addNotes(DonorName name) {
        //write notes to database; associate with donor by name (or DBID)

        //likely a desired functionality...
        System.out.println("notes");
    }

    public void trivialMethod(){
        //do nothing
        System.out.println(" ");
    }

    public void generateInventoryReport(LocalDateTime date){
        //get all current donations status by donation type from DB - use date to specify appropriate query
        //add DB connection
        //DB =
        //provide string as temp workaround for logging
        String DB = "MySQL_database";
        logger.log(Level.INFO, "GenerateInventoryReport(): Connecting to DB: " + DB);

        //add query
        String query = "";
        logger.log(Level.INFO, "GenerateInventoryReport(): query: " + query);
    
        //meanwhile simply print out data of the distribution
        logger.log(Level.INFO, "GenerateInventoryReport(): Creating a report for donation inventory by type for \"as of\" date of: " + date);

        //output values by donation type
        System.out.println("\nInventory report by donation type:");
        for(DonationType type : DonationType.values()){
            switch(type) {
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

    public void generateTypeMONEYReport(){

        //use query against DB for all current funds of donation type MONEY
        System.out.println("MONEY donations report... (TBD)");
    }

    public void generateTypeFOODReport(){

        //use query against DB for all current funds of donation type FOOD
        System.out.println("FOOD donations report... (TBD)");
    }

    public void generateTypeCLOTHINGReport(){

        //use query against DB for all current funds of donation type CLOTHING
        System.out.println("CLOTHING donations report... (TBD)");
    }

    public void generateTypeMISCReport(){

        //use query against DB for all current funds of donation type MISC
        System.out.println("MISC donations report... (TBD)");
    }

    public void generateDonatorReport(){

        Map<DonorName, Double> donorAmountList = new HashMap<>();

        //test scenario
        DonorName name1 = new DonorName();
        DonorName name2 = new DonorName();
        DonorName name3 = new DonorName();
        DonorName name4 = new DonorName();
        DonorName name5 = new DonorName();

        name1.firstName = "Joe";
        name1.lastName = "Miller";
        name2.firstName = "Michael";
        name2.lastName = "Adams";
        name2.middleInitial = "X";
        name3.firstName = "Susan";
        name3.lastName = "Smith";
        name4.firstName = "Able";
        name4.lastName = "Body";
        name4.middleInitial = "S";
        name5.firstName = "Richie";
        name5.lastName = "Rich";

        donorAmountList.put(name1, 4450.00);
        donorAmountList.put(name2, 100000.00);
        donorAmountList.put(name3, 357.00);
        donorAmountList.put(name4, 22.50);
        donorAmountList.put(name5, 18500.00);

        //access data from DB using a query based to get list of all donors and all amounts donated by each donor
        //add DB connection
        //DB =
        //provide string as temp workaround for logging
        String DB = "MySQL_database";
        logger.log(Level.INFO, "GenerateDonatorReport(): Connecting to DB: " + DB);

        //add query
        String query = "";
        logger.log(Level.INFO, "GenerateDonatorReport(): query: " + query);
    
        //meanwhile simply print out data of the distribution
        logger.log(Level.INFO, "GenerateDonatorReport(): Creating a report for donations summary by donor");


        //then summarize all dollar amounts for all donations per donor - place donor's name and total amount in dictionary
        System.out.print("\nDonations report by donor:");

        outputListOfDonorsAndAmounts(donorAmountList);
    }

    public void outputListOfDonorsAndAmounts(Map<DonorName, Double> donorAmountList){
        for(DonorName donor : donorAmountList.keySet()){
            System.out.print("\nDonor: " + donor.toString() + ", \t$" + donorAmountList.get(donor));
        }
    }
}
