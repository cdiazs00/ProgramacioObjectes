import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionsRegulars {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("santako.txt"))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {

                Pattern santaPattern = Pattern.compile("\\*<]:-DOo");
                Pattern reindeerPattern = Pattern.compile(">:o\\)");
                Pattern elfPattern = Pattern.compile("<]:-D");

                int santaCount = countMatches(line, santaPattern);
                int reindeerCount = countMatches(line, reindeerPattern);
                int elfCount = countMatches(line, elfPattern);

                printResults(lineNumber, santaCount, reindeerCount, elfCount);

                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printResults(int lineNumber, int santaCount, int reindeerCount, int elfCount) {
        printCharacterCount("Pare Noel", santaCount);
        printCharacterCount("Ren", reindeerCount);
        printCharacterCount("Follet", elfCount);
        System.out.println();
    }

    private static void printCharacterCount(String character, int count) {
        if (count > 0) {
            System.out.print(character + " (" + count + ") ");
        }
    }

    private static int countMatches(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}