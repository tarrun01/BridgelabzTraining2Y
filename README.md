# Recursion and Backtracking - BridgeLabz Training 2Y

This `Recursion-and-backtracking` branch contains my written analysis and Java
implementations for the Recursion and Backtracking assignment.

## Folder Structure

```text
Recursion-and-backtracking/
├── README.md
├── .gitignore
├── docs/
│   ├── Problem1-Recursion-Mechanics.md
│   ├── Problem2-Backtracking-WordSearch.md
│   └── Problem3-NQueens.md
└── src/
    └── com/
        └── gla/
            └── recursion/
                ├── DirectorySizeCalculator.java
                ├── WordSearchBacktracking.java
                └── NQueensOptimized.java
```

## Written Answers

1. [Problem 1 - Understanding Recursion Mechanics](docs/Problem1-Recursion-Mechanics.md)
   - Recursive directory-size pseudocode, call-stack trace, `O(F + D)` time,
     iterative stack version, symbolic-link cycle handling.
2. [Problem 2 - Backtracking Template Application](docs/Problem2-Backtracking-WordSearch.md)
   - Word-search state/choice/constraint/goal template, decision tree for
     `DREAM`, full trace, `O(N*M*8^L)` analysis, all-paths variant.
3. [Problem 3 - N-Queens Optimization and Variants](docs/Problem3-NQueens.md)
   - Search-space sizes, 1D array + diagonal tracking, N=4 trace,
     first-solution optimizations, forbidden-squares variant.

## Programs

1. `DirectorySizeCalculator.java` - Models the assignment's `project/` tree and
   computes its size recursively with a printed call trace (380 KB). Also has
   an iterative stack version and a `docs/loop -> project` symlink cycle demo.
2. `WordSearchBacktracking.java` - Searches the 4x4 letter grid for `DREAM`.
   Prints the decision trace and all three valid paths. Direction order is
   `NW, N, NE, W, E, SW, S, SE`.
3. `NQueensOptimized.java` - Optimized N-Queens using a 1D queen array and
   three attack-tracking arrays. Finds all 92 solutions for 8-Queens in 2,057
   search nodes, finds a single solution with early exit, traces N=4, and
   supports forbidden squares.

## How to Run

```bash
# compile all programs
javac $(find src -name "*.java") -d out

# Problem 1
java -cp out com.gla.recursion.DirectorySizeCalculator

# Problem 2
java -cp out com.gla.recursion.WordSearchBacktracking

# Problem 3
java -cp out com.gla.recursion.NQueensOptimized
```

## Quick Answers

| Problem | Main result |
|---|---|
| 1 | Total directory size = **380 KB**. Every file and directory is visited once, so time is `Θ(F + D)`; recursion uses `O(H)` stack space. An explicit stack makes it iterative. A canonical-path set (or `FileSystemLoopException` handling) prevents symlink cycles. |
| 2 | `DREAM` can be formed in **3 ways** on the given grid. First solution: `D(2,0) -> R(1,1) -> E(1,2) -> A(1,3) -> M(2,3)`. Worst case `O(N*M*8^L)` time and `O(N*M + L)` space. |
| 3 | 8-Queens: `C(64,8) ≈ 4.4×10^9` unrestricted placements, `8^8 = 16,777,216` with one queen per row, but only **2,057** nodes with backtracking (92 solutions). The optimized solver uses `O(N)` space. N=4 first solution is `[1, 3, 0, 2]`. |

## Key Ideas

### Recursion

Every recursive solution needs:

1. **Base case(s)** - inputs small enough to answer directly (a file is a
   leaf; an empty directory is 0).
2. **Recursive case** - break the problem into smaller instances of the same
   problem (sum the children of a directory).
3. **Progress** - each call must move towards a base case (a file cannot have
   children; a cycle guard stops re-entering a directory).
4. **Combine** - use the returned values (`total += childSize`).
5. **Unwind** - remove the node from the path after the children are done.

Common pitfalls: forgetting the base case, mutating shared state without
restoring it, and infinite recursion from cycles.

### Backtracking

The general template used in Problems 2 and 3:

```text
backtrack(state):
    if goal(state): record/return
    for choice in choices(state):
        if valid(choice):
            apply(choice)
            backtrack(nextState)
            undo(choice)          # restore state exactly
```

- **State** must describe the partial solution.
- **Choices** are the possible next moves.
- **Constraints** prune invalid moves early.
- **Goal** decides when a complete solution is found.
- **Undo** is what makes the search space a tree instead of an ever-growing
  path.

Backtracking is still exponential in the worst case, but the constraints
usually remove most of the tree (for example, 8-Queens drops from `8^8` to
2,057 nodes).
