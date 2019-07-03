package ru.smartsoft.shop.model.dto.converter;

import ru.smartsoft.shop.model.dto.BuyItemDto;
import ru.smartsoft.shop.model.entity.BuyItem;

import java.util.List;

public interface BuyItemDtoConverter {
    BuyItemDto convertToBuyItemDto(BuyItem buyItem);

    List<BuyItemDto> convertToBuyItemDtos(List<BuyItem> buyItems);
}
