package com.example.task_manager.customStatusCodes;

import com.example.task_manager.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CustomStatus {
    private int status;
    private String statusMessage;
}
