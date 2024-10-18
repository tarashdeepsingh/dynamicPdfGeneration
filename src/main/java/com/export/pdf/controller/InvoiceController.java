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

    /**
     * Generates a PDF invoice based on the provided invoice data.
     * 
     * @param invoice The invoice object containing seller and buyer details along with items.
     * @return ResponseEntity containing the generated PDF file as a byte array,
     *         or a message if the invoice could not be generated.
     * @throws IOException if there is an error while reading the PDF file.
     */
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Downloads the generated PDF invoice based on the provided timestamp.
     * 
     * @param dateTimeStamp The timestamp used to identify the specific invoice file.
     * @return ResponseEntity containing the PDF file as a byte array, 
     *         or a message if the invoice is not found.
     * @throws IOException if there is an error while reading the PDF file.
     */
    @PostMapping("/generate")
    public ResponseEntity<Object> generateInvoicePdf(@RequestBody Invoice invoice) throws IOException {
        Object result = invoiceService.generateInvoicePdf(invoice);

        if (result instanceof String) {
            // If the result is a message, return it as a plain response
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

    @GetMapping("/download/{dateTimeStamp}")
    public ResponseEntity<Object> downloadInvoicePdf(@PathVariable String dateTimeStamp) throws IOException {
        // Construct the PDF file name based on the dateTimeStamp
        String pdfFileName = dateTimeStamp + ".pdf"; 
        File pdfFile = new File("public/invoices", pdfFileName);

        if (!pdfFile.exists()) {
            // Return 404 if the file does not exist along with a message
            return ResponseEntity
                    .status(404)
                    .body("Invoice not found"); // Returning a message
        }

        // Convert the PDF file to byte array
        byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());

        // Return the PDF file as a downloadable response
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}