import java.util.*;
import java.io.*;

public class KeywordCount {

    public static void main(String[] args) throws Exception {
        
        // check if user provided a file name when running the program
        if (args.length != 1) {
            System.out.println("Usage: java KeywordCount <sourceFile>");
            System.exit(1); // Stop the program if file name isn't provided
        }

        // create a File object from the provided file name
        File file = new File(args[0]);
        
        // check if the file actually exists on the system
        if (file.exists()) {
            // If the file is found, count the keywords and display the result
            System.out.println("The number of keywords in " + args[0] 
                + " is " + countKeywords(file));
        } else {
            // if the file isn't found, notify the user
            System.out.println("File " + args[0] + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
        // array containing all Java keywords + true, false and null
        String[] keywordString = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"};

        // convert the array of keywords into a set for faster searches
        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0; // Counter for the number of keywords found

        // create a scanner to read the file word by word
        Scanner input = new Scanner(file);

        // continue reading while there are more words in the file
        while (input.hasNext()) {
            String word = input.next(); // Read the next word

            // if find a line comment, skip reading until the end of the line
            if (word.startsWith("//")) {
                input.nextLine();
            }
            // if find a string, skip reading until the end of the string
            else if (word.startsWith("\"")) {
                while (!word.endsWith("\"")) {
                    word = input.next();
                }
            }
            // if find the start of a block comment, skip reading until the end of the comment
            else if (word.startsWith("/*")) {
                while (!word.endsWith("*/")) {
                    word = input.next();
                }
            }
            // if the word is one of our keywords, increase our count
            else if (keywordSet.contains(word)) {
                count++;
            }
        }

        // return the final count of keywords found
        return count;
    }
}

