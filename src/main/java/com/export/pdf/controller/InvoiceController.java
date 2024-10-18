package com.export.pdf.controller;

import com.export.pdf.model.Invoice;
import com.export.pdf.service.InvoiceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Generates a PDF invoice based on the provided invoice data.
     * If an invoice with the same details already exists, it will return the existing invoice for download.
     * 
     * @param invoice The invoice object containing seller and buyer details along with items.
     * @return ResponseEntity containing the generated or existing PDF file as a byte array,
     *         or a message if the invoice could not be generated.
     * @throws IOException if there is an error while reading the PDF file.
     */
    @PostMapping("/generate")
    public ResponseEntity<Object> generateInvoicePdf(@RequestBody Invoice invoice) throws IOException {
        // Check if an invoice already exists and generate it if not
        Object result = invoiceService.generateInvoicePdf(invoice);

        if (result instanceof String) {
            // If the result is a message, it means the invoice already exists
            return ResponseEntity.ok(result);
        }

        // If result is a File, return the PDF content
        File pdfFile = (File) result;
        byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());

        // Return the PDF file as a downloadable response
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}