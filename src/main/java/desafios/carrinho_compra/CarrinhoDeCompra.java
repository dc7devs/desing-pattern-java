package desafios.carrinho_compra;
// Design pattern: Strategy

import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

interface DiscountStrategy {
    double applyDiscount(double total);
}

// Strategies:
class TenPercentDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.9;
    }
}

class FreeShipping implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        System.out.println("Frete GRÁTIS");
        return total;
    }
}

// Generator Context
class ShoppingCart {
    private final List<Product> products = new ArrayList<>();
    private DiscountStrategy discountStrategy;

    public void addProduct(Product product) {
        if(product == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo!");
        }

        products.add(product);
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateTotal() {
        Double totalProduct = products.stream()
                .map(Product::getPrice)
                .toList()
                .stream().reduce(0.0, Double::sum);

        return discountStrategy.applyDiscount(totalProduct);  // : Total calculado
    }
}

public class CarrinhoDeCompra {
    public static void main(String[] args) {
        DiscountStrategy tenPercentDiscount = new TenPercentDiscount();
        DiscountStrategy freeShipping = new FreeShipping();

        // Carrinho de compras
        ShoppingCart cart = new ShoppingCart();

        String productName = args[0];
        double productPrice = Double.parseDouble(args[1]);

        cart.addProduct(new Product(
            productName,
            productPrice
        ));

        cart.setDiscountStrategy(tenPercentDiscount);
        System.out.println("Total da compra: R$"+cart.calculateTotal()+" (10% off)\n");

        cart.setDiscountStrategy(freeShipping);
        System.out.println("Total da compra: R$"+cart.calculateTotal());
    }
}
