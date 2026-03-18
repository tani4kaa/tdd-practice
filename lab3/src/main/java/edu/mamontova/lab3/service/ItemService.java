package edu.mamontova.lab3.service;

import edu.mamontova.lab3.model.Item;
import edu.mamontova.lab3.repository.ItemRepository;
import edu.mamontova.lab3.response.ApiResponse;
import edu.mamontova.lab3.response.BaseMetaData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElse(null);
    }

    public ApiResponse<BaseMetaData, Item> getByIdAsApiResponse(String id) {
        Item item = getById(id);

        ApiResponse<BaseMetaData, Item> response = new ApiResponse<>();
        List<Item> data = new ArrayList<>();

        if (item != null) {
            data.add(item);
            response.setMeta(new BaseMetaData(200, true, null));
            response.setData(data);
        } else {
            response.setMeta(new BaseMetaData(404, false, "Item not found"));
            response.setData(data);
        }

        return response;
    }

    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }
}