package com.epam.spark.exam.repo;

import com.epam.spark.exam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dekel Levitan
 */
public interface UserRepo extends JpaRepository<User, Integer> {

    //   @RestResource(path = "byemail")
    //  List<User> findByEmailContaining(String email);


    //@RestResource(path = "byname")
    //  List<User> findByNameContaining(String partOfName);
}
