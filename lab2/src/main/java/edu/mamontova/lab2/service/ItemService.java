package edu.mamontova.lab2.service;



import edu.mamontova.lab2.model.Item;
import edu.mamontova.lab2.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }

    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }
}