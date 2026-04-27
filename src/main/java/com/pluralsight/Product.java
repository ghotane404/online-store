package com.pluralsight;

public class Product {
    private String sku;
    private String productName;
    private Double price;
    private String department;

    Product(String sku, String productName, Double price, String department){
         this.sku = sku;
         this.productName = productName;
         this.price = price;
         this.department = department;
    }

    //getters
    public String getSku(){
        return sku;
    }

    public String getProductName(){
        return productName;
    }

    public Double getPrice(){
        return price;
    }

    public String getDepartment(){
        return department;
    }

    //setters
    public void setSku(String newSku){
        this.sku = newSku;
    }

    public void setProductName(String newProductName){
        this.productName = newProductName;
    }

    public void setPrice(Double newPrice){
        this.price = newPrice;
    }

    public void setDepartment(String newDepartment){
        this.department = newDepartment;
    }
}