
import java.time.LocalDateTime;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

    private final Logger logger = LogManager.getLogger(this);

    public Donation.DonorName createDonor(String firstName, String lastName, String MI) {

        Donation.DonorName name = new Donation.DonorName();

        name.setFirstName(firstName);
        name.setLastName(lastName);
        name.setMI(MI);

        logger.log(Level.INFO, "Testing: Creating donor: " + name);
        return name;
    }

    public Donation.DonationType getDonationType(Donation.DonorName name) {
        Donation.DonationType type = Donation.DonationType.MONEY; // default value

        logger.log(Level.INFO, "Testing: Generating donation type: " + type + " for donor: " + name);
        return type;
    }

    public Donation.DonationType getDonationType(Donation.DonorName name, Donation.DonationType type) {

        logger.log(Level.INFO, "Testing: Generating donation type: " + type + " for donor: " + name);
        return type;
    }

    public Double getAmountOfDonation(Donation.DonorName name) {
        Double amount = 100.00; // default value

        logger.log(Level.INFO, "Testing: Specifying donation amount: " + amount + " for donor: " + name);
        return amount;
    }

    public Double getAmountOfDonation(Donation.DonorName name, Double amount) {

        logger.log(Level.INFO, "Testing: Specifying donation amount: " + amount + " for donor: " + name);
        return amount;
    }

    public LocalDateTime getDateOfDonation(Donation.DonorName name) {
        LocalDateTime date = LocalDateTime.now(); // default value

        logger.log(Level.INFO, "Testing: Setting date as: " + date + " for donor: " + name);
        return date;
    }

    public LocalDateTime getDateOfDonation(Donation.DonorName name, LocalDateTime date) {

        logger.log(Level.INFO, "Testing: Setting date as: " + date + " for donor: " + name);
        return date;
    }

    public Donation.DonationType getTypeOfDistribution() {
        Donation.DonationType type = Donation.DonationType.MONEY; // default value

        logger.log(Level.INFO, "Testing: Setting distribution to be of donation type: " + type);
        return type;
    }

    public Donation.DonationType getTypeOfDistribution(Donation.DonationType type) {

        logger.log(Level.INFO, "Testing: Setting distribution to be of donation type: " + type);
        return type;
    }

    public Double getAmountOfDistribution() {
        Double amount = 100.00; // default value

        logger.log(Level.INFO, "Testing: Setting distribution amount: " + amount);
        return amount;
    }

    public Double getAmountOfDistribution(Double amount) {

        logger.log(Level.INFO, "Testing: Setting distribution amount: " + amount);
        return amount;
    }

    public LocalDateTime getDateOfDistribution() {
        LocalDateTime date = LocalDateTime.now(); // default value

        logger.log(Level.INFO, "Testing: Setting distribution date: " + date);
        return date;
    }

    public LocalDateTime getDateOfDistribution(LocalDateTime date) {

        logger.log(Level.INFO, "Testing: Setting distribution date: " + date);
        return date;
    }
}