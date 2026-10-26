# Problem 3 - N-Queens Optimization and Variants

## (a) Size of the search space (8-Queens)

### Brute force, no constraints

Choosing 8 distinct squares out of 64:

```text
C(64, 8) = 64! / (8! * 56!) = 4,426,165,368  ≈ 4.4 x 10^9
```

If the queens are treated as ordered (queen 1, queen 2, ...), the count is:

```text
64P8 = 64! / 56! = 178,462,987,637,760 ≈ 1.78 x 10^14
```

A naive "8 nested loops over 64 squares" implementation is even larger
(`64^8 ≈ 2.8 x 10^14`) because it also allows two queens on the same square.

### One queen per row

Put exactly one queen in each of the 8 rows. Each row independently has 8
possible columns:

```text
8^8 = 16,777,216
```

If we also require one queen per column, the candidates become permutations of
the 8 columns:

```text
8! = 40,320
```

### How much backtracking actually explores

With the row-by-row backtracking algorithm, a node is a **valid partial
placement** of `k` queens in the first `k` rows. The number of such partial
placements for 8-Queens is:

| Queens placed `k` | Valid partial boards |
|---:|---:|
| 0 (root) | 1 |
| 1 | 8 |
| 2 | 42 |
| 3 | 140 |
| 4 | 344 |
| 5 | 568 |
| 6 | 550 |
| 7 | 312 |
| 8 (complete solutions) | 92 |
| **Total nodes** | **2,057** |

Reasoning: when placing the queen in row `k`, most columns are already
attacked by one of the previous `k` queens (same column or same diagonal). A
branch dies as soon as it has no legal column, so the tree is cut off long
before `8^8` positions are reached. The final level has exactly 92 nodes,
which is the well-known number of 8-Queens solutions.

```text
16,777,216 / 2,057 ≈ 8,157
```

Backtracking explores roughly **8,000 times fewer** nodes than the
one-queen-per-row brute-force space, and the full search takes only a few
microseconds/ milliseconds on a modern machine.

## (b) Optimized solution: 1D array + diagonal tracking

### Representation

```text
queenCol[row] = column of the queen in this row, or -1 if empty
cols[col]                            = true if some queen already uses column col
diagSum[row + col]                   = true if some queen uses the "/" diagonal
diagDiff[row - col + (n - 1)]        = true if some queen uses the "\" diagonal
```

Why this works:

- Exactly one queen is placed per row, so a row conflict is impossible and we
  never need to check the row.
- A move `(row, col)` is attacked iff:
  `cols[col] || diagSum[row+col] || diagDiff[row-col+n-1]`.
- All three checks are `O(1)` array lookups.
- `diagSum` and `diagDiff` both have length `2n - 1`, because
  `row+col` ranges from `0` to `2n-2` and `row-col+n-1` ranges from `0` to
  `2n-2`.
- Only `O(n)` memory is needed. There is no `n x n` board and no scanning of
  previous rows.

### Pseudocode

```text
FUNCTION solveAll(n):
    queenCol ← array[0..n-1] filled with -1
    cols     ← boolean[n]      filled false
    diagSum  ← boolean[2n-1]   filled false       # r + c
    diagDiff ← boolean[2n-1]   filled false       # r - c + n - 1
    solutions ← empty list

    place(0)
    RETURN solutions


FUNCTION place(row):
    IF row == n:                                  # all rows filled
        solutions.ADD(copy(queenCol))             # GOAL
        RETURN

    FOR col ← 0 TO n-1:
        sum  ← row + col
        diff ← row - col + n - 1

        IF cols[col] OR diagSum[sum] OR diagDiff[diff]:
            CONTINUE                              # constraint

        # CHOOSE
        queenCol[row] ← col
        cols[col] ← true
        diagSum[sum] ← true
        diagDiff[diff] ← true

        place(row + 1)                            # EXPLORE

        # UN-CHOOSE (backtrack)
        cols[col] ← false
        diagSum[sum] ← false
        diagDiff[diff] ← false
        queenCol[row] ← -1
```

### Why it is better than the basic solution

| Basic board solution | Optimized solution |
|---|---|
| Stores an `n x n` board | Stores `n` queen columns |
| To test a square, loops over all previously placed queens: `O(n)` | Three array lookups: `O(1)` |
| Checks row, column and diagonals by scanning | Row is free by construction; column and both diagonals are tracked |
| More memory: `O(n^2)` | `O(n)` |
| More work when copying/printing a solution | Copy only `n` integers |

