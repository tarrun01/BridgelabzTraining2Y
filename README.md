# Searching + String and File Handling - BridgeLabz Training 2Y

This `Searching` branch contains all programs from the Linear and Binary Search
submission PDF, including StringBuilder, StringBuffer, FileReader,
InputStreamReader, Linear Search, Binary Search and the challenge problems.

## Folder Structure

```text
Searching/
├── README.md
└── src/
    └── com/
        └── gla/
            └── searching/
                ├── stringbuilder/
                │   ├── ReverseStringBuilder.java
                │   └── RemoveDuplicatesStringBuilder.java
                ├── stringbuffer/
                │   ├── ConcatenateStringsStringBuffer.java
                │   └── CompareStringBufferBuilder.java
                ├── filereader/
                │   ├── ReadFileLineByLine.java
                │   └── CountWordOccurrence.java
                ├── inputstreamreader/
                │   ├── ByteToCharStreamDemo.java
                │   └── UserInputToFile.java
                ├── linear/
                │   ├── FirstNegativeNumber.java
                │   └── SearchWordInSentences.java
                ├── binary/
                │   ├── RotationPointFinder.java
                │   ├── PeakElementFinder.java
                │   ├── SearchIn2DMatrix.java
                │   └── FirstLastOccurrence.java
                └── challenge/
                    ├── StringAndFileChallenge.java
                    └── MissingPositiveAndTargetSearch.java
```

## Programs

StringBuilder:
1. `ReverseStringBuilder.java` - Reverse "hello" to "olleh" using `reverse()`.
2. `RemoveDuplicatesStringBuilder.java` - Remove duplicate chars, keep order.

StringBuffer:
3. `ConcatenateStringsStringBuffer.java` - Join string array with `append()`.
4. `CompareStringBufferBuilder.java` - Time 100000 appends with both classes.

FileReader:
5. `ReadFileLineByLine.java` - Make `sample.txt` if missing, print each line.
6. `CountWordOccurrence.java` - Count "java" in `wordcount.txt`, ignore case.

InputStreamReader:
7. `ByteToCharStreamDemo.java` - Read UTF-8 file with `FileInputStream + InputStreamReader`.
8. `UserInputToFile.java` - Read console lines until "exit", save to file.

Linear Search:
9. `FirstNegativeNumber.java` - Return index of first negative, else -1.
10. `SearchWordInSentences.java` - Return first sentence with the word, else "Not Found".

Binary Search:
11. `RotationPointFinder.java` - Index of smallest element in rotated sorted array.
12. `PeakElementFinder.java` - Find any peak element (`arr[mid] > neighbours`).
13. `SearchIn2DMatrix.java` - Treat sorted matrix as 1D array, search target.
14. `FirstLastOccurrence.java` - First and last index of target in sorted array.

Challenge:
15. `StringAndFileChallenge.java` - Concat timing + word count with FileReader and InputStreamReader.
16. `MissingPositiveAndTargetSearch.java` - First missing positive by linear check, then binary search target.

## How to Run

```bash
# compile all
javac $(find src/com/gla/searching -name "*.java") -d out

# run examples
java -cp out com.gla.searching.stringbuilder.ReverseStringBuilder
java -cp out com.gla.searching.linear.FirstNegativeNumber
java -cp out com.gla.searching.binary.RotationPointFinder
java -cp out com.gla.searching.filereader.ReadFileLineByLine
```
