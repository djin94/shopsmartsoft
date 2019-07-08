package ru.smartsoft.shop.model.service;

import ru.smartsoft.shop.model.entity.Item;

import java.util.Optional;

public interface ItemService {
    Item update(Item item);

    Optional<Item> getById(long id);
}
