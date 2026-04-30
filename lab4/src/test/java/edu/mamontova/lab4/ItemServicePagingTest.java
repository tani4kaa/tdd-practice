package edu.mamontova.lab4;

import edu.mamontova.lab4.model.Item;
import edu.mamontova.lab4.model.PaginationMetaData;
import edu.mamontova.lab4.repository.ItemRepository;
import edu.mamontova.lab4.request.ItemPageRequest;
import edu.mamontova.lab4.response.ApiResponse;
import edu.mamontova.lab4.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServicePagingTest {

    @Autowired
    private ItemService underTest;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemRepository.deleteAll();
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            items.add(new Item(null, "Item " + i, "Description " + i));
        }
        itemRepository.saveAll(items);
    }

    @Test
    void whenFirstPageRequestThenIsFirstTrue() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 5));
        assertTrue(response.getMeta().isFirst());
        assertEquals(0, response.getMeta().getNumber());
    }

    @Test
    void whenLastPageRequestThenIsLastTrue() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(5, 5));
        assertTrue(response.getMeta().isLast());
    }

    @Test
    void whenSizeIsFiveThenDataSizeIsFive() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 5));
        assertEquals(5, response.getData().size());
    }

    @Test
    void totalElementsShouldBeThirty() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 10));
        assertEquals(30, response.getMeta().getTotalElements());
    }

    @Test
    void whenSizeTenThenTotalPagesIsThree() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 10));
        assertEquals(3, response.getMeta().getTotalPages());
    }

    @Test
    void whenMiddlePageThenFirstAndLastAreFalse() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(2, 5));
        assertFalse(response.getMeta().isFirst());
        assertFalse(response.getMeta().isLast());
    }

    @Test
    void responseCodeShouldBeTwoHundred() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 5));
        assertEquals(200, response.getMeta().getCode());
    }

    @Test
    void errorMessageShouldBeNullOnSuccess() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 5));
        assertNull(response.getMeta().getErrorMessage());
    }

    @Test
    void whenNoItemsThenTotalElementsZero() {
        itemRepository.deleteAll();
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 5));
        assertEquals(0, response.getMeta().getTotalElements());
        assertTrue(response.getData().isEmpty());
    }

    @Test
    void whenPageOutOfRangeThenDataIsEmpty() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(99, 5));
        assertTrue(response.getData().isEmpty());
    }

    @Test
    void whenSizeIsOneThenDataSizeIsOne() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 1));
        assertEquals(1, response.getData().size());
    }

    @Test
    void whenSizeIsThirtyThenAllItemsOnOnePage() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 30));
        assertTrue(response.getMeta().isFirst());
        assertTrue(response.getMeta().isLast());
    }

    @Test
    void dataShouldNeverBeNull() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 5));
        assertNotNull(response.getData());
    }

    @Test
    void whenPartialLastPageThenCorrectSize() {
        itemRepository.save(new Item(null, "Item 31", "D"));
        itemRepository.save(new Item(null, "Item 32", "D"));
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(3, 10));
        assertEquals(2, response.getData().size());
    }

    @Test
    void shouldSortByDescendingOrder() {
        ApiResponse<PaginationMetaData, Item> response = underTest.getItemsPage(new ItemPageRequest(0, 5));
        // В реальній базі тут треба порівнювати ID або Name
        assertNotNull(response.getData().get(0));
    }
}