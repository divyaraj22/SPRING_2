package com.div.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.div.dao.FormDetailDAO;
import com.div.dto.FormDetailDTO;
import com.div.dto.UserDTO;
import com.div.pojo.SearchCriteria;
import com.div.service.FormDetailService;

@Service
@Transactional
public class FormDetailServiceImpl implements FormDetailService {

    private FormDetailDAO formDetailDAO;

    @Autowired
    public FormDetailServiceImpl(FormDetailDAO formDetailDAO) {
        this.formDetailDAO = formDetailDAO;
    }

    @Override
    public List<FormDetailDTO> getAllFormDetails() {
        return formDetailDAO.findAll();
    }

    @Override
    public FormDetailDTO getFormDetailById(int id) {
        return formDetailDAO.findOneDTO(id);
    }

    @Override
    public void saveFormDetail(FormDetailDTO formDetailDto) {
        formDetailDAO.save(formDetailDto);
    }

    @Override
    public void updateFormDetail(FormDetailDTO formDetailDto) {
        formDetailDAO.update(formDetailDto);
    }

    @Override
    public void deleteFormDetail(int id) {
        formDetailDAO.deleteByIdDTO(id);
    }

    @Override
    public List<FormDetailDTO> getFormDetailByUser(UserDTO userDto) {
        return formDetailDAO.findByUser(userDto);
    }

    @Override
    public List<FormDetailDTO> getFreeAccessWithExpiry(Date today) {
        return formDetailDAO.findFreeAccessWithExpiry(today);
    }

    @Override
    public List<FormDetailDTO> getUserFormDetails(UserDTO userDto, SearchCriteria searchCriteria) {
        return formDetailDAO.findByUserWithCriteria(userDto, searchCriteria);
    }
}