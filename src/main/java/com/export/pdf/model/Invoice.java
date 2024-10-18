package com.export.pdf.model;

import java.util.List;

/**
 * Represents an invoice with seller and buyer details, 
 * as well as the items being sold.
 */
public class Invoice {
    
    /**
     * The name of the seller.
     */
    private String seller;
    
    /**
     * The GSTIN (Goods and Services Tax Identification Number) of the seller.
     */
    private String sellerGstin;
    
    /**
     * The address of the seller.
     */
    private String sellerAddress;
    
    /**
     * The name of the buyer.
     */
    private String buyer;
    
    /**
     * The GSTIN of the buyer.
     */
    private String buyerGstin;
    
    /**
     * The address of the buyer.
     */
    private String buyerAddress;
    
    /**
     * A list of items included in the invoice.
     */
    private List<Item> items;

    /**
     * Calculates the total amount for the invoice based on the items' amounts.
     *
     * @return The total amount of all items in the invoice.
     */
    public double getTotalAmount() {
        return items.stream().mapToDouble(Item::getAmount).sum();
    }

    // Getters and Setters

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerGstin() {
        return sellerGstin;
    }

    public void setSellerGstin(String sellerGstin) {
        this.sellerGstin = sellerGstin;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerGstin() {
        return buyerGstin;
    }

    public void setBuyerGstin(String buyerGstin) {
        this.buyerGstin = buyerGstin;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}