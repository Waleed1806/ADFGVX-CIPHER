
public class PolybiusSquare {
    private static final char[][] square = {
    {' ','A', 'D', 'F', 'G', 'V', 'X'},
    {'A','P', 'H', '0', 'Q', 'G', '6'},
    {'D','4', 'M', 'E', 'A', '1', 'Y'},
    {'F', 'L', '2', 'N', 'O', 'F','D'},
    {'G', 'X', 'K', 'R', '3', 'C','V'},
    {'V', 'S', '5', 'Z', 'W', '7','B'},
    {'X', 'J', '9', 'U', 'T', 'I','8'},
   
    };

   // Find the character in the Polybius Square and return its code (two symbols)
    public static String getCode(char character) {
        
        character = Character.toUpperCase(character);
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                if (square[row][col] == character) {
                    return "" + square[row][0] + square[0][col];
                }
            }
        }
        // return an empty string
        return "";
    }


        // Given a code (two symbols), find the corresponding character in the Polybius Square
    public static char getCharacter(String code) {
        
        if (code.length() != 2) {
            throw new IllegalArgumentException("Invalid code length. It should be 2 characters long.");
        }
        char firstSymbol = code.charAt(0);
        char secondSymbol = code.charAt(1);

        int row = 0;
        int col = 0;

        // Find the row and column index of the character in the square
        for (int i = 0; i < square.length; i++) {
            if (square[i][0] == firstSymbol) {
                row = i;
            }
        }
        for (int i = 0; i < square[0].length; i++) {
            if (square[0][i] == secondSymbol) {
                col = i;
            }
        }
        // Return the character at the specified row and column
        return square[row][col];

    }


}
            