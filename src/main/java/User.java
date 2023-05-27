import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//The abstract class for all users
abstract class User {

    private String username;
    private String password;
    private int role;

    public User() {
    }

    public User(String username, String password , int role, boolean read_only) {
        this.username = username;
        this.password = password;
        this.role = role;
        if (!read_only) {
            try {
                File userlist = new File("Userlist.txt");
                FileWriter fwriter = new FileWriter(userlist, true);
                if (userlist.length()==0) {
                    fwriter.write(this.username + ":" + this.password + ":"  + this.role);
                } else {
                    fwriter.write("\n" + this.username + ":" + this.password + ":" +  this.role);
                }
                fwriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public int getRole() {
        return role;
    }

}
