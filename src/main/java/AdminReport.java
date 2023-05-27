import java.time.LocalDate;
import java.util.ArrayList;

//Class that contains the methods to process user data for generating reports
public class AdminReport {
    private LocalDate date;
    private double sum = 0;
    private String product;
    private final ArrayList<String> feedbacks = new ArrayList<String>();

    public AdminReport(LocalDate date) {
        this.date = date;
    }

    public AdminReport(LocalDate date, double sum) {
        this(date);
        this.sum = sum;
    }

    public AdminReport(float sum, String product) {
        this.sum = sum;
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProduct() {
        return product;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum += sum;
    }

    public void setFeedbacks(String feedback) {
        this.feedbacks.add(feedback);
    }

    public static ArrayList<AdminReport> printSalesByDayReport(ArrayList<ReceiptList> allReceipt) {
        ArrayList<AdminReport> salesByDay = new ArrayList<AdminReport>();
        AdminReport adminReport = new AdminReport(allReceipt.get(0).getDate(), allReceipt.get(0).sumOfOrder());
        salesByDay.add(adminReport);

        for (int i = 1; i < allReceipt.size(); i++) {
            for (int j = 0; j < salesByDay.size(); j++) {
                if (allReceipt.get(i).getDate().compareTo(salesByDay.get(j).getDate()) != 0) {
                    adminReport = new AdminReport(allReceipt.get(i).getDate());
                    salesByDay.add(adminReport);
                }
            }
        }

        for (int i = 1; i < allReceipt.size(); i++) {
            for (int j = 0; j < salesByDay.size(); j++) {
                if (allReceipt.get(i).getDate().compareTo(salesByDay.get(j).getDate()) == 0) {
                    adminReport = salesByDay.get(j);
                    adminReport.setSum(allReceipt.get(i).sumOfOrder());
                    salesByDay.set(j, adminReport);
                    break;
                }
            }
        }

        return salesByDay;
    }

    public static ArrayList<ProductAndCount> printCountOfProduct(ArrayList<ReceiptList> allReceipt) {
        ArrayList<ProductAndCount> collection = new ArrayList<ProductAndCount>();
        ArrayList<ProductAndCount> productAndCount;
        ProductAndCount example;
        collection = allReceipt.get(0).countProduct();

        boolean product_exist = false;
        for (int i = 1; i < allReceipt.size(); i++) {
            productAndCount = allReceipt.get(i).countProduct();
            for (int j = 0; j < productAndCount.size(); j++) {
                for (int k = 0; k < collection.size(); k++) {
                    if (productAndCount.get(j).getProduct().equals(collection.get(k).getProduct())) {
                        product_exist = true;
                        break;
                    }
                }
                if (!product_exist) {
                    ProductAndCount temp = new ProductAndCount(productAndCount.get(j).getProduct());
                    collection.add(temp);
                }
            }
        }
        for (int i = 1; i < allReceipt.size(); i++) {
            productAndCount = allReceipt.get(i).countProduct();
            for (int j = 0; j < productAndCount.size(); j++) {
                for (int k = 0; k < collection.size(); k++) {
                    if (productAndCount.get(j).getProduct().equals(collection.get(k).getProduct())) {
                        example = collection.get(k);
                        example.setCount(productAndCount.get(j).getCount());
                        collection.set(k, example);
                        break;
                    }
                }
            }
        }
        return collection;
    }

}
