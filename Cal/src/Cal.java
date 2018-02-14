/**
 * © Maison de Code
 * Created by connorsampson on 2/2/18.
 */


import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Cal {
    public static int year;

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter year: ");

        year = scanner.nextInt();
        String yearPadding ="";
        for (int yp = 0; yp<32; yp++) {
            yearPadding += " ";
        }
        System.out.println(yearPadding + year + yearPadding);


        for(int quarter = 1; quarter<=12; quarter+=3) {
            printMonth(quarter);
        }
    }


    public static void printMonth (int currentMonth) {
        LocalDate monthOne = LocalDate.of(year, currentMonth, 1);
        LocalDate monthTwo = LocalDate.of(year, currentMonth+1, 1);
        LocalDate monthThree = LocalDate.of(year, currentMonth+2, 1);

        // Print month titles and grid headers
        String monthHeader = monthTitle(monthOne.getMonth().toString()) + "      " +  monthTitle(monthTwo.getMonth().toString())  + "      " + monthTitle(monthThree.getMonth().toString());
        System.out.println(monthHeader);
        System.out.println("Su Mo Tu We Th Fr Sa      Su Mo Tu We Th Fr Sa      Su Mo Tu We Th Fr Sa");

        // Set up variables to hold the weeks and current values of each week
        String weekOne = "", weekTwo = "", weekThree = "", weekFour = "", weekFive = "";
        int weekCount = 1;
        String weekValues ="";

        // Counting variable for while loop
        int monthCount = 0;

        while( monthCount <= 2) {
            // set current working month
            LocalDate month = LocalDate.of(year, currentMonth, 1);
            month = month.plusMonths(monthCount);
            for (int i = 0; i < month.lengthOfMonth(); i++) {
                // Add current date to weekValue string
                weekValues+= String.format("%2d ", month.getDayOfMonth());
                // Check to see if it is then end of the week
                if (month.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    if (weekCount == 1) {
                        DayOfWeek firstDay = month.getDayOfWeek();
                        int padding = (firstDay.ordinal() % 7);
                        for (int p = 0; p <= padding; p++) {
                            weekOne += " ";
                        }
                        weekOne += weekValues + "     ";
                        weekValues = "";
                        weekCount++;
                    } else if (weekCount == 2) {
                        weekTwo += weekValues + "     ";
                        weekValues = "";
                        weekCount++;
                    } else if (weekCount == 3) {
                        weekThree += weekValues + "     ";
                        weekValues = "";
                        weekCount++;
                    } else if (weekCount == 4) {
                        weekFour += weekValues + "     ";
                        weekValues = "";
                        weekCount++;
                    } else {
                        int endOfMonthPaddingSize = 20 - weekValues.length();
                        String endOfTheMonthPadding = "";
                        for (int p = 0; p<=endOfMonthPaddingSize; p++) {
                            endOfTheMonthPadding += " ";
                        }
                        weekFive += weekValues + endOfTheMonthPadding + "     ";
                    }
                }
                month = month.plusDays(1);
            }


            monthCount ++;
            weekCount=1;
            weekValues = "";

        }

        System.out.println(weekOne);
        System.out.println(weekTwo);
        System.out.println(weekThree);
        System.out.println(weekFour);
        System.out.println(weekFive);

    }

    public static String monthTitle(String month) {
        int padding = (20 - month.length()) / 2;
        String paddingString = "";

        for (int p=0; p<padding; p++) {
            paddingString += " ";
        }

        return paddingString + month + paddingString;
    }
}