The asymptotic worst case is still exponential (`O(n!)` complete placements),
but the constant factor is much smaller. The standard complexity statement is
`O(n!)` time and `O(n)` auxiliary space.

## (c) Trace for N = 4

Arrays are shown as the **indices whose value is `true`** (empty = `{}`).
`diagSum` uses `r+c`; `diagDiff` uses `r-c+3`.

| Step | Action | `queenCol` | `cols` | `diagSum` | `diagDiff` |
|---:|---|---|---|---|---|
| 0 | start | `[-1,-1,-1,-1]` | `{}` | `{}` | `{}` |
| 1 | row 0, col 0: place | `[0,-1,-1,-1]` | `{0}` | `{0}` | `{3}` |
| 2 | row 1, col 0 | — | — | — | skip: column 0 used |
| 3 | row 1, col 1 | — | — | — | skip: `1-1+3 = 3` used by `(0,0)` |
| 4 | row 1, col 2: place | `[0,2,-1,-1]` | `{0,2}` | `{0,3}` | `{3,2}` |
| 5 | row 2, col 0 | — | — | — | skip: column 0 used |
| 6 | row 2, col 1 | — | — | — | skip: `2+1 = 3` used by `(1,2)` |
| 7 | row 2, col 2 | — | — | — | skip: column 2 used |
| 8 | row 2, col 3 | — | — | — | skip: `2-3+3 = 2` used by `(1,2)` |
| 9 | row 2 exhausted → **backtrack `(1,2)`** | `[0,-1,-1,-1]` | `{0}` | `{0}` | `{3}` |
| 10 | row 1, col 3: place | `[0,3,-1,-1]` | `{0,3}` | `{0,4}` | `{3,1}` |
| 11 | row 2, col 0 | — | — | — | skip: column 0 used |
| 12 | row 2, col 1: place | `[0,3,1,-1]` | `{0,1,3}` | `{0,3,4}` | `{3,1,4}` |
| 13 | row 3, col 0 | — | — | — | skip: column 0 used |
| 14 | row 3, col 1 | — | — | — | skip: column 1 used |
| 15 | row 3, col 2 | — | — | — | skip: `3-2+3 = 4` used by `(2,1)` |
| 16 | row 3, col 3 | — | — | — | skip: column 3 used |
| 17 | row 3 exhausted → **backtrack `(2,1)`** | `[0,3,-1,-1]` | `{0,3}` | `{0,4}` | `{3,1}` |
| 18 | row 2, col 2 | — | — | — | skip: `2+2 = 4` used by `(1,3)` |
| 19 | row 2, col 3 | — | — | — | skip: column 3 used |
| 20 | row 2 exhausted → **backtrack `(1,3)`** | `[0,-1,-1,-1]` | `{0}` | `{0}` | `{3}` |
| 21 | row 1 exhausted → **backtrack `(0,0)`** | `[-1,-1,-1,-1]` | `{}` | `{}` | `{}` |
| 22 | row 0, col 1: place | `[1,-1,-1,-1]` | `{1}` | `{1}` | `{2}` |
| 23 | row 1, col 0 | — | — | — | skip: `1+0 = 1` used by `(0,1)` |
| 24 | row 1, col 1 | — | — | — | skip: column 1 used |
| 25 | row 1, col 2 | — | — | — | skip: `1-2+3 = 2` used by `(0,1)` |
| 26 | row 1, col 3: place | `[1,3,-1,-1]` | `{1,3}` | `{1,4}` | `{2,1}` |
| 27 | row 2, col 0: place | `[1,3,0,-1]` | `{0,1,3}` | `{1,4,2}` | `{2,1,5}` |
| 28 | row 3, col 0 | — | — | — | skip: column 0 used |
| 29 | row 3, col 1 | — | — | — | skip: column 1 used |
| 30 | row 3, col 2: place | `[1,3,0,2]` | `{0,1,2,3}` | `{1,2,4,5}` | `{1,2,4,5}` |
| 31 | row 4 = n | — | — | — | **SOLUTION** |

First solution: `queenCol = [1, 3, 0, 2]`.

```text
row 0:  . Q . .
row 1:  . . . Q
row 2:  Q . . .
row 3:  . . Q .
```

