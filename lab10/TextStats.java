import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Lab 10 - Exercise 3 (Intermediate)
 * Read a text file and print number of lines, words and characters.
 * Usage: java TextStats [file]    (defaults to sample.txt)
 */
public class TextStats {
    public static void main(String[] args) {
        Path file = Path.of(args.length > 0 ? args[0] : "sample.txt");
        try {
            List<String> lines = Files.readAllLines(file);
            long words = 0, chars = 0;
            for (String line : lines) {
                chars += line.length();
                String t = line.trim();
                if (!t.isEmpty()) words += t.split("\\s+").length;   // split on runs of whitespace
            }
            System.out.println("File      : " + file);
            System.out.println("Lines     : " + lines.size());
            System.out.println("Words     : " + words);
            System.out.println("Characters: " + chars + " (excluding line breaks)");
        } catch (IOException e) {
            System.out.println("Cannot read " + file + ": " + e.getMessage());
        }
    }
}
