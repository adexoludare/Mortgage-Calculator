
import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Mortgage Calculator 1.0");

        final byte months_in_year = 12;
        final byte percent = 100;

        Scanner Scanner = new Scanner(System.in);

        System.out.println("Principal: ");
        int principal = Scanner.nextInt();
        // next() and nextLine() are use for strings,

        System.out.print("Annual Intrest Rate: ");
        double annualIntrestRate = Scanner.nextDouble();

        System.out.print("Period (Years): ");
        byte years = Scanner.nextByte();

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
