# Instructor Code Review Feedback

**Contact**: Dr. Yiji Zhang (yiji.zhang@northwestern.edu)

**Purpose of This Document**:
The instructor will perform code review with respect to software design, error handling, format and style on the main branch every week starting Week 6 using the letter grade A standards.
The following chapters of the textbook are considered: Chapter 1, 2, 3, 4, 5, 6, 7, 9, and 10. The corresponding lectures are considered, too.

## Week 7 Code Review

1. CardDistribution constructor too big: similar to the comment I gave last time, CardDistribution constructor is  too big and should be split into a couple of private smaller method.

Otherwise, looks good to me!

## Week 6 Code Review
I have read every line of production code currently in the main branch. Mostly looks good and I have one piece of feedback:

The PlayerNames constructor probably can be broken into 2 smaller private methods -- the first `if` is for checking counts, and the rest is for initPlayerNames or sth like that. 

```
        if (inputNames.size() != playerCount) {
            throw new IllegalArgumentException(
                "Number of names must match player count"
            );
        }

        this.names = new ArrayList<>();

        for (int i = 0; i < inputNames.size(); i++) {
            String name = inputNames.get(i).trim();

            if (name.isEmpty()) {
                name = "Player " + (i + 1);
            }

            this.names.add(name);
        }
```
