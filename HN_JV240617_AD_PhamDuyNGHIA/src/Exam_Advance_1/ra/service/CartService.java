package Exam_Advance_1.ra.service;

import Exam_Advance_1.ra.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartService implements IGenericService<CartItem, Integer> {

    private final List<CartItem>  cartList = new ArrayList<>();
    @Override
    public List<CartItem> getAll() {
        return cartList;
    }

    @Override
    public void save(CartItem cartItem) {
        if (findById(cartItem.getCartItemId()) == null) {
            cartList.add(cartItem);
            System.out.println("Thêm thành công.");
        } else {
            cartList.set(cartList.indexOf(findById(cartItem.getCartItemId())), cartItem);
            System.out.println("Sửa thành công.");
        }
    }

    @Override
    public CartItem findById(Integer integer) {
        for (CartItem c : cartList) {
            if (c.getCartItemId() == integer) {
                return c;
            }
        }
        return null;
    }


    @Override
    public void delete(Integer integer) {
        if (findById(integer) != null) {
            cartList.remove(findById(integer));
            System.out.println("Xóa thành công.");
        } else {
            System.err.println("Không có sản phẩm này trong giỏ hàng!.");
        }
    }

    public void deleteAll() {
        cartList.clear();
    }

    public int getNewId() {
        int maxId = 0;
        for (CartItem c : cartList) {
            if (c.getCartItemId() > maxId) {
                maxId = c.getCartItemId();
            }
        }
        return maxId + 1;
    }

    // Giỏ hàng tăng
    public void shoppingCartIncreases (CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
    }
    // Giỏ hàng giảm
    public void shoppingCartReduce (CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() - 1);
    }
    // Tăng Giỏ hàng theo số lượng
    public void shoppingCartIncreasesByNumber (CartItem cartItem, int number) {
        cartItem.setQuantity(cartItem.getQuantity() + number);
    }
    //Giảm Giỏ hàng theo số lượng
    public void shoppingCartReduceByNumber (CartItem cartItem, int number) {
        cartItem.setQuantity(cartItem.getQuantity() - number);
    }
}