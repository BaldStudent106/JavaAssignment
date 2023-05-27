import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

//A class of the final receipt of an order that may contain multiple menu item
public class ReceiptList {

    private final String username;
    private final ArrayList<MenuItem> order = new ArrayList<MenuItem>();

    private int ordercount;

    private String feedback;

    public LocalDate date;

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public ReceiptList(String username) {
        this.username = username;
    }

    public void addItem(MenuItem item) {
        order.add(item);
    }

    public void setOrdercount(int ordercount) {
        this.ordercount = ordercount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFeedback() {
        return feedback;
    }

    public ArrayList<MenuItem> getOrder() {
        return order;
    }

    public double sumOfOrder() {
        double sum = 0;
        for (MenuItem item : order) {
            sum += item.getFinalprice();
        }
        return sum;
    }

    public void write_to_file() {
        this.date = LocalDate.now();
        String combination = ArraylistToString();
        ordercount = order.size();
        try {
            File file = new File("Orderlist.txt");
            boolean file_exist = file.exists();
            FileWriter fileWriter = new FileWriter(file, true);
            if (file_exist) {
                fileWriter.write("\n" + this.username + ":" + this.ordercount + ":" + combination + this.feedback + ":" + this.date);
            } else {
                fileWriter.write(this.username + ":" + this.ordercount + ":" + combination + this.feedback + ":" + this.date);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public String ArraylistToString() {
        String total = "";
        String one;
        for (MenuItem menuItem : order) {
            one = menuItem.getName() + ":" + menuItem.getPrice() + ":" + menuItem.getQuantity() + ":" + menuItem.getFinalprice() + ":";
            total = total.concat(one);
        }
        return total;
    }

    public ArrayList<ProductAndCount> countProduct() {
        ArrayList<ProductAndCount> collection = new ArrayList<ProductAndCount>();
        for (MenuItem item : order) {
            String product = item.getName();
            int count = item.getQuantity();
            ProductAndCount productAndCount = new ProductAndCount(product, count);
            collection.add(productAndCount);
        }
        return collection;
    }
}