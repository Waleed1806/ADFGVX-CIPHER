
import java.io.*;
import java.util.*;

public class Runner {

    // Directory Validation
    private static boolean isValidDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.exists() && directory.isDirectory();
    }

    // Key Validation
    private static boolean isValidCipherKey(String key) {
        return key != null && !key.isEmpty();
    }

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String inputDirectory = null;
        String outputDirectory = null;
        String cipherKey = null;
        boolean encrypt = true;

        System.out
                .println(ConsoleColour.PURPLE_BOLD + "\n************************************************************");
        System.out.println("*       ATU - Dept. Computer Science & Applied Physics     *");
        System.out.println("*                                                          *");
        System.out.println("*                   ADFGVX File Encryption                 *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************" + ConsoleColour.RESET);

        while (true) {

            System.out.println(ConsoleColour.CYAN + "\n(1) Specify Input File Directory" + ConsoleColour.RESET);
            System.out.println(ConsoleColour.CYAN + "(2) Specify Output File Directory" + ConsoleColour.RESET);
            System.out.println(ConsoleColour.CYAN + "(3) Set Key" + ConsoleColour.RESET);
            System.out.println(ConsoleColour.CYAN + "(4) " + (encrypt ? "Encrypt" : "Encrypt") + ConsoleColour.RESET);
            System.out.println(ConsoleColour.CYAN + "(5) " + (encrypt ? "Decrypt" : "Decrypt") + ConsoleColour.RESET);
            System.out.println(ConsoleColour.CYAN + "(6) Options" + ConsoleColour.RESET);
            System.out.println(ConsoleColour.CYAN + "(7) Quit" + ConsoleColour.RESET);

            System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT + "Select Option [1-7]: " + ConsoleColour.RESET);

            if (!scanner.hasNextInt()) {
                System.out.println(
                        ConsoleColour.RED + "Invalid input! Please enter a valid option (a number between 1 and 7)."
                                + ConsoleColour.RESET);
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println(ConsoleColour.YELLOW + "Enter input file directory: " + ConsoleColour.RESET);
                    inputDirectory = scanner.nextLine();
                    if (!isValidDirectory(inputDirectory)) {
                        System.out.println(
                                ConsoleColour.RED + "Invalid input directory! Please provide a valid directory path."
                                        + ConsoleColour.RESET);
                        continue;
                    }
                    break;
                case 2:
                    System.out.println(ConsoleColour.YELLOW + "Enter output file directory: " + ConsoleColour.RESET);
                    outputDirectory = scanner.nextLine();
                    if (!isValidDirectory(outputDirectory)) {
                        System.out.println(
                                ConsoleColour.RED + "Invalid output directory! Please provide a valid directory path."
                                        + ConsoleColour.RESET);
                        continue;
                    }
                    break;
                case 3:
                    System.out.println(ConsoleColour.YELLOW + "Enter the cipher key: " + ConsoleColour.RESET);
                    cipherKey = scanner.nextLine();
                    if (!isValidCipherKey(cipherKey)) {
                        System.out.println(ConsoleColour.RED + "Invalid cipher key! Please provide a non-empty key."
                                + ConsoleColour.RESET);
                        continue;
                    }
                    break;
                case 4:
                    encrypt = true;
                    processFiles(inputDirectory, outputDirectory, cipherKey, encrypt);
                    break;
                case 5:
                    encrypt = false;
                    processFiles(inputDirectory, outputDirectory, cipherKey, encrypt);
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println(ConsoleColour.YELLOW_BOLD + "Goodbye!" + ConsoleColour.RESET);
                    System.exit(0);
                default:
                    System.out.println(
                            ConsoleColour.RED + "Invalid input! Please enter a valid option (a number between 1 and 7)."
                                    + ConsoleColour.RESET);
            }
        }
    }

    // Call to FileProcessor methods
    private static void processFiles(String inputDirectory, String outputDirectory, String cipherKey, boolean encrypt) {
        FileProcessor fileProcessor = new FileProcessor(inputDirectory, outputDirectory);
        fileProcessor.processFiles(cipherKey, encrypt);
    }
}
