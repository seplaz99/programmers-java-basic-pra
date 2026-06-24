package org.example.springtheory.testcodeex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
class ProductDaoTest {
    @Autowired
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        productDao.deleteAll();
    }

    private Product newProduct(String id, String name, int price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    @Test
    void add() {
        // given
        assertEquals(0, productDao.getCount());
        Product product = newProduct("p1", "연필", 500);

        // when
        productDao.add(product);

        // then
        assertEquals(1, productDao.getCount());

    }

    @Test
    void get() {
        // given
        Product product = newProduct("p1", "연필", 500);
        productDao.add(product);

        // when
        Product found = productDao.get("p1");

        // then
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getPrice(), found.getPrice());
    }

    @Test
    void add_중복_id_예외() {
        final Product product = newProduct("dup", "지우개", 300);
        productDao.add(product);

        Executable action = new Executable() {
            @Override
            public void execute() {
                productDao.add(product);
            }
        };
        assertThrows(IllegalStateException.class, action);
    }

    @Test
    void get_없는_id_예외() {
        Executable action = new Executable() {
            @Override
            public void execute() {
                productDao.get("없는_id");
            }
        };
        assertThrows(NoSuchElementException.class, action);
    }

    @Disabled("일부로 틀린 기대값을 넣은 학습용 실패 예제")
    @Test
    void 일부로_실패하는_테스트() {
        productDao.add(newProduct("fail_demo", "공책", 1000));
        assertEquals(2, productDao.getCount());
    }
}