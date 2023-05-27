//Class for customers
public class Customer extends User implements Callmenu {

    public Customer(String username, String password, int role, boolean read_only) {
        super(username, password, role, read_only);
    }

    @Override
    public void callmenu(){
        CustomerMenu customerMenu = new CustomerMenu(this);
    };
}
