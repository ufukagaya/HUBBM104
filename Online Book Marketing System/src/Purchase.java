import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Purchase {
    public static ArrayList<String[]> Reader() {
        ArrayList<String[]> customers = new ArrayList<>();
        String fileName = "purchaseOrder.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                customers.add(parts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}

