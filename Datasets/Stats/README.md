## Overview
This directory contains four CSV files that provide statistical analyses of SQL queries from different datasets used in our experiments. 
Each file documents metrics such as Query_length/char_count, word_count, Query_Type, Aggregate, Table_Count, join_count, Nestedness_Level, Column_Count, 
Function_Count, and predicate_count. These metrics help understand the structural and syntactic properties of the queries, aiding in the evaluation 
of LLM performance on SQL tasks.
### Files Overview
* sdss_stats.csv: Statistics for the Sloan Digital Sky Survey (SDSS) dataset queries. We used 285 queries from 2023.
* sqlshare_stats.csv: Statistics for queries from the SQLShare dataset.
* join-order_stats.csv: Statistics of queries from the Join-Order Benchmark.
* spider_stats.csv: Statistical details for queries from the Spider Benchmark.
