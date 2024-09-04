package Exam_Advance_1.ra.model;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Product implements  Comparable<Product>{
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status = true;

    public Product() {
    }

    public Product(boolean status, Catalog catalog, int stock, String description, double productPrice, String productName, String productId) {
        this.status = status;
        this.catalog = catalog;
        this.stock = stock;
        this.description = description;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData (Scanner scanner, List<Catalog> list) {
        this.productName = inputProductName(scanner);
        this.productPrice = inputProductPrice(scanner);
        this.description = inputDescription(scanner);
        this.stock = inputStock(scanner);
        for (Catalog c : list) {
            System.out.println(c);
        }
        while (true) {
            boolean flag = false;
            System.out.print("Vui lòng chọn ID danh mục: ");
            int id = Integer.parseInt(scanner.nextLine());
            for (Catalog c : list) {
                if (c.getCatalogId() == id) {
                    catalog = c;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            } else {
                System.err.println("Không có danh mục đó, Vui lòng chọn lại: ");
            }
        }
    }


    //    stock - int (ít nhất là 10)
    public int inputStock(Scanner scanner) {
        int stock;
        while (true) {
            try {
                System.out.print("Nhập vào stock (lớn hơn hoặc bằng 10): ");
                stock = Integer.parseInt(scanner.nextLine());
                if (stock >= 10) {
                    break;
                } else {
                    System.err.println("Số lượng stock phải lớn hơn hoặc bằng 10. Vui lòng nhập lại.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Số lượng stock không hợp lệ. Vui lòng nhập lại.");
            }
        }
        return stock;
    }


    //description - String
    public String inputDescription(Scanner scanner) {
        System.out.println("Mời bạn nhập vào mô tả sản phẩm: ");
        return scanner.nextLine();
    }

    //productPrice - double (phải lớn hơn 0)
    public double inputProductPrice(Scanner scanner) {
        double productPrice;
        System.out.print("Mời bạn nhập vào giá sản phẩm: ");
        while (true) {
            String importPrice = scanner.nextLine();
            try {
                productPrice = Double.parseDouble(importPrice);
                if (productPrice > 0) {
                    return productPrice;
                } else {
                    System.err.println("Giá sản phẩm phải lớn hơn 0. Vui lòng nhập lại: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Giá không hợp lệ. Vui lòng nhập lại: ");
            }
        }
    }

    //productName - String (Không được để trống )
    public String inputProductName(Scanner scanner) {
        System.out.println("Mời bạn nhập vào tên sản phẩm: ");
        do {
            String productName = scanner.nextLine();
            if (productName.trim().isEmpty()) {
                System.err.println("* Tên sản phẩm không được để trống");
            } else {
                return productName;
            }
        } while (true);
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId= " + this.productId + "| " +
                "productName= " + this.productName + "                  | " +
                "productPrice= " + this.productPrice + "     | " +
                "description= " + this.description + "      | " +
                "stock= " + this.stock + "    | " +
                "catalog= " + this.catalog.getCatalogName() + "        | " +
                "status= " + (status ? "Bán" : "Không bán") +
                "     }";
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(o.getProductPrice(), this.productPrice);
    }
}