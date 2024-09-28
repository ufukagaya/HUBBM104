import org.jetbrains.annotations.NotNull;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        ArrayList<String[]> customers = Purchase.Reader();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            int i = 0;

            for (String[] customer: customers) {
                i += 1;
                writer.write("------------Bill for Customer " + i + "-------------\n");
                String name = customer[0];
                writer.write("Customer: " + name + "\n");
                String membershipType = customer[1];
                writer.write("Membership Type: " + membershipType + "\n");
                String Date = customer[2];
                writer.write("Date: "+ Date + "\n\n");
                writer.write("Items Purchased:\n");
                String Book1 = customer[3];
                String BookQ1 = customer[4];
                String[] data1 = getPrice(Book1,membershipType,Date);
                String price1 = data1[0];
                String DateFrom1 = data1[1];
                String DateTo1 = data1[2];
                writer.write(Book1 + " (Qty: " + BookQ1 + ") - " +  price1 + " each\n");
                writer.write("(Valid from " + DateFrom1 + " to " + DateTo1 + ")\n");
                float priceInt1 = StringToFloat(price1);
                float QuantityInt1 = StringToFloat(BookQ1);
                float subtotal1 = priceInt1 * QuantityInt1;
                writer.write("Subtotal: " + subtotal1 + "\n");
                String Book2 = customer[5];
                String BookQ2 = customer[6];
                String[] data2 = getPrice(Book2,membershipType,Date);
                String price2 = data2[0];
                String DateFrom2 = data2[1];
                String DateTo2 = data2[2];
                writer.write(Book2 + " (Qty: " + BookQ2 + ") - " + price2 +" each\n");
                writer.write("(Valid from " + DateFrom2 + " to "+ DateTo2 +")\n");
                float priceInt2 = StringToFloat(price2);
                float QuantityInt2 = StringToFloat(BookQ2);
                float subtotal2 = priceInt2 * QuantityInt2;
                writer.write("Subtotal: " + subtotal2 + "\n\n");
                float subtotal = subtotal1 + subtotal2;
                writer.write("Total Cost: " + subtotal + "\n\n");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String @NotNull [] getPrice(String bookName, String membershipType, String date) {
        String[] data = new String[3];
        String[] date_splitted = date.split("\\.");
        String month = date_splitted[1];
        if (Objects.equals(bookName, "Book A")) {
            if (Objects.equals(membershipType, "Premium")) {
                if (Objects.equals(month, "01")) {
                    data[0] = "15.99";
                    data[1] = "01.01.2023";
                    data[2] = "31.01.2023";
                } else if (Objects.equals(month, "02")) {
                    data[0] = "17.99";
                    data[1] = "01.02.2023";
                    data[2] = "29.02.2023";
                }
            } else if (Objects.equals(membershipType, "Standard")) {
                data[0] = "17.99";
                data[1] = "01.02.2023";
                data[2] = "28.02.2023";
            } else if (Objects.equals(membershipType, "Basic")) {
                data[0] = "19.99";
                data[1] = "01.01.2023";
                data[2] = "31.01.2023";
            }
        } else if (Objects.equals(bookName, "Book B")) {
            if (Objects.equals(membershipType, "Premium")) {
                data[0] = "13.39";
                data[1] = "01.01.2023";
                data[2] = "31.01.2023";
            } else if (Objects.equals(membershipType, "Standard")) {
                data[0] = "14.49";
                data[1] = "01.02.2023";
                data[2] = "28.02.2023";
            } else if (Objects.equals(membershipType, "Basic")) {
                data[0] = "15.10";
                data[1] = "01.01.2023";
                data[2] = "31.01.2023";
            }
        }
        return data;
    }
    public static float StringToFloat(String priceStr){
        try {
            return Float.parseFloat(priceStr);
        } catch (NumberFormatException e) {
            System.err.println("The string does not represent a valid float.");
            return 0.0f;
        }
    }
}