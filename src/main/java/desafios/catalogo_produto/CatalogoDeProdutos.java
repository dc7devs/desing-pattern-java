package desafios.catalogo_produto;
// Design pattern: Observer

import java.util.ArrayList;
import java.util.List;

enum CatalogAction {
    ADDED, REMOVED
}

record CatalogEvent(Product product, CatalogAction action) {
}

// Interface Observer
interface Observer {
    void update(CatalogEvent action);
}

// Observers:
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(CatalogEvent event) {
        String actionText = switch (event.action()) {
            case ADDED -> "adicionado";
            case REMOVED -> "removido";
        };

        Product p = event.product();
        System.out.printf("%s foi notificado: Produto %s - %s (R$ %.2f)%n",
                name, actionText, p.getName(), p.getPrice());
    }
}
class Email implements Observer {
    private String email;

    Email(String email) {
        this.email = email;
    }

    @Override
    public void update(CatalogEvent event) {
        String actionText = switch (event.action()) {
            case ADDED -> "adicionado";
            case REMOVED -> "removido";
        };

        Product p = event.product();
        System.out.printf("%s foi notificado: Produto %s - %s (R$ %.2f)%n",
                email, actionText, p.getName(), p.getPrice());

    }
}

class Product {
    private String name;
    private String description;
    private double price;

    Product(String name, String description, double price) {
        this.name = name; this.description = description; this.price = price;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}

interface CatalogObservable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(CatalogEvent event);
}

class Catalog implements CatalogObservable {
    private List<Observer> observers = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(CatalogEvent event) {
        observers.forEach(observer -> {
            observer.update(event);
        });
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Produto não pode ser nulo");

        products.add(product);
        notifyObservers(new CatalogEvent(product, CatalogAction.ADDED));
    }

    public void removeProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Produto não pode ser nulo");

        if (products.remove(product))
            notifyObservers(new CatalogEvent(product, CatalogAction.REMOVED));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}

public class CatalogoDeProdutos {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java CatalogoDeProdutos <nome> <descricao> <preco>");
            return;
        }

        Catalog catalog = new Catalog();
        User user = new User("Roy");
        Email email = new Email("alberteinstein@gmail.com");

        // Inscricão de usuários em catálogo
        catalog.addObserver(user);
        catalog.addObserver(email);

        //  Adicionando novo produto!!
        String name = args[0];
        String description = args[1];
        double price = Double.parseDouble(args[2]);
        var novoProduto = new Product(name, description, price);

        catalog.addProduct(novoProduto);

        catalog.getAllProducts()
            .forEach(p -> { System.out.printf("\n - %s | R$ %.2f%n\n", p.getName(), p.getPrice());
        });

        catalog.removeProduct(novoProduto);
    }
}
