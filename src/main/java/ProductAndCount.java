//A class that groups product with the count of product
public class ProductAndCount {

    private final String product;
    private int count;

    public ProductAndCount(String product, int count) {
        this(product);
        this.count = count;
    }

    public ProductAndCount(String product) {
        this.product = product;
        this.count = 0;
    }

    public String getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count += count;
    }
}
