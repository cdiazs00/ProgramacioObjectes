import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MetodesString {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("santako.txt"))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {

                int santaCount = countVisits(line, "*<]:-DOo");
                int reindeerCount = countVisits(line, ">:o)");
                int elfCount = countVisits(line, "<]:-D");

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

    private static int countVisits(String input, String target) {
        int count = 0;
        int index = input.indexOf(target);
        while (index != -1) {
            count++;
            index = input.indexOf(target, index + 1);
        }
        return count;
    }
}