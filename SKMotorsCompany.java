/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package uganda.skmotorscompany;

/**
 * @Kidula Mark
 * @Wadaba Alex
 * @Nannono Sumayiya
 * @Kayiira Tonny
 * @Othman Kasozi
 */
import java.util.Scanner;

public class SKMotorsCompany {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Capture Vehicle Details
        System.out.println("--- Vehicle Information Entry ---");
        System.out.print("Enter Registration Number: ");
        String regNo = input.nextLine();
        System.out.print("Enter Original Vehicle Cost: ");
        double vehicleCost = input.nextDouble();
        System.out.print("Enter Total Expenses Incurred: ");
        double expenses = input.nextDouble();

        // 2. Accept 3 Bidders
        System.out.println("\n--- Auction Process ---");
        System.out.print("Enter Bid for Bidder 1: ");
        double bid1 = input.nextDouble();
        System.out.print("Enter Bid for Bidder 2: ");
        double bid2 = input.nextDouble();
        System.out.print("Enter Bid for Bidder 3: ");
        double bid3 = input.nextDouble();

        // 3. Determine the Highest Bidder
        double winningBid;
        String winner;

        if (bid1 >= bid2 && bid1 >= bid3) {
            winningBid = bid1;
            winner = "Bidder 1";
        } else if (bid2 >= bid1 && bid2 >= bid3) {
            winningBid = bid2;
            winner = "Bidder 2";
        } else {
            winningBid = bid3;
            winner = "Bidder 3";
        }

        // 4. Financial Calculations
        System.out.print("\nEnter total deposits made by " + winner + ": ");
        double deposits = input.nextDouble();

        double balanceLeft = winningBid - deposits;

        // Profit/Loss Calculation: (Winning Bid) - (Cost + Expenses)
        double totalInvestment = vehicleCost + expenses;
        double profitOrLoss = winningBid - totalInvestment;

        // 5. Display Results
        System.out.println("\n====================================");
        System.out.println("          AUCTION SUMMARY           ");
        System.out.println("====================================");
        System.out.println("Vehicle Reg No: " + regNo);
        System.out.println("Winning Bidder: " + winner);
        System.out.println("Winning Price:  " + winningBid);
        System.out.println("Balance Due:    " + balanceLeft);

        if (profitOrLoss >= 0) {
            System.out.println("Total Profit:   " + profitOrLoss);
        } else {
            System.out.println("Total Loss:     " + Math.abs(profitOrLoss));
        }
        System.out.println("====================================");

        input.close();
    }
}
