import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//Class that process the file reading process
public class FileReader {
    private static final ArrayList<User> list_of_user = new ArrayList<User>();

    public static void read_user_from_file() {
        int fileNotCreated = 0;
        do {
            try {
                list_of_user.clear();
                File file = new File("Userlist.txt");
                Scanner file_reader = new Scanner(file);
                while (file_reader.hasNextLine()) {
                    String user = file_reader.nextLine();
                    String[] userArray = user.split(":");
                    User existingUser = new Customer(userArray[0], userArray[1], Integer.parseInt(userArray[2]), true);
                    list_of_user.add(existingUser);
                }
            } catch (FileNotFoundException e) {
                try {
                    File file = new File("Userlist.txt");
                    file.createNewFile();
                } catch (IOException a) {
                    System.out.println(a.getMessage());
                }
            }
           fileNotCreated+=1;
        } while (fileNotCreated>1);
    }

    public static User loginCheck(String username, String password) {
        read_user_from_file();
        for (User targetUser : list_of_user) {
            if (username.equals(targetUser.getUsername())) {
                if (password.equals(targetUser.getPassword())) {
                    return targetUser;
                }
            }
        }
        return null;
    }

    private static User getUser(int count) {
        return list_of_user.get(count);
    }

    public static boolean duplicateName(String username) {
        read_user_from_file();
        for (User user : list_of_user) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<ReceiptList> readOrder() {

        ArrayList<ReceiptList> allreceipt = new ArrayList<ReceiptList>();
        try {
            File file = new File("Orderlist.txt");
            Scanner file_reader = new Scanner(file);
            while (file_reader.hasNextLine()) {
                String order = file_reader.nextLine();
                String[] seperated_order = order.split(":");
                String username = seperated_order[0];
                ReceiptList receiptList = new ReceiptList(username);
                int order_count = Integer.parseInt(seperated_order[1]);
                receiptList.setOrdercount(order_count);
                for (int i = 2; i <12 ; i += 4) {
                    String food_name = seperated_order[i];
                    double food_price = Double.parseDouble(seperated_order[i + 1]);
                    int food_quantity = Integer.parseInt(seperated_order[i + 2]);
                    MenuItem item = new MenuItem(food_name, food_price, food_quantity);
                    receiptList.addItem(item);
                }
                receiptList.setFeedback(seperated_order[(order_count * 4) + 2]);
                receiptList.setDate(LocalDate.parse(seperated_order[(order_count * 4) + 3]));
                allreceipt.add(receiptList);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return allreceipt;
    }
}
