package com.epam.spark.exam.services;

import com.epam.spark.exam.model.User;
import com.epam.spark.exam.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dekel Levitan
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}








