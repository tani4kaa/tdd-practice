package edu.mamontova.lab3;

import edu.mamontova.lab3.model.Item;
import edu.mamontova.lab3.response.ApiResponse;
import edu.mamontova.lab3.response.BaseMetaData;
import edu.mamontova.lab3.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService underTest;

    private List<Item> savedItems;

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
        savedItems = underTest.getAll();
    }

    @Test
    void whenGetAllItemsListThenSizeIs30() {
        assertEquals(30, underTest.getAll().size());
    }

    @Test
    void whenGetAllItemsThenListIsNotEmpty() {
        assertFalse(underTest.getAll().isEmpty());
    }

    @Test
    void whenGetAllItemsThenFirstItemIsNotNull() {
        assertNotNull(underTest.getAll().get(0));
    }

    @Test
    void whenItemIsPresentThenGetByIdReturnsItem() {
        String id = savedItems.get(0).getId();

        Item item = underTest.getById(id);

        assertNotNull(item);
    }

    @Test
    void whenItemIsPresentThenReturnedItemHasSameId() {
        String id = savedItems.get(0).getId();

        Item item = underTest.getById(id);

        assertEquals(id, item.getId());
    }

    @Test
    void whenItemIsNotPresentThenGetByIdReturnsNull() {
        Item item = underTest.getById("not-existing-id");

        assertNull(item);
    }

    @Test
    void whenItemIsPresentThenReturnAsOkApiResponse() {
        String id = savedItems.get(0).getId();
        Item item = underTest.getById(id);

        ApiResponse<BaseMetaData, Item> response = underTest.getByIdAsApiResponse(id);

        assertNotNull(response);
        assertFalse(response.getData().isEmpty());
        assertNotNull(response.getData().get(0));
        assertTrue(response.getMeta().isSuccess());
        assertEquals(200, response.getMeta().getCode());
        assertNull(response.getMeta().getErrorMessage());
        assertEquals(item.getId(), response.getData().get(0).getId());
    }

    @Test
    void whenItemIsPresentThenApiResponseContainsExactlyOneItem() {
        String id = savedItems.get(0).getId();

        ApiResponse<BaseMetaData, Item> response = underTest.getByIdAsApiResponse(id);

        assertEquals(1, response.getData().size());
    }

    @Test
    void whenItemIsNotPresentThenReturnApiResponseCode404() {
        ApiResponse<BaseMetaData, Item> response = underTest.getByIdAsApiResponse("not-existing-id");

        assertNotNull(response);
        assertTrue(response.getData().isEmpty());
        assertFalse(response.getMeta().isSuccess());
        assertEquals(404, response.getMeta().getCode());
    }

    @Test
    void whenItemIsNotPresentThenReturnApiResponseWithErrorMessage() {
        ApiResponse<BaseMetaData, Item> response = underTest.getByIdAsApiResponse("not-existing-id");

        assertEquals("Item not found", response.getMeta().getErrorMessage());
    }
}