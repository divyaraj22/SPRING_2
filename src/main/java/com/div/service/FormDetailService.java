package com.div.service;

import java.sql.Date;
import java.util.List;
import com.div.dto.FormDetailDTO;
import com.div.dto.UserDTO;
import com.div.pojo.SearchCriteria;

public interface FormDetailService {
    List<FormDetailDTO> getAllFormDetails();
    FormDetailDTO getFormDetailById(int id);
    void saveFormDetail(FormDetailDTO formDetailDto);
    void updateFormDetail(FormDetailDTO formDetailDto);
    void deleteFormDetail(int id);
    List<FormDetailDTO> getFormDetailByUser(UserDTO userDto);
    List<FormDetailDTO> getFreeAccessWithExpiry(Date today);
    List<FormDetailDTO> getUserFormDetails(UserDTO userDto, SearchCriteria searchCriteria);
}