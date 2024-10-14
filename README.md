# Repository Overview

This repository contains all the resources used and generated for our analysis of the effects of statistical properties on various SQL tasks using Language Learning Models (LLMs).

## Directory and File Descriptions

### Code
This directory houses the essential scripts and utilities for our project:
- **ANTLR**: Contains ANTLR-related files and scripts which are crucial for parsing and analyzing SQL queries.
- **llms_interaction.ipynb**: A Jupyter notebook that demonstrates the interaction with different language models including GPT-4, GPT-3.5, Llama3, Mistralai, and Gemini, showcasing their handling of various SQL tasks.

### Datasets
This directory contains two folders and a Jupyter notebook:
- **Stats**: Holds statistical data and analyses relevant to the SQL tasks we evaluated.
- **Tasks**: Contains datasets and resources specific to each SQL task such as Syntax Error Identification, Missing Token Identification, Performance Prediction, and Query Equivalence.
- **datasets_stats.ipynb**: A Jupyter notebook that provides detailed statistical evaluations used in our research, analyzing the impact of statistical properties on various SQL tasks.

### results.ipynb
This Jupyter notebook presents the results of our analysis, showing the impact of statistical properties on SQL tasks such as Syntax Error Identification, Missing Token Identification, Performance Prediction, and Query Equivalence across different datasets. Results are shown for five LLMs: GPT-4, GPT-3.5, Llama3, Mistralai, and Gemini. While not all figures made it into the paper, those included best illustrate the significant relationships or their absence among the variables studied.

# SQL Task Evaluation in LLMs

This repository is dedicated to evaluating the capability of Language Learning Models (LLMs) to handle complex SQL tasks. Our research investigates several tasks that showcase an LLM's ability to understand and manipulate SQL queries beyond basic syntax checking.

## SQL Related Tasks

### 1. Syntax Error Identification
This task assesses the ability of LLMs to identify advanced syntax errors that impact the structure and semantics of SQL queries. Unlike simple mistakes (such as missing parentheses), advanced errors include misalignment of attributes and functions among `SELECT`, `GROUP BY`, `HAVING` clauses, type mismatches in joins, and invalid join operations. This evaluation reflects the depth of an LLM’s SQL "understanding."

### 2. Missing Token Identification
The ability to identify and precisely locate missing tokens in SQL queries is crucial for query recommendation systems and auto-completion functionalities. This task evaluates not just the recognition but also the pinpointing of the exact type and location of missing tokens, facilitating enhancements in query-building applications.

### 3. Query Performance Estimation
Estimating the runtime performance of SQL queries based solely on their text is challenging due to influences like database schema, data specifics, and query workload. We explore how well LLMs can predict the execution costs of queries, particularly those that are complex, involve multiple joins, or have several predicate conditions.

### 4. Query Equivalence
Two queries that yield identical results across all database instances are considered equivalent, crucial for query optimization and recommendation. This task involves evaluating LLMs with labeled query pairs (equivalent and non-equivalent) to determine their effectiveness in recognizing query equivalence, thus aiding in simpler and faster query execution strategies.

### 5. Query Explainability
We assess LLMs on their ability to explain SQL queries by describing their expected outputs. This task is akin to generating documentation for code or captions for images, measuring the model's depth of understanding. Our evaluation includes a range of complex queries involving multiple tables, nested subqueries, and intricate logical conditions.

# Query Equivalence and Non-Equivalence

## Query Equivalence Types

Due to page limitations in our paper, we were unable to include detailed discussions on various types of query equivalence transformations. Here, we outline several key transformations used to test the capability of LLMs to understand and manipulate SQL queries:

1. **Swapping Nestedness** ("Swapping_Nestedness")
   - Changes the order of nestedness in queries to evaluate flexibility in understanding different query structures.
   - **Example**:
     ```sql
     -- Original Query
     SELECT customer_id
     FROM Orders
     WHERE customer_id IN (
         SELECT customer_id
         FROM Customers
         WHERE country = 'USA'
     );
     
     -- Equivalent Query
     SELECT customer_id
     FROM Customers
     WHERE country = 'USA'
     AND customer_id IN (
         SELECT customer_id
         FROM Orders
     );
     ```

2. **Changing Join Order** ("Join_Order")
   - Assesses the model's understanding of join operations by altering the order of tables in the join clause.
   - **Example**:
     ```sql
     -- Original Query
     SELECT first_name, last_name FROM employees, department WHERE department_id = 10;
     
     -- Equivalent Query
     SELECT first_name, last_name FROM department, employees WHERE department_id = 10;
     ```

