package hva;

import java.io.Serializable;
import java.util.Comparator;
import java.text.Normalizer;

public class CorrectComparator implements Comparator<String>, Serializable {

    /** Class Serial Number */
    private static final long serialVersionUID = 202407081733L;

    @Override
    public int compare(String s1, String s2) {
        int caseInsensitiveCompare = s1.compareToIgnoreCase(s2);
        return (caseInsensitiveCompare != 0) ? caseInsensitiveCompare : s1.compareTo(s2);
    }
}