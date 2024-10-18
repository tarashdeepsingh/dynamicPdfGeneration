package com.export.pdf.service;

import com.export.pdf.model.Invoice;
import com.export.pdf.repository.InvoiceRepository;
import com.export.pdf.utils.PDFGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class InvoiceService {

    private final PDFGeneratorUtil pdfGeneratorUtil;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceService(PDFGeneratorUtil pdfGeneratorUtil) {
        this.pdfGeneratorUtil = pdfGeneratorUtil;
    }

    /**
     * Generates a PDF invoice and saves it to a designated directory.
     *
     * This method creates the necessary directory structure if it does not already exist,
     * checks for duplicate invoices in the database, and saves the invoice to the database.
     *
     * @param invoice The Invoice object containing the necessary information for the PDF.
     * @return The File object representing the generated or existing PDF file.
     * @throws IOException if an error occurs during file creation or writing.
     */
    public File generateInvoicePdf(Invoice invoice) throws IOException {
        // Check if an invoice with the same details already exists
        Invoice existingInvoice = invoiceRepository.findBySellerAndBuyerAndItems_NameAndItems_QuantityAndItems_RateAndItems_Amount(
                invoice.getSeller(),
                invoice.getBuyer(),
                invoice.getItems().get(0).getName(),
                invoice.getItems().get(0).getQuantity(),
                invoice.getItems().get(0).getRate(),
                invoice.getItems().get(0).getAmount());

        if (existingInvoice != null) {
            // If an existing invoice is found, return the existing PDF file
            File existingPdfFile = getExistingInvoiceFile(existingInvoice);
            
            if (existingPdfFile.exists()) {
                return existingPdfFile; // Return the existing PDF file if found
            } else {
                throw new IOException("PDF file for the existing invoice not found.");
            }
        }

        // Ensure that the createdAt field is set before saving the invoice
        invoice.setCreatedAt(LocalDateTime.now());

        // Create the directory structure
        String directoryPath = "public/invoices";
        File invoiceDir = new File(directoryPath);
        
        if (!invoiceDir.exists()) {
            invoiceDir.mkdirs(); // Create directories if they do not exist
        }

        // Construct the PDF file name using date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String currentTime = LocalDateTime.now().format(formatter);
        String pdfFileName = currentTime + ".pdf";

        // Define the full path for the PDF file
        File pdfFile = new File(invoiceDir, pdfFileName);

        // Generate the PDF using the utility class
        pdfGeneratorUtil.createPdf(invoice, pdfFile);

        // Save the invoice details in the database
        invoiceRepository.save(invoice);

        return pdfFile; // Return the new PDF file
    }

    /**
     * Helper method to get the PDF file based on existing invoice.
     * 
     * @param invoice The existing invoice to get the file from.
     * @return The File object of the existing invoice PDF.
     */
    private File getExistingInvoiceFile(Invoice invoice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String pdfFileName = invoice.getCreatedAt().format(formatter) + ".pdf";
        return new File("public/invoices/" + pdfFileName);
    }
}