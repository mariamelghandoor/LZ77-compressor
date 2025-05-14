# LZ77 Compression Algorithm
A Java implementation of the LZ77 compression algorithm.

## Description
This project implements the LZ77 compression algorithm in Java, which is a lossless data compression technique that identifies and encodes repeated patterns in data using a sliding window approach.
Features

* Compression: Convert text data into LZ77 encoded format
* Decompression: Restore original data from LZ77 encoded format
* Configurable Parameters: Adjust search window and look-ahead window sizes
* Verification: Built-in similarity checking between original and decompressed data
* Bit Savings Analysis: Calculate and display compression ratio statistics

## Usage

```java

// Compress data
List<List<String>> compressed = Compress_in_77(data, lookAheadWindow, searchWindow);

// Decompress data
String decompressed = DeCompress_in_77(compressed);

// Check if decompression was successful
boolean isSuccessful = Compute_similarity(originalData, decompressed);

```
### Example
Original data size: 1024 bits
Compressed data size: 512 bits
Compression ratio: 50%
Similarity test: true

## Getting Started

1. Clone the repository
2. Open the project in your Java IDE
3. Run the App.java file
4. Use the commented console interface or modify the code to process your data

## License
MIT
MIT
