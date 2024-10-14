# Overview
This repository contains a collection of tools and datasets designed to evaluate the understanding of SQL queries by large language models (LLMs). Our focus is on various aspects of SQL processing, including syntax error detection, missing token identification, query performance prediction, and query equivalence checking and SQL to Test Conversion.
### What's inside the repository:
* Codes: Jupyter Notebooks with scripts for syntax error evaluation, missing token identification, and more.
* Datasets: Includes datasets used for different SQL-related tasks to assess model performance.


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

## Objectives

The main objective of this repository is to provide a comprehensive suite of SQL tasks that help in evaluating the proficiency of LLMs in understanding and manipulating SQL at an advanced level. These tasks are designed to push the boundaries of what artificial intelligence can achieve in the realm of database querying and management.

## Contributing

Contributors are welcome to suggest improvements, add new tasks, or enhance the evaluation methods for existing tasks. Please fork this repository and submit pull requests with your proposals.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
