# Sorting Algorithms - BridgeLabz Training 2Y

This `Sorting` branch contains my solutions for the Sorting Algorithms assignment.
Each program sorts a small sample array and prints before / after.

## Folder Structure

```text
Sorting/
├── README.md
└── src/
    └── com/
        └── gla/
            └── sorting/
                ├── BubbleSortStudentMarks.java
                ├── InsertionSortEmployeeIds.java
                ├── MergeSortBookPrices.java
                ├── QuickSortProductPrices.java
                ├── SelectionSortExamScores.java
                ├── HeapSortJobApplicants.java
                └── CountingSortStudentAges.java
```

## Programs

1. `BubbleSortStudentMarks.java` - Bubble Sort student marks in ascending order. Compare neighbours and swap.
2. `InsertionSortEmployeeIds.java` - Insertion Sort employee IDs. Insert each element into sorted part.
3. `MergeSortBookPrices.java` - Merge Sort book prices (double). Divide, sort, merge.
4. `QuickSortProductPrices.java` - Quick Sort product prices (double). Last element pivot, partition, recurse.
5. `SelectionSortExamScores.java` - Selection Sort exam scores. Find minimum and swap.
6. `HeapSortJobApplicants.java` - Heap Sort salary demands. Build max heap, extract one by one.
7. `CountingSortStudentAges.java` - Counting Sort student ages (10-18). Count frequency and rebuild.

## How to Run

```bash
# compile all
javac $(find src/com/gla/sorting -name "*.java") -d out

# run one example
java -cp out com.gla.sorting.BubbleSortStudentMarks
java -cp out com.gla.sorting.InsertionSortEmployeeIds
java -cp out com.gla.sorting.MergeSortBookPrices
java -cp out com.gla.sorting.QuickSortProductPrices
java -cp out com.gla.sorting.SelectionSortExamScores
java -cp out com.gla.sorting.HeapSortJobApplicants
java -cp out com.gla.sorting.CountingSortStudentAges
```
