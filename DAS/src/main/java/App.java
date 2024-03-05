
import java.time.LocalDateTime;


// Requirements:

// This shelter is in need of a user-friendly tool to accurately record and track the inflow and outflow of donations, and to generate insightful reports about their donation management.

// Here are the core functionalities your solution should address:

// Donation Registration: A feature that allows the shelter staff to record details of the donations, such as the donor's name, type of donation (money, food, clothing, etc.), quantity or amount donated, and the date of the donation.
// Donation Distribution: A feature to log when and how much of the donations are distributed, capturing the type of donation, quantity or amount distributed, and the date of distribution.
// Donation Reports: Your solution should have the capacity to generate two types of reports: (1) An inventory report displaying the current status of donations, grouped by type. (2) A donator report, summarizing the total contributions received from each donor.


public class App {

    public static void main(String[] args) {

        Donation donation = new Donation();
        // System.out.println(donation.Donor.toString());
        Test test = new Test();

        Donation.DonorName name = test.createDonor("Sam","Smith","L");
        LocalDateTime date = test.getDateOfDonation(name);
        Donation.DonationType type = test.getDonationType(name);
        Double amount = test.getAmountOfDonation(name);

        donation.register(name, type, amount, date);

        date = test.getDateOfDistribution();
        type = test.getTypeOfDistribution();
        amount = test.getAmountOfDistribution();

        donation.distribute(type, amount, date);

        donation.generateReports(date);
    }
}