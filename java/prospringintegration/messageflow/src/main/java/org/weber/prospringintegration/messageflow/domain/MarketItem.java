package org.weber.prospringintegration.messageflow.domain;

/**
 * Created by wxu on 5/25/2015.
 */
public class MarketItem {String type;
    String symbol;
    String description;
    String price;
    String openPrice;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }
}
