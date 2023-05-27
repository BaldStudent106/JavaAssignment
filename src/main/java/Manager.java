//The class for manager
public class Manager extends User implements Callmenu{
    public Manager(String username, String password, int role, boolean read_only) {
        super(username, password, role, read_only);
    }

    @Override
    public void callmenu(){
        ManagerMenu managerMenu = new ManagerMenu();
    };
}
