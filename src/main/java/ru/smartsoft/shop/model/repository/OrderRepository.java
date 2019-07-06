package ru.smartsoft.shop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByPurchaseDateAfter(Timestamp date);

    @Query("select o from Order o where o.purchaseDate BETWEEN :startDate and :endDate")
    List<Order> findByPeriod(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    @Query("select o from Order o where o.user.age = :age")
    List<Order> findByUserAge(@Param("age") int age);
}
