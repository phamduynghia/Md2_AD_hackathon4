package Exam_Advance_1.ra.run;

import Exam_Advance_1.ra.controller.CartController;
import Exam_Advance_1.ra.controller.CatalogController;
import Exam_Advance_1.ra.controller.ProductController;

import java.util.Scanner;

public class BookManagement {
    static ProductController productController = new ProductController();
    static CatalogController catalogController = new CatalogController();
    static CartController cartController = new CartController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*****************BOOK MENU****************");
            System.out.println("*                                        *");
            System.out.println("*     1.   Quản lý danh mục sản phẩm     *");
            System.out.println("*     2.   Quản lý sản phẩm              *");
            System.out.println("*     3.   Dành cho người dùng (***)     *");
            System.out.println("*     4.   Thoát                         *");
            System.out.println("*                                        *");
            System.out.println("******************************************");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    new CatalogManagement(catalogController);
                    break;
                case 2:
                    new ProductManagement(productController, catalogController);
                    break;
                case 3:
                    new CartManagement(productController, cartController);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-4!.");
            }
        } while (true);
    }
}