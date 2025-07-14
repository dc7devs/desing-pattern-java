package desafios.conversor_moeda;
// Design pattern: Adapter

// Antiga classe de conversão que só suporta a conversão de USD para GBP
class OldCurrencyConverter {
    public double convertUSDtoGBP(double amount) {
        return amount * 0.80; // 80% do valor em USD
    }
}

// Novo adaptador que usa a antiga conversão e aplica a taxa adicional de GBP para EUR
class CurrencyAdapter {
    private final OldCurrencyConverter oldConverter;

    public CurrencyAdapter(OldCurrencyConverter oldConverter) {
        this.oldConverter = oldConverter;
    }

    public double convertUSDtoEUR(double amount) {
        return amount * 0.85;
    }
}


public class ConversorMoeda {
    public static void main(String[] args) {
        OldCurrencyConverter oldConverter = new OldCurrencyConverter();
        CurrencyAdapter adapter = new CurrencyAdapter(oldConverter);

        for (String entrie : args) {
            double input = Double.parseDouble(entrie);
            double output = adapter.convertUSDtoEUR(input);

            System.out.println("--------");
            System.out.println("USD: " + input);
            System.out.println("EUR: " + output);
        }
    }
}
