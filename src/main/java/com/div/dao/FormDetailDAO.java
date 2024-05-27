package com.div.dao;

import java.sql.Date;
import java.util.List;
import com.div.pojo.SearchCriteria;
import com.div.dto.FormDetailDTO;
import com.div.dto.UserDTO;
import com.div.pojo.FormDetail;

public interface FormDetailDAO {
    List<FormDetailDTO> findAll();
    List<FormDetailDTO> findByUser(UserDTO userDto);
    List<FormDetailDTO> findFreeAccessWithExpiry(Date today);
    List<FormDetailDTO> getSortedFormDetails(String sortField, String sortOrder);
    void save(FormDetailDTO formDetailDto);
    void save(FormDetail formDetail);
    void update(FormDetailDTO formDetailDto);
    FormDetailDTO findOneDTO(Integer id);
    void deleteByIdDTO(Integer id);
    List<FormDetailDTO> findByUserWithCriteria(UserDTO userDto, SearchCriteria searchCriteria);
}