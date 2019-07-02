package ru.smartsoft.shop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.shop.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
