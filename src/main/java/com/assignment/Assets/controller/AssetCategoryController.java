package com.assignment.Assets.controller;

import com.assignment.Assets.model.AssetCategory;
import com.assignment.Assets.model.dto.ResponseDTO;
import com.assignment.Assets.service.AssetCategoryService;
import com.assignment.Assets.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is AssetCategoryController class for managing categories of assets.
 * @author Karan
 */
@RestController
@RequestMapping(Constant.CATEGORY_MAPPING)
public class AssetCategoryController {

    /**
     * assetCategoryService field for implementation.
     */
    @Autowired
    private AssetCategoryService assetCategoryService;

    /**
     * This is createAssetCategory() method for creating new asset category.
     * @param assetCategory field.
     * @return ResponseDTO.
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createAssetCategory(@RequestBody AssetCategory assetCategory){

        final AssetCategory assetCategoryResponse = assetCategoryService.createCategory(assetCategory);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_CATEGORY_CREATE_SUCCESS, assetCategoryResponse);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    /**
     * This is getAllCategories() method for fetching all asset categories.
     * @return ResponseDTO.
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllCategories(){

        final List<AssetCategory> assetCategories = assetCategoryService.fetchAllCategories();
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_CATEGORY_FETCH_SUCCESS, assetCategories);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    /**
     * This is updateCategory() method for updating asset category.
     * @param assetCategory field.
     * @param categoryId field.
     * @return ResponseDTO.
     */
    @PutMapping(value = "/update/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateCategory(@RequestBody final AssetCategory assetCategory,
                                                      @PathVariable(value = "categoryId", required = false) Long categoryId){

        final AssetCategory assetCategoryResponse = assetCategoryService.updateCategory(assetCategory, categoryId);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_CATEGORY_UPDATE_SUCCESS, assetCategoryResponse);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
