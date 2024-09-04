package Exam_Advance_1.ra.controller;

import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.service.CartService;

import java.util.List;

public class CartController implements IGenericController<CartItem , Integer> {
    private CartService cartService = new CartService();
    @Override
    public List<CartItem> getAll() {
        return cartService.getAll();
    }

    @Override
    public void save(CartItem cartItem) {
        cartService.save(cartItem);
    }

    @Override
    public CartItem findById(Integer integer) {
        return cartService.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        cartService.delete(integer);
    }

    public void deleteAll() {
        cartService.deleteAll();
    }

    public int getNewId() {
        return cartService.getNewId();
    }

    // Giỏ hàng tăng
    public void shoppingCartIncreases (CartItem cartItem) {
        cartService.shoppingCartIncreases(cartItem);
    }
    // Giỏ hàng giảm
    public void shoppingCartReduce (CartItem cartItem) {
        cartService.shoppingCartReduce(cartItem);
    }
    // Tăng Giỏ hàng theo số lượng
    public void shoppingCartIncreasesByNumber (CartItem cartItem, int number) {
        cartService.shoppingCartIncreasesByNumber(cartItem, number);
    }
    //Giảm Giỏ hàng theo số lượng
    public void shoppingCartReduceByNumber (CartItem cartItem, int number) {
        cartService.shoppingCartReduceByNumber(cartItem, number);
    }

}