# Problem 2 - Backtracking Template Application

## The grid

```text
        col 0   col 1   col 2   col 3
row 0     C       A       T       S
row 1     O       R       E       A
row 2     D       E       A       M
row 3     E       L       L       S
```

Target word: **DREAM** (length `L = 5`).

Movement: 8 directions (horizontal, vertical, diagonal). A cell may be used at
most once per word.

The trace uses this fixed neighbour order, which is also the order in the Java
program:

```text
NW, N, NE, W, E, SW, S, SE
```

## (a) Backtracking design

### State, choices, constraints, goal

| Part | Definition |
|---|---|
| **State** | `(row, col, index, used)` - the current cell, how many letters have been matched, and which cells are already in the current path. |
| **Choices** | The 8 neighbouring cells of `(row, col)`. |
| **Constraints** | The neighbour must be inside the grid, must not be in `used`, and its letter must equal `word[index]`. |
| **Goal** | `index == word.length() - 1` (the last letter has been matched). |
| **Failure** | No neighbour satisfies the constraints, or all 8 are exhausted. |

### Pseudocode

```text
FUNCTION exists(grid, word):
    FOR r ← 0 TO rows-1:
        FOR c ← 0 TO cols-1:
            IF grid[r][c] == word[0]:
                used ← new boolean[rows][cols]
                IF dfs(r, c, 0, word, used):
                    RETURN true
    RETURN false


FUNCTION dfs(r, c, index, word, used):
    # Precondition: grid[r][c] == word[index] and (r,c) is free

    used[r][c] ← true                  # CHOOSE this cell
    path.ADD((r,c))

    IF index == length(word) - 1:      # GOAL
        RETURN true

    FOR each (nr, nc) IN 8 neighbours of (r,c):     # EXPLORE
        IF nr,nc inside grid
           AND NOT used[nr][nc]
           AND grid[nr][nc] == word[index+1]:

            IF dfs(nr, nc, index+1, word, used):
                RETURN true

    used[r][c] ← false                 # UN-CHOOSE (backtrack)
    path.REMOVE()

    RETURN false
```

In the "first solution only" version the successful `true` is returned all the
way up immediately. If the caller does not stop at the first solution, the
`used` flag and the path entry must also be undone after a success before the
search continues.

## (b) Decision tree for DREAM

There is only one `D` in the grid, at `(2,0)`, so the whole search starts there.

```text
D(2,0), index 0
└── R(1,1), index 1                    [only R neighbour of the D]
    ├── E(1,2), index 2                [first E found in neighbour order]
    │   ├── A(0,1), index 3
    │   │   └── M? none                ✗ DEAD END -> backtrack
    │   ├── A(1,3), index 3
    │   │   └── M(2,3), index 4        ✓ PATH 1
    │   └── A(2,2), index 3
    │       └── M(2,3), index 4        ✓ PATH 2
    └── E(2,1), index 2                [reached after E(1,2) is fully undone]
        └── A(2,2), index 3
            └── M(2,3), index 4        ✓ PATH 3
```

### The three successful paths

| # | Cells | Letters |
|---|---|---|
| 1 | `(2,0) -> (1,1) -> (1,2) -> (1,3) -> (2,3)` | `D R E A M` |
| 2 | `(2,0) -> (1,1) -> (1,2) -> (2,2) -> (2,3)` | `D R E A M` |
| 3 | `(2,0) -> (1,1) -> (2,1) -> (2,2) -> (2,3)` | `D R E A M` |

The dead-end `A(0,1)` is important: `A(0,1)` is adjacent to `C, T, O, R, E`,
but not to any `M`, so the algorithm must undo it and try the next `A` from
`E(1,2)`.

`A(2,2)` is visited in two different branches. That is legal because the
`used` set was restored when the first branch was undone; it is not a global
"visited forever" set.

## (c) Execution trace (first solution)

Stack is shown bottom → top. `used` lists only the cells currently in the path.

