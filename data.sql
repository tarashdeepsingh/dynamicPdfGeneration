-- Create the database
CREATE DATABASE invoice_system;

-- Use the created database
USE invoice_system;

-- Create the Invoices table
CREATE TABLE Invoices (
    id TINYINT AUTO_INCREMENT PRIMARY KEY,  -- Auto-incrementing primary key
    seller VARCHAR(255) NOT NULL,           -- Seller name
    seller_gstin VARCHAR(255) NOT NULL,     -- Seller GSTIN (tax ID)
    seller_address VARCHAR(255) NOT NULL,   -- Seller address
    buyer VARCHAR(255) NOT NULL,            -- Buyer name
    buyer_gstin VARCHAR(255) NOT NULL,      -- Buyer GSTIN (tax ID)
    buyer_address VARCHAR(255) NOT NULL,    -- Buyer address
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP  -- Timestamp of invoice creation
);

-- Create the Item table
CREATE TABLE Item (
    id TINYINT AUTO_INCREMENT PRIMARY KEY,  -- Auto-incrementing primary key for items
    name VARCHAR(255) NOT NULL,             -- Item name
    quantity VARCHAR(50) NOT NULL,          -- Quantity of the item
    rate DECIMAL(10, 2) NOT NULL,           -- Rate per unit of the item
    amount DECIMAL(10, 2) NOT NULL,         -- Total amount for the item (quantity * rate)
    invoice_id INT,                         -- Foreign key to link the item to an invoice
    FOREIGN KEY (invoice_id) REFERENCES Invoices(id) ON DELETE CASCADE  -- Cascading delete
);