package com.div.dao;

import com.div.dto.UserDTO;
import java.util.List;

public interface UserDAO {
    UserDTO findByEmail(String email);
    List<UserDTO> findAllDTO();
    void save(UserDTO userDto);
    void update(UserDTO userDto);
    UserDTO findOneDTO(Integer id);
    void deleteByIdDTO(Integer id);
}
