import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SmartLamp {
    public static void main(String[] args) throws IOException {
        String[] array_of_lamp_names = null;
        String[] arrays_lamps_color_codes = null;
        File file = new File("C:\\Users\\ufuka\\IdeaProjects\\assignment2\\src\\input1.txt");

        BufferedReader bReader = new BufferedReader(new FileReader(file));
        String initialTime;
        String line;
        String[] smartDevices = null;
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
                if (second.equals("SmartLamp")) {
                    array_of_lamp_names = new String[]{third};
                }
            }
        }}}

