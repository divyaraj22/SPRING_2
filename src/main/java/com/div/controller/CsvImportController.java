package com.div.controller;

import com.div.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CsvImportController {

    private CsvImportService csvImportService;

    @Autowired(required = true)
    @Qualifier("csvImportServiceImpl")
    public void setCsvImportService(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @GetMapping("/upload-csv")
    public String showUploadForm(Model model) {
        return "uploadCsvForm";
    }

    @PostMapping("/import-csv")
    public String importCsv(@RequestParam("file") MultipartFile file, Model model) {
        try {
            csvImportService.importCsv(file);
            model.addAttribute("message", "File imported successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "File import failed: " + e.getMessage());
            model.addAttribute("stackTrace", e.getStackTrace());
        }
        return "csvImportStatus";
    }
}