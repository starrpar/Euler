
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

// Requirements:

// This shelter is in need of a user-friendly tool to accurately record and track the inflow and outflow of donations, and to generate insightful reports about their donation management.

// Here are the core functionalities your solution should address:

// DONE - Donation Registration: A feature that allows the shelter staff to record details of the donations, such as the donor's name, 
// type of donation (money, food, clothing, etc.), quantity or amount donated, and the date of the donation.
// NOTES: Support input of Donor (name), type of donation (ENUM supporting Money, Clothing, Food and Misc, but could be expanded), value of donation (monetary equivalence),
// date of donation, and "amount remaining" is calculated behind the scenes - primarily to adjust value available based on distributions of funds/resources.

// DONE - Donation Distribution: A feature to log when and how much of the donations are distributed, capturing the type of donation, 
// quantity or amount distributed, and the date of distribution.
// NOTES: Added some trivial simulation/test scenario code, but functionality is present to evaluate if appropriate funds are present, against
// which to draw for a given donation type and donor in the DB, so that could be used to render whatever more refined functionality was preferred
// (simply build on the existing structure for data that is currently present)

// DONE - Donation Reports: Your solution should have the capacity to generate two types of reports: (1) An inventory report 
// displaying the current status of donations, grouped by type. (2) A donator report, summarizing the total contributions received from each donor.
// NOTES: you can see in the "reports" (logged to log file currently - entitled "application-20240318.log" in the resources folder in Git - DAS/src/main/resources) 
// that the data is present to be able to "present" the requested/expected data in a preferred format (nice UI, for example) relatively easily.

// TO DO List:
// DONE Move instructions to README file for GitHub repository
// 
// DONE 0. Add JSON file data intake
//   (FUTURE: could eventually be REST API from a UI for query support)
// 
// DONE 1. Add business logic to support not distributing funds of a given type unless such quantity currently exists
//   (i.e. use query to verify present in Donations table first - already have query - just need to expand functionality)
//   DONE //ToDo: determine if amount expected of given DonationType is present in remaining/existing donations (not already distributed)
//   DONE //ToDo: add logic to determine if distribution data was successfully inserted or not
//   
// DONE 2. Improve reporting (even though trivial without better formatting - see below/FUTURE)
//
// (NEXT) 3. Add unit testing
//
// DONE? 4. (Add more logging - minor, possibly not even necessary)
// 
// FUTURE: Augment reporting functionality with a UI to render the output if customer was wanting such.
// 
// NOTE for self:
// build jar file command: jar cvf DonationsDistribution.jar -C target\classes .

public class App {

    public static void main(String[] args) {

        // behavior steering parameters - could add as input args if preferred
        // ...or support some other input format (config file?)
        boolean logCandidateData = false;
        boolean logReportListing = true;
        boolean logSummary = true;
        boolean logInputs = false;
        boolean distributeFunds = true;

        String JSONFileLocation = "C:\\Git\\Euler\\DAS\\src\\main\\resources\\donors.json";
        List<Donation> donations = new ArrayList<>();
        Donation donationInstance = new Donation();
        TestInputs testInputsInstance = new TestInputs();
        donations = testInputsInstance.ReadInDataFromJSONFile(JSONFileLocation, logSummary);

        // set the following to control amount of distribution
        LocalDateTime nowForTestUse = LocalDateTime.now();
        System.out.println("\nDateTime.now: " + nowForTestUse);
        LocalDateTime date = nowForTestUse;
        Donation.DonationType type = Donation.DonationType.MONEY;
        Double amount = 1800.00;

        for (Donation donationInfo : donations) {

            donationInfo.register(donationInfo.getDonorName(), donationInfo.getDonationType(),
                    donationInfo.getDonationAmount(), donationInfo.getDonationDate(), logInputs);
        }

        // Uncomment next line to trigger error: attempt to distribute too large an
        // amount
        // amount = 50000.00;
        // what the error looks like in the log
        // 2024-03-12 12:18:00.756 [main] ERROR Donation - Unable to find any donations
        // of sufficient value to satisfy expected donation distribution

        if (distributeFunds) {
            donationInstance.distribute(type, amount, date, logCandidateData);
        }

        // In a production app, I would back this functionality with exporting the data
        // in tabular form to some rendered UI page (ReactJS)
        // I'm still learning React currently, so am not ready to do that yet...
        // however, this method does attempt to output the data in an organized form
        // that could be employed by users in the interim
        donationInstance.generateReports(date, logReportListing);

    }
}