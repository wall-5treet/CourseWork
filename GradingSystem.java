/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package uganda.gradingsystem;

/**
 * @Kidula Mark
 * @Wadaba Alex
 * @Nannono Sumayiya
 * @Kayiira Tonny
 * @Othman Kasozi
 */
 import java.util.Scanner;
public class GradingSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Array to store the count for each grade (index 1 to 9)
        // We use size 10 so that index 9 is accessible
        int[] gradeCounts = new int[10];
        
        int studentCount = 1;
        int totalStudents = 5;

        System.out.println("--- Physics Quiz Grading System ---");

        // i) Process scores for five students using a while loop
        while (studentCount <= totalStudents) {
            System.out.print("\nEnter score for student " + studentCount + " (0-100): ");
            int score = input.nextInt();

            // Validate input
            if (score < 0 || score > 100) {
                System.out.println("Invalid score! Please enter a value between 0 and 100.");
                continue; // Skip the rest of the loop and try again for the same student
            }

            int grade;
            String remark;

            // ii) Determine grade and remark using if-else-if
            if (score >= 80) { grade = 1; remark = "D1"; }
            else if (score >= 75) { grade = 2; remark = "D2"; }
            else if (score >= 66) { grade = 3; remark = "C3"; }
            else if (score >= 60) { grade = 4; remark = "C4"; }
            else if (score >= 50) { grade = 5; remark = "C5"; }
            else if (score >= 45) { grade = 6; remark = "C6"; }
            else if (score >= 35) { grade = 7; remark = "P7"; }
            else if (score >= 30) { grade = 8; remark = "P8"; }
            else { grade = 9; remark = "F"; }

            // iii) Display results
            System.out.println("Score: " + score + " | Grade: " + grade + " | Remark: " + remark);

            // Increment the count for this specific grade in our summary array
            gradeCounts[grade]++;
            
            studentCount++;
        }

        // a-ii) Display Summary
        System.out.println("\n--- Final Summary ---");
        for (int i = 1; i <= 9; i++) {
            System.out.println("Grade " + i + ": " + gradeCounts[i] + " student(s)");
        }
        
        input.close();
    }
}