import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Time {
    public static String time = null;

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
            if (first.equals("SetTime")) {
                time = second;
            }
            else if (first.equals(("SkipMinutes"))) {
                String str = time;
                String[] arrOfStr = str.split("_", 2);
                String[] minutes = arrOfStr[1].split(":");
                Integer İnteger = null;
                int minute_int = İnteger.parseInt(minutes[1]);
                int new_minute_int = minute_int + 5;
                time = arrOfStr[0]+"_"+minutes[0]+":"+new_minute_int+":"+minutes[2];

            }
            System.out.println(time);
        }
    }
}