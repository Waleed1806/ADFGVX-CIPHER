# ADFGVX Cipher Application

## File Processor Class:
This class manages the processing of files in a specified input directory and puts the processed file in a specified output directory. It processes each file using a provided cipher key to either encrypt or decrypt the file content. It uses the `Cipher` class for encryption and decryption. The progress of file processing is displayed as a percentage.

## PolybiusSquare Class:
This class defines the Polybius Square used for encoding characters. It contains a matrix of characters and two functions for mapping characters to their corresponding symbols in the square.

## Cipher Class:
This class represents the ADFGVX cipher encryption and decryption process. It uses a provided key to encode and decode text. The encryption process involves several steps: encoding using a Polybius Square, matrix creation, columnar transposition, and generating ciphertext. The decryption process reverses the encryption steps to retrieve the original text.

## Runner Class:
This class serves as the entry point for the application. It provides an interactive command-line interface for users to specify input and output directories, cipher keys, choose between encryption and decryption, and the option to quit. Overall, the application provides functionality to encrypt and decrypt text files using the ADFGVX cipher. Users can provide input and output directories, a cipher key, and choose between encryption and decryption operations. The application reads files from the input directory, processes them using the specified key, and writes the processed content to the output directory.

# ADFGVX Cipher Application

## Compilation Instructions:

### Prerequisites:
Make sure you have the following prerequisites installed on your system:
- Java Development Kit (JDK)
- Git (optional)

### Clone the Repository:

git clone https://github.com/your-username/adfgvx-cipher.git
cd adfgvx-cipher
##Compile application
javac FileProcessor.java PolybiusSquare.java Cipher.java Runner.java

##Run the application
java Runner
