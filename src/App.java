
import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    final static byte months_in_year = 12;
    final static byte percent = 100;

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Mortgage Calculator 1.0");

        int principal = (int) readNumber("Principal ($1k - $1M): ", 1_000, 1_000_000);
        float annualIntrestRate = (float) readNumber("Annual Interest Rate(0% - 30%) : ", 0.1, 30);
        byte years = (byte) readNumber("Period (Years): ", 1, 30);

        System.out.println(
                "You are requeting for a loan : $" + principal + " at a rate :" + annualIntrestRate + " for : "
                        + years);

        printMortgage(principal, annualIntrestRate, years);
        printPaymentSchedule(principal, annualIntrestRate, years);
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

        short numberOfPayment = (short) (years * months_in_year);
        double monthlyItrest = (annualIntrestRate / percent) / months_in_year;

        double mortgage = principal *
                (monthlyItrest * Math.pow(1 + monthlyItrest, numberOfPayment)
                        / (Math.pow(1 + monthlyItrest, numberOfPayment) - 1));

        return mortgage;
    }

    public static double calculateBalance(int principal,
            float annualIntrestRate,
            byte years,
            short numberOfPaymentMade) {

        short numberOfPayment = (short) (years * months_in_year);
        double monthlyItrest = (annualIntrestRate / percent) / months_in_year;

        double balance = principal *
                ((Math.pow(1 + monthlyItrest, numberOfPayment) - Math.pow(1 + monthlyItrest, numberOfPaymentMade))
                        / (Math.pow(1 + monthlyItrest, numberOfPayment) - 1));

        return balance;

    }

    public static void printPaymentSchedule(
            int principal,
            float annualIntrestRate,
            byte years) {
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("--------------------");
        for (short month = 1; month <= years * months_in_year; month++) {
            double balance = calculateBalance(principal, annualIntrestRate, years, month);
            String mortgageBalance = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(month + " : " + mortgageBalance);
        }
    }

    public static void printMortgage(
            int principal,
            float annualIntrestRate,
            byte years) {
        double mortgage = calculateMortgage(principal, annualIntrestRate, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------------------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }
}
