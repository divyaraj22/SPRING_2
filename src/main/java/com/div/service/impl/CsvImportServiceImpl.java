package com.div.service.impl;

import com.div.dao.FormDetailDAO;
import com.div.dto.FormDetailDTO;
import com.div.pojo.FormDetail;
import com.div.service.CsvImportService;
import com.div.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CsvImportServiceImpl implements CsvImportService {

    @Autowired
    private FormDetailDAO formDetailDAO;

    @Override
    @Transactional
    public void importCsv(MultipartFile file) throws Exception {
        try {
            System.out.println("Starting CSV import...");
            List<FormDetail> formDetails = CsvUtil.parseCsvFile(file);
            System.out.println("Parsed CSV file successfully.");
            for (FormDetail formDetail : formDetails) {
                FormDetailDTO formDetailDTO = FormDetailDTO.fromModel(formDetail);
                System.out.println("Saving form detail: " + formDetailDTO);
                formDetailDAO.save(formDetailDTO.toModel());
                System.out.println("Saved form detail: " + formDetailDTO);
            }
            System.out.println("CSV import completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("CSV import failed: " + e.getMessage(), e);
        }
    }
}