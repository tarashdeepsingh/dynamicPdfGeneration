package com.export.pdf.repository;

import com.export.pdf.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice findBySellerAndBuyerAndItems_NameAndItems_QuantityAndItems_RateAndItems_Amount(
            String seller, String buyer, String itemName, String itemQuantity, Double itemRate, Double itemAmount);
}