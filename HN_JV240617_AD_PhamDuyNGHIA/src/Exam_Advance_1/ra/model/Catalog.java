package Exam_Advance_1.ra.model;

import java.util.Scanner;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String description;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String description) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void inputData(Scanner scanner) {
        this.catalogName = inputCatalogName(scanner);
        this.description = inputDescription(scanner);
    }

    public String inputCatalogName(Scanner scanner) {
        System.out.println("Mời bạn nhập vào tên danh mục: ");
        do {
            String catalogName = scanner.nextLine();
            if(catalogName.trim().isEmpty()) {
                System.err.println(" Tên danh mục không được để trống!.");
            }
            else {
                return catalogName;
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Mời bạn nhập vào mô tả danh mục: ");
        do {
            String description = scanner.nextLine();
            if(description.trim().isEmpty()) {
                System.err.println(" Mô tả danh mục không được để trống!.");
            }
            else {
                return description;
            }
        } while (true);
    }


    @Override
    public String toString() {
        return "Catalog [  " +
                " catalogId= " + this.catalogId + "   |   " +
                " catalogName= " + this.catalogName + "   |   " +
                " description= " + this.description +
                "   ]";
    }
}