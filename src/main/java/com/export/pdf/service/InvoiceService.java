package com.export.pdf.service;

import com.export.pdf.model.Invoice;
import com.export.pdf.utils.PDFGeneratorUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class InvoiceService {

    private final PDFGeneratorUtil pdfGeneratorUtil;

    public InvoiceService(PDFGeneratorUtil pdfGeneratorUtil) {
        this.pdfGeneratorUtil = pdfGeneratorUtil;
    }

    /**
     * Generates a PDF invoice and saves it to a designated directory.
     *
     * This method creates the necessary directory structure if it does not already exist,
     * constructs a PDF file name based on the current date and time, 
     * and uses the PDFGeneratorUtil to create the PDF file.
     *
     * @param invoice The Invoice object containing the necessary information for the PDF.
     * @return The File object representing the generated PDF file.
     * @throws IOException if an error occurs during file creation or writing.
     */
    public File generateInvoicePdf(Invoice invoice) throws IOException {
        // Create the directory structure
        String directoryPath = "public/invoices"; // Adjust the path as necessary
        File invoiceDir = new File(directoryPath);
        
        if (!invoiceDir.exists()) {
            invoiceDir.mkdirs(); // Create directories if they do not exist
        }

        // Construct the PDF file name using date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String currentTime = LocalDateTime.now().format(formatter);
        String pdfFileName = currentTime + ".pdf"; // Naming convention as dateTime.pdf

        // Define the full path for the PDF file
        File pdfFile = new File(invoiceDir, pdfFileName);

        // Generate the PDF using the utility class
        pdfGeneratorUtil.createPdf(invoice, pdfFile);

        return pdfFile;
    }
}