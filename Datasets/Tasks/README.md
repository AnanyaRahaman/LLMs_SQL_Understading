# Datasets

This repository contains a collection of subfolders, each hosting datasets used for specific SQL-related tasks evaluated in our study. Each dataset is designed to test different aspects of SQL query handling by large language models (LLMs). Below are the detailed descriptions of the tasks and the datasets associated with them:

## Equivalence Task

- **Datasets Included:** SDSS, SQLShare, Join-Order
- **Description:** These datasets are used to assess LLMs' ability to determine if two syntactically different queries are equivalent. Here, 'equivalence' refers to the queries returning the same result for all database instances. This is crucial for tasks like query optimization and recommendation. The datasets include information on whether the SQL queries are equivalent and the types of equivalence assessed.

## Missing Token Task

- **Datasets Included:** SDSS, SQLShare, Join-Order
- **Description:** This subfolder includes datasets for testing LLMs' ability to identify and correctly impute missing tokens in SQL queries. It provides a binary answer indicating whether an SQL query has a missing token and details the type and position of the missing token.

## Performance Prediction Task

- **Datasets Included:** SDSS
- **Description:** This dataset contains runtime data used to determine whether an SQL query will take a high or low amount of time to run.

## SQLToText Task

- **Datasets Included:** Spider
- **Description:** This task involves the SQLToText conversion, where the results of LLMs are stored as they generate a natural language description of what a given SQL query does.

## Syntax Error Task

- **Datasets Included:** SDSS, SQLShare, Join-Order
- **Description:** This task involves identifying advanced syntax errors that go beyond simple mistakes, such as missing parentheses, to more complex errors like misalignment of attributes across SQL clauses or inappropriate attribute types in joins. This assesses a deeper level of syntactic and semantic understanding of SQL by LLMs. It includes a binary answer indicating whether an SQL query has any syntax error, and also details the types of errors.
