package Exam_Advance_1.ra.run;
import Exam_Advance_1.ra.controller.CatalogController;
import Exam_Advance_1.ra.controller.ProductController;
import Exam_Advance_1.ra.model.Product;
import java.util.Collections;
import java.util.Scanner;

public class ProductManagement {
    private final ProductController productController;
    private final CatalogController catalogController;

    public ProductManagement(ProductController productController, CatalogController catalogController) {
        this.productController = productController;
        this.catalogController = catalogController;
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("********************************PRODUCT MENU*********************************");
            System.out.println("*                                                                           *");
            System.out.println("*     1.     Nhập số sản sản phẩm và nhập thông tin sản phẩm                *");
            System.out.println("*     2.     Hiển thị thông tin các sản phẩm                                *");
            System.out.println("*     3.     Sắp xếp sản phẩm theo giá giảm dần                             *");
            System.out.println("*     4.     Xóa sản phẩm theo mã                                           *");
            System.out.println("*     5.     Tìm kiếm sách theo tên sách                                    *");
            System.out.println("*     6.     Thay đổi thông tin của sách theo mã sách                       *");
            System.out.println("*     7.     Quay lại                                                       *");
            System.out.println("*                                                                           *");
            System.out.println("*****************************************************************************");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputAddNewProduct(scanner);
                    break;
                case 2:
                    printListProduct();
                    break;
                case 3:
                    Collections.sort(productController.getAll());
                    break;
                case 4:
                    inputDeleteProduct(scanner);
                    break;
                case 5:
                    searchProductByName(scanner);
                    break;
                case 6:
                    inputUpdateProduct(scanner);
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-7!.");
            }
        } while (isExit);
    }

    public void inputAddNewProduct(Scanner scanner) {
        System.out.println("Mời bạn nhập vào số lượng sản phẩm: ");
        int countProduct = Integer.parseInt(scanner.nextLine());
        for (int i = 0 ; i < countProduct; i++)  {
            System.out.println("Sản phẩm thứ " + (i +1));
            Product product = new Product();
            product.setProductId(productController.getNewIdProduct());
            product.inputData(scanner, catalogController.getAll());
            productController.save(product);
        }
        System.out.println("Đã thêm thành công " + countProduct + " sản phẩm.");
    }

    public void printListProduct() {
        if (productController.getAll().isEmpty()) {
            System.err.println("Không có sản phẩm nào!.");
            return;
        }
        for (Product product : productController.getAll()) {
            System.out.println(product);
        }
    }

    private void inputUpdateProduct(Scanner scanner) {
        System.out.print("Nhập vào Mã sản phẩm: ");
        String idProduct = scanner.nextLine();
        Product product = productController.findById(idProduct);
        if (product == null) {
            System.err.println("Không có sản phẩm bạn muốn tìm!.");
            return;
        }
        Product newProduct = new Product();
        newProduct.setProductId(product.getProductId());
        newProduct.inputData(scanner, catalogController.getAll());
        productController.save(newProduct);
        System.out.println("Đã thay đổi thành công sản phẩm " + idProduct );
    }

    private void searchProductByName(Scanner scanner) {
        boolean flag = false;
        System.out.print("Nhập vào tên sản phẩm bạn muốn tìm: ");
        String textName = scanner.nextLine();
        for (Product product : productController.getAll()) {
            if (product.getProductName().toLowerCase().contains(textName.toLowerCase())) {
                System.out.println(product);
                flag = true;
            }
        }
        if (!flag) {
            System.err.println("Không có sản phẩm nào!.");
        } else {
            System.out.println("Đã tìm thấy " + textName + " sản phẩm.");
        }
    }

    public void inputDeleteProduct(Scanner scanner) {
        System.out.println("Mời bạn nhập vào mã muốn nhập: ");
        String indexProduct = scanner.nextLine();
        productController.delete(indexProduct);
        System.out.println("Đã xóa thành công sản phẩm " + indexProduct);
    }
}