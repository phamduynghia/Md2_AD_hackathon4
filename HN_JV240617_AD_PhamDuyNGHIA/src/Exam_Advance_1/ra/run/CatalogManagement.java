package Exam_Advance_1.ra.run;
import Exam_Advance_1.ra.controller.CatalogController;
import Exam_Advance_1.ra.model.Catalog;
import java.util.Scanner;

public class CatalogManagement {
    private final CatalogController catalogController;
    public CatalogManagement (CatalogController catalogController) {
        this.catalogController = catalogController;
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("*****************************CATEGORIES MENU*********************************");
            System.out.println("*                                                                           *");
            System.out.println("*    1.    Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục    *");
            System.out.println("*    2.    Hiển thị thông tin tất cả các danh mục                           *");
            System.out.println("*    3.    Sửa tên danh mục theo mã danh mục                                *");
            System.out.println("*    4.    Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)     *");
            System.out.println("*    5.    Quay lại                                                         *");
            System.out.println("*                                                                           *");
            System.out.println("*****************************************************************************");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputAddNewCatalog(scanner);
                    break;
                case 2:
                    printListCatalog();
                    break;
                case 3:
                    updateCatalog(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5!.");
            }
        }
        while (isExit);
    }

    public void inputAddNewCatalog(Scanner scanner) {
        System.out.println("Mời bạn nhập vào số lượng danh mục: ");
        int countCatalog = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countCatalog; i++) {
            System.out.println("Danh mục thứ " + (i + 1));
            Catalog catalog = new Catalog();
            catalog.setCatalogId(catalogController.getNewId());
            catalog.inputData(scanner);
            catalogController.save(catalog);
        }
        System.out.println("Đã thêm thành công " + countCatalog + " danh mục.");
    }

    public void printListCatalog() {
        if (catalogController.getAll().isEmpty()) {
            System.err.println("Chưa có danh mục nào!.");
            return;
        }
        for (Catalog catalog : catalogController.getAll()) {
            System.out.println(catalog);
        }
    }

    public void updateCatalog (Scanner scanner) {
        System.out.println("Bạn muốn sửa danh mục có mã là: ");
        int indexCatalog = Integer.parseInt(scanner.nextLine());
        Catalog catalog = catalogController.findById(indexCatalog);
        if (catalog == null) {
            System.err.println("Không tồn tại danh mục!.");
            return;
        }
        Catalog newCatalog = new Catalog();
        newCatalog.setCatalogId(catalog.getCatalogId());
        newCatalog.inputData(scanner);
        catalogController.save(newCatalog);
        System.out.println("Đã thay đổi thành công danh mục " + indexCatalog );
    }

    public void deleteCatalog (Scanner scanner) {
        System.out.println("Bạn muốn sửa danh mục có mã là: ");
        int indexCatalog = Integer.parseInt(scanner.nextLine());
        catalogController.delete(indexCatalog);
        System.out.println("Đã xóa thành công danh mục " + indexCatalog );
    }
}