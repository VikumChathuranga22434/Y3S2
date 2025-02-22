# can you give me the answers

---
I can help you with the lab exercise on creating and querying object-relational tables. Here's a breakdown of how to approach each part, along with example SQL code to get you started.

**1. Defining Object Types and Tables**

**(a) Define object types `emp_t` and `dept_t`:**

```sql
CREATE OR REPLACE TYPE emp_t AS OBJECT (
  empno CHAR(6),
  firstname VARCHAR2(12),
  lastname VARCHAR2(15),
  workdept REF dept_t, -- Reference to dept_t
  sex CHAR(1),
  birthdate DATE,
  salary NUMBER(8,2)
);
/

CREATE OR REPLACE TYPE dept_t AS OBJECT (
  deptno CHAR(3),
  deptname VARCHAR2(36),
  mgrno REF emp_t,   -- Reference to emp_t
  admrdept REF dept_t  -- Reference to dept_t
);
/
```

**(b) Create tables `OREMP` and `ORDEPT`:**

```sql
CREATE TABLE ORDEPT OF dept_t (
  deptno PRIMARY KEY
);

CREATE TABLE OREMP OF emp_t (
  empno PRIMARY KEY
);

ALTER TABLE OREMP
ADD CONSTRAINT emp_dept_fk
FOREIGN KEY (workdept) REFERENCES ORDEPT;

ALTER TABLE ORDEPT
ADD CONSTRAINT dept_mgr_fk
FOREIGN KEY (mgrno) REFERENCES OREMP;

ALTER TABLE ORDEPT
ADD CONSTRAINT dept_admr_fk
FOREIGN KEY (admrdept) REFERENCES ORDEPT;
```

**(c) Insert data into `OREMP` and `ORDEPT`:**

First, insert data with NULL references:

```sql
-- Insert into ORDEPT
INSERT INTO ORDEPT VALUES (dept_t('A00', 'SPIFFY COMPUTER SERVICE DIV.', NULL, NULL));
INSERT INTO ORDEPT VALUES (dept_t('B01', 'PLANNING', NULL, NULL));
INSERT INTO ORDEPT VALUES (dept_t('C01', 'INFORMATION CENTRE', NULL, NULL));
INSERT INTO ORDEPT VALUES (dept_t('D01', 'DEVELOPMENT CENTRE', NULL, NULL));

-- Insert into OREMP
INSERT INTO OREMP VALUES (emp_t('000010', 'CHRISTINE', 'HAAS', NULL, 'F', DATE '1953-08-14', 72750));
INSERT INTO OREMP VALUES (emp_t('000020', 'MICHAEL', 'THOMPSON', NULL, 'M', DATE '1968-02-02', 61250));
INSERT INTO OREMP VALUES (emp_t('000030', 'SALLY', 'KWAN', NULL, 'F', DATE '1971-05-11', 58250));
INSERT INTO OREMP VALUES (emp_t('000060', 'IRVING', 'STERN', NULL, 'M', DATE '1965-07-07', 55555));
INSERT INTO OREMP VALUES (emp_t('000070', 'EVA', 'PULASKI', NULL, 'F', DATE '1973-05-26', 56170));
INSERT INTO OREMP VALUES (emp_t('000050', 'JOHN', 'GEYER', NULL, 'M', DATE '1955-09-15', 60175));
INSERT INTO OREMP VALUES (emp_t('000090', 'EILEEN', 'HENDERSON', NULL, 'F', DATE '1961-05-15', 49750));
INSERT INTO OREMP VALUES (emp_t('000100', 'THEODORE', 'SPENSER', NULL, 'M', DATE '1976-12-18', 46150));
```

Now, update the tables with the correct REF values:

```sql
-- Update OREMP with workdept references
UPDATE OREMP e
SET e.workdept = (SELECT REF(d) FROM ORDEPT d WHERE d.deptno = e.empno);

-- Update ORDEPT with mgrno references
UPDATE ORDEPT d
SET d.mgrno = (SELECT REF(e) FROM OREMP e WHERE e.empno = d.deptno);

-- Update ORDEPT with admrdept references
UPDATE ORDEPT d
SET d.admrdept = (SELECT REF(ad) FROM ORDEPT ad WHERE ad.deptno = d.deptno);
```

