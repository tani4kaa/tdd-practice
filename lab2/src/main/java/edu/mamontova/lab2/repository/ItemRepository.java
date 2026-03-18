package edu.mamontova.lab2.repository;

import edu.mamontova.lab2.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}