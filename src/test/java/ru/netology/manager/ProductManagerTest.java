package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private final Product firstProduct = new Product(1, "Ivanov", 100);
    private final Book life = new Book(2, "Ivanov", 500, "Kot");
    private final Book game = new Book(3, "Game", 700, "Ivanov");
    private final Smartphone pear = new Smartphone(4, "Ivanov", 2000, "LLL");
    private final Smartphone apricot = new Smartphone(5, "Green Apricot", 4000, "Ivanov");

    private final ProductManager manager = new ProductManager(new ProductRepository());

    @BeforeEach
    void setUp() {
        manager.save(firstProduct);
        manager.save(life);
        manager.save(game);
        manager.save(pear);
        manager.save(apricot);
    }

    @Test
    void shouldReturnByText() {
        Product[] expected = {firstProduct, life, game, pear, apricot};
        Product[] actual = manager.searchBy("Ivanov");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnByTextAuthorBook() {
        Product[] expected = {life};
        Product[] actual = manager.searchBy("Kot");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnByTextManufacturerSmartphone() {
        Product[] expected = new Product[]{pear};
        Product[] actual = manager.searchBy("LLL");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnByInvalidText() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Red");

        assertArrayEquals(expected, actual);
    }
}