**2. Queries Using `OREMP` and `ORDEPT` Tables**

**(a) Department name and manager's last name:**

```sql
SELECT d.deptname, d.mgrno.lastname
FROM ORDEPT d;
```

**(b) Employee number, last name, and department name:**

```sql
SELECT e.empno, e.lastname, e.workdept.deptname
FROM OREMP e;
```

**(c) Department number, department name, and administrative department name:**

```sql
SELECT d.deptno, d.deptname, d.admrdept.deptname
FROM ORDEPT d;
```

**(d) Department number, department name, administrative department name, and manager's last name of the administrative department:**

```sql
SELECT d.deptno, d.deptname, d.admrdept.deptname, d.admrdept.mgrno.lastname
FROM ORDEPT d;
```

**(e) Employee information and manager's last name and salary:**

```sql
SELECT e.empno, e.firstname, e.lastname, e.salary, e.workdept.mgrno.lastname, e.workdept.mgrno.salary
FROM OREMP e;
```

**(f) Average salary for men and women for each department:**

```sql
SELECT d.deptno, d.deptname, e.sex, AVG(e.salary)
FROM OREMP e, ORDEPT d
WHERE e.workdept.deptno = d.deptno
GROUP BY d.deptno, d.deptname, e.sex;
```

**3. SQL Script and Environment Variables**

**(a) Save queries to a file:**

Save all the queries from Exercise 2 into a text file named `script.sql` in your `Z:` drive.  Remember to end each query with a forward slash (`/`) instead of a semicolon (`;`).

**(b) Execute the script:**

In SQL*Plus, run the script using:

```sql
SQL> @Z:\script.sql
```

**(c) `SET ECHO ON/OFF`:**

* `SET ECHO ON`: Displays each SQL statement in the script as it's executed, along with the results.  This is useful for debugging and understanding what's happening.
* `SET ECHO OFF`: Suppresses the display of the SQL statements, showing only the results.

**(d) `SET TERMOUT ON/OFF`:**

* `SET TERMOUT ON`: Displays the output of the SQL statements on the screen.
* `SET TERMOUT OFF`: Suppresses the display of the output on the screen.  This is useful when you want to run a script in the background and capture the output to a file (using `SPOOL`).

**(e) `SPOOL`:**

```sql
SQL> SET TERMOUT OFF
SQL> SET ECHO ON
SQL> SPOOL Z:\prac2.out
SQL> @Z:\script.sql
SQL> SPOOL OFF
```

This sequence does the following:

1. Turns off the display of output to the screen.
2. Turns on the display of the SQL statements being executed.
3. Starts spooling (saving) all output to the file `Z:\prac2.out`.
4. Executes the SQL script.
5. Stops spooling.

The `prac2.out` file will contain both the SQL statements (because `ECHO` is `ON`) and the results of those statements.

**4. Dropping Types and Tables**

```sql
DROP TYPE emp_t FORCE;
DROP TYPE dept_t FORCE;
DROP TABLE OREMP CASCADE CONSTRAINTS;
DROP TABLE ORDEPT CASCADE CONSTRAINTS;
```

**Important Notes:**

* **Error Handling:** The provided code doesn't include error handling.  In a real-world scenario, you'd want to add error handling to gracefully handle potential issues during data insertion or updates.
* **Data Types:** Double-check the data types and sizes in your `CREATE TABLE` statements to ensure they match the data you're inserting.
* **REF Updates:** The update statements for the `REF` columns assume that the corresponding rows already exist in the referenced tables.  Make sure you insert the department and employee data in the correct order to avoid referential integrity violations.

Remember to test each step of the lab exercise carefully and consult your database documentation for more details. Good luck!

<div style="text-align: center">⁂</div>

[^1]: https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/53436387/3368e451-4c14-477c-95ad-ed67827691f7/IT3020_Prac02.pdf

