
import java.time.LocalDateTime;


// Requirements:

// This shelter is in need of a user-friendly tool to accurately record and track the inflow and outflow of donations, and to generate insightful reports about their donation management.

// Here are the core functionalities your solution should address:

// Donation Registration: A feature that allows the shelter staff to record details of the donations, such as the donor's name, type of donation (money, food, clothing, etc.), quantity or amount donated, and the date of the donation.
// Donation Distribution: A feature to log when and how much of the donations are distributed, capturing the type of donation, quantity or amount distributed, and the date of distribution.
// Donation Reports: Your solution should have the capacity to generate two types of reports: (1) An inventory report displaying the current status of donations, grouped by type. (2) A donator report, summarizing the total contributions received from each donor.


// TO DO:

// As mentioned in comments before the "reports" function call, I would augment this with a UI to render the report output if that was something the users/customers wanted as a requirement.
// 
// Also, I haven't added any "data intake" methodology yet (file or REST call to an exposed API that could support individual changes or batch/file submission).
// 
// I would also modify the business logic regarding verifying funds were still present / had not already been distributed before issuing a distribution change to the DB.
// I started to put logic in place to address this, but it is in no way sufficient yet.
// 
// There would also be more logging (just added some as starter/examples so far) and commenting of the code.  Just didn't want to take forever in getting this returned to you.
// If any of these "deficiencies" are limiting in my consideration, please advise and I can perform the cleanup and finish the above-mentioned aspects.
// Just wasn't sure "how much was too much" for such an exercise to demonstrate my basic abililties.  Thank you for reviewing/considering.
// 






public class App {

    public static void main(String[] args) {

        // sqlTestConnection();

        Donation donation = new Donation();
        // System.out.println(donation.Donor.toString());
        Test test = new Test();
        LocalDateTime nowForTestUse = LocalDateTime.now();
        Double amountForTestUse = 500.00;

        System.out.println("DateTime.now: " + nowForTestUse);

        //test data
        Donation.DonorName name = test.createDonor("Sam", "Smith", "L");
        LocalDateTime date = test.getDateOfDonation(name, nowForTestUse);
        Donation.DonationType type = test.getDonationType(name, Donation.DonationType.CLOTHING);
        Double amount = test.getAmountOfDonation(name, amountForTestUse);

        donation.register(name, type, amount, date);

        //test data
        date = test.getDateOfDistribution(nowForTestUse);
        type = test.getTypeOfDistribution(Donation.DonationType.CLOTHING);
        amount = test.getAmountOfDistribution(amountForTestUse);

        donation.distribute(type, amount, date);

        //In a production app, I would back this functionality with exporting the data in tabular form to some rendered UI page (ReactJS)
        //I'm still learning React currently, so am not ready to do that yet...
        //however, this method does attempt to output the data in an organized form that could be employed by users in the interim
        donation.generateReports(date);
    }

    /* //sqlTestConnection()
    private static void sqlTestConnection(){
        String sqlQuery = "SELECT * FROM donations_manager.donations";
        String connectionUrl = "jdbc:mysql://localhost:3306/donations_manager";
    
        try(Connection connection = DriverManager.getConnection(connectionUrl, "starrp", "ABCD****1234");
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                int id = rs.getInt("ID");
                String donationType = rs.getString("DonationType");
                Double dollarValue = rs.getDouble("DollarValue");
                String date = rs.getString("Date");
                
                System.out.println("DB data: " + id + "; " + donationType + "; " + dollarValue + "; " + date);
            }
            
        }
        catch(SQLException e){
            //for now, log and swallow exception...
            //or simply print out
            System.out.println("\nSQLException: " + e.getErrorCode());
        }
    }
    */
}