Check: columns `1,3,0,2` are distinct; `r+c` values are `1,4,2,5` (distinct);
`r-c+3` values are `2,1,5,4` (distinct). No two queens attack each other.

The two dead ends at steps 9 and 17 are the key backtracking moments. In both
cases every remaining column of the current row is attacked, so the algorithm
removes the previous queen and resumes from the next column.

Note: the full N=4 search has only 2 solutions:

```text
[1, 3, 0, 2]      [2, 0, 3, 1]
```

## (d) Finding only one solution as fast as possible

The standard algorithm already stops exploring at the first complete board if
the recursive function returns a boolean:

```text
FUNCTION place(row) RETURNS boolean:
    IF row == n:
        RETURN true                      # stop immediately, do not copy
    FOR col ← 0 TO n-1:
        IF attacked: CONTINUE
        choose(col)
        IF place(row+1):
            RETURN true                  # bubble success up
        un-choose(col)
    RETURN false
```

`findFirst()` in `NQueensOptimized` does exactly this. Additional improvements:

1. **Do not collect solutions.** No list, no `clone()` for answers that will
   be discarded; return the first complete array (or just `true`).
2. **Symmetry breaking (existence only).** If `[c0, c1, ..., cn-1]` is a
   solution, its horizontal mirror is also a solution. For finding *one*
   solution, restrict row 0 to the left half (`0 <= c0 < n/2`). This halves
   the search; it must **not** be used when counting all solutions.
3. **Column ordering heuristic.** Try promising columns first. For plain
   N-Queens all rows are equivalent, but a static centre-out order or a
   random order often reaches a first solution sooner. Correctness is
   unaffected because all columns are still eventually tried.
4. **MRV (minimum remaining values).** Choose the next row with the fewest
   available columns. For plain N-Queens this is not helpful (all rows have
   the same number of candidates), but it is very useful for the forbidden
   squares variant.
5. **Bitmasks.** Replace three boolean arrays with three `int`s (or `long`s
   for large `n`):
   ```text
   available = ~(cols | diagSum | diagDiff) & ((1 << n) - 1)
   ```
   Then iterate over set bits with `Integer.numberOfTrailingZeros`, clearing
   the lowest bit each time. This removes array indexing from the hot loop.
6. **Avoid I/O and tracing** in the search loop (the trace mode is for
   teaching only).
7. For very large `n`, a complete backtracking search is still expensive; a
   local-search heuristic such as **min-conflicts** finds a solution quickly
   for large boards, but it is not guaranteed and is not a replacement for
   backtracking when completeness matters.

## (e) N-Queens with forbidden squares

Add a `boolean[][] forbidden` (or a per-row bitmask) and reject a square
before doing the column/diagonal checks:

```text
FUNCTION place(row):
    IF row == n:
        record solution; RETURN

    FOR col ← 0 TO n-1:
        IF forbidden[row][col]:                       # NEW
            CONTINUE
        IF cols[col] OR diagSum[row+col] OR diagDiff[row-col+n-1]:
            CONTINUE

        choose(row, col)
        place(row+1)
        un-choose(row, col)
```

More efficient representation: precompute `forbiddenMask[row]`, then

```text
available = ~(cols | diagSum | diagDiff | forbiddenMask[row]) & allColumns
```

### Impact

- **Correctness:** unchanged. Forbidden squares are simply never chosen; every
  other constraint is still enforced.
- **Worst-case complexity:** still `O(n!)` time and `O(n)` space for the
  search itself, because the forbidden set can only remove branches; it cannot
  create new valid placements. The extra input storage is `O(n^2)` for a full
  boolean matrix, or `O(f)` for a list of `f` forbidden coordinates. Precomputed
  masks cost `O(n)` extra.
- **Practical performance:** usually much faster because the tree is smaller.
  If an entire row becomes forbidden, the search returns immediately at that
  row (zero solutions). A forbidden square can also make a previously valid
  row have very few candidates, so MRV row selection becomes worthwhile.
- **Early detection:** before searching, check that every row has at least one
  allowed column and that there are at least `n` allowed squares overall.
  These are necessary (not sufficient) conditions and can reject impossible
  instances in `O(n^2)`.

Example from the Java program: N=4 has 2 solutions; forbidding `(0,0)` and
`(0,1)` leaves only `[2, 0, 3, 1]`; forbidding all of row 2 leaves 0
solutions.
