
import java.time.LocalDateTime;


public class Test{

    public Donation.DonorName createDonor(String firstName, String lastName, String MI){
        Donation donation = new Donation();
        Donation.DonorName name = donation.Donor;

        name.firstName = firstName;
        name.lastName = lastName;
        name.middleInitial = MI;

        return name;
    }

    public Donation.DonationType getDonationType(Donation.DonorName name){
        Donation.DonationType type;

        //have a method for looking up amount of donation
        //type = LookUpDonationType(name);

        //for test purposes, just use one of the enum types (could make this random for testing purposes)
        type = Donation.DonationType.MONEY;

        return type;
    }

    public Double getAmountOfDonation(Donation.DonorName name){
        Double amount;

        //have a method for looking up amount of donation
        //amount = LookUpDonationAmount(name);

        //for test purposes, just use some miscellaneous amount (could randomize by timestamp hash)
        amount = 1380.16;

        return amount;
    }

    public LocalDateTime getDateOfDonation(Donation.DonorName name){
        LocalDateTime date;

        //have a method for looking up date of donation
        //date = LookUpDateOfDonation(name);

        //for test purposes, just use today (could randomize)
        date = LocalDateTime.now();

        return date;
    }

    public Donation.DonationType getTypeOfDistribution(){
        Donation.DonationType type;

        //this is just test data
        type = Donation.DonationType.CLOTHING;

        return type;
    }

    public Double getAmountOfDistribution(){
        Double amount;

        //this is just test data
        amount = 500.00;

        return amount;
    }
    
    public LocalDateTime getDateOfDistribution(){
        LocalDateTime date;

        //this is just test data
        date = LocalDateTime.now();

        return date;
    }
}