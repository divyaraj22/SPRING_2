package com.div.constantsURL;

public class Constants {

    // UserController paths
    public static final String GET_SIGNUP = "/signup"; 
    public static final String POST_SIGNUP = "/signup";
    public static final String LOGIN = "/Login";
    
    // UserController messages
    public static final String ERR_INVALID_EMAIL = "Enter Valid Email";
    public static final String ERR_INVALID_PASSWORD = "Enter Valid Password";
    
    // Views
    public static final String VIEW_SIGNUP = "signup";
    public static final String VIEW_LOGIN = "Login";
    public static final String VIEW_FORM = "ViewForm";
    public static final String VIEW_ALL_DETAILS = "ViewAllDetails";
    public static final String EDIT_FORM = "EditForm";

    // FormDetailController paths
    public static final String ADD_DETAILS = "/addDetails";
    public static final String VIEW_ALL = "/viewAll";
    public static final String EDIT_DETAIL = "/editDetail";
    public static final String UPDATE_DETAILS = "/updateDetails";
    public static final String DELETE_DETAIL = "/deleteDetail";
    public static final String TRIGGER_SCHEDULER="/trigger-scheduler";
    
    // FormDetailController messages
    public static final String MSG_USER_NOT_LOGGED_IN = "User is not logged in or session has expired.";
    public static final String MSG_INVALID_FILE_TYPE = "Invalid file type. Only JPEG and PNG files are allowed.";
    public static final String MSG_FORM_DETAIL_NOT_FOUND = "FormDetail with ID id not found.";
}
