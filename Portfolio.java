/*
* Class Name: IFT 210
* Author: Renay Hernandez
* Date Created: 02/26/23
* Purpose: checks if a given stock object is already present in list of stocks. 
*/
import java.util.ArrayList;

public class Portfolio {

    ArrayList<Stock> listOfStocks = new ArrayList<Stock>();

   
    private int isStockAlreadyInPortfolio(Stock stock) {
        // Get the ticker from the new Stock.
        for(int i = 0; i < listOfStocks.size(); i++) {
            if(listOfStocks.get(i).getTicker().equals(stock.getTicker())) {
                return i;
            }
        }
        return -1;
    }

    public boolean isStockAlreadyInPortfolio(String ticker) {
        // Get the ticker from the new Stock.
        for(int i = 0; i < listOfStocks.size(); i++) {
            if(listOfStocks.get(i).getTicker().equals(ticker)) {
                return true;
            }
        }
        return false;
    }
   
    public void editStock(Stock stock) {
        Stock stockToRepalce;
        if(isStockAlreadyInPortfolio(stock) > -1) {
            stockToRepalce = listOfStocks.get(isStockAlreadyInPortfolio(stock));
        } else {
            stockToRepalce = null;
        }

        if(stockToRepalce == null) {
            if(stock.getQuantity() > 0) {
                listOfStocks.add(stock);
            } else {
                // If we have fallen below owning 0 of this Stock, we don't own any, so don't add it to the list.
            }
        } else {
            Stock updatedStock = new Stock(stock.getTicker(), stockToRepalce.getQuantity() + stock.getQuantity(), stock.getCostBasis());
            listOfStocks.remove(stockToRepalce);
            if(updatedStock.getQuantity() > 0.0) {
                listOfStocks.add(updatedStock);
            }
        }
    }

   
    public void printPortfolio() {
        if(listOfStocks.size() == 0) {
            System.out.println("Nothing in portfolio");
        } else {
            for(int i = 0; i < listOfStocks.size(); i++) {
                System.out.printf("%s %11.2f %n" ,listOfStocks.get(i).getTicker(),listOfStocks.get(i).getQuantity());
            }
        }
        System.out.println("\n\n");
    }

    public double getAmmountOfTicker(String ticker) {
        for(int i = 0; i < listOfStocks.size(); i++) {
            if(listOfStocks.get(i).getTicker().equals(ticker)) {
                return listOfStocks.get(i).getQuantity();
            }
        }
        return -1;
    }
}
