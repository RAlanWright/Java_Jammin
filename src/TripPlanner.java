import java.util.Scanner;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class TripPlanner {
    // Reduce the decimal places to 1 or 2 depending
    private static DecimalFormat df2 = new DecimalFormat(".##");
    private static DecimalFormat df1 = new DecimalFormat(".#");

    private static final int R = 6371; // Approx Earth radius in KM

    public static void main(String[] args){
        NameAndLocation();
        DurationMoney();
        TimeDifference();
        SquareArea();
    }
    
    // Method for gathering user information such as name and destination
    public static void NameAndLocation() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Vacation Planner!");
        System.out.print("What is your name? ");
        String name = input.nextLine();
        System.out.print("Nice to meet you " + name + ", where are you travelling to? ");
        String place = input.nextLine();
        System.out.println("Great! " + place + " sounds like a great trip.");
        System.out.println("**************");
        System.out.println();
        System.out.println();
    }
    
    // Method for determining budget over duration of trip
    public static void DurationMoney(){
        Scanner input = new Scanner(System.in);
        System.out.print("How many days are you going to spend travelling? ");
        int days = input.nextInt();
        System.out.print("How much money, in USD, are you planning to spend on your trip? ");
        double money = input.nextDouble();
        double dailyMoney = money / days;
        System.out.print("What is the three letter currency symbol for your travel destination? ");
        String currency = input.next();
        System.out.print("How many " + currency + " are there in 1 USD? ");
        double conversion = input.nextDouble();
        double foreign = money * conversion;
        double foreignDaily = foreign / days;
        int hours = days * 24;
        int minutes = hours * 60;
        System.out.println();
        System.out.println();
        System.out.println("If you are traveling for " + days + " days that is the same as " + hours + " hours or " + minutes + " minutes");

        df2.setRoundingMode(RoundingMode.FLOOR);
        
        // Casts double as an integer to hide trailing 0's
        if (money % 1 == 0) {
            System.out.println("If you are going to spend $" + (int) money + " USD that means per day you can spend up to $" + df2.format(dailyMoney) + " USD.");
        }
        else {
            System.out.println("If you are going to spend $" + money + " USD that means per day you can spend up to $" + df2.format(dailyMoney) + " USD.");
        }

        System.out.println("Your total budget in " + currency + ", which per day is " + df2.format(foreignDaily) + " " + currency + ".");
        System.out.println("**************");
        System.out.println();
        System.out.println();

    }

    // Method for determining time difference
    public static void TimeDifference() {
        Scanner input = new Scanner(System.in);

        int noon = 12;
        int midnight = 0;
        int currentNoon = 0;
        int currentMidnight = 0;

        System.out.print("What is the time difference, in hours, between your home and your destination? ");
        int timeDiff = input.nextInt();

        // Prevent time difference calculation from going beyond 24 hours and remain positive
        if (timeDiff > 0) {
            currentNoon = noon + timeDiff;
            currentMidnight = midnight + timeDiff;
            if (currentNoon > 24) {
                currentNoon %= 24;
            }

            else if (currentMidnight > 24){
                currentMidnight %= 24;
                }
        }
        else if (timeDiff < 0){
            currentNoon = noon - timeDiff;
            currentMidnight = midnight - timeDiff;
            if (currentNoon > 24) {
                currentNoon %= 24;
            }

            else if (currentMidnight > 24){
                    currentMidnight %= 24;
            }
        }

        System.out.println("That means that when it is midnight at home, it will be " + currentMidnight + ":00 in your travel destination");
        System.out.println("and when it is noon it will be " + currentNoon + ":00.");
        System.out.println("**************");
        System.out.println();
        System.out.println();
    }

    // Method for calculating square miles from square kilometers
    public static void SquareArea(){
        Scanner input = new Scanner(System.in);

        System.out.print("What is the square area of your destination country in km2? ");
        double km = input.nextInt();
        
        // Convert km to miles
        df1.setRoundingMode(RoundingMode.FLOOR);
        double miles = km * 0.3861;
        System.out.println("In miles2 that is " + df1.format(miles));
        
        // Get coordinates
        System.out.print("What is your starting latitude? ");
        double lat1 = input.nextDouble();
        System.out.print("What is your starting longitude? ");
        double lon1 = input.nextDouble();
        System.out.print("What is your ending latitude? ");
        double lat2 = input.nextDouble();
        System.out.print("What is your ending latitude? ");
        double lon2 = input.nextDouble();

        System.out.println("The distance from you to your destination is: " + haversine(lat1, lon1, lat2, lon2) + "km.");

        System.out.println("**************");
        System.out.println();
        System.out.println();
    }

    // Haversine method for determining distance between two points on the globe
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
