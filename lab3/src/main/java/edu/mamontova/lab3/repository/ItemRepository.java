package edu.mamontova.lab3.repository;

import edu.mamontova.lab3.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}