package palindrome_seq;

import java.util.Scanner;

public class PalindromeDetector {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sequenceBuilder = new StringBuilder();

        System.out.println("Paste your FASTA format protein sequence below.");
        System.out.println("End the input with an empty line:");

       boolean headerSeen = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break; 
            if (line.startsWith(">")) {
                headerSeen = true; 
                continue;
            }
            sequenceBuilder.append(line);
        }

        String sequence = sequenceBuilder.toString().toUpperCase().replaceAll("[^A-Z]", "");

        if (sequence.isEmpty() || !headerSeen) {
            System.out.println("Invalid FASTA input. Please include a header and sequence.");
            return;
        }

        System.out.println("\nAnalyzing sequence of length " + sequence.length() + "...");
        detectPalindromicSequences(sequence);
    }

    public static void detectPalindromicSequences(String sequence) {
        boolean found = false;

        for (int len = 4; len <= 110; len++) {
            for (int i = 0; i <= sequence.length() - len; i++) {
                String sub = sequence.substring(i, i + len);
                if (isPalindrome(sub)) {
                    System.out.println("Palindrome found: " + sub + " at position " + (i + 1));
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No palindromic sequences found.");
        }
    }

    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

}
