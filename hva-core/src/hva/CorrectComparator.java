package hva;

import java.io.Serializable;
import java.util.Comparator;
import java.text.Normalizer;

public class CorrectComparator implements Comparator<String>, Serializable {

    /** Class Serial Number */
    private static final long serialVersionUID = 202407081733L;

    /** 
     * Compares two strings 
     * 
     * @param s1 the first string to compare
     * @param s2 the second string to compare
     * 
     * @return the value 0 if the argument string is equal to this string;
     *  a value less than 0 if this string is lexicographically less than the string argument; 
     *  and a value greater than 0 if this string is lexicographically greater than the string argument.
     */
    @Override
    public int compare(String s1, String s2) {
        int caseInsensitiveCompare = s1.compareToIgnoreCase(s2);
        return (caseInsensitiveCompare != 0) ? caseInsensitiveCompare : s1.compareTo(s2);
    }
}