package controller;

import MyTools.MyTools;
import java.io.IOException;
import models.RamItems;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Collections;

public class RAMManagementSystem {
    FileController fc = new FileController();
    public static final Scanner sc = new Scanner(System.in);
    private List<RamItems> listRam;  // List of all RAM items
    private List<String> ramTypes;  // Supported RAM types
    private InputRam input;  // Class to handle input operations
    private int count = 0;  // Counter to generate unique RAM codes
    
    public RAMManagementSystem() throws IOException, ClassNotFoundException {
        listRam = new ArrayList<>();
        input = new InputRam();
        ramTypes = new ArrayList<>();
        // Add supported RAM types
        ramTypes.add("DDR4");
        ramTypes.add("DDR5");
        ramTypes.add("LPDDR4");
        ramTypes.add("LPDDR5");
        loadFromFile(); 
    }

    // Generate a RAM code based on type and counter
    private String generateRamCode(String type) {
        return "RAM" + type.charAt(0) + "_" + count++;
    }

    // Add a new RAM item to the system
    public void addRam() {
        // Display available RAM types
        System.out.println("Available RAM types: " + String.join(", ", ramTypes));
        System.out.print("Enter RAM type: ");
        String type = sc.nextLine().trim().toUpperCase();

        // Validate RAM type
        if (!ramTypes.contains(type)) {
            System.out.println("Invalid RAM type. RAM not added.");
            return;
        }

        // Generate code for the new RAM item
        String code = generateRamCode(type);
        System.out.println("Generated RAM code: " + code);

        // Input other RAM details
        String bus = input.inputBusSpeed();
        String brand = input.inputRamBrand();
        int quantity = input.inputQuantity();
        String productionDate = input.inputProduction_month_year(); 

        // Create new RAM item and add to the list
        RamItems newRam = new RamItems(code, type, bus, brand, quantity, productionDate, true);
        listRam.add(newRam);
        System.out.println("RAM added successfully: " + newRam);
    }

