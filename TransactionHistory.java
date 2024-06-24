import java.util.ArrayList;

/*
 * Class Name: IFT 210
 * Author: Renay Hernandez
 * Date Created: 02/20/23
 * Purpose: Transaction history program to keep a running documentation on transactions completed by the user
 */

 public class TransactionHistory {

    String ticker;
    double qty;
    double costBasis;
    String transDate;
    String transType;

    ArrayList<Transaction> listOfTransactions = new ArrayList<Transaction>();

    public void printTransactions() {
        if(listOfTransactions.size() == 0) {
            System.out.println("No transaction history");
        } else {
            System.out.println("Transaction History:");
            System.out.println("                Renay Hernandez Brokerage Acount");
            System.out.println("                =================================");
            System.out.println("");

            System.out.println("Date            Ticker  Quantity        Cost Basis      Trans Type");
            System.out.println("==================================================================");
            for(int i = 0; i < listOfTransactions.size(); i++) {
                System.out.printf("%s %9s %8.1f %11s%.1f %19s%n", listOfTransactions.get(i).getTransDate(), listOfTransactions.get(i).getTicker(), listOfTransactions.get(i).getQuantity(), "$", listOfTransactions.get(i).getCostBasis(), listOfTransactions.get(i).getTransType());
            }
            
        }
    }

    

    public void getCostBasis() {

    }

    public void getQuantity() {

    }

    public void getTicker() {
        
    }

    public void getTransDate() {
        
    }

    public void getTransType() {
        
    }

    public void setCostBasis(double costBasis) {
        this.costBasis = costBasis;
    }

    public void setQuantity(double qty) {
        this.qty = qty;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
    
    public void setTransType(String transType) {
        this.transType = transType;
    }
} 
  
