package ru.smartsoft.shop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.shop.model.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
