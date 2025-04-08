package com.example.task_manager.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String profilePicToBase64(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }

    public static String generateTaskId(){
        return "T-"+ UUID.randomUUID();
    }
}
