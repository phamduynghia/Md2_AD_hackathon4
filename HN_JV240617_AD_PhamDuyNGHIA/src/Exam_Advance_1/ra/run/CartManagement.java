package Exam_Advance_1.ra.run;

import Exam_Advance_1.ra.controller.CartController;
import Exam_Advance_1.ra.controller.ProductController;
import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.model.Product;

import java.util.List;
import java.util.Scanner;

public class CartManagement {
    private final ProductController productController;
    private final CartController cartController;

    public CartManagement(ProductController productController, CartController cartController) {
        this.productController = productController;
        this.cartController = cartController;
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("********************************PRODUCT MENU*********************************");
            System.out.println("*                                                                           *");
            System.out.println("*     1. Xem danh sách sản phẩm                                             *");
            System.out.println("*     2. Thêm vào giỏ hàng                                                  *");
            System.out.println("*     3. Xem tất cả sản phẩm giỏ hàng                                       *");
            System.out.println("*     4. Thay đổi số lượng sản phẩm trong giỏ hàng                          *");
            System.out.println("*     5. Xóa 1 sản phẩm trong giỏ hàng                                      *");
            System.out.println("*     6. Xóa toàn bộ sản phẩm trong giỏ hàng                                *");
            System.out.println("*     7. Quay lại                                                           *");
            System.out.println("*                                                                           *");
            System.out.println("*****************************************************************************");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    printAllProducts();
                    break;
                case 2:
                    inputAddProductToCart(scanner);
                    break;
                case 3:
                    showAllCart();
                    break;
                case 4:
                    UpdateNumberProduct(scanner);
                    break;
                case 5:
                    deleteProductInCartByCartItemId(scanner);
                    break;
                case 6:
                    clearAllProductInCart();
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6!.");
            }
        } while (isExit);
    }
    //1.  hiển thị danh sách tất cả sản phẩm đang được bán của cửa hàng .
    public void printAllProducts() {
        if (productController.getAll().isEmpty()) {
            System.err.println("Không có sản phẩm nào!.");
            return;
        }
        for (Product product : productController.getAll()) {
            System.out.println(product);
        }
    }

    //2. thêm mới 1 sản phẩm vào giỏ hàng dựa theo mã sản phẩm mà người dùng [10 điểm]
    //nhập vào.
    public void inputAddProductToCart(Scanner scanner) {
        showAllCart();
        CartItem listCart = null;
        System.out.println("bạn muốn mua sản phẩm có mã là: ");
        String indexId = scanner.nextLine();
        System.out.println("Bạn muốn mua bao nhiêu sản phẩm: ");
        int number = Integer.parseInt(scanner.nextLine());
        Product product = productController.findById(indexId);
        if (product == null) {
            System.err.println("Hiện tại không có sản phẩm này!.");
            return;
        }
        if (number <  product.getStock()) {
            for (CartItem cartItem : cartController.getAll()) {
                if (cartItem.getProduct().getProductId().equals(product.getProductId())) {
                    listCart = cartItem;
                }
            }
            if (listCart != null) {
                cartController.shoppingCartIncreasesByNumber(listCart, number);
            }
            else  {
                CartItem cartItem = new CartItem();
                cartItem.setCartItemId(cartController.getNewId());
                cartItem.setProduct(product);
                cartItem.setPrice(product.getProductPrice());
                cartController.save(cartItem);
                cartController.shoppingCartIncreasesByNumber(cartItem, number - 1);
                productController.ProductReduceByNumber(product, number);
            }
        }
        else  {
            System.err.println("Sản phẩm này không đủ số lượng!.");
        }
    }

    //Chọn 3: hiển thị danh sách giỏ hàng .
    public void showAllCart() {
        if (cartController.getAll().isEmpty()) {
            System.err.println("Giỏ hàng trống!.");
            return;
        }
        for (CartItem cartItem : cartController.getAll()) {
            System.out.println(cartItem);
        }
    }

    //Chọn 4: cập nhật số lượng sản phẩm muốn mua theo trường cartItemId .
    public void UpdateNumberProduct (Scanner scanner) {
        showAllCart();
        System.out.println("Mời bạn nhập vào sản phẩm muốn thay đổi có mã là: ");
        int indexProduct = Integer.parseInt(scanner.nextLine());
        CartItem cartItem = cartController.findById(indexProduct);
        if (cartItem == null) {
            System.err.println("Không có sản phẩm!.");
            return;
        }
        Product product = productController.findById(cartItem.getProduct().getProductId());
        if (product.getStock() == 0) {
            System.err.println("Sản phẩm đã hết hàng!.");
            return;
        }

        System.out.println(cartItem);
        System.out.println("************************************************************");
        System.out.println("*                                                          *");
        System.out.println("*           1.     Thêm 1 sản phẩm vào giỏ hàng            *");
        System.out.println("*           2.     Giảm 1 sản phẩm vào giỏ hàng            *");
        System.out.println("*           3.     Thêm nhiều sản phẩm vào giỏ hàng        *");
        System.out.println("*           4.     Xóa nhiều sản phẩm vào giỏ hàng         *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
        System.out.print("Lựa chọn của bạn: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                if (product.getStock() > 0) {
                    cartController.shoppingCartIncreases(cartItem);
                    productController.ProductReduce(product);
                } else {
                    System.err.println("Sản phẩm đã hết!.");
                }
                break;
            case 2:
                if (cartItem.getQuantity() == 1) {
                    cartController.delete(cartItem.getCartItemId());
                    productController.ProductIncreases(product);
                } else {
                    cartController.shoppingCartReduce(cartItem);
                    productController.ProductIncreases(product);
                }
                break;
            case 3:
                changeProductById("ADD", cartItem, product, scanner);
                break;
            case 4:
                changeProductById("DELETE", cartItem, product, scanner);
                break;
            default:
                System.err.println("Vui lòng nhập lại (1 -> 4)");
                break;
        }
    }


    public void changeProductById(String select, CartItem cartItem, Product product, Scanner scanner) {
        if (select.equals("ADD")) {
            System.out.print("Bạn muốn thêm bao nhiêu sản phẩm: ");
            int number =Integer.parseInt(scanner.nextLine());
            if (number > product.getStock()) {
                System.err.println("Hiện tại không đủ số lượng sản phẩm!.");
                return;
            }
            cartController.shoppingCartIncreasesByNumber(cartItem, number);
            productController.ProductReduceByNumber(product, number);
        }
        if (select.equals("DELETE")) {
            System.out.print("Bạn muốn xóa bao nhiêu sản phẩm: ");
            int number =Integer.parseInt(scanner.nextLine());
            if (number > cartItem.getQuantity()) {
                System.err.println("Hiện tại không có nhiều như vậy!.");
                return;
            }
            cartController.shoppingCartReduceByNumber(cartItem, number);
            productController.ProductIncreasesByNumber(product, number);
        }
    }

    //Chọn 5: xóa 1 sản phẩm ra khỏi giỏ hàng theo cartItemId .
    public void deleteProductInCartByCartItemId(Scanner scanner) {
        System.out.println("Bạn muốn xóa sản phẩm có ID là: ");
        int indexProduct = Integer.parseInt(scanner.nextLine());
        CartItem cartItem = cartController.findById(indexProduct);
        if (cartItem == null) {
            return;
        }
        int quantity = cartItem.getQuantity();
        Product product = productController.findById(cartItem.getProduct().getProductId());
        for (int i = 0; i < quantity; i++) {
            productController.ProductIncreases(product);
        }
        cartController.delete(indexProduct);
    }

    //Chọn 6: xóa toàn bộ sản phẩm trong trong giỏ hàng.
    public void clearAllProductInCart() {
        List<CartItem> list = cartController.getAll();
        for (CartItem cartItem : list) {
            Product product = productController.findById(cartItem.getProduct().getProductId());
            for (int j = 0; j < cartItem.getQuantity(); j++) {
                productController.ProductIncreases(product);
            }
        }
        cartController.deleteAll();
        System.out.println("Xóa thành công.");
    }
}