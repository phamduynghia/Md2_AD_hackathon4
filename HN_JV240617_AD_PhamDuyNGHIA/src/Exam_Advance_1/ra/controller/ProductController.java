package Exam_Advance_1.ra.controller;
import Exam_Advance_1.ra.model.Product;
import Exam_Advance_1.ra.service.ProductService;

import java.util.List;

public class ProductController implements IGenericController <Product, String> {
    private ProductService productService = new ProductService();

    @Override
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Override
    public void save(Product product) {
        productService.save(product);
    }

    @Override
    public Product findById(String s) {
        return productService.findById(s);
    }

    @Override
    public void delete(String s) {
        productService.delete(s);
    }

    public String getNewIdProduct() {
        return productService.getNewIdProduct();
    }

    // Giỏ hàng tăng
    public void ProductIncreases (Product product) {
        productService.ProductIncreases(product);
    }
    // Giỏ hàng giảm
    public void ProductReduce (Product product) {
        productService.ProductReduce(product);
    }
    // Tăng Giỏ hàng theo số lượng
    public void ProductIncreasesByNumber (Product product, int number) {
        productService.ProductIncreasesByNumber(product, number);
    }
    //Giảm Giỏ hàng theo số lượng
    public void ProductReduceByNumber (Product product, int number) {
        productService.ProductReduceByNumber(product, number);
    }
}