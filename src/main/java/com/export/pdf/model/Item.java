package com.export.pdf.model;

/**
 * Represents an item in an invoice with its details such as name, quantity, rate, and amount.
 */
public class Item {
    
    /**
     * The name of the item.
     */
    private String name;
    
    /**
     * The quantity of the item being sold.
     */
    private String quantity;
    
    /**
     * The rate per unit of the item.
     */
    private Double rate;
    
    /**
     * The total amount for the item (quantity * rate).
     */
    private Double amount;

    /**
     * Gets the name of the item.
     * 
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     * 
     * @param name The name to set for the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity of the item.
     * 
     * @return The quantity of the item.
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * 
     * @param quantity The quantity to set for the item.
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the rate of the item.
     * 
     * @return The rate of the item.
     */
    public Double getRate() {
        return rate;
    }

    /**
     * Sets the rate of the item.
     * 
     * @param rate The rate to set for the item.
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**
     * Gets the total amount for the item.
     * 
     * @return The total amount of the item.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the total amount for the item.
     * 
     * @param amount The amount to set for the item.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}