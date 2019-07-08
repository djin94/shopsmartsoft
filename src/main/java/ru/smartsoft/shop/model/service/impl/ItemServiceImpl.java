package ru.smartsoft.shop.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smartsoft.shop.model.entity.Item;
import ru.smartsoft.shop.model.repository.ItemRepository;
import ru.smartsoft.shop.model.service.ItemService;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> getById(long id) {
        return itemRepository.findById(id);
    }
}
