import java.io.*;
import java.lang.*;
import java.util.*;
public class Main extends Time{
    // Function to add x in arr
    public static void main(String[] args) throws IOException {
        String time = null;
        Integer elapsed_time = null;
        File file = new File(args[0]);
        FileWriter myWriter = new FileWriter(args[1]);
        BufferedReader bReader = new BufferedReader(new FileReader(file));
        String initialTime;
        String line;
        String smartDevices = null;
        while ((line = bReader.readLine()) != null) {
            String[] line_array = line.split("\t");
            int counter = 0;
            for (String s : line_array)
                if (s != null)
                    counter++;

            String first = line_array[0];
            String second = "";
            String third = "";
            String fourth = "";
            String fifth = "";
            String sixth = "";

            if (counter >1) {
                second = line_array[1];
            }
            if (counter >2) {
                third = line_array[2];
            }
            if (counter >3) {
                fourth = line_array[3];
            }
            if (counter >4) {
                fifth = line_array[4];
            }
            if (counter >5) {
                sixth = line_array[5];
            }
            if (first.equals("Add")) {
                switch (second) {
                    case "SmartPlug": {
                        String sf = String.format("COMMAND: %s", line);
                        myWriter.write(sf);
                        assert false;
                        ArrayList<String> list = new ArrayList<>(Arrays.asList(smartDevices));
                        if (line.length() == 3) {
                            smartDevices = smartDevices + "Smart Plug "+ third + " is on and consumed " + 220*elapsed_time+"W " + "so far (excluding current device), and its time to switch its status is " + time + "\n";
                        }
                        else if (line.length() == 4) {
                            smartDevices = smartDevices + "Smart Plug "+ third + " is " + fourth + " and consumed " + 220*elapsed_time+"W" + "so far (excluding current device), and its time to switch its status is " + time + "\n";
                        }
                        else if (line.length() == 5) {
                            int watt = Integer.parseInt(fifth);
                            smartDevices = smartDevices + "Smart Plug "+ third + " is " + fourth + " and consumed " + watt *elapsed_time+"W" + "so far (excluding current device), and its time to switch its status is " + time +"\n";
                        }
                        else {
                            smartDevices = smartDevices + "Smart Plug "+ third + " is on and consumed " + "0,00W" + " so far (excluding current device), and its time to switch its status is " + time + "\n";
                        }
                        break;
                    }
                    case "SmartCamera": {
                        String sf = String.format("COMMAND: %s", line);
                        myWriter.write(sf);
                        smartDevices = smartDevices + "Smart Camera " + third + " is on and used 0,00 MB of storage so far (excluding current status), and its time to switch its status is null.\n";
                        break;
                    }
                    case "SmartColorLamp": {
                        String sf = String.format("COMMAND: %s", line);
                        myWriter.write(sf);
                        if (line_array.length < 5) {
                            smartDevices = smartDevices + "Smart Color Lamp " + third + " is on and its color value is 2000K with 55% brightness, and its time to switch its status is" + time + ".\n";
                        }
                        else if (line_array.length == 6) {
                            smartDevices = smartDevices + "Smart Color Lamp " + third + " is on and its color value is " + fifth + "K with " + sixth + "% brightness, and its time to switch its status is" + time + ".\n";
                        }
                        break;
                    }
                    case "SmartLamp": {
                        String sf = String.format("COMMAND: %s", line +"\n");
                        myWriter.write(sf);
                        if (line_array.length < 5) {
                            smartDevices = smartDevices + "Smart Lamp Lamp2 is on and its kelvin value is 4000K with 100% brightness, and its time to switch its status is" + time +".\n";
                        }
                        else if (line_array.length == 6) {
                            smartDevices = smartDevices + "Smart Lamp Lamp2 is on and its kelvin value is " + fifth+ "K with" + sixth + "% brightness, and its time to switch its status is"+ time +".\n";
                        }
                        break;
                    }
                }
            } else if (first.equals("Remove")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("Switch")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("SetInitialTime")) {
                initialTime = second;
                String sf1 = String.format("COMMAND: %s", line + "\n");
                String sf2 = "SUCCESS: Time has been set to " + initialTime + "!\n";
                myWriter.write(sf1);
                myWriter.write(sf2);
            } else if (first.equals("Nop")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("PlugIn")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("SetTime")) {
                time = second;
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("SkipMinutes")) {
                String str = time;
                assert str != null;
                String[] arrOfStr = str.split("_", 2);
                String[] minutes = arrOfStr[1].split(":");

                int minute_int = java.lang.Integer.parseInt(minutes[1]);
                int added_minutes = java.lang.Integer.parseInt(second);
                int new_minute_int = minute_int + added_minutes;
                time = arrOfStr[0]+"_"+minutes[0]+":"+new_minute_int+":"+minutes[2];

                if (elapsed_time != null) {
                    elapsed_time = elapsed_time + added_minutes;
                }
                else {
                    elapsed_time = added_minutes;
                }

                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("PlugOut")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("ChangeName")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("SetKelvin")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("SetBrightness")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("SetColorCode")) {
                String sf = String.format("COMMAND: %s", line + "\n");
                myWriter.write(sf);
            } else if (first.equals("SetWhite")) {
                String sf = String.format("COMMAND: %s", line +"\n");
                myWriter.write(sf);
            } else if (first.equals("SetColor")) {
                String sf = String.format("COMMAND: %s", line +"\n");
                myWriter.write(sf);
            } else if (first.equals("SetSwitchTime")) {
                String sf = String.format("COMMAND: %s", line +"\n");
                myWriter.write(sf);
            } else if (first.equals("ZReport")) {
                String sf1 = String.format("COMMAND: %s", line + "\n");
                String sf2 = String.format("Time:\t%s", time + "\n");
                myWriter.write(sf1);
                myWriter.write((sf2));
                if (smartDevices != null) {
                    myWriter.write(smartDevices);
                }
            } else {
                myWriter.write("COMMAND: ILLEGAL_COMMAND\n");
            }
        }
        bReader.close();
    }
}
