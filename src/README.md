# DSA Practical Assignment: Library Management System

Course: Data Structures & Algorithms (DSA)

Single-file Java program implementing 5 library tasks from the assignment PDF.

## File Structure

```text
src/
├── LibraryManagementSystem.java  # Book class + 5 methods + main (sample test)
└── README.md                     # This file
```

## Tasks

| # | Description | Method | Complexity |
|---|-------------|--------|------------|
| 1 | Remove duplicate records in-place (sorted by `bookId`) | `removeDuplicates(Book[] books, int n)` | O(N) time, O(1) space |
| 2 | Partial title search (case-insensitive) | `searchByTitle(books, count, query)` | O(N) time, O(1) space |
| 3 | Sort by price, ascending (Selection Sort + swap count) | `sortByPrice(books, count)` | O(N^2) time, O(1) space |
| 4 | Exact price search (Binary Search on price-sorted array) | `searchByPrice(books, count, targetPrice)` | O(log N) time, O(1) space |
| 5 | Min consecutive books with total price >= S (Sliding Window) | `minBooksForTargetCost(books, count, targetCost)` | O(N) time, O(1) space |

## How to Compile and Run

Requires JDK 8+.

```bash
javac src/LibraryManagementSystem.java -d out
java -cp out LibraryManagementSystem
```

Or compile in place:

```bash
javac src/LibraryManagementSystem.java
java -cp src LibraryManagementSystem
```

## Sample Input

6 books with 1 duplicate (sorted by `bookId`):

- 101, "Data Structures", "Mark", 400.0
- 101, "Data Structures", "Mark", 400.0 (duplicate)
- 102, "Java Basics", "James", 300.0
- 103, "Python Guide", "Guido", 600.0
- 104, "Database Systems", "Raghu", 500.0
- 105, "Computer Networks", "Andrew", 700.0

## Expected Output

1. Unique count: 5
2. Search `"data"` finds Data Structures + Database Systems
3. Sorted by price: 300, 400, 500, 600, 700 — Total Swaps: 2
4. Search price `500.0` found at index 2
5. Target `1000.0` needs minimum 2 consecutive books
