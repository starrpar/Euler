import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestInputs {

    private final Logger logger = LogManager.getLogger(this);

    public List<Donation> ReadInDataFromJSONFile(String JSONFileLocation, boolean logSummary) {

        List<Donation> donations = new ArrayList<>();

        if (logSummary) {
            logger.log(Level.INFO, "Summary inputs:");
        }
        // read in donors.json file and process by calling donation.register for each
        JSONArray donorsList = new JSONArray();
        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonObj = (JSONObject) parser
                    .parse(new FileReader(JSONFileLocation));
            donorsList = (JSONArray) jsonObj.get("Donors");

            for (Object obj : donorsList) {

                Donation donationInfo = new Donation();
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

                String dateStr = (String) donate.get("date");
                dateStr = dateStr.replaceAll("-", "").replaceAll("/", "");
                if (dateStr.length() < 7) {
                    dateStr = "0" + dateStr.substring(0, 1) + "0" + dateStr.substring(1, dateStr.length());
                } else if (dateStr.length() < 8) {
                    dateStr = "0" + dateStr.substring(0, dateStr.length());
                }
                // System.out.println(dateStr);
                LocalDate jsonDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MMddyyyy"));
                LocalTime time = LocalTime.of(0, 0, 0, 0);
                LocalDateTime dateTime = LocalDateTime.of(jsonDate, time);
                donationInfo.setDonorName(jsonName);
                donationInfo.setDonationType(jsonType);
                donationInfo.setDonationAmount(jsonValue);
                donationInfo.setDonationDate(dateTime);
                if (logSummary) {
                    if (((donationInfo.getDonorName()).toString()).length() > 13) {
                        if (donationInfo.getDonationType() == Donation.DonationType.CLOTHING) {
                            logger.log(Level.INFO, donationInfo.getDonorName() + "\t:" + donationInfo.getDonationType()
                                    + "\t:" + donationInfo.getDonationAmount() + "\t\t:"
                                    + donationInfo.getDonationDate());
                        } else {
                            logger.log(Level.INFO, donationInfo.getDonorName() + "\t:" + donationInfo.getDonationType()
                                    + "\t\t:" + donationInfo.getDonationAmount() + "\t\t:"
                                    + donationInfo.getDonationDate());
                        }
                    } else {
                        if (donationInfo.getDonationType() == Donation.DonationType.CLOTHING) {
                            logger.log(Level.INFO,
                                    donationInfo.getDonorName() + "\t\t:" + donationInfo.getDonationType()
                                            + "\t:" + donationInfo.getDonationAmount() + "\t\t:"
                                            + donationInfo.getDonationDate());
                        } else {
                            logger.log(Level.INFO,
                                    donationInfo.getDonorName() + "\t\t:" + donationInfo.getDonationType()
                                            + "\t\t:" + donationInfo.getDonationAmount() + "\t\t:"
                                            + donationInfo.getDonationDate());
                        }
                    }
                }
                donations.add(donationInfo);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "DEBUGGING: Exception hit... " + e.getLocalizedMessage() + ", " + e.getStackTrace());
        }
        return donations;
    }
}
