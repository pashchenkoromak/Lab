/*
 * Author: Piotr Likus, SpaceInSoft, 2015
 * License: MIT
 */
package commons;

/**
 * Implements generic pair class that can hold pair of any objects.
 * Class is immutable, so setting values is done by creation of new object.
 *
 * @author Piotr Likus
 */

final public class Pair implements Comparable<Pair>{

    public String first;
    public String second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(final Pair src) {
        first = src.first;
        second = src.second;
    }

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(Pair p){
        if (first == p.first)
            return second.compareTo(p.second);
        return first.compareTo(p.first);
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}