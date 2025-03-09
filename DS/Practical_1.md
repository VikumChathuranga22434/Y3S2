<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" class="logo" width="120"/>

# This practical is aimed at refreshing your previous knowledge of relational SQL and familiarity with

Oracle. It would be a good idea to write down the SQL before coming to the practical and also bring
along a softcopy if you want to save time at the practical session.

1. Use the following relational database tables for recording the information shown below. Primary
keys are underlined. (Assume each client is given a client number for unique reference.)
client(clno: char(3), name: varchar(12), address: varchar(30))
stock(company: char(7), price: number(6,2), dividend: number(4,2), eps: number(4,2))
trading(company: char(7), exchange: varchar(12))
purchase(clno: char(3), company: char(7), pdate: date, qty: number(6), price: number(6,2))
2. Create the relational tables in Oracle with suitable primary and foreign keys, and insert the sample
data given below.
For example, to create the client table, you can use:
Create table client (clno char(3), name varchar(12), address varchar(30), primary key(clno));
Similarly, you can insert a client by using (assuming the first client number is c01):
Insert into client values ('c01', 'John Smith', '3 East Av, Bentley, WA 6102');
---
To create the required relational database tables in Oracle, you can use the following SQL commands. Each table includes primary keys and relevant data types as specified in your query.

### SQL Commands to Create Tables

1. **Create the Client Table**

```sql
CREATE TABLE client (
    clno CHAR(3),
    name VARCHAR(12),
    address VARCHAR(30),
    PRIMARY KEY (clno)
);
```

2. **Create the Stock Table**

```sql
CREATE TABLE stock (
    company CHAR(7),
    price NUMBER(6,2),
    dividend NUMBER(4,2),
    eps NUMBER(4,2),
    PRIMARY KEY (company)
);
```

3. **Create the Trading Table**

```sql
CREATE TABLE trading (
    company CHAR(7),
    exchange VARCHAR(12),
    PRIMARY KEY (company)
);
```

4. **Create the Purchase Table**

```sql
CREATE TABLE purchase (
    clno CHAR(3),
    company CHAR(7),
    pdate DATE,
    qty NUMBER(6),
    price NUMBER(6,2),
    PRIMARY KEY (clno, company, pdate),
    FOREIGN KEY (clno) REFERENCES client(clno),
    FOREIGN KEY (company) REFERENCES stock(company)
);
```


### Sample Data Insertion

You can insert sample data into these tables using the following commands:

1. **Insert Sample Data into Client Table**

```sql
INSERT INTO client VALUES ('c01', 'John Smith', '3 East Av, Bentley, WA 6102');
INSERT INTO client VALUES ('c02', 'Jane Doe', '45 West St, Perth, WA 6000');
```

2. **Insert Sample Data into Stock Table**

```sql
INSERT INTO stock VALUES ('AAPL', 150.00, 0.85, 5.00);
INSERT INTO stock VALUES ('GOOGL', 2800.00, 0.00, 25.00);
```

3. **Insert Sample Data into Trading Table**

```sql
INSERT INTO trading VALUES ('AAPL', 'NASDAQ');
INSERT INTO trading VALUES ('GOOGL', 'NASDAQ');
```

4. **Insert Sample Data into Purchase Table**

```sql
INSERT INTO purchase VALUES ('c01', 'AAPL', TO_DATE('2025-02-21', 'YYYY-MM-DD'), 10, 150.00);
INSERT INTO purchase VALUES ('c02', 'GOOGL', TO_DATE('2025-02-21', 'YYYY-MM-DD'), 5, 2800.00);
```


### Notes

- Ensure that you have the necessary privileges to create tables in your Oracle database schema.
- Adjust the sample data as needed based on your requirements.
- The `TO_DATE` function is used to insert date values in Oracle SQL.

This setup will allow you to manage client information, stock details, trading information, and purchase records effectively within your relational database environment.

<div style="text-align: center">⁂</div>

[^1]: https://docs.oracle.com/en/database/oracle/oracle-database/21/sqlrf/CREATE-TABLE.html

[^2]: https://blog.devart.com/how-to-create-table-in-oracle.html

[^3]: https://www.knack.com/blog/how-to-design-an-effective-relational-database/

[^4]: https://www.oracle.com/database/what-is-a-relational-database/

[^5]: https://www.atlassian.com/data/admin/how-to-list-all-tables-in-oracle

[^6]: https://docs.oracle.com/en/database/oracle/oracle-database/18/cncpt/introduction-to-oracle-database.html

[^7]: https://docs.oracle.com/cd/F49540_01/DOC/server.815/a67772/create.htm

[^8]: https://www.techtarget.com/searchdatamanagement/definition/relational-database

