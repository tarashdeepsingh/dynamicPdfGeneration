# PDF Generation System

## Overview
This project is a Spring Boot application that generates PDF based on user-provided data. It allows users to create, download, and retrieve invoice pdf efficiently while avoiding duplicates.

![Sample Invoice](public/invoices/20241018140847.pdf)

## Features
- Generate invoices in PDF format.
- Store generated invoices in a designated folder.
- Download existing invoices using a unique timestamp.
- Prevent duplicate invoice creation based on the same data.

## Technologies Used
- **Java 21**
- **Spring Boot** (for RESTful API development)
- **Thymeleaf** (for HTML template rendering)
- **iText** (for PDF generation)

### Prerequisites
- Java Development Kit (JDK) 21 or later
- VS Code

### Steps to Run the Project
   **Clone the Repository**:
``` bash
   git clone <repository-url>
   cd <repository-name> 
```

### API Endpoints
**Generate Invoice**
- POST `/api/invoice/generate`
- Request Body Example:
```json
{
    "seller": "XYZ Pvt. Ltd.",
    "sellerGstin": "29AABBCCDD121ZD",
    "sellerAddress": "New Delhi, India",
    "buyer": "Vedant Computers",
    "buyerGstin": "29AABBCCDD131ZD",
    "buyerAddress": "New Delhi, India",
    "items": [
        {
            "name": "Product 1",
            "quantity": "12 Nos",
            "rate": 123.00,
            "amount": 1476.00
        }
    ]
} 
```

- Response:
- Returns the generated PDF file for download.

## Project Directory Structure
``` bash
your-project-root/
├── public/
│   └── invoices/
│       ├── 20241018143000.pdf
│       ├── 20241018143500.pdf
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── export/
│   │   │           └── pdf/
│   │   │               ├── controller/
│   │   │               │   └── InvoiceController.java
│   │   │               ├── model/
│   │   │               │   ├── Invoice.java
│   │   │               │   └── Item.java
│   │   │               ├── service/
│   │   │               │   └── InvoiceService.java
│   │   │               ├── utils/
│   │   │               │    └── PDFGeneratorUtil.java
│   │   │               └── PdfApplication.java 
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── invoice-template.html
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── export/
│                   └── pdf/
│                       └── PdfApplicationTests.java
└── pom.xml

```

## Directory Structure for Invoices
- Generated invoices will be stored in the following directory:
``` bash
public/
 └── invoices/
     ├── 2024-10-18_14-30-00.pdf
     ├── 2024-10-18_14-35-00.pdf
```