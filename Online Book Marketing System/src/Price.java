import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Price {
    public static ArrayList<String[]> PriceReader(String filename) {
        ArrayList<String[]> prices = new ArrayList<>(3);
        String fileName = "priceCatalog.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                //logic
                String[] parts = line.split("\t");
                prices.add(parts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prices;
    }
}
