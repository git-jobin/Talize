package com.jobinjose.talize.dao;

import com.jobinjose.talize.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends CrudRepository<UserModel, Integer> {
    @Query(value = "SELECT * FROM `user` WHERE `email`= :email", nativeQuery = true)
    List<UserModel> getUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM `user` WHERE `email`= :email AND `password`= :password", nativeQuery = true)
    List<UserModel> getUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
