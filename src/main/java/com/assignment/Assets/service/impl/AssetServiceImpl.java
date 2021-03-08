package com.assignment.Assets.service.impl;

import com.assignment.Assets.exception.CategoryNotPresentException;
import com.assignment.Assets.exception.InvalidRequestException;
import com.assignment.Assets.model.Asset;
import com.assignment.Assets.model.AssetCategory;
import com.assignment.Assets.model.dto.AssetRequestDTO;
import com.assignment.Assets.repository.AssetCategoryRepository;
import com.assignment.Assets.repository.AssetRepository;
import com.assignment.Assets.service.AssetService;
import com.assignment.Assets.util.Constant;
import com.assignment.Assets.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is AssetServiceImpl implementation class.
 * @author Karan
 */
@Service
public class AssetServiceImpl implements AssetService {

    /**
     * assetRepository field.
     */
    @Autowired
    private AssetRepository assetRepository;

    /**
     * assetCategoryRepository field.
     */
    @Autowired
    private AssetCategoryRepository assetCategoryRepository;

    /**
     * This is createAsset() implementation method for creating new asset.
     * @param assetRequestDTO field.
     * @return Asset.
     */
    @Override
    public Asset createAsset(AssetRequestDTO assetRequestDTO) {
        Validation.validateAssetRequestDTO(assetRequestDTO);
        final Optional<AssetCategory> assetCategoryOptional = assetCategoryRepository
                .findById(assetRequestDTO.getCategoryId());

        if(assetCategoryOptional.isEmpty()){
            throw new CategoryNotPresentException(Constant.CATEGORY_NOT_PRESENT_MESSAGE + assetRequestDTO.getCategoryId());
        }

        if(assetRepository.existsByNameIgnoreCase(assetRequestDTO.getName())){
            throw new InvalidRequestException(Constant.ASSET_ALREADY_EXIST_MSG);
        }
        final Asset requestedAsset = getRequestedAsset(assetRequestDTO, assetCategoryOptional.get());

        return assetRepository.saveAndFlush(requestedAsset);
    }

    /**
     * This is fetchAllAssets() implementation method for fetching all assets.
     * @return List of assets.
     */
    @Override
    public List<Asset> fetchAllAssets() {
        return assetRepository.findAll();
    }

    /**
     * This is searchAssetByName() implementation method for searching asset by given name.
     * @param name field.
     * @return List of asset.
     */
    @Override
    public List<Asset> searchAssetByName(String name) {

        if(name.isBlank()){
            throw new InvalidRequestException(Constant.EMPTY_ASSET_NAME_EXCEPTION_MSG);
        }
        return assetRepository.findByNameStartingWithIgnoreCase(name);
    }

    /**
     * This is updateAsset() implementation method for updating assets
     * @param assetRequestDTO field.
     * @param assetId field.
     * @return Asset.
     */
    @Override
    public Asset updateAsset(AssetRequestDTO assetRequestDTO, Long assetId) {

        if(assetId==null){
            throw new InvalidRequestException(Constant.EMPTY_ASSET_ID_MSG);
        }

        AssetCategory assetCategory = null;
        if(assetRequestDTO.getCategoryId() != null){
            Optional<AssetCategory> assetCategoryOptional = assetCategoryRepository
                    .findById(assetRequestDTO.getCategoryId());

            if(assetCategoryOptional.isEmpty()){
                throw new InvalidRequestException(Constant.CATEGORY_NOT_PRESENT_MESSAGE + assetRequestDTO.getCategoryId());
            }
            else{
                assetCategory = assetCategoryOptional.get();
            }
        }
        final Optional<Asset> assetOptional = assetRepository.findById(assetId);

        if(assetOptional.isEmpty()){
            throw new InvalidRequestException(Constant.ASSET_NOT_PRESENT_MESSAGE + assetId);
        }
        if(assetCategory==null){
            assetCategory = assetOptional.get().getAssetCategory();
        }
        final Asset savedAsset = assetOptional.get();
        final Asset updatedAsset = getRequestedUpdatedAsset(assetRequestDTO,assetCategory, savedAsset);

        return assetRepository.saveAndFlush(updatedAsset);
    }

