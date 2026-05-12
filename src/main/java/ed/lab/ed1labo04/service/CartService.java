package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.ProductEntity;
import ed.lab.ed1labo04.model.Cart;
import ed.lab.ed1labo04.model.CartItem;
import ed.lab.ed1labo04.model.dto.CartItemRequest;
import ed.lab.ed1labo04.model.dto.CartRequest;
import ed.lab.ed1labo04.repository.CartRepository;
import ed.lab.ed1labo04.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart createCart(CartRequest request) {

        List<CartItem> cartItems = new ArrayList<>();
        double totalPrice = 0;

        for (CartItemRequest itemRequest : request.getCartItems()) {

            // validar cantidad
            if (itemRequest.getQuantity() <= 0) {
                throw new RuntimeException("Cantidad invalida");
            }

            // buscar producto
            Optional<ProductEntity> optionalProduct =
                    productRepository.findById(itemRequest.getProductId());

            // validar existencia
            if (optionalProduct.isEmpty()) {
                throw new RuntimeException("Producto no existe");
            }

            ProductEntity product = optionalProduct.get();

            // validar inventario
            if (product.getQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Inventario insuficiente");
            }

            // restar inventario
            product.setQuantity(
                    product.getQuantity() - itemRequest.getQuantity()
            );

            productRepository.save(product);

            // crear cart item
            CartItem cartItem = new CartItem();

            cartItem.setProductId(product.getId());
            cartItem.setName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(itemRequest.getQuantity());

            cartItems.add(cartItem);

            // acumular total
            totalPrice +=
                    product.getPrice() * itemRequest.getQuantity();
        }

        // crear carrito
        Cart cart = new Cart();

        cart.setCartItems(cartItems);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public Cart getCart(Long id) {

        Optional<Cart> optionalCart = cartRepository.findById(id);

        return optionalCart.orElse(null);
    }
}