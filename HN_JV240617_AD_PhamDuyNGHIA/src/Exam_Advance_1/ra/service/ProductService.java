package Exam_Advance_1.ra.service;

import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService implements IGenericService<Product, String> {

    private final List<Product> products = new ArrayList<>();
    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            products.add(product);
        } else {
            products.set(products.indexOf(findById(product.getProductId())), product);
        }
    }

    @Override
    public Product findById(String s) {
        for (Product p : products) {
            if (p.getProductId().equals(s)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void delete(String s) {
        if (findById(s) != null) {
            products.remove(findById(s));
        } else {
            System.err.println("Không có sản phẩm này");
        }
    }

    public String getNewIdProduct() {

        String idProduct = "P";
        int maxId = 0;
        for (Product product : products) {
            if (product != null) {
                int productId = Integer.parseInt(product.getProductId().replace("P", "0"));
                if (maxId < productId) {
                    maxId = productId;
                }
            }
        }
        maxId += 1;
        String newId = Integer.toString(maxId);
        if (newId.length() == 1) {
            idProduct += "0" + 0  + newId;
        }
        if (newId.length() == 2) {
            idProduct += "0" + newId;
        }
        if (newId.length() == 3) {
            idProduct = newId;
        }
        return idProduct;

    }

    // Giỏ hàng tăng
    public void ProductIncreases (Product product) {
        product.setStock(product.getStock() + 1);
    }
    // Giỏ hàng giảm
    public void ProductReduce (Product product) {
        product.setStock(product.getStock() - 1);
    }
    // Tăng Giỏ hàng theo số lượng
    public void ProductIncreasesByNumber (Product product, int number) {
        product.setStock(product.getStock() + number);
    }
    //Giảm Giỏ hàng theo số lượng
    public void ProductReduceByNumber (Product product, int number) {
        product.setStock(product.getStock() - number);
    }
}