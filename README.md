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

## Objectives

The main objective of this repository is to provide a comprehensive suite of SQL tasks that help in evaluating the proficiency of LLMs in understanding and manipulating SQL at an advanced level. These tasks are designed to push the boundaries of what artificial intelligence can achieve in the realm of database querying and management.

