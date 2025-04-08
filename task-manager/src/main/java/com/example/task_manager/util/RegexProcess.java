package com.example.task_manager.util;

import java.util.regex.Pattern;

public class RegexProcess {

    public static boolean vehicleIdMatcher(String vehicleCode) {
        String regexForVehicleID = "^VEHICLE-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForVehicleID);
        return regexPattern.matcher(vehicleCode).matches();
    }

    public static boolean userEmailMatcher(String email) {
        String regexForUserEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern regexPattern = Pattern.compile(regexForUserEmail);
        return regexPattern.matcher(email).matches();
    }
}
