package com.example.task_manager.util;

import java.util.regex.Pattern;

public class RegexProcess {

    public static boolean taskIdMatcher(String taskId) {
        String regexForVehicleID = "^T-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForVehicleID);
        return regexPattern.matcher(taskId).matches();
    }

    public static boolean userEmailMatcher(String email) {
        String regexForUserEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern regexPattern = Pattern.compile(regexForUserEmail);
        return regexPattern.matcher(email).matches();
    }
}
