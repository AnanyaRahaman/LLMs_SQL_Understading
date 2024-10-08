# Overview
This repository contains a collection of tools and datasets designed to evaluate the understanding of SQL queries by large language models (LLMs). Our focus is on various aspects of SQL processing, including syntax error detection, missing token identification, query performance prediction, and query equivalence checking.
### What's inside the repository:
* ANTLR: Uses ANTLR files for parsing SQL queries to check their syntactic correctness.
* Codes: Jupyter Notebooks with scripts for syntax error evaluation, missing token identification, and more.
* Datasets: Includes datasets used for different SQL-related tasks to assess model performance.
* Stats: Contains statistical analysis files detailing the properties of SQL queries.
* Queries Equivalence and Syntax Error Types: Documentation on methods for assessing query Equivalence and Types of Sybtax Error.
### SQL Related Tasks: 
* Syntax erroridentification: The ability to detect advanced syntax errors that violate structural and semantic requirements rather than
basic errors (e.g., missing parentheses, quotation marks) shows how much LLMs “understand” SQL. For example, detecting the misalignment of attributes and aggregation functions among SELECT,
GROUP BY, HAVING clauses, incompatible attribute types between
outer and inner (sub-)queries, and invalid join operations requires
a complex “understanding” of the queries.
* Missing token identification: Identifying missing tokens is a crucial
pre-step for applications such as query recommendation, where
missing token imputation and query auto-completion are key functionalities. We evaluate the ability to not only recognize a
missing token but to identify the precise location and the type of
missing token (e.g., FeiComment: Can we give examples here of
the types?).
* Query performance estimation: Given only the SQL query text,
accurately estimating its runtime performance is challenging, as
multiple factors such as the database schema, specific data instances,
and the query workload all play a role . Using publicly available
query workloads, recent work has shown that more complex, longer
queries with multiple joins and multiple predicate conditions incur
higher execution costs.
* Query equivalence: Two syntactically different queries are considered equivalent if they return the same result for all database
instances. This is important for query optimization, and
query recommendation, where simpler query representations
can facilitate faster execution times. We evaluate query equivalence
using labelled, equivalent (positive), and non-equivalent (negative)
query pairs, where the latter is not as straightforward. 
* Query explainability: We evaluate LLMs to explain SQL queries
by describing what a given query returns. This task is similar to
assessments in code and image understanding, to generate code
documentation and image captions, respectively, to measure
understanding. We evaluate over a wide range of complex queries,
including multiple tables, nested subqueries, and intricate logical
conditions.


