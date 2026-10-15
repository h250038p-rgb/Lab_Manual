import java.util.*;

/**
 * Lab 8 - Exercise 4 (Intermediate)
 * Word frequency using a Map, printed sorted by frequency descending.
 */
public class WordFreq {
    public static void main(String[] args) {
        String paragraph = "the quick brown fox jumps over the lazy dog and the dog barks "
                + "while the fox runs away and the quick fox hides";

        Map<String, Integer> freq = new HashMap<>();
        // EXPLAIN: normalise case, split on anything that is not a letter
        for (String w : paragraph.toLowerCase().split("[^a-z]+")) {
            if (w.isEmpty()) continue;
            freq.merge(w, 1, Integer::sum);   // add 1, or start at 1 if absent
        }

        // EXPLAIN: HashMap has no order, so move entries to a list and sort that
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort(
            Map.Entry.<String, Integer>comparingByValue().reversed()   // highest count first
                .thenComparing(Map.Entry.comparingByKey()));            // ties: alphabetical

        for (Map.Entry<String, Integer> e : entries) {
            System.out.printf("%-8s %d%n", e.getKey(), e.getValue());
        }
    }
}
