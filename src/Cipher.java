import java.util.*;

public class Cipher {

    private String key;

    public Cipher(String key) {
        this.key = key.toUpperCase().replaceAll("[^A-Z0-9]", "");
    }

    // Encryption
    public String encrypt(String plaintext) {
        // Encode the plaintext using the Polybius Square
        StringBuilder ciphertextBuilder = new StringBuilder();
        for (char character : plaintext.toUpperCase().toCharArray()) {
            String code = PolybiusSquare.getCode(character);
            ciphertextBuilder.append(code);
        }

        // Create the matrix from the code word
        int cols = key.length();
        int rows = (int) Math.ceil((double) ciphertextBuilder.length() / cols);
        char[][] matrix = new char[rows][cols];

        int textIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (textIndex < ciphertextBuilder.length()) {
                    matrix[row][col] = ciphertextBuilder.charAt(textIndex);
                    textIndex++;
                }
            }
        }

        // Sort Key
        char[] sortedKeyChars = key.toCharArray();
        Arrays.sort(sortedKeyChars);

        // Perform columnar transposition
        StringBuilder transposedBuilder = new StringBuilder();
        boolean[] visitedIndices = new boolean[cols]; // To keep track of visited indices

        for (int col = 0; col < cols; col++) {
            int sortedIndex = key.indexOf(sortedKeyChars[col]);
            while (visitedIndices[sortedIndex]) {
                sortedIndex = key.indexOf(sortedKeyChars[col], sortedIndex + 1);
            }
            visitedIndices[sortedIndex] = true;

            for (int row = 0; row < rows; row++) {
                if (Character.isLetterOrDigit(matrix[row][sortedIndex])) {
                    transposedBuilder.append(matrix[row][sortedIndex]);
                }
            }
        }

        // Form the final ciphertext
        String ciphertext = transposedBuilder.toString();
        return ciphertext;
    }

    // Implement method for decryption
    public String decrypt(String ciphertext) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / cols);
        char[][] matrix = new char[rows][cols];

        int textIndex = 0;
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if (textIndex < ciphertext.length()) {
                    matrix[row][col] = ciphertext.charAt(textIndex);
                    textIndex++;
                }
            }
        }

        char[] sortedKeyChars = key.toCharArray();
        Arrays.sort(sortedKeyChars);

        // Inverse the columnar transposition
        char[][] invertedMatrix = new char[rows][cols];
        boolean[] visitedIndices = new boolean[cols];

        for (int col = 0; col < cols; col++) {
            int sortedIndex = key.indexOf(sortedKeyChars[col]);
            while (visitedIndices[sortedIndex]) {
                sortedIndex = key.indexOf(sortedKeyChars[col], sortedIndex + 1);
            }
            visitedIndices[sortedIndex] = true;

            for (int row = 0; row < rows; row++) {
                invertedMatrix[row][sortedIndex] = matrix[row][col];
            }
        }

        // Extract the original text from the inverted matrix
        StringBuilder originalTextBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (Character.isLetterOrDigit(invertedMatrix[row][col])) {
                    originalTextBuilder.append(invertedMatrix[row][col]);
                }
            }
        }

        String originalText = originalTextBuilder.toString();

        // Getting Original Text BY using Polybuis Square
        StringBuilder decodedMessage = new StringBuilder();
        int index = 0;

        while (index < originalText.length()) {
            if (index + 2 <= originalText.length()) {
                String twoCharacters = originalText.substring(index, index + 2);
                char decodedCharacter = PolybiusSquare.getCharacter(twoCharacters);

                decodedMessage.append(decodedCharacter);
            } else {
                decodedMessage.append(originalText.charAt(index));
            }

            index += 2;
        }

        return decodedMessage.toString();
    }
}
