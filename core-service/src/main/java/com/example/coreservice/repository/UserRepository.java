package com.example.coreservice.repository;

import com.example.coreservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.email " +
            "from users u " +
            "where u.id in (select ug.user_id from user_group ug where ug.group_id = :groupId)", nativeQuery = true)
    List<String> getEmailsByGroup(@Param("groupId") long groupId);
}
