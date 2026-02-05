package csd230.lab1.controllers;

import csd230.lab1.entities.*;
import csd230.lab1.pojos.Product;
import csd230.lab1.repositories.BookEntityRepository;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartEntityRepository cartRepository;
    @Autowired
    private BookEntityRepository bookRepository;
    @Autowired
    private OrderEntityRepository orderEntityRepository;

    // 1. View the contents of the cart
    @GetMapping
    public String viewCart(Model model) {
        // HARDCODED ID: In a real app, this comes from the Session or SecurityContext
        Long defaultCartId = 1L;

        // Find cart with ID 1, or create a temporary empty one if not found
        CartEntity cart = cartRepository.findById(defaultCartId)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setId(defaultCartId);
                    return cartRepository.save(newCart); // Save it so it exists
                });
        model.addAttribute("cart", cart);
        return "cartDetails";
    }
    // 2. Add a specific book to the cart
    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);
        if (cart != null && book != null) {
            cart.addProduct(book); // Uses the helper method in CartEntity
            cartRepository.save(cart); // Updates the Join Table
        }
        return "redirect:/books"; // Send them back to the shopping list
    }

    // 3. Remove item from cart
    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);
        if(cart != null && book != null) {
            cart.getProducts().remove(book);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkoutCart(Model model) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        if (cart != null && cart.getProducts().isEmpty()) {
            return "redirect:/cart";
        }

        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());

        for (ProductEntity product : cart.getProducts()) {
            order.setTotalAmount(order.getTotalAmount() + product.getPrice());
            if (product instanceof BookEntity) {
                if (((BookEntity) product).getCopies() <= 0) {
                    continue;
                } else {
                    ((BookEntity) product).setCopies(((BookEntity) product).getCopies() - 1);
                    order.addProduct(product);
                    bookRepository.save((BookEntity) product);
                }
            }else{
                order.addProduct(product);
            }
        }

        cart.getProducts().clear();
        orderEntityRepository.save(order);
        cartRepository.save(cart);

        model.addAttribute("order", order);
        return "orderDetails";
    }


}
