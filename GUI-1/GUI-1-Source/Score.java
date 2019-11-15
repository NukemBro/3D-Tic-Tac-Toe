package project4;

import java.util.*;

public class Score {
    String name;
    int wins;

    // constructor (<24 lines)
    public Score(String n, int w) {
        name  = n;
        wins = w;
    }

    // increments the score (<24 lines)
    public void increment() {
        wins++;
    }

    // comparison function (<24 lines)
    public int compareTo(Score s) {
        if (this.wins == s.wins) {
            return 0;
        }
        if (this.wins > s.wins) {
            return 1;
        }
        return -1;
    }

    // converts to string
    public String toString() {
        String s = "";
        if (!(name.equals("null") || name.equals(("Not a score")))) {
        s =name +  " " + wins ;
        }
        return s;
    }
}
