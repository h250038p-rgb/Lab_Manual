import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * Lab 10 - Exercise 5 (Challenge)
 * Load learners from learners.csv, group by grade band (A, B, C) with Collectors.groupingBy,
 * and write one report file per band. (Marks below 50 are banded "F" so nobody is dropped.)
 */
public class GroupLearners {
    record Learner(String name, int marks) {
        String band() {
            if (marks >= 75) return "A";
            if (marks >= 60) return "B";
            if (marks >= 50) return "C";
            return "F";
        }
    }

    public static void main(String[] args) throws IOException {
        // EXPLAIN: skip(1) drops the CSV header row; each line is split into its columns
        List<Learner> learners = Files.readAllLines(Path.of("learners.csv")).stream()
                .skip(1)
                .filter(l -> !l.isBlank())
                .map(l -> l.split(","))
                .map(p -> new Learner(p[0].trim(), Integer.parseInt(p[1].trim())))
                .toList();

        // EXPLAIN: groupingBy builds Map<band, List<Learner>>; TreeMap keeps A,B,C,F in order
        Map<String, List<Learner>> byBand = learners.stream()
                .collect(Collectors.groupingBy(Learner::band, TreeMap::new, Collectors.toList()));

        for (Map.Entry<String, List<Learner>> e : byBand.entrySet()) {
            List<String> report = new ArrayList<>();
            report.add("Band " + e.getKey() + " report - " + e.getValue().size() + " learner(s)");
            for (Learner l : e.getValue()) report.add(l.name() + "," + l.marks());

            Path out = Path.of("report_" + e.getKey() + ".txt");
            Files.write(out, report);                       // one file per band
            System.out.println("Wrote " + out + ": " + e.getValue().stream().map(Learner::name).toList());
        }
    }
}
