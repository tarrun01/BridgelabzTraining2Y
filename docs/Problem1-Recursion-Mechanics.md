# Problem 1 - Understanding Recursion Mechanics

## The directory tree

```text
project/                         directory
├── src/                         directory
│   ├── main.java                100 KB
│   └── utils.java                50 KB
├── docs/                        directory
│   ├── readme.txt                10 KB
│   └── guides/                  directory
│       └── setup.pdf            200 KB
└── config.xml                    20 KB
```

Counts used below:

- Files `F = 5` (`main.java`, `utils.java`, `readme.txt`, `setup.pdf`, `config.xml`)
- Directories `D = 4` (`project`, `src`, `docs`, `guides`)
- Total nodes `N = F + D = 9`
- Total size `100 + 50 + 10 + 200 + 20 = 380 KB`

## (a) Recursive function design

### Model

A node is either:

- a **file** - it has a size and no children (a leaf), or
- a **directory** - it has children, which can be files or more directories.

### Pseudocode

```text
FUNCTION totalSize(node, path):
    # BASE CASE 1: a file is a leaf
    IF node is FILE:
        RETURN node.sizeKB

    # BASE CASE 2: the directory is already on the current recursion path
    IF node is DIRECTORY AND node IN path:
        PRINT "cycle detected at " + node.name
        RETURN 0

    # RECURSIVE CASE: a directory is the sum of its children
    path.ADD(node)
    total ← 0
    FOR each child IN node.children:
        total ← total + totalSize(child, path)
    path.REMOVE(node)          # undo before returning

    RETURN total
```

### Base case vs recursive case

| Part | Condition | Action |
|---|---|---|
| Base case 1 | Node is a file | Return its size directly; there is nothing to recurse on. |
| Base case 2 | Directory is already on the current path (symbolic-link cycle) | Return `0` and skip it, so recursion terminates. |
| Base case 3 (implicit) | Empty directory | The `FOR` loop runs zero times and returns `0`. |
| Recursive case | Directory with one or more children | Call `totalSize` for every child and add the results. |

Only files contribute size. Directories contribute only the sum of their
children, so the same directory is never counted twice on a normal tree.

## (b) Execution trace

Call stack shown bottom (oldest) → top (newest). Execution is depth-first, in
the order the children appear in the tree.

| Step | Action | Call stack after the action | Value / note |
|---:|---|---|---|
| 1 | `totalSize(project)` | `project` | enter root |
| 2 | `totalSize(src)` | `project, src` | enter directory |
| 3 | `totalSize(main.java)` | `project, src, main.java` | file → base case |
| 4 | return `100` from `main.java` | `project, src` | `src.total = 100` |
| 5 | `totalSize(utils.java)` | `project, src, utils.java` | file → base case |
| 6 | return `50` from `utils.java` | `project, src` | `src.total = 100 + 50 = 150` |
| 7 | return `150` from `src` | `project` | `project.total = 150` |
| 8 | `totalSize(docs)` | `project, docs` | enter directory |
| 9 | `totalSize(readme.txt)` | `project, docs, readme.txt` | file → base case |
| 10 | return `10` from `readme.txt` | `project, docs` | `docs.total = 10` |
| 11 | `totalSize(guides)` | `project, docs, guides` | enter directory |
| 12 | `totalSize(setup.pdf)` | `project, docs, guides, setup.pdf` | deepest call, max depth 4 |
| 13 | return `200` from `setup.pdf` | `project, docs, guides` | `guides.total = 200` |
| 14 | return `200` from `guides` | `project, docs` | `docs.total = 10 + 200 = 210` |
| 15 | return `210` from `docs` | `project` | `project.total = 150 + 210 = 360` |
| 16 | `totalSize(config.xml)` | `project, config.xml` | file → base case |
| 17 | return `20` from `config.xml` | `project` | `project.total = 360 + 20 = 380` |
| 18 | return `380` from `project` | empty | final answer: **380 KB** |

### Stack building and unwinding

```text
CALL (push)                              RETURN (pop / unwind)
project                                  config.xml  -> 20
└─ src                                   src         -> 150
   ├─ main.java  -> 100
   └─ utils.java ->  50                  docs        -> 210
└─ docs                                  project     -> 380
   ├─ readme.txt ->  10
   └─ guides
      └─ setup.pdf -> 200
└─ config.xml -> 20
```

The stack grows to depth `4` (root → `docs` → `guides` → `setup.pdf`) and then
unwinds. Each directory adds the return values of its children; this is the
"combine" step of the recursion.

