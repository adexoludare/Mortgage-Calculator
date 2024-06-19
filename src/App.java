
import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Mortgage Calculator 1.0");

        final byte months_in_year = 12;
        final byte percent = 100;

        int principal;
        double annualIntrestRate;
        byte years;

        Scanner Scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Principal ($1k - $1M): ");
            principal = Scanner.nextInt();
            if (principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1000 and 1000000");
        }

        while (true) {
            System.out.print("Annual Intrest Rate(0% -30%) : ");
            annualIntrestRate = Scanner.nextDouble();
            if (annualIntrestRate > 0 && annualIntrestRate <= 30)
                break;
            System.out.println("Invalid Rate! Enter a value greater than 0 and less than or equal to 30");
        }
        while (true) {
            System.out.print("Period (Years): ");
            years = Scanner.nextByte();
            if (years > 0 && years <= 30) {
                break;
            }
            System.out.println("Enter a value greater than 0 and less than or equal to 30");
        }

        int numberOfPayment = years * months_in_year;
        System.out.println(
                "You are requeting for a loan : $" + principal + " at a rate :" + annualIntrestRate + " for : "
                        + years);

        double monthlyItrest = (annualIntrestRate / percent) / months_in_year;

        double mortgage = principal *
                (monthlyItrest * Math.pow(1 + monthlyItrest, numberOfPayment)
                        / (Math.pow(1 + monthlyItrest, numberOfPayment) - 1));

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println(mortgageFormatted);

    }

}
