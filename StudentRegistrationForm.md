The Student Registration Form is a comprehensive data entry tool designed for academic institutions. It features a split-pane interface where the left side is dedicated to a structured input form and the right side acts as a live data preview area. The system is built to ensure data integrity by enforcing strict validation rules before any record is finalized.
Key features
	Comprehensive Data Capture: Collects personal information including name, email, date of birth, gender, and academic department.
	Dynamic Date Selection: Automatically calculates the correct number of days in a month based on the selected year and month (including leap year support).
	Robust Input Validation:
•	Email Verification: Checks for correct email formatting and ensures confirmation fields match.
•	Password Security: Enforces a length of 8–20 characters and requires a mix of letters and digits.
•	Age Eligibility: Restricts registration to individuals between the ages of 16 and 60.
	Automated ID Generation: Generates a unique student ID based on the current year and a sequential counter (e.g., 2026-00002).
	Persistent Storage: Successfully submitted records are appended to a local students.csv file for external spreadsheet analysis.

Technical specifications
o	GUI Library: javax.swing and java.awt.
o	Layout Management: Uses GridBagLayout for precise alignment of form fields and BorderLayout for the overall frame structure.
o	Data Validation: Utilizes Regular Expressions (Regex) for email and password pattern matching.
o	File I/O: Implements PrintWriter and FileWriter to handle comma-separated values (CSV) logging.

How to Run
a)	Environment Setup: Ensure you have the Java Development Kit (JDK) installed.
b)	Compilation: javac StudentRegistrationForm.java
c)	Execution: java uganda.studentregistrationform.StudentRegistrationForm
d)	Submission: Fill out the required fields. If successful, the data will appear in the right-hand preview box and be saved to students.csv. If errors exist, they will be highlighted in the preview area.

