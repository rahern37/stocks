/*
* Class Name: IFT 210
* Author: Renay Hernandez
* Date Created: 02/26/23
* Purpose: Creates a deposit object to not overwrite other deposit values
*/

public class Transaction {
    String ticker;
    double qty;
    double costBasis;
    String transDate;
    String transType;

    Transaction(String ticker, double qty, double costBasis, String transDate, String transType) {
        this.ticker = ticker;
        this.qty = qty;
        this.costBasis = costBasis;
        this.transDate = transDate;
        this.transType = transType;
    }

    // Setters
    public void setTicker(String ticker){
        this.ticker = ticker;
    }

    public void setQuantity(double qty){
        this.qty = qty;
    }

    public void setCostBasis(double costBasis){
        this.costBasis = costBasis;
    }

    public void setTransDate(String transDate){
        this.transDate = transDate;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    // Getters
    public String getTicker(){
        return ticker;
    }

    public double getQuantity(){
        return qty;
    }

    public double getCostBasis(){
        return costBasis;
    }

    public String getTransDate(){
        return transDate;
    }

    public String getTransType(){
        return transType;
    }

   
}