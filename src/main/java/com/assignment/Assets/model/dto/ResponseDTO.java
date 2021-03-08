package com.assignment.Assets.model.dto;

import com.assignment.Assets.model.AssetCategory;
import lombok.Data;

@Data
public class ResponseDTO {

    private String status;
    private String message;
    private Object response;

    public ResponseDTO(String status, String message, Object response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public ResponseDTO(){}
}
