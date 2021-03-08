package com.assignment.Assets.util;

import com.assignment.Assets.exception.InvalidRequestException;
import com.assignment.Assets.model.Asset;
import com.assignment.Assets.model.AssetCategory;
import com.assignment.Assets.model.dto.AssetRequestDTO;

/**
 * This is Validation class for validating data.
 * @author Karan
 */
public class Validation {

    /**
     * This is static validateCategory method for validating category.
     * @param assetCategory field.
     */
    public static void validateCategory(AssetCategory assetCategory) {
        if(assetCategory.getName()== null|| assetCategory.getName().isBlank()){
            throw new InvalidRequestException(Constant.EMPTY_CAT_NAME_EXCEPTION_MSG);
        }

    }

    /**
     * This is validateAssetRequestDTO() method for validating request.
     * @param assetRequestDTO field.
     */
    public static void validateAssetRequestDTO(AssetRequestDTO assetRequestDTO) {

        if(assetRequestDTO.getName()== null || assetRequestDTO.getName().isBlank()){
            throw new InvalidRequestException(Constant.EMPTY_ASSET_NAME_EXCEPTION_MSG);
        }

        if(assetRequestDTO.getCategoryId()==null){
            throw new InvalidRequestException(Constant.EMPTY_CAT_ID_MSG);
        }
    }

    /**
     * This is validateAssetRequestDTOInUpdateCase() method.
     * @param assetRequestDTO field.
     */
    public static void validateAssetRequestDTOInUpdateCase(AssetRequestDTO assetRequestDTO) {

        if(assetRequestDTO.getName()== null || assetRequestDTO.getName().isBlank()){
            throw new InvalidRequestException(Constant.EMPTY_ASSET_NAME_EXCEPTION_MSG);
        }
    }
}
