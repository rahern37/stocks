/*
* Class Name: IFT 210
* Author: Renay Hernandez
* Date Created: 02/26/23
* Purpose: Creates a stock object to keep better track for portfolio
*/
public class Stock {
    String ticker;
    double quantity;
    double costBasis;

   
    Stock(String ticker, double quantity, double costBasis) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.costBasis = costBasis;
    }
    
    // Getters
    public String getTicker() {
        return ticker;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCostBasis() {
        return costBasis;
    }

    // Setters
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setCostBasis(double costBasis) {
        this.costBasis = costBasis;
    }
}
