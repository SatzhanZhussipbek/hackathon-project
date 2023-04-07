CREATE TABLE if not exists General_user
(
    id IDENTITY,
    name NVARCHAR(80) NOT NULL,
    password NVARCHAR(256) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE if not exists Invoice
(
    id NVARCHAR(80),
    created_at NVARCHAR(80) NOT NULL,
    payment_due NVARCHAR(80) NOT NULL,
    payment_terms BIGINT NOT NULL,
    description NVARCHAR(256) NOT NULL,
    client_name NVARCHAR(80) NOT NULL,
    client_email NVARCHAR(80) NOT NULL,
    status NVARCHAR(40) NOT NULL,
    sender_address OBJECT,
    client_address OBJECT,
    total DOUBLE NOT NULL,
    PRIMARY KEY(id)
);

Create table if not exists Invoice_item
(
    id IDENTITY,
    name NVARCHAR(80) NOT NULL,
    quantity BIGINT NOT NULL,
    price DOUBLE NOT NULL,
    total DOUBLE NOT NULL,
    PRIMARY KEY(id)
)