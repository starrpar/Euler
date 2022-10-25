import java.util.List;
import java.util.ArrayList;

public class App {
    /*
     * You are given the following information, but you may prefer to do some
     * research for yourself.
     * 
     * 1 Jan 1900 was a Monday.
     * Thirty days has September,
     * April, June and November.
     * All the rest have thirty-one,
     * Saving February alone,
     * Which has twenty-eight, rain or shine.
     * And on leap years, twenty-nine.
     * A leap year occurs on any year evenly divisible by 4, but not on a century
     * unless it is divisible by 400.
     * How many Sundays fell on the first of the month during the twentieth century
     * (1 Jan 1901 to 31 Dec 2000)?
     */
    public static void main(String[] args) {
        // to sort out how many Sundays fell on the first of the month what do we need
        // to do?
        // brute force:
        // map out the days of all the years (the whole how many days in what
        // months/leap years)
        mapOutDays();
        // then identify the first Sunday, count every 7 days and sum up how many are
        // hit (Sundays)
        // by end of timeframe
    }

    private static void mapOutDays() {
        // 30 day months - September, April, June and November
        int jan = 31, mar = 31, may = 31, jul = 31, aug = 31, oct = 31, dec = 31;
        int apr = 30, jun = 30, sep = 30, nov = 30;
        int feb = 28;
        Boolean firstTime = true;
        int daysAccrued = 0;

        List<Integer> monthsOfYear = new ArrayList<Integer>();
        Boolean leapYear = false; // 1900 is NOT a leap year
        int numSundaysOnFirstOfMonth = 0;

        for (int i = 1901; i < 2001; i++) {
            monthsOfYear.clear(); // reset for reuse

            if (i % 4 == 0) {
                leapYear = true;
                feb = 29;
            } else {
                leapYear = false;
                feb = 28;
            }

            monthsOfYear.add(jan);
            monthsOfYear.add(feb);
            monthsOfYear.add(mar);
            monthsOfYear.add(apr);
            monthsOfYear.add(may);
            monthsOfYear.add(jun);
            monthsOfYear.add(jul);
            monthsOfYear.add(aug);
            monthsOfYear.add(sep);
            monthsOfYear.add(oct);
            monthsOfYear.add(nov);
            monthsOfYear.add(dec);

            // set to 2 already because 1 Jan 1900 was a Monday, and 1900 was NOT a
            // leapyear, so had 365 days, which makes 1 Jan 1901 a Tuesday
            int previousMonthLength = 0;

            for (int j = 0; j < monthsOfYear.size(); j++) {
                previousMonthLength = monthsOfYear.get(j);
                daysAccrued += previousMonthLength;

                if (firstTime) {
                    daysAccrued += 2;
                    firstTime = false;
                }
                if (daysAccrued % 7 == 0) { // means an even number of weeks from starting setting (2 days before 1 Jan
                                            // 1901, which would have been a Sunday)
                    numSundaysOnFirstOfMonth++;
                    System.out
                            .println("daysAccrued: " + daysAccrued + ", current month: " + (j + 2)
                                    + " (previousMonthLength = " + previousMonthLength + "), current year: " + i + ", "
                                    + leapYear);
                }
            }
        }
        System.out.println(numSundaysOnFirstOfMonth);
    }

    // solved the wrong problem (thought it said Sunday as the first day of the
    // month OF JANUARY of each year)
    private static void mapOutDaysVersion1() {
        // 30 day months - September, April, June and November
        int jan = 31, mar = 31, may = 31, jul = 31, aug = 31, oct = 31, dec = 31;
        int apr = 30, jun = 30, sep = 30, nov = 30;
        int feb = 28;

        int nonLeapYear = jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov + dec;
        int leapYear = nonLeapYear + 1;

        // because leap years are divisible by 4, unless divisible by 100, but for
        // divisible by 400 - since 2000 *is*
        // divisible by 400, all years divisible by 4 between 1 Jan 1901 and 31 Dec 2000
        // ARE leap years

        // double numWeeksInNonLeapYear = nonLeapYear / 7; // =52.14
        // double numWeeksInLeapYear = leapYear / 7; // =52.286

        // 1900 was NOT a leap year because 1900 is divisible by 100 and is NOT
        // divisible by 400, hence since
        // 1 Jan 1900 was a Monday, and 1900 was NOT a leap year - had 365 days, that
        // would make 1 Jan 1901
        // a Tuesday (364/7 = 52.0, so there is 1 more day over an even set of weeks in
        // a non leap year).

        // moving to a Sunday would be a +6 from a Monday, and then any subsequent +7
        // after that (for as many as happen
        // before we reach the end date - 31 Dec 2000, in this case)

        // actually the whole leap year thing will mess up this linear approximation
        // gotta go more granular

        // working backward, since 1899 was a non-leapyear, the 1st of Jan 1899 WAS a
        // Sunday
        // so then going in increments of "+7 days" would tell us which years have
        // Sunday as
        // the first day of the year...

        int year = 0;
        int sum = 0;

        sum = 365; // for 1900 (a non-leapyear)
        int numSundaysOnJanFirst = 0;

        for (int i = 1901; i < 2001; i++) {
            if (i % 4 == 0) {
                year = leapYear;
            } else {
                year = nonLeapYear;
            }

            sum += year;
            // System.out.println("adding " + year + " days; sum is: " + sum);

            int addlDays = sum % 364;
            if (addlDays % 7 == 0) {
                System.out.println("addlDays: " + addlDays + ", year with 1 Jan as a Sunday: " + i);
                numSundaysOnJanFirst++;
            }
        }

        System.out.println(numSundaysOnJanFirst);
    }
}
