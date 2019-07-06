package ru.smartsoft.shop.model.service;

import ru.smartsoft.shop.model.entity.BuyItem;

import java.util.List;

public interface BuyItemService {
    BuyItem getBestSellingItemForLastMonth();

    List<BuyItem> getBuyItemsForLastWeek();

    List<BuyItem> getBestSellingItemsBy18YearsOldPerson();
}
