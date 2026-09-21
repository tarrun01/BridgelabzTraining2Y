class Book {
    int bookId;
    String title;
    String author;
    double price;

    public Book(int bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" + bookId + "] " + title + " - Rs. " + price;
    }
}

public class LibraryManagementSystem {

    public static int removeDuplicates(Book[] books, int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int j = 0;
        for (int i = 1; i < n; i++) {
            if (books[i].bookId != books[j].bookId) {
                j++;
                books[j] = books[i];
            }
        }
        return j + 1;
    }
    public static void searchByTitle(Book[] books, int count, String query) {
        System.out.println("Search Results for '" + query + "':");
        String lowerQuery = query.toLowerCase();
        boolean foundAny = false;
        for (int i = 0; i < count; i++) {
            if (books[i].title.toLowerCase().contains(lowerQuery)) {
                System.out.println("- Found: [" + books[i].bookId + "] " + books[i].title + " (Rs. " + books[i].price + ")");
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("No books found matching '" + query + "'.");
        }
    }
    public static void sortByPrice(Book[] books, int count) {
        int swaps = 0;
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (books[j].price < books[minIndex].price) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Book temp = books[i];
                books[i] = books[minIndex];
                books[minIndex] = temp;
                swaps++;
            }
        }
        System.out.println("Books Sorted by Price:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". [" + books[i].bookId + "] " + books[i].title + " - Rs. " + books[i].price);
        }
        System.out.println("Total Swaps: " + swaps);
    }

    public static int searchByPrice(Book[] books, int count, double targetPrice) {
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (Double.compare(books[mid].price, targetPrice) == 0) {
                return mid;
            } else if (books[mid].price < targetPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static int minBooksForTargetCost(Book[] books, int count, double targetCost) {
        int minLen = Integer.MAX_VALUE;
        double currentSum = 0;
        int left = 0;
        for (int right = 0; right < count; right++) {
            currentSum += books[right].price;
            while (currentSum >= targetCost) {
                int windowLen = right - left + 1;
                if (windowLen < minLen) {
                    minLen = windowLen;
                }
                currentSum -= books[left].price;
                left++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

    public static void main(String[] args) {
        Book[] books = new Book[6];
        books[0] = new Book(101, "Data Structures", "Mark", 400.0);
        books[1] = new Book(101, "Data Structures", "Mark", 400.0);
        books[2] = new Book(102, "Java Basics", "James", 300.0);
        books[3] = new Book(103, "Python Guide", "Guido", 600.0);
        books[4] = new Book(104, "Database Systems", "Raghu", 500.0);
        books[5] = new Book(105, "Computer Networks", "Andrew", 700.0);

        int count = removeDuplicates(books, books.length);
        System.out.println("1. After Task 1 (Remove Duplicates):");
        System.out.println("Unique Books Count: " + count);
        System.out.println("Book List:");
        for (int i = 0; i < count; i++) {
            System.out.println("[" + books[i].bookId + "] " + books[i].title + " - Rs. " + books[i].price);
        }
        System.out.println();

        System.out.println("2. After Task 2 (Search Query: \"data\"):");
        searchByTitle(books, count, "data");
        System.out.println();
        System.out.println("3. After Task 3 (Sort by Price):");
        sortByPrice(books, count);
        System.out.println();

        double targetPrice = 500.0;
        System.out.println("4. After Task 4 (Search for Price: " + targetPrice + "):");
        System.out.println("Searching for Price Rs. " + targetPrice + "...");
        int index = searchByPrice(books, count, targetPrice);
        if (index != -1) {
            System.out.println("Result: Book found at index " + index + ": [" + books[index].bookId + "] "
                    + books[index].title + " (Rs. " + books[index].price + ")");
        } else {
            System.out.println("Result: No book found with price Rs. " + targetPrice);
        }
        System.out.println();

        double targetCost = 1000.0;
        System.out.println("5. After Task 5 (Sliding Window for Target Cost S = Rs. " + targetCost + "):");
        System.out.println("Finding minimum consecutive books whose total price >= Rs. " + targetCost + "...");
        int minBooks = minBooksForTargetCost(books, count, targetCost);
        System.out.println("Minimum Consecutive Books Needed: " + minBooks);
        System.out.println("Explanation:");
        System.out.println("- Window 1 (Index 2 to 3):");
        System.out.println("  [104] Database Systems (Rs. 500.0) + [103] Python Guide (Rs. 600.0)");
        System.out.println("  Total = Rs. 1100.0 (>= 1000.0) -> Length: 2 books");
        System.out.println("- Window 2 (Index 3 to 4):");
        System.out.println("  [103] Python Guide (Rs. 600.0) + [105] Computer Networks (Rs. 700.0)");
        System.out.println("  Total = Rs. 1300.0 (>= 1000.0) -> Length: 2 books");
    }
}
