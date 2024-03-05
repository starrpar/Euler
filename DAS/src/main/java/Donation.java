import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    // A feature that allows the shelter staff to record details of the donations,
    // such as the donor's name, type of donation (money, food, clothing, etc.),
    // quantity or amount donated, and the date of the donation.
    public void register(DonorName name, DonationType type, Double amountDonated, LocalDateTime date) {
        //write data to database
        //add DB connection

        //add query



        //meanwhile simply print out data for given donor
        System.out.println();
        System.out.println("\nRegister():");
        System.out.print(name.firstName + " " + name.middleInitial + " " + name.lastName);
        System.out.print(" contributed a donation of type: <");
        System.out.print(type.toString().toLowerCase());
        System.out.print(">, valued in dollar amounts at: ");
        System.out.print("$" + amountDonated.toString());
        System.out.print(" on: ");
        System.out.print(date);


    }

    // A feature to log when and how much of the donations are distributed, capturing
    // the type of donation, quantity or amount distributed, and the date of distribution.
    public void distribute(DonationType type, Double amountDonated, LocalDateTime date) {
        //make adjustments in the database...
        //add DB connection

        //add query

    

        //meanwhile simply print out data of the distribution
        System.out.println();
        System.out.println("\nDistribute():");
        System.out.print("A distribution of donation type: <");
        System.out.print(type.toString().toLowerCase());
        System.out.print(">, was dispersed, valued in dollar amounts at: ");
        System.out.print("$" + amountDonated.toString());
        System.out.print(" on: ");
        System.out.print(date);
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

        //add query
        


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
