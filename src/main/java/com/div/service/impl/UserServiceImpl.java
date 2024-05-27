package com.div.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.div.dao.UserDAO;
import com.div.dto.UserDTO;
import com.div.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.findAllDTO();
    }

    @Override
    public UserDTO getUserById(int id) {
        return userDAO.findOneDTO(id);
    }

    @Override
    public void saveUser(UserDTO userDto) {
        userDAO.save(userDto);
    }

    @Override
    public void updateUser(UserDTO userDto) {
        userDAO.update(userDto);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteByIdDTO(id);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}