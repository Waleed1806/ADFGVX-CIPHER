
import java.io.*;
import java.util.*;

public class FileProcessor {
    private String inputDirectory;
    private String outputDirectory;

    public FileProcessor(String inputDirectory, String outputDirectory) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
    }

    // Implement method to parse and process text files in the directory
    public void processFiles(String cipherKey, boolean encrypt) {
        File[] files = listFilesInInputDirectory();

        if (files == null) {
            System.out.println("Input directory not found or empty.");
            return;
        }

        createOutputDirectory();

        Cipher cipher = new Cipher(cipherKey);

        int totalFiles = files.length;
        int currentFile = 0;
        // Progress of files completed
        for (File file : files) {
            currentFile++;
            System.out.println("\nProcessing file " + currentFile + " of " + totalFiles);
            processFile(file, cipher, encrypt, totalFiles, currentFile);
        }

    }

    // List files in a directory
    private File[] listFilesInInputDirectory() {
        File inputDir = new File(inputDirectory);
        return inputDir.listFiles();
    }

    // Create Output Directory
    private void createOutputDirectory() {
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    // Process each file
    private void processFile(File file, Cipher cipher, boolean encrypt, int totalFiles, int currentFile) {
        if (file.isFile() && file.getName().endsWith(".txt")) {
            try {
                String content = readContentFromFile(file);
                String processedText = encrypt ? cipher.encrypt(content) : cipher.decrypt(content);
                writeProcessedTextToFile(file.getName(), processedText);
                System.out.println("File '" + file.getName() + "' processed successfully.");
            } catch (IOException e) {
                System.out.println("Error processing file '" + file.getName() + "': " + e.getMessage());
            }
        }

        printProgress(currentFile, totalFiles);
    }

    // Read a text from a file
    private String readContentFromFile(File file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                contentBuilder.append(scanner.nextLine());
            }
        }
        return contentBuilder.toString();
    }

    // Write text to a file
    private void writeProcessedTextToFile(String fileName, String processedText) {
        String outputFilePath = outputDirectory + "/" + fileName;
        try (PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.print(processedText);
        } catch (IOException e) {
            System.out.println("Error writing to output file: " + e.getMessage());
        }
    }

    // Print Progress of the files completed so far
    private void printProgress(int currentFile, int totalFiles) {
        int progress = (currentFile * 100) / totalFiles;
        int size = 50;
        char done = '█';
        char todo = '░';

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int completeLen = size * progress / 100;
        for (int i = 0; i < size; i++) {
            sb.append((i < completeLen) ? done : todo);
        }
        System.out.print("\r" + sb + "] " + progress + "%");
    }
}
