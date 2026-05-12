package ed.lab.ed1labo04.model.dto;

import java.util.List;

public class CartRequest {

    private List<CartItemRequest> cartItems;

    public CartRequest() {
    }

    public List<CartItemRequest> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemRequest> cartItems) {
        this.cartItems = cartItems;
    }
}
