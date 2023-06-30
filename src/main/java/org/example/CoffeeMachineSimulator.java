package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachineSimulator {
    private int water;
    private int milk;
    private int beans;
    private int money;
    private Map<String, Integer> sales;
    private Map<String, Integer> prices;
    private Map<String, Map<String, Integer>> ingredients;

    public CoffeeMachineSimulator() {
        water = 0;
        milk = 0;
        beans = 0;
        money = 0;
        sales = new HashMap<>();
        prices = new HashMap<>();
        ingredients = new HashMap<>();

        // Initialize coffee types, prices, and ingredients
        sales.put("Espresso", 0);
        sales.put("Latte", 0);
        sales.put("Cappuccino", 0);

        prices.put("Espresso", 4);
        prices.put("Latte", 7);
        prices.put("Cappuccino", 6);

        Map<String, Integer> espressoIngredients = new HashMap<>();
        espressoIngredients.put("water", 250);
        espressoIngredients.put("milk", 0);
        espressoIngredients.put("beans", 16);

        Map<String, Integer> latteIngredients = new HashMap<>();
        latteIngredients.put("water", 350);
        latteIngredients.put("milk", 75);
        latteIngredients.put("beans", 20);

        Map<String, Integer> cappuccinoIngredients = new HashMap<>();
        cappuccinoIngredients.put("water", 200);
        cappuccinoIngredients.put("milk", 100);
        cappuccinoIngredients.put("beans", 12);

        ingredients.put("Espresso", espressoIngredients);
        ingredients.put("Latte", latteIngredients);
        ingredients.put("Cappuccino", cappuccinoIngredients);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("=== Coffee Machine Simulator ===");
            System.out.println("1. Buy");
            System.out.println("2. Fill");
            System.out.println("3. Take");
            System.out.println("4. Show");
            System.out.println("5. Analytics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    buyCoffee(scanner);
                    break;
                case 2:
                    fillIngredients(scanner);
                    break;
                case 3:
                    takeMoney();
                    break;
                case 4:
                    showIngredients();
                    break;
                case 5:
                    showAnalytics();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    private void buyCoffee(Scanner scanner) {
        System.out.println("Available Coffee Types:");
        System.out.println("1. Espresso");
        System.out.println("2. Latte");
        System.out.println("3. Cappuccino");
        System.out.print("Enter the number corresponding to your desired coffee type: ");
        int coffeeType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String selectedCoffee;
        switch (coffeeType) {
            case 1:
                selectedCoffee = "Espresso";
                break;
            case 2:
                selectedCoffee = "Latte";
                break;
            case 3:
                selectedCoffee = "Cappuccino";
                break;
            default:
                System.out.println("Invalid coffee type. Please try again.");
                return;
        }

        if (hasEnoughIngredients(selectedCoffee)) {
            water -= ingredients.get(selectedCoffee).get("water");
            milk -= ingredients.get(selectedCoffee).get("milk");
            beans -= ingredients.get(selectedCoffee).get("beans");
            money += prices.get(selectedCoffee);
            sales.put(selectedCoffee, sales.get(selectedCoffee) + 1);
            System.out.println("Dispensing " + selectedCoffee + ". Enjoy your coffee!");
        } else {
            System.out.println("Insufficient ingredients to make this coffee.");
        }
    }

    private boolean hasEnoughIngredients(String coffeeType) {
        Map<String, Integer> coffeeIngredients = ingredients.get(coffeeType);
        return water >= coffeeIngredients.get("water") &&
                milk >= coffeeIngredients.get("milk") &&
                beans >= coffeeIngredients.get("beans");
    }

    private void fillIngredients(Scanner scanner) {
        System.out.print("Enter the amount of water to fill: ");
        int addedWater = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the amount of milk to fill: ");
        int addedMilk = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the amount of beans to fill: ");
        int addedBeans = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        water += addedWater;
        milk += addedMilk;
        beans += addedBeans;
    }

    private void takeMoney() {
        System.out.println("Taking $" + money + " from the money box.");
        money = 0;
    }

    private void showIngredients() {
        System.out.println("Water: " + water + " ml");
        System.out.println("Milk: " + milk + " ml");
        System.out.println("Beans: " + beans + " units");
        System.out.println("Money: $" + money);
    }

    private void showAnalytics() {
        System.out.println("=== Coffee Machine Analytics ===");
        System.out.println("Coffee Sales:");
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " cups");
        }
        System.out.println("Total Earnings: $" + money);
        System.out.println("Ingredients Consumed:");
        for (Map.Entry<String, Map<String, Integer>> entry : ingredients.entrySet()) {
            String coffeeType = entry.getKey();
            Map<String, Integer> coffeeIngredients = entry.getValue();
            System.out.println(coffeeType + ":");
            System.out.println("Water: " + (coffeeIngredients.get("water") * sales.get(coffeeType)) + " ml");
            System.out.println("Milk: " + (coffeeIngredients.get("milk") * sales.get(coffeeType)) + " ml");
            System.out.println("Beans: " + (coffeeIngredients.get("beans") * sales.get(coffeeType)) + " units");
        }
    }

    public static void main(String[] args) {
        CoffeeMachineSimulator coffeeMachine = new CoffeeMachineSimulator();
        coffeeMachine.run();
    }
}
