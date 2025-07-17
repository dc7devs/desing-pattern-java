package desafios.gerenciar_usuario;
// Design pattern: Singleton, Focade

import java.util.*;

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
    private int nextId = 1;

    private UserManager() {
        super();
    }

    public static UserManager getInstancia() {
        if(instancia == null) {
            instancia = new UserManager();
        }

        return instancia;
    }

    public void registerUser(Map<Integer, User> userMap, String userName) {
        User newUser = new User(nextId, userName);
        userMap.put(nextId, newUser);
        nextId++;
    }

    public void listUsers(Map<Integer, User> userMap) {
        userMap.values().forEach(u -> {
            System.out.printf("%d - %s%n", u.getId(), u.getName());
        });
    }
}

public class GerenciarUsuario {
    private static final Map<Integer, User> users = new HashMap<>();
    private static final UserManager userManager = UserManager.getInstancia();

    public static void addUser(String name) {
        userManager.registerUser(users, name);
    }

    public static void listUsers() {
        userManager.listUsers(users);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: <nome1> [<nome2> ...]");
            return;
        }

        for (String arg : args) {
            addUser(arg);
        }

        listUsers();
    }
}
