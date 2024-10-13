# Datasets
This folder hosts a variety of subfolders, each containing datasets used for specific SQL-related tasks evaluated in our study. 
Each dataset is tailored to test different aspects of SQL query handling by large language models (LLMs):
*	Equivalence Task: Contains three datasets (SDSS, SQLShare, Join-Order) used to assess LLMs' ability to determine if two syntactically different
  queries are equivalent. In this context, 'equivalence' means that the queries return the same result for all database instances, which is crucial
 	for tasks like query optimization and recommendation. The datasets include information on whether the SQL queries are equivalent, as well as the types
 	of equivalence.
* Missing Token Task: This subfolder includes datasets (SDSS, SQLShare, Join-Order) for testing the LLMs' ability to identify and correctly impute
  missing tokens in SQL queries. It provides a binary answer indicating whether an SQL query has a missing token and also includes details to identify
  the type and position of the missing token.
* Performance Prediction Task: Hosts only the SDSS dataset, which contains runtime data used to determine whether an SQL query will take a high or low amount of time to run.
* SQLToText Task: Includes the Spider dataset for the SQLToText conversion, where the results of LLMs are stored as they generate a natural language
  description of what a given SQL query does. 
* Syntax Error Task: Contains three datasets (SDSS, SQLShare, Join-Order) for identifying advanced syntax errors that go beyond simple mistakes,
  such as missing parentheses, to more complex errors like misalignment of attributes across SQL clauses or inappropriate attribute types in joins.
  This assesses a deeper level of syntactic and semantic understanding of SQL by LLMs. It includes a binary answer indicating whether an SQL query
  has any syntax error, and also details the types of errors.
