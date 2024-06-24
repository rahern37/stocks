/*
* Class Name: IFT 210
* Author: Renay Hernandez
* Date Created: 02/20/23
* Purpose: Stock Portfolio application that allows multiple transactions and tracks those transactions
*/

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PortfolioManager {

    static Portfolio portfolio = new Portfolio();
    static TransactionHistory transactionHistory = new TransactionHistory();
    // static ArrayList<TransactionHistory> portfolioList = new ArrayList<TransactionHistory>();

    /**
     * Creates a Stock object with the given parameters.
     * @param ticker The name of the Stock.
     * @param quantity The number of the Stock for a given transaction.
     * @param costBasis The cost of the Stock for a given transaction.
     * @return The Stock object.
     */
    public static Stock createStock(String ticker, double quantity, double costBasis) {
        Stock newStock = new Stock(ticker, quantity, costBasis);
        return newStock;
    }

    public static Transaction createTransaction(String ticker, double qty, double costBasis, String transDate, String transType) {
        Transaction newTrasaction = new Transaction(ticker, qty, costBasis, transDate, transType);
        return newTrasaction;
    }

    public static Transaction createDeposit(String ticker, double quantity, double costBasis, String transDate, String transType) {
        Transaction newDeposit = new Transaction(ticker, quantity, costBasis, transDate, transType);
        return newDeposit;
    }

    public static void printMenu() {
        System.out.println("Renay Hernandez Brokerage Acount");
        System.out.println("");
        System.out.println("0 - Exit");
        System.out.println("1 - Deposit Cash");
        System.out.println("2 - Withdraw Cash");
        System.out.println("3 - Buy Stock");
        System.out.println("4 - Sell Stock");
        System.out.println("5 - Display Transaction History");
        System.out.println("6 - Display Portfolio");
        System.out.print("Enter option (0 to 6): ");
    }

    public static void main(String[] args){

        Date date = new Date();
        SimpleDateFormat formatedDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String currentDate = formatedDate.format(date);
        Scanner scnr = new Scanner(System.in);
        
        String menu; //used for switch-case for menu
        double quantity;
        double costBasis;
        double stockPrice; //used to hold stock price when multiplying quantity by cost basis
        String ticker;
        String transactionDate;

        printMenu();

         
        while(scnr.hasNext()) {
            
            menu = scnr.next();
            switch(menu) {
                
                case("0"):
                    scnr.close();
                    System.out.println("Goodbye");
                    return;
    
                case("1"):
                    System.out.print("Please enter current date (mm/dd/yyyy): ");
                    transactionDate = scnr.next();
                    System.out.println("");

                    System.out.print("Please enter cash to deposit: ");
                    quantity = scnr.nextDouble();                    
                    System.out.println("");

                    transactionHistory.listOfTransactions.add(createTransaction("CASH", quantity, 1.00, transactionDate, "DEPOSIT"));

                    portfolio.editStock(createStock("CASH", quantity, 1.0));
                
                    printMenu();
                    break;
    
                case("2"):
                    System.out.print("Please enter cash amount to withdraw: ");
                    quantity = scnr.nextDouble();
                    if(quantity > portfolio.getAmmountOfTicker("CASH") || portfolio.getAmmountOfTicker("CASH") == -1) {
                        System.out.println("OVERDRAWN FUNDS: Withdrawal exceeds current available cash.");
                        printMenu();
                        break;
                    }
                    System.out.print("Please enter current date (mm/dd/yyyy): ");
                    transactionDate = scnr.next();
                    
                    transactionHistory.listOfTransactions.add(createTransaction("CASH", -quantity, 1.00, transactionDate, "WITHDRAW"));
                    portfolio.editStock(createStock("CASH", -quantity, 1.0));
                    
                    printMenu();
                    break;
    
                case("3"):
                    System.out.print("Please enter cost basis of stock: ");
                    costBasis = scnr.nextDouble();
                    System.out.println("");

                    System.out.print("Please enter quantity of stock to be purchased: ");
                    quantity = scnr.nextDouble();
                    System.out.println("");

                    stockPrice = costBasis * quantity;
                    if(stockPrice > portfolio.getAmmountOfTicker("CASH") || portfolio.getAmmountOfTicker("CASH") == -1) {
                        System.out.println("INSUFFICIENT FUNDS: total stock cost exceeds current available funds");
                        System.out.println("\n");
                        printMenu();
                        break;
                    }
    
                    System.out.print("Please enter ticker for stock: ");
                    ticker = scnr.next();
                    System.out.println("");

                    System.out.print("Please enter current date (mm/dd/yyyy): ");
                    transactionDate = scnr.next();
                    
                    // Buy the stock.
                    transactionHistory.listOfTransactions.add(createTransaction(ticker, quantity, costBasis, transactionDate, "BUY"));
                    portfolio.editStock(createStock(ticker, quantity, costBasis));

                    // Remove the cash.
                    transactionHistory.listOfTransactions.add(createTransaction("CASH", -stockPrice, 1.00, transactionDate, "WITHDRAW"));
                    portfolio.editStock(createStock("CASH", -1*quantity*costBasis, 1.00));

                    printMenu();
                    break;
    
                case("4"):
                    System.out.print("Please enter the ticker: ");
                    ticker = scnr.next();
                    System.out.println("");
                    if(!portfolio.isStockAlreadyInPortfolio(ticker)) {
                        System.out.println("You don't own any " + ticker + " to sell.");
                        printMenu();
                        break;
                    }

                    System.out.print("Please enter cost basis of stock: ");
                    costBasis = scnr.nextDouble();
                    System.out.println("");

                    System.out.print("Please enter quantity of stock to be sold: ");
                    quantity = scnr.nextDouble();
                    System.out.println("");
                    if(portfolio.getAmmountOfTicker(ticker) <= quantity) {
                        System.out.println("You don't own enough " + ticker + " to sell that many.");
                        printMenu();
                        break;
                    }

                    stockPrice = costBasis * quantity;
                    
                    System.out.print("Please enter the current date (mm/dd/yyyy): ");
                    transactionDate = scnr.next();
                    System.out.println("");

                    
                    // Sell the stock
                    transactionHistory.listOfTransactions.add(createTransaction(ticker, quantity, costBasis, transactionDate, "SELL"));
                    portfolio.editStock(createStock(ticker, -quantity, costBasis));
                    
                    // Get the money
                    transactionHistory.listOfTransactions.add(createTransaction("CASH", stockPrice, 1.00, transactionDate, "DEPOSIT"));
                    portfolio.editStock(createStock("CASH", quantity*costBasis, 1.00));
                    
                    printMenu();
                    break;
                
                case("5"):
                    transactionHistory.printTransactions();
                    printMenu();
                    break;
    
                case("6"):
                    System.out.println("Portfolio as of: " + currentDate);
                    System.out.println("=================================");
                    System.out.println("Ticker    Quantity");
                    System.out.println("==================");
                    portfolio.printPortfolio();
                    
                    printMenu();
                    break;
    
                default:
                    System.out.println("Invalid option! Please enter option (0-6): ");
                    printMenu();
                    break;
            }
        }
                  
    }
 }