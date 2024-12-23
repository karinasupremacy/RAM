package models;

import java.io.Serializable;

public class RamItems  implements Serializable {
   private static final long serialVersionUID = 1L;
    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private String productionMonthYear;
    private boolean active;

    public RamItems(String code, String type, String bus, String brand, int quantity, String productionMonthYear, boolean active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.productionMonthYear = productionMonthYear;
        this.active = active;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductionMonthYear() {
        return productionMonthYear;
    }

    public void setProductionMonthYear(String productionMonthYear) {
        this.productionMonthYear = productionMonthYear;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "RamItems: " + "code: " + code + ", type:" + type + ", bus:" + bus + ", brand:" + brand + ", quantity:" + quantity + ", productionMonthYear:" + productionMonthYear + ", active:" + active;
    }
    
    
}
