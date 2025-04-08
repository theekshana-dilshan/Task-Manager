package com.example.task_manager.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean cropIdMatcher(String cropCode) {
        String regexForCropID = "^CROP-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForCropID);
        return regexPattern.matcher(cropCode).matches();
    }

    public static boolean equipmentIdMatcher(String equipmentId) {
        String regexForEquipmentID = "^EQUIPMENT-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForEquipmentID);
        return regexPattern.matcher(equipmentId).matches();
    }

    public static boolean fieldIdMatcher(String fieldCode) {
        String regexForFieldID = "^FIELD-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForFieldID);
        return regexPattern.matcher(fieldCode).matches();
    }

    public static boolean logIdMatcher(String logCode) {
        String regexForLogID = "^LOG-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForLogID);
        return regexPattern.matcher(logCode).matches();
    }

    public static boolean staffIdMatcher(String staffId) {
        String regexForStaffID = "^STAFF-\\d{4}$";
        Pattern regexPattern = Pattern.compile(regexForStaffID);
        return regexPattern.matcher(staffId).matches();
    }

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
