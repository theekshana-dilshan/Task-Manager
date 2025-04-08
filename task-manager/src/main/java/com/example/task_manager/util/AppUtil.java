package com.example.task_manager.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String profilePicToBase64(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }

    public static String generateFieldId(){
        return "F-"+ UUID.randomUUID();
    }

    public static String generateCropId(){
        return "C-"+ UUID.randomUUID();
    }

    public static String generateStaffId(){
        return "S-"+ UUID.randomUUID();
    }

    public static String generateVehicleId(){
        return "V-"+ UUID.randomUUID();
    }

    public static String generateEquipmentId(){
        return "E-"+ UUID.randomUUID();
    }

    public static String generateLogId(){
        return "LOG-"+ UUID.randomUUID();
    }
}