| Step | Stack | `used` (path) | Event |
|---:|---|---|---|
| 1 | `D(2,0)` | `{D}` | `index = 0`. Try neighbours in order: `N` is `O` (wrong), `NE` is `R` → choose `R(1,1)`. |
| 2 | `D, R(1,1)` | `{D,R}` | `index = 1`. Try `E` neighbours: `E(1,2)` is the first match → choose it. |
| 3 | `D, R, E(1,2)` | `{D,R,E12}` | `index = 2`. First matching `A` is `A(0,1)` → choose it. |
| 4 | `D, R, E, A(0,1)` | `{D,R,E12,A01}` | `index = 3`. Look for an unused neighbouring `M`: none. **Dead end.** Unmark `A(0,1)`, remove it from the path, return `false`. |
| 5 | `D, R, E(1,2)` | `{D,R,E12}` | Back at `E(1,2)`. Continue its neighbour loop: `N` is `T`, `NE` is `S`, `W` is the used `R`, `E` is `A(1,3)` → choose `A(1,3)`. |
| 6 | `D, R, E, A(1,3)` | `{D,R,E12,A13}` | `index = 3`. `SW` is `A(2,2)` (wrong letter), `S` is `M(2,3)` → choose `M(2,3)`. |
| 7 | `D, R, E, A(1,3), M(2,3)` | `{D,R,E12,A13,M23}` | `index = 4 == L-1` → **goal**. Return `true` up the stack. |

Result:

```text
D(2,0) -> R(1,1) -> E(1,2) -> A(1,3) -> M(2,3)   => "DREAM"
DFS calls made: 6
```

After step 4 the state is exactly what it was before step 3: `used` no longer
contains `A(0,1)` and the path no longer ends with it. That restoration is what
lets the loop at `E(1,2)` try `A(1,3)`.

### Trace when finding ALL paths

| Event | Stack / path after the event | Action |
|---|---|---|
| Found path 1 | `D,R,E12,A13,M23` | Record a copy. Unmark `M23`. |
| Back at `A(1,3)` | `D,R,E12,A13` | No other `M`; unmark `A13`, return. |
| Back at `E(1,2)` | `D,R,E12` | Next matching `A` is `A(2,2)` → choose. |
| Found path 2 | `D,R,E12,A22,M23` | Record a copy. Unmark `M23`, then `A22`, then `E12`. |
| Back at `R(1,1)` | `D,R` | Next matching `E` is `E(2,1)` → choose. |
| `E(2,1)` → `A(2,2)` | `D,R,E21,A22` | Choose `A(2,2)`, then `M(2,3)`. |
| Found path 3 | `D,R,E21,A22,M23` | Record a copy. Unwind all the way to the root. |

The all-paths run makes **11 DFS calls** and returns **3 paths**.

## (d) Worst-case time complexity

Let the grid be `N x M` and the word length be `L`.

- There are at most `N*M` starting cells.
- The recursion depth is at most `L`.
- Each call may branch to at most 8 neighbours.

A simple upper bound is:

```text
Time = O(N * M * 8^L)
```

A slightly tighter bound is `O(N*M*7^(L-1))` because the cell just visited is
always excluded, but the `8^L` form is the usual one and is easier to read.
The bound is loose because:

1. many neighbours are outside the grid,
2. `used` removes cells from the search,
3. the character check rejects most branches immediately.

For the 4x4 board and `L = 5` the loose bound is `16 * 8^5 = 524,288` calls,
while the real run uses only 6 calls for the first path and 11 for all three
paths. The pruning is doing most of the work.

Space:

```text
O(L)            recursion stack + current path
O(N*M)          used[][] board
-----------------------------
O(N*M + L)      total auxiliary space
```

If the board is mutated and restored instead of a separate `used` array, the
auxiliary space is `O(L)` (excluding the input grid).

## (e) Finding ALL ways to form a word

Changes to the template:

1. Do **not** return `true` at the goal. Copy the current path into a results
   list, then continue the loop.
2. After recording, undo the current cell (`used[r][c] = false`, remove it from
   the path) so sibling branches start from a clean state.
3. Keep exploring all starting cells, not only until the first `true`.

Modified success block:

```text
IF index == length(word) - 1:
    results.ADD(copy(path))
    used[r][c] ← false
    path.REMOVE()
    RETURN                     # continue with the caller's next choice
```

### Additional data structures

| Structure | Purpose |
|---|---|
| `boolean[][] used` | Cells in the current path; restored on every backtrack. |
| `List<int[]> path` | Current sequence of coordinates; needed to print/report a solution. |
| `List<List<int[]>> results` | Every complete path (deep copy, because `path` keeps changing). |
| `List<String>` | Optional formatted output, e.g. `D(2,0) -> ...`. |
| `Set<String>` | Optional de-duplication if the same coordinate sequence could be recorded twice (not needed in this implementation). |

Time is still bounded by the explored search tree,
`O(N*M*8^L)`, plus `O(P*L)` to copy/report `P` solutions. Memory becomes
`O(N*M + L + P*L)` because all solutions are stored.
