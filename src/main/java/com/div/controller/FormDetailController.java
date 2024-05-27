package com.div.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.div.dto.FormDetailDTO;
import com.div.dto.UserDTO;
import com.div.pojo.SearchCriteria;
import com.div.constantsURL.Constants;
import com.div.service.FormDetailService;
import com.div.service.SchedulerService;
import com.div.util.MultipartFileEditor;
import com.div.util.SqlDateEditor;

@Controller
public class FormDetailController {

    public final static Logger logger = Logger.getLogger(FormDetailController.class);

    private FormDetailService formDetailService;
    private SchedulerService schedulerService;

    @Autowired(required = true)
    @Qualifier("formDetailServiceImpl")
    public void setFormDetailService(FormDetailService formDetailService) {
        this.formDetailService = formDetailService;
    }

    @Autowired(required = true)
    @Qualifier("schedulerServiceImpl")
    public void setSchedulerService(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping(Constants.TRIGGER_SCHEDULER)
    @Transactional
    public String triggerScheduler() {
        schedulerService.checkAndUpdateAccessCategories();
        return "Scheduler";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(java.sql.Date.class, new SqlDateEditor("yyyy-MM-dd"));
        binder.registerCustomEditor(byte[].class, new MultipartFileEditor());
    }

    @GetMapping(Constants.ADD_DETAILS)
    public String showAddFormDetail() {
        logger.info("GET request to show add form detail");
        return Constants.VIEW_FORM;
    }

    @PostMapping(Constants.ADD_DETAILS)
    public String saveFormDetail(@Valid @ModelAttribute("formDetail") FormDetailDTO formDetailDto,
                                 @RequestParam("banner") MultipartFile bannerFile,
                                 @RequestParam(value = "premiumCheckbox", required = false) boolean isPremium, HttpSession session)
            throws IOException {
        logger.info("POST request to save form detail: {}");

        UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");
        if (userDto == null) {
            logger.warn(Constants.MSG_USER_NOT_LOGGED_IN);
            return "redirect:" + Constants.LOGIN;
        }

        formDetailDto.setUser(userDto);

        if (!bannerFile.isEmpty()) {
            String contentType = bannerFile.getContentType();
            if (contentType.equals("image/jpeg") || contentType.equals("image/png")) {
                byte[] imageData = bannerFile.getBytes();
                formDetailDto.setBanner(imageData);
                formDetailDto.setContentType(contentType);
            } else {
                logger.warn(Constants.MSG_INVALID_FILE_TYPE);
            }
        }

        formDetailDto.setPremium(isPremium);
        formDetailService.saveFormDetail(formDetailDto);
        return "redirect:" + Constants.ADD_DETAILS;
    }

    @GetMapping(Constants.VIEW_ALL)
    public String viewAllDetails(SearchCriteria searchCriteria, Model model, HttpSession session) {
        logger.info("GET request to view all details");
        UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");
        if (userDto == null) {
            logger.warn(Constants.MSG_USER_NOT_LOGGED_IN);
            return "redirect:" + Constants.LOGIN;
        }

        if (searchCriteria.getSortField() == null || searchCriteria.getSortField().isEmpty()) {
            searchCriteria.setSortField("title"); 
        }
        if (searchCriteria.getSortOrder() == null || searchCriteria.getSortOrder().isEmpty()) {
            searchCriteria.setSortOrder("asc"); 
        }

        List<FormDetailDTO> details = formDetailService.getUserFormDetails(userDto, searchCriteria);
        model.addAttribute("details", details);
        model.addAttribute("searchCriteria", searchCriteria);
        return Constants.VIEW_ALL_DETAILS;
    }

    @GetMapping(Constants.EDIT_DETAIL)
    public String showEditForm(@RequestParam("id") int id, Model model, HttpSession session) {
        logger.info("GET request to edit form detail with ID: {}");
        UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");
        if (userDto == null) {
            logger.warn(Constants.MSG_USER_NOT_LOGGED_IN);
            return "redirect:" + Constants.LOGIN;
        }

        FormDetailDTO formDetailDto = formDetailService.getFormDetailById(id);
        if (formDetailDto == null) {
            logger.warn(String.format(Constants.MSG_FORM_DETAIL_NOT_FOUND, id));
            return "redirect:" + Constants.VIEW_ALL;
        }

        model.addAttribute("formDetail", formDetailDto);
        return Constants.EDIT_FORM;
    }

    @PostMapping(Constants.UPDATE_DETAILS)
    public String updateDetails(@ModelAttribute FormDetailDTO formDetailDto,
                                @RequestParam("banner") MultipartFile banner,
                                @RequestParam(value = "isPremium", required = false) boolean isPremium, HttpSession session)
            throws IOException {
        logger.info("POST request to update form detail with ID: {}");

        FormDetailDTO existingFormDetailDto = formDetailService.getFormDetailById(formDetailDto.getId());

        if (existingFormDetailDto == null) {
            logger.warn(String.format(Constants.MSG_FORM_DETAIL_NOT_FOUND, formDetailDto.getId()));
            return "redirect:" + Constants.VIEW_ALL;
        }

        formDetailDto.setUser(existingFormDetailDto.getUser());

        if (banner != null && !banner.isEmpty()) {
            formDetailDto.setBanner(banner.getBytes());
            formDetailDto.setContentType(banner.getContentType());
        } else {
            formDetailDto.setBanner(existingFormDetailDto.getBanner());
            formDetailDto.setContentType(existingFormDetailDto.getContentType());
        }

        formDetailDto.setPremium(isPremium);
        formDetailService.updateFormDetail(formDetailDto);

        return "redirect:" + Constants.VIEW_ALL;
    }

    @GetMapping(Constants.DELETE_DETAIL)
    public String deleteDetails(@RequestParam("id") int id) {
        logger.info("GET request to delete form detail with ID: {}");
        formDetailService.deleteFormDetail(id);
        return "redirect:" + Constants.VIEW_ALL;
    }
}