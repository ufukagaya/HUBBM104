import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SmartPlug {
    static String[] array_of_names = null;
    
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\ufuka\\IdeaProjects\\assignment2\\src\\input1.txt");

        BufferedReader bReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bReader.readLine()) != null) {
            String[] line_array = line.split("\t");
            int counter = 0;
            for (String s : line_array)
                if (s != null)
                    counter++;

            String first = line_array[0];
            String second = "";
            String third = "";
            if (counter > 1) {
                second = line_array[1];
            }
            if (counter > 2) {
                third = line_array[2];
            }
            if (first.equals("Add")) {
                if (second.equals("SmartPlug")) {
                    AddToStringArray(array_of_names, third);
                }
            }
        }
    }

    private static void AddToStringArray(String[] arrayOfNames, String third) {

    }
}    
    