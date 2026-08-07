# BridgeLabz Training 2Y

This is my BridgeLabz training repository. The `main` branch contains only this
README, which provides an overview of how I organize my assignment branches.
I maintain each module in its own branch.

## My Branch Organization

```text
main
└── README.md

Arrays
└── Arrays/
    ├── Level1/                 # When the assignment has levels
    ├── Level2/
    └── *.java                 # Otherwise, Java files go here

Strings
└── Strings/
    ├── Level1/
    ├── Level2/
    └── *.java

Searching-And-Sorting
└── Searching-And-Sorting/
    ├── Level1/
    ├── Level2/
    └── *.java

Recursion
└── Recursion/
    ├── Level1/
    ├── Level2/
    └── *.java

Linked-List
└── Linked-List/
    ├── Level1/
    ├── Level2/
    └── *.java

Stack
└── Stack/
    ├── Level1/
    ├── Level2/
    └── *.java

Queue
└── Queue/
    ├── Level1/
    ├── Level2/
    └── *.java
```

## How I Organize My Assignments

- I use a separate branch for each module listed above.
- Every module branch contains a folder with the same name as the branch.
- When a day's assignments are divided into levels, I organize the Java files
  inside the module's `Level1` and `Level2` folders.
- When an assignment does not have levels, I keep its Java files directly in
  the module folder.
- I give each Java file a meaningful PascalCase name based on the problem it
  solves. For example, a program that finds the second-largest array element is
  named `SecondLargestElement.java`.
