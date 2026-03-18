package edu.mamontova.lab2;

import edu.mamontova.lab2.model.Item;
import edu.mamontova.lab2.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();

        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            items.add(new Item(
                    "Item " + i,
                    "CODE-" + i,
                    "Description " + i
            ));
        }

        underTest.saveAll(items);
    }

    @Test
    void whenGetAllItemsListThenSizeIs30() {
        assertEquals(30, underTest.getAll().size());
    }
}