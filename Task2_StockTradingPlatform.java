import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {

    HashMap<String, Integer> holdings = new HashMap<>();
    double balance = 10000;

    public void buyStock(String symbol, int qty, double price) {

        double cost = qty * price;

        if (balance >= cost) {
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + qty);
            balance -= cost;
            System.out.println("Successfully bought " + qty + " shares of " + symbol);
        } else {
            System.out.println("Not enough balance.");
        }
    }

    public void sellStock(String symbol, int qty, double price) {

        int owned = holdings.getOrDefault(symbol, 0);

        if (owned >= qty) {
            holdings.put(symbol, owned - qty);
            balance += qty * price;
            System.out.println("Successfully sold " + qty + " shares of " + symbol);
        } else {
            System.out.println("You don't have enough shares.");
        }
    }

    public void showPortfolio() {

        System.out.println("\n===== YOUR PORTFOLIO =====");

        if (holdings.isEmpty()) {
            System.out.println("No stocks owned.");
        }

        for (String s : holdings.keySet()) {
            System.out.println("Stock: " + s + " | Shares: " + holdings.get(s));
        }

        System.out.println("Available Balance: $" + balance);
    }
}

public class Task2_StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Portfolio portfolio = new Portfolio();

        ArrayList<Stock> market = new ArrayList<>();

        market.add(new Stock("APPLE", 190));
        market.add(new Stock("GOOGLE", 2700));
        market.add(new Stock("TESLA", 250));

        while (true) {

            System.out.println("\n===== STOCK MARKET =====");
            System.out.println("Available Stocks:");

            for (Stock s : market) {
                System.out.println("Stock: " + s.symbol + " | Price: $" + s.price);
            }

            System.out.println("\nChoose an option:");
            System.out.println("1 → Buy Stock");
            System.out.println("2 → Sell Stock");
            System.out.println("3 → View Portfolio");
            System.out.println("4 → Exit");
            System.out.println();

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.print("Enter stock symbol to BUY: ");
                String sym = sc.next();

                System.out.print("Enter quantity to buy: ");
                int qty = sc.nextInt();

                boolean found = false;

                for (Stock s : market) {
                    if (s.symbol.equalsIgnoreCase(sym)) {
                        portfolio.buyStock(sym.toUpperCase(), qty, s.price);
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Stock not found.");
                }

            } else if (choice == 2) {

                System.out.print("Enter stock symbol to SELL: ");
                String sym = sc.next();

                System.out.print("Enter quantity to sell: ");
                int qty = sc.nextInt();

                boolean found = false;

                for (Stock s : market) {
                    if (s.symbol.equalsIgnoreCase(sym)) {
                        portfolio.sellStock(sym.toUpperCase(), qty, s.price);
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Stock not found.");
                }

            } else if (choice == 3) {

                portfolio.showPortfolio();

            } else if (choice == 4) {

                System.out.println("Exiting trading platform...");
                break;

            } else {

                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}