3. **Converting Joins** ("Explicit_Implicit_Joins")
   - Tests the model's ability to interpret and convert between explicit and implicit join syntax.
   - **Example**:
     ```sql
     -- Original Query
     SELECT first_name, last_name FROM employees, department WHERE department_id = 10;
     
     -- Equivalent Query
     SELECT e.first_name, e.last_name FROM employees e JOIN departments d ON e.department_id = d.department_id WHERE d.department_id = 10;
     ```

4. **Using Subqueries** ("Join_Nested", "Condition_Nested")
   - Evaluates the model's capability to interchange joins and nested queries to achieve the same result.
   - **Join to Nested Example**:
     ```sql
     -- Original Query
     SELECT e.EmployeeName, e.EmployeeID
     FROM Employees e
     JOIN Departments d ON e.DepartmentID = d.DepartmentID
     WHERE d.DepartmentName = 'Sales';
     
     -- Equivalent Query
     SELECT EmployeeName, EmployeeID
     FROM Employees
     WHERE DepartmentID IN (
         SELECT DepartmentID
         FROM Departments
         WHERE DepartmentName = 'Sales'
     );
     ```
   - **Condition to Nested Example**:
     ```sql
     -- Original Query
     SELECT first_name, last_name FROM employees, department WHERE department_id = 10;
     
     -- Equivalent Query
     SELECT first_name, last_name FROM employees WHERE department_id = (SELECT department_id FROM departments WHERE department_id = 10);
     ```

5. **Using Common Table Expressions (CTEs)** ("CTEs")
   - Examines the model's proficiency in utilizing CTEs to organize complex queries.
   - **Example**:
     ```sql
     -- Original Query
     SELECT text 
     FROM DBObjects 
     WHERE name = 'PhotoTag'
     
     -- Equivalent Query
     WITH FilteredObjects AS (
         SELECT text
         FROM DBObjects
         WHERE name = 'PhotoTag'
     )
     SELECT text
     FROM FilteredObjects
     ```

6. **Using UNION or INTERSECT ALL** ("Condition_IntersectUnion")
   - Tests understanding of set operations and their implications on query results.
   - **Example**:
     ```sql
     -- Original Query
     SELECT employee_id, name, department, salary
     FROM employees
     WHERE department = 'Sales' OR salary > 50000;
     
     -- Equivalent Query
     SELECT employee_id, name, department, salary
     FROM employees
     WHERE department = 'Sales'
     UNION
     SELECT employee_id, name, department, salary
     FROM employees
     WHERE salary > 50000;
     ```

7. **Using EXISTS** ("Condition_EXISTs")
   - Checks the model's ability to handle EXISTS logical operator within subqueries to filter results.
   - **Example**:
     ```sql
     -- Original Query
     SELECT DISTINCT d.department_name
     FROM departments d
     JOIN employees e ON d.department_id = e.department_id;
     
     -- Equivalent Query
     SELECT department_name
     FROM departments d
     WHERE EXISTS (
         SELECT 1
         FROM employees e
         WHERE e.department_id = d.department_id
     );
     ```

8. **Using CASE Statements** ("Case_Statement")
   - Assesses the handling of conditional logic within queries to produce dynamic results.
   - **Example**:
     ```sql
     -- Original Query
     SELECT p.product_id,
            p.price,
            p.category,
            p.price * (1 - d.discount_rate) AS sale_price
     FROM products p
     LEFT JOIN discounts d
     ON p.category = d.category;
     
     -- Equivalent Query
     SELECT product_id,
            price,
            category,
            CASE
                WHEN category = 'Electronics' THEN price * 0.90  -- 10% discount
                WHEN category = 'Clothing' THEN price * 0.85     -- 15% discount
                WHEN category = 'Books' THEN price * 0.80        -- 20% discount
                ELSE price                                      -- No discount
            END AS sale_price
     FROM products;
     ```

9. **Simplification and Direct Application** ("Simplification")
   - Focuses on simplifying complex queries while maintaining the same logical conditions and outputs.
   - **Example**:
     ```sql
     -- Original Query
     SELECT * max(salary), min(salary), avg(salary), count(*) 
     FROM (
         SELECT Name, [Job Title] as job_title, [2010 Gross Earnings] as salary
         FROM [1314howe].[uw_salaries_2011.txt]
     ) x 
     WHERE job_title LIKE '%RESEAR%SR' 
     AND job_title NOT LIKE '%APL%' 
     AND salary > 100000 
     ORDER BY salary desc;
     
     -- Equivalent Query
     SELECT MAX(salary) AS max_salary, 
            MIN(salary) AS min_salary, 
            AVG(salary) AS avg_salary, 
            COUNT(*) AS total_count
     FROM [1314howe].[uw_salaries_2011.txt]
     WHERE [Job Title] LIKE '%RESEAR%SR' 
     AND [Job Title] NOT LIKE '%APL%' 
     AND [2010 Gross Earnings] > 100000;
     ```

