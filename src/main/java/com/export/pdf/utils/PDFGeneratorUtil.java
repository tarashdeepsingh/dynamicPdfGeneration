package com.export.pdf.utils;

import com.export.pdf.model.Invoice;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class PDFGeneratorUtil {

    private final TemplateEngine templateEngine;

    public PDFGeneratorUtil(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * Generates a PDF document from an Invoice object using an HTML template.
     * 
     * This method prepares the Thymeleaf context with the provided invoice data,
     * processes the HTML template, and converts the rendered HTML to a PDF file.
     *
     * @param invoice The Invoice object containing data to populate the PDF.
     * @param pdfFile The File object representing the destination for the generated PDF.
     * @throws IOException if an error occurs while writing to the PDF file.
     */
    public void createPdf(Invoice invoice, File pdfFile) throws IOException {
        // Prepare the Thymeleaf context with dynamic content
        Context context = new Context();
        context.setVariable("invoice", invoice);

        // Render the HTML template with dynamic content
        String htmlContent = templateEngine.process("invoice_template", context);

        // Convert HTML to PDF using iText
        try (FileOutputStream outputStream = new FileOutputStream(pdfFile)) {
            HtmlConverter.convertToPdf(htmlContent, outputStream);
        }
    }
}