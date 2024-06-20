
import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Mortgage Calculator 1.0");

        int principal = (int) readNumber("Principal ($1k - $1M): ", 1_000, 1_000_000);
        float annualIntrestRate = (float) readNumber("Annual Interest Rate(0% - 30%) : ", 0.1, 30);
        byte years = (byte) readNumber("Period (Years): ", 1, 30);

        System.out.println(
                "You are requeting for a loan : $" + principal + " at a rate :" + annualIntrestRate + " for : "
                        + years);

        double mortgage = calculateMortgage(principal, annualIntrestRate, years);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println(mortgageFormatted);

    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value = 0;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out
                    .println("Invalid Input! Enter a value between " + min + " and  " + max);
        }
        return value;
    }

    public static double calculateMortgage(
            int principal,
            float annualIntrestRate,
            byte years) {
        final byte months_in_year = 12;
        final byte percent = 100;

        short numberOfPayment = (short) (years * months_in_year);
        double monthlyItrest = (annualIntrestRate / percent) / months_in_year;

        double mortgage = principal *
                (monthlyItrest * Math.pow(1 + monthlyItrest, numberOfPayment)
                        / (Math.pow(1 + monthlyItrest, numberOfPayment) - 1));

        return mortgage;
    }
}
