import java.text.NumberFormat;
import java.util.Scanner;


public class MortgageCalc {
    final static int MONTHS_IN_A_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int)readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterest, years);
        printPaymentSchedule(principal, annualInterest, years);
    }

    private static void printMortgage(int principal, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Mortgage Payments:" + " " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for(short month = 1; month <= years * MONTHS_IN_A_YEAR; month++){
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner in = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = in.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " add " + max);
        }
        return value;
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numbersOfPaymentsMade){

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_A_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_A_YEAR);

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numbersOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) -1);

        return balance;
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years){

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_A_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_A_YEAR);

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1+monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }
}
