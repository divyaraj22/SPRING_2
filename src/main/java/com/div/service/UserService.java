package com.div.service;

import java.util.List;
import com.div.dto.UserDTO;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    void saveUser(UserDTO userDto);
    void updateUser(UserDTO userDto);
    void deleteUser(int id);
    UserDTO findByEmail(String email);
}
