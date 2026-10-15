import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Lab 10 - Exercise 1 (Basic)
 * Save five lines typed by the user to a file, then read them back.
 */
public class SaveLines {
    public static void main(String[] args) {
        Path file = Path.of("lines.txt");
        List<String> lines = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            for (int i = 1; i <= 5; i++) {
                System.out.print("Line " + i + ": ");
                lines.add(in.nextLine());
            }
        }
        try {
            // EXPLAIN: Files.write creates/overwrites the file and closes it for us
            Files.write(file, lines);
            System.out.println("\nSaved to " + file.toAbsolutePath().getFileName());

            // EXPLAIN: readAllLines loads the whole file as a List<String>
            List<String> back = Files.readAllLines(file);
            System.out.println("Read back:");
            for (String s : back) System.out.println("  " + s);
        } catch (IOException e) {                    // checked exception from NIO calls
            System.out.println("I/O problem: " + e.getMessage());
        }
    }
}