10. **Reordering the Conditions**
    - Tests whether the model maintains query integrity despite reordering of WHERE clause conditions.
    - **Example**:
      ```sql
      -- Original Query
      SELECT * FROM Orders
      WHERE CustomerID = 1234 AND OrderDate > '2020-01-01' AND Status = 'Shipped';
      
      -- Equivalent Query
      SELECT * FROM Orders
      WHERE Status = 'Shipped' AND OrderDate > '2020-01-01' AND CustomerID = 1234;
      ```

## Creating Non-Equivalent Queries

We also explored generating non-equivalent queries to further challenge the LLMs. These tests include:

1. **Using Different Aggregate Functions** ("Aggregate_Function")
   - Modifies aggregate functions to observe changes in group results.
   - **Example**:
     ```sql
     -- Original Query
     SELECT department, AVG(salary) FROM employees GROUP BY department;
     
     -- Non-equivalent Query
     SELECT department, SUM(salary) FROM employees GROUP BY department;
     ```

2. **Changing Join Conditions** ("Change_Join_Condition")
   - Alters join types to test how join conditions affect the results.
   - **Example**:
     ```sql
     -- Original Query
     SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.id;
     
     -- Non-equivalent Query
     SELECT * FROM orders LEFT JOIN customers ON orders.customer_id = customers.id;
     ```

3. **Modifying Grouping Criteria** ("Group_by_Criteria")
   - Changes grouping fields and observes its impact on aggregation.
   - **Example**:
     ```sql
     -- Original Query
     SELECT city, COUNT(*) FROM employees GROUP BY city;
     
     -- Non-equivalent Query
     SELECT state, city, COUNT(*) FROM employees GROUP BY state, city;
     ```

4. **Introducing Subtle Changes**
   - Includes logical conditions, value changes, and operator modifications to assess sensitivity to query specifics.
   - **Logical Conditions Example**:
     ```sql
     -- Original Query
     SELECT * FROM products WHERE price < 20 AND quantity > 100;
     
     -- Non-equivalent Query
     SELECT * FROM products WHERE price < 20 OR quantity > 100;
     ```
   - **Changing Values Example**:
     ```sql
     -- Original Query
     SELECT * FROM employees WHERE salary > 50000;
     
     -- Non-equivalent Query
     SELECT * FROM employees WHERE salary > 500000;
     ```
   - **Operators Example**:
     ```sql
     -- Original Query
     SELECT * FROM employees WHERE salary > 50000;
     
     -- Non-equivalent Query
     SELECT * FROM employees WHERE salary >= 50000;
     ```

5. **Changing Sorting and Limiting** ("Change_Sorting_Limiting")
   - Varies ordering and pagination to see its effect on the query output.
   - **Example**:
     ```sql
     -- Original Query
     SELECT * FROM employees ORDER BY hire_date DESC LIMIT 10;
     
     -- Non-equivalent Query
     SELECT * FROM employees ORDER BY hire_date ASC LIMIT 10;
     ```

6. **Modifying Order By Criteria** ("Order_by_Column")
   - Adjusts sorting criteria to test the model's understanding of order significance.
   - **Example**:
     ```sql
     -- Original Query
     SELECT city, COUNT(*) FROM employees Order BY city;
     
     -- Non-equivalent Query
     SELECT state, city, COUNT(*) FROM employees Order BY state, city;
     ```

7. **Ambiguous Column References and Table Name Changes**
   - Introduces errors in column references and table names to evaluate error detection capabilities.
   - **Ambiguous Column References Example**:
     ```sql
     -- Original Query
     SELECT EmployeeName, Department, Salary, HireDate, Position, Email
     FROM Employees;
     
     -- Non Equivalent Query
     SELECT EmployeeName, Department, Salary, HireDate, Position
     FROM Employees;
     ```
   - **Change Table Name Example**:
     ```sql
     -- Original Query
     SELECT EmployeeID, EmployeeName, Department, Salary
     FROM Employees;
     
     -- Non Equivalent Query
     SELECT EmployeeID, EmployeeName, Department, Salary
     FROM Employee;
     ```

These categories help in thoroughly evaluating the adaptability and accuracy of LLMs in SQL query manipulation and understanding.
