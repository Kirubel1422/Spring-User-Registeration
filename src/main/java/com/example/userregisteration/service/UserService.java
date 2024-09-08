package com.example.userregisteration.service;

import com.example.userregisteration.entity.User;
import com.example.userregisteration.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Create user by id
    public User createProduct(User user){
        try{
            return userRepository.save(user);
        }catch(Exception e){
            throw new RuntimeException("Failed to create a user: " + e.getMessage());
        }
    }

    // Fetch user by id
    public Optional<User> getUserById(Long id){
        try{
            return userRepository.findById(id);
        }catch(Exception e){
            throw new RuntimeException("Failed to get user: " + e.getMessage());
        }
    }

    // Fetch all users
    public List<User> getAllUsers(){
        try{
            return userRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Failed to get all users: " + e.getMessage());
        }
    }

    // Update user
    public Optional<User> updateUserById(User user, Long id){
        try{
            Optional<User> retrievedUserOptional = userRepository.findById(id);
            if(retrievedUserOptional.isPresent()){
                User retrievedUser = retrievedUserOptional.get();
                retrievedUser.setGender(user.getGender());
                retrievedUser.setFirstName(user.getFirstName());
                retrievedUser.setLastName(user.getLastName());
                retrievedUser.setEmail(user.getEmail());

                userRepository.save(retrievedUser);

                return Optional.of(retrievedUser);
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            throw new RuntimeException("Failed to update user " + e.getMessage());
        }
    }

    // Delete user
    public boolean deleteUserById(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception e){
            throw new RuntimeException("Failed to delete user " + e.getMessage());
        }
    }
}