    private Asset getRequestedUpdatedAsset(AssetRequestDTO assetRequestDTO, AssetCategory assetCategory, Asset savedAsset) {

        if(assetRequestDTO.getName()!=null && !assetRequestDTO.getName().isBlank()){
            if(assetRepository.existsByNameIgnoreCase(assetRequestDTO.getName())){
                throw new InvalidRequestException(Constant.ASSET_ALREADY_EXIST_MSG);
            }
            savedAsset.setName(assetRequestDTO.getName());
        }
        if(assetRequestDTO.getAssignmentStatus()!=null && !assetRequestDTO.getAssignmentStatus().isBlank()){
            savedAsset.setAssignmentStatus(assetRequestDTO.getAssignmentStatus());
        }
        if(assetRequestDTO.getConditionNotes()!=null && !assetRequestDTO.getConditionNotes().isBlank()){
            savedAsset.setConditionNotes(assetRequestDTO.getConditionNotes());
        }
        if(assetRequestDTO.getPurchaseDate()!=null){
            savedAsset.setPurchaseDate(assetRequestDTO.getPurchaseDate());
        }
        savedAsset.setAssetCategory(assetCategory);

        return savedAsset;
    }

    /**
     * This is assignAsset() implementation method for assigning assets to employee.
     * @param assetId field.
     * @return Asset.
     */
    @Override
    public Asset assignAsset(Long assetId) {
        if(assetId==null){
            throw new InvalidRequestException(Constant.EMPTY_ASSET_ID_MSG);
        }
        final Optional<Asset> assetOptional = assetRepository.findById(assetId);
        if(assetOptional.isEmpty()){
            throw new InvalidRequestException(Constant.ASSET_NOT_PRESENT_MESSAGE + assetId);
        }
        final Asset assignedAsset = assetOptional.get();
        assignedAsset.setAssignmentStatus(Constant.ASSIGNED);

        return assetRepository.saveAndFlush(assignedAsset);
    }

    /**
     * This is recoverAsset() implementation method for recover assets from employee.
     * @param assetId field.
     * @return Asset.
     */
    @Override
    public Asset recoverAsset(Long assetId) {

        if(assetId==null){
            throw new InvalidRequestException(Constant.EMPTY_ASSET_ID_MSG);
        }
        final Optional<Asset> assetOptional = assetRepository.findById(assetId);
        if(assetOptional.isEmpty()){
            throw new InvalidRequestException(Constant.ASSET_NOT_PRESENT_MESSAGE + assetId);
        }
        final Asset recoveredAsset = assetOptional.get();
        recoveredAsset.setAssignmentStatus(Constant.RECOVERED);

        return assetRepository.saveAndFlush(recoveredAsset);
    }

    /**
     * This is deleteAsset() implementation method for deleting assets.
     * @param assetId field.
     * @return Asset.
     */
    @Override
    public Asset deleteAsset(Long assetId) {

        if(assetId==null){
            throw new InvalidRequestException(Constant.EMPTY_ASSET_ID_MSG);
        }
        final Optional<Asset> assetOptional = assetRepository.findById(assetId);
        if(assetOptional.isEmpty()){
            throw new InvalidRequestException(Constant.ASSET_NOT_PRESENT_MESSAGE + assetId);
        }
        final Asset deletedAsset = assetOptional.get();

        if(deletedAsset.getAssignmentStatus().equalsIgnoreCase(Constant.ASSIGNED)){
            String message = Constant.ASSET_NOT_DELETE_MESSAGE;
            message = message.replace("{}",String.valueOf(assetId));
            throw new InvalidRequestException(message);
        }
        assetRepository.delete(deletedAsset);
        return deletedAsset;
    }

    /**
     * Ths is getRequestedAsset() method.
     * @param assetRequestDTO field.
     * @param assetCategory field.
     * @return Asset.
     */
    private Asset getRequestedAsset(AssetRequestDTO assetRequestDTO, AssetCategory assetCategory) {

        final Asset asset =new Asset();
        asset.setName(assetRequestDTO.getName());
        asset.setAssignmentStatus(assetRequestDTO.getAssignmentStatus());
        asset.setConditionNotes(assetRequestDTO.getConditionNotes());
        asset.setAssetCategory(assetCategory);
        asset.setPurchaseDate(assetRequestDTO.getPurchaseDate());

        return asset;
    }
}
