Project overview
The program automates the process of evaluating student scores, assigning specific academic grades and remarks, and generating a statistical summary of performance.
The Grading System application processes results for a fixed batch of five students. It utilizes a controlled loop to capture inputs and uses a multi-tier conditional structure to categorize performance based on standard grading scales.

Key features
	Iterative Processing
	 Uses a while loop to process exactly five student records sequentially.
	Input Validation: Ensures that all entered scores fall within the logical range of 0 to 100; invalid entries trigger an error message and a retry.
	Grading Logic: Implements a detailed if-else-if ladder to assign grades (1–9) and remarks (e.g., D1, C3, P7, F).
	Summary Statistics: Utilizes an array (grade Counts) to keep a running tally of how many students achieved each specific grade.

 Grading scale reference
The system evaluates scores based on the following threshold table:

Score Range	Grade	Remark
80 - 100	1	D1 (Distinction)
75 - 79	2	D2 (Distinction)
66 - 74	3	C3 (Credit)
60 - 65	4	C4 (Credit)
50 - 59	5	C5 (Credit)
45 - 49	6	C6 (Credit)
35 - 44	7	P7 (Pass)
30 - 34	8	P8 (Pass)
0 - 29	9	F (Fail)





