/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import MyTools.MyTools;
import controller.RAMManagementSystem;
import java.io.IOException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) throws IOException, ClassNotFoundException {
        RAMManagementSystem ramManagementSystem = new RAMManagementSystem();
        
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                choice = MyTools.menu("Add a RAM", "Search SubMenu", "Update RAM Information", 
                                      "Delete RAM",  "Show all RAM from file", "Save RAM to file"
                                      , "Quit");
                
                switch (choice) {
                    case 1:
                        ramManagementSystem.addRam();
                        break;
                    case 2:
                        ramManagementSystem.searchRam();
                        break;
                    case 3:
                        ramManagementSystem.updateRam();
                        break;
                    case 4:
                        ramManagementSystem.deleteRam();
                        break;
                    case 5: 
                        ramManagementSystem.displayAll();
                        break;
                    case 6:
                        ramManagementSystem.saveToFile();
                        break;
                    case 7:
                        System.out.println("Thank you for using Laptop RAM Management System");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (choice != 7); 
    }  
}
