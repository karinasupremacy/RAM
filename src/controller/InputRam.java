package controller;

import MyTools.MyTools;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class InputRam {
    public static final Scanner sc = new Scanner(System.in);
    
    
    
    public String inputRamType() {
        String type;
        do {
            type = MyTools.readStr("Enter RAM type (e.g., DDR4, DDR5, LPDDR4, LPDDR5)").trim().toUpperCase();
            if (!type.matches("(DDR4|DDR5|LPDDR4|LPDDR5)")) {
                System.out.println("Invalid RAM type. Please enter DDR4, DDR5, LPDDR4, or LPDDR5.");
            }
        } while (!type.matches("(DDR4|DDR5|LPDDR4|LPDDR5)"));
        return type;
    }
    
    
    public String inputBusSpeed() {
        String bus;
        boolean check = false;
        do {
            System.out.println("Input Ram's Bus: ");
            bus = sc.nextLine();
            if (bus.matches("[0-9]{4}MHZ")) {
                check = true;
            } else {
                System.out.println("Invalid Bus!!! 4 number + MHZ");
            }
        } while (!check);
        return bus;
    }
    
    
    public String inputRamBrand() {
        String brand;
        do {
            System.out.println("Enter RAM brand: ");
            brand = sc.nextLine().trim();
            if (brand.isEmpty()) {
                System.out.println("Brand cannot be empty.");
            }
        } while (brand.isEmpty());
        return brand;
    }
    public int inputQuantity() {
        int quantity = 0;
        boolean valid = false;
        while(!valid) {
            try {
                System.out.println("Enter quantity: ");
                quantity = Integer.parseInt(sc.nextLine());
                if(quantity > 0) {
                    valid = true;
                } else {
                    System.out.println("Quantity must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return quantity;
    }
    
    public String inputProduction_month_year() {
        System.out.println("Enter Production Date(MM/YYYY): ");
        String input;
        YearMonth productionDate;
        YearMonth currentDate = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        while (true) {
            
            input = sc.nextLine().trim();

            try {
                productionDate = YearMonth.parse(input, formatter);

                // Check if the year is between 2000 and current year
                if (productionDate.getYear() < 2000 || 
                (productionDate.getYear() == currentDate.getYear() && productionDate.getMonthValue() > currentDate.getMonthValue()) || 
                productionDate.getYear() > currentDate.getYear()) {
                    System.out.println("Invalid year. Year must be between 2000 and " + currentDate.getYear());
                    continue;
                }

               
                return input;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use MM/YYYY format.");
            }
        }
    }
}

