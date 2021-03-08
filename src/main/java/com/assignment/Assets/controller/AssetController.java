package com.assignment.Assets.controller;


import com.assignment.Assets.model.Asset;
import com.assignment.Assets.model.dto.AssetRequestDTO;
import com.assignment.Assets.model.dto.ResponseDTO;
import com.assignment.Assets.service.AssetService;
import com.assignment.Assets.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is AssetController class for managing assets.
 * @author Karan
 */
@RestController
@RequestMapping(Constant.ASSET_MAPPING)
public class AssetController {

    /**
     * assetService field for implementation.
     */
    @Autowired
    private AssetService assetService;

    /**
     * This is createAsset() method for creating new assets in a particular category.
     * @param assetRequestDTO field.
     * @return ResponseDTO.
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createAsset(@RequestBody AssetRequestDTO assetRequestDTO){

        final Asset assetResponse = assetService.createAsset(assetRequestDTO);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_CREATE_SUCCESS, assetResponse);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * This is getAllAssets for fetching all assets.
     * @return ResponseDTO.
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllAssets(){

        final List<Asset> assets = assetService.fetchAllAssets();
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSETS_FETCH_SUCCESS, assets);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    /**
     * This is searchAsset() method for finding assets which are start with passing name.
     * @param name field.
     * @return ResponseDTO.
     */
    @GetMapping(value = "/{name}")
    public ResponseEntity<ResponseDTO> searchAsset(@PathVariable(value = "name", required = false) String name){

        final List<Asset> assetsResponse = assetService.searchAssetByName(name);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_FETCH_SUCCESS, assetsResponse);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is updateAsset() method for updating assets in a particular category.
     * @param assetRequestDTO field.
     * @param assetId field.
     * @return ResponseDTO.
     */
    @PutMapping(value = "/update/{assetId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateAsset(@RequestBody final AssetRequestDTO assetRequestDTO,
                                                      @PathVariable(value = "assetId", required = false) Long assetId){

        final Asset assetResponse = assetService.updateAsset(assetRequestDTO, assetId);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_UPDATE_SUCCESS, assetResponse);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    /**
     * This is assignAnAsset() method for assigning assets to employee.
     * @param assetId field.
     * @return ResponseDTO.
     */
    @PutMapping(value = "/assign/{assetId}"
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> assignAnAsset(@PathVariable(value = "assetId", required = false) Long assetId){

        final Asset assetResponse = assetService.assignAsset(assetId);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_ASSIGNED_SUCCESS, assetResponse);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is recoverAnAsset() method for recover assets from employee.
     * @param assetId field.
     * @return ResponseDTO.
     */
    @PutMapping(value = "/recover/{assetId}"
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> recoverAnAsset(@PathVariable(value = "assetId", required = false) Long assetId){

        final Asset assetResponse = assetService.recoverAsset(assetId);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_RECOVERED_SUCCESS, assetResponse);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is deleteAnAsset() method for deleting assets but that deleting asset must not assigned to any employee.
     * @param assetId field.
     * @return ResponseDTO.
     */
    @DeleteMapping(value = "/delete"
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deleteAnAsset(@RequestParam(value = "assetId", required = false) Long assetId){

        final Asset assetResponse = assetService.deleteAsset(assetId);
        final ResponseDTO responseDTO = new ResponseDTO(Constant.SUCCESS,
                Constant.ASSET_DELETE_SUCCESS, assetResponse);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