    public void searchRam() {
        int choice;
        do {
            try {
                System.out.println("How do you want to search?");
                choice = MyTools.menu("Search by Type", "Search by Bus", "Search by Brand", "Return to Main Menu");
                switch (choice) {
                    case 1:
                        searchByType();
                        break;
                    case 2:
                        searchByBus();
                        break;
                    case 3:
                        searchByBrand();
                        break;
                    case 4:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                choice = 0;
            }
        } while (choice != 4);
    }

    private void searchByType() {
        String type = input.inputRamType();
        List<RamItems> foundItems = listRam.stream()
                .filter(ramItem -> ramItem.getType().equalsIgnoreCase(type) && ramItem.isActive())
                .collect(Collectors.toList());

        if (foundItems.isEmpty()) {
            System.out.println("No RAM found for the specified type.");
        } else {
            foundItems.forEach(System.out::println);
        }
    }

    private void searchByBus() {
        String bus = input.inputBusSpeed();
        List<RamItems> foundItems = listRam.stream()
                .filter(ramItem -> ramItem.getBus().equalsIgnoreCase(bus) && ramItem.isActive())
                .collect(Collectors.toList());

        if (foundItems.isEmpty()) {
            System.out.println("No RAM found for the specified bus speed.");
        } else {
            foundItems.forEach(System.out::println);
        }
    }

    private void searchByBrand() {
        String brand = input.inputRamBrand();
        List<RamItems> foundItems = listRam.stream()
                .filter(ramItem -> ramItem.getBrand().equalsIgnoreCase(brand) && ramItem.isActive())
                .collect(Collectors.toList());

        if (foundItems.isEmpty()) {
            System.out.println("No RAM found for the specified brand.");
        } else {
            foundItems.forEach(System.out::println);
        }
    }

    public void updateRam() {
        String code = MyTools.readStr("Enter RAM code");
        RamItems ramToUpdate = listRam.stream()
                .filter(ramItem -> ramItem.getCode().equals(code) && ramItem.isActive())
                .findFirst()
                .orElse(null);

        if (ramToUpdate == null) {
            System.out.println("RAM not found.");
            return;
        }

        int choice;
        do {
            System.out.println("Choose update option: ");
            choice = MyTools.menu("Update specific information", "Update all information", "Quit");
            switch (choice) {
                case 1:
                    updateSpecificInfo(ramToUpdate);
                    break;
                case 2:
                    updateAllInfo(ramToUpdate);
                    break;
                case 3:
                    System.out.println("Update cancelled.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);

        saveToFile();
    }

        private void updateSpecificInfo(RamItems ram) {
        int choice = MyTools.menu("Type", "Bus", "Brand", "Quantity", "Month/Year");
        switch (choice) {
            case 1:
                ram.setType(input.inputRamType());
                break;
            case 2:
                ram.setBus(input.inputBusSpeed());
                break;
            case 3: 
                ram.setBrand(input.inputRamBrand());
                break;
            case 4: 
                ram.setQuantity(input.inputQuantity());
                break;
            case 5:
                ram.setProductionMonthYear(input.inputProduction_month_year());
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        } 
        System.out.println("RAM updated successfully.");
    }
    
         private void updateAllInfo(RamItems ram) {
        ram.setType(input.inputRamType());
        ram.setBus(input.inputBusSpeed());
        ram.setBrand(input.inputRamBrand());
        ram.setQuantity(input.inputQuantity());
        ram.setProductionMonthYear(input.inputProduction_month_year());
        System.out.println("RAM updated successfully.");
    }   
    public void deleteRam() {
        String code = MyTools.readStr("Enter RAM code to delete");
        RamItems ramToDelete = listRam.stream()
                .filter(ramItem -> ramItem.getCode().equals(code) && ramItem.isActive())
                .findFirst()
                .orElse(null);

        if (ramToDelete != null) {
            ramToDelete.setActive(false);
            System.out.println("RAM deleted successfully.");
            saveToFile();
        } else {
            System.out.println("RAM not found.");
        }
    }
    
public void displayAll() {
       if(listRam.isEmpty()) {
           System.out.println("No RAM items available to display.");
           return;
       }
       
    // Sort type, bus, brand RAM
    Collections.sort(listRam, new Comparator<RamItems>() {
        @Override
        public int compare(RamItems r1, RamItems r2) {
            // So sánh bằng Type
            int typeComparison = r1.getType().compareToIgnoreCase(r2.getType());
            if (typeComparison != 0) {
                return typeComparison;
            }

            // Type giống -> Bus
            int busComparison = r1.getBus().compareToIgnoreCase(r2.getBus());
            if (busComparison != 0) {
                return busComparison;
            }

            // Type, bus y chang -> Brand
            return r1.getBrand().compareToIgnoreCase(r2.getBrand());
        }
    });

  
    System.out.println("\n=== RAM Inventory ===");
    for (RamItems ramItem : listRam) {
        if (ramItem.isActive()) {    // Display only active RAM items
            System.out.println("Code: " + ramItem.getCode() +
                               ", Type: " + ramItem.getType() +
                               ", Bus: " + ramItem.getBus() +
                               ", Brand: " + ramItem.getBrand() +
                               ", Quantity: " + ramItem.getQuantity() +
                               ", Production Month/Year: " + ramItem.getProductionMonthYear());
        }
    }
}

    
    public void saveToFile(){
        fc.saveToFile(listRam);
        System.out.println("Succesfully save");
    }
    
    private void loadFromFile() {
      try {
        listRam = FileController.loadFromFile(); // Cố gắng tải file
    } catch (IOException | ClassNotFoundException e) {
        // Xử lý các lỗi liên quan đến IO và tuần tự hóa không thành công
        System.err.println("Failed to load products from file: " + e.getMessage());
        // Khởi tạo danh sách mới trong trường hợp không thể tải file
        listRam = new ArrayList<>();
    }
}

}
