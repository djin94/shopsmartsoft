package ru.smartsoft.shop.model.dto.converter.impl;

import ru.smartsoft.shop.model.dto.BuyItemDto;
import ru.smartsoft.shop.model.dto.converter.BuyItemDtoConverter;
import ru.smartsoft.shop.model.entity.BuyItem;

import java.util.ArrayList;
import java.util.List;

public class BuyItemDtoConverterImpl implements BuyItemDtoConverter {
    @Override
    public BuyItemDto convertToBuyItemDto(BuyItem buyItem) {
        BuyItemDto buyItemDto = new BuyItemDto();
        buyItemDto.setId(buyItem.getId());
        buyItemDto.setCount(buyItem.getCount());
        buyItemDto.setItem(buyItem.getItem());
        buyItemDto.setOrder(new OrderDtoConverterImpl().convertToDto(buyItem.getOrder()));
        buyItemDto.setAmount((double) buyItem.getItem().getPrice() * buyItem.getCount() / 100);
        return buyItemDto;
    }

    @Override
    public List<BuyItemDto> convertToBuyItemDtos(List<BuyItem> buyItems) {
        return buyItems.stream().collect(ArrayList::new, (buyItemDtos, buyItem) -> buyItemDtos.add(convertToBuyItemDto(buyItem)), ArrayList::addAll);
    }
}