## (c) Time and space complexity

Let `N = F + D` be the number of nodes in the tree (files + directories).

- Every node is entered exactly once.
- Every parent-child edge is followed exactly once; a tree with `N` nodes has
  `N - 1` edges.
- Each call does `O(1)` work plus one addition per child.

Therefore:

```text
Time  = O(N) = O(F + D)
```

This is optimal because the size of every file must be read at least once. If
`B` is the total number of bytes stored, the I/O cost is `O(B)` in addition to
the `O(N)` traversal cost.

Space:

- Recursive: `O(H)` call-stack frames and `O(H)` entries in `path`, where `H`
  is the height of the tree (`H = 4` here). Worst case (a chain of
  directories) `H = O(N)`; a balanced tree has `H = O(log N)`.
- Iterative: `O(N)` heap space for the explicit stack and visited set.

## (d) Iterative solution

### Data structure

An explicit **stack** (LIFO) gives the same depth-first order as recursion.
Because addition is associative and commutative, the file sizes can simply be
added when a file is popped; no post-order unwinding is required.

```text
FUNCTION totalSizeIterative(root):
    total   ← 0
    stack   ← empty stack
    visited ← empty set
    stack.PUSH(root)

    WHILE stack is not empty:
        node ← stack.POP()

        IF node is FILE:
            total ← total + node.sizeKB
            CONTINUE

        IF node IN visited:          # already expanded, also breaks cycles
            CONTINUE
        visited.ADD(node)

        IF node is SYMLINK:
            stack.PUSH(node.target)
        ELSE:
            FOR each child IN node.children:
                stack.PUSH(child)

    RETURN total
```

If the requirement were to return a value from each directory (for example a
per-directory report), the stack would need **frames** `(node, nextChildIndex,
partialSum)` so the aggregation can happen when a directory is popped for the
second time. For a plain total, the simpler node stack above is enough.

### Recursive vs iterative

| Aspect | Recursive | Iterative (explicit stack) |
|---|---|---|
| Code clarity | Short and mirrors the definition of a tree | More code; state managed by hand |
| Memory | Call stack, `O(H)` | Heap stack, up to `O(N)` |
| Deep input | Risk of `StackOverflowError` for a very deep tree | Safe for deep trees (heap limit only) |
| Order | Natural pre-order DFS | LIFO gives DFS; a queue gives BFS |
| Combining results | Return values unwind automatically | Needs frames or order-independent aggregation |
| Speed | Slight call overhead | Usually a little faster, no call overhead |

Both are `O(N)` time. The recursive version is the better teaching solution;
the iterative version is safer when the tree depth is not under our control
(user-uploaded folder structures, for example).

## (e) Symbolic-link cycle

Suppose:

```text
project/docs/loop  ->  project
```

Without a guard, the call chain becomes:

```text
totalSize(project)
  totalSize(docs)
    totalSize(loop) -> totalSize(project)
      totalSize(docs)
        totalSize(loop) -> ...
```

The call stack grows without bound until Java throws `StackOverflowError`.
On a real file system the OS may instead stop with `ELOOP` ("too many levels
of symbolic links"), and some APIs throw `FileSystemLoopException`. Either
way, the calculation never finishes.

### Fix

Track the **canonical identity of every directory on the current path** and
treat re-entry as a base case:

```text
IF node is DIRECTORY AND node IN path:
    LOG "skipping cycle"
    RETURN 0
```

Important details:

1. Use a **current-path set**, not one global set of everything ever visited.
   A path-based set detects true cycles while still allowing the same directory
   to be reached twice through two different links (which is often what users
   expect for a link, though see the hard-link note below).
2. The identity should be stable: a canonical path (`toRealPath()`), an inode /
   `BasicFileAttributes.fileKey()`, or `Files.isSameFile(a, b)`.
3. Add a depth cap (for example 1000) as a second safety net.
4. On a real file system, prefer `Files.walkFileTree` with
   `EnumSet.of(FileVisitOption.FOLLOW_LINKS)`. Java detects loops itself and
   reports `FileSystemLoopException` in `visitFileFailed`.
5. Decide the policy for links: not following links at all (the default `du`
   behaviour) makes the link a leaf and avoids cycles completely; following
   links may double-count a directory reachable by two different paths.
6. Hard links are a separate issue: two names for the same file. If "disk
   usage" must not double-count, keep a set of `fileKey()` values for files.

In the Java solution, `DirectorySizeCalculator` demonstrates both the cycle
guard and the iterative `visited` set; the total stays **380 KB** even with
`docs/loop -> project`.
