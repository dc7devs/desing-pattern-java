package desafios.gerenciar_usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private final int id;
    private final String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


class UserManager {
    private static UserManager instancia;
    
    private UserManager() {
        super();
    }

    public static UserManager getInstancia() {
        if(instancia == null) {
            instancia = new UserManager();
        }

        return instancia;
    }

    public void registerUser(List<User> users, String userName) {
        int id = users.size() +1;
        User new_user = new User(id, userName);

        users.add(new_user);
    }

    public void listUsers(List<User> users) {
        users.forEach(u -> {
            System.out.println(String.format("%d - %s", u.getId(), u.getName()));
        });
    }
}

public class GerenciarUsuarioMain {

    private static final List<User> users = new ArrayList<>();
    private static UserManager userManager = UserManager.getInstancia();

    public static void addUser(String name) {
        userManager.registerUser(users, name);
    }

    public static void listUsers() {
        userManager.listUsers(users);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantity = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= quantity; i++) {
            String name = scanner.nextLine();
            addUser(name);
        }

        listUsers();
    }
}
