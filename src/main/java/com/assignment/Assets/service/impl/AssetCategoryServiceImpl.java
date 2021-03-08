package com.assignment.Assets.service.impl;

import com.assignment.Assets.exception.InvalidRequestException;
import com.assignment.Assets.model.AssetCategory;
import com.assignment.Assets.repository.AssetCategoryRepository;
import com.assignment.Assets.service.AssetCategoryService;
import com.assignment.Assets.util.Constant;
import com.assignment.Assets.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is AssetCategoryServiceImpl implementation class for manipulating assets categories.
 * @author Karan
 */
@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {

    /**
     * assetCategoryRepository field.
     */
    @Autowired
    private AssetCategoryRepository assetCategoryRepository;

    /**
     * This is createCategory() implementation method for creating new category of asset.
     * @param assetCategory field.
     * @return AssetCategory.
     */
    @Override
    public AssetCategory createCategory(AssetCategory assetCategory) {

        Validation.validateCategory(assetCategory);
        if(assetCategoryRepository.existsByNameIgnoreCase(assetCategory.getName())){
            throw new InvalidRequestException(Constant.CATEGORY_ALREADY_EXIST_MSG);
        }
        return assetCategoryRepository.saveAndFlush(assetCategory);
    }

    /**
     * This is fetchAllCategories() implementation method for fetching all categories.
     * @return List of category
     */
    @Override
    public List<AssetCategory> fetchAllCategories() {
        return assetCategoryRepository.findAll();
    }

    /**
     * This is updateCategory() method for updating category.
     * @param assetCategory field.
     * @param categoryId field.
     * @return AssetCategory.
     */
    @Override
    public AssetCategory updateCategory(AssetCategory assetCategory, Long categoryId) {

        if(categoryId==null){
            throw new InvalidRequestException(Constant.EMPTY_CAT_ID_MSG);
        }
        if(assetCategory.getName()!=null && !assetCategory.getName().isBlank()){
            if(assetCategoryRepository.existsByNameIgnoreCase(assetCategory.getName())){
                throw new InvalidRequestException(Constant.CATEGORY_ALREADY_EXIST_MSG);
            }
        }
        final Optional<AssetCategory> assetCategoryOptional = assetCategoryRepository.findById(categoryId);
        if(assetCategoryOptional.isEmpty()){
            throw new InvalidRequestException(Constant.CATEGORY_NOT_PRESENT_MESSAGE + categoryId);
        }
        final AssetCategory updatedCategory = getRequestCategory(assetCategoryOptional.get(), assetCategory);

        return assetCategoryRepository.saveAndFlush(updatedCategory);
    }

    private AssetCategory getRequestCategory(AssetCategory savedCategory, AssetCategory assetCategory) {
        if(assetCategory.getName()!=null && !assetCategory.getName().isBlank()){
            savedCategory.setName(assetCategory.getName());
        }
        if(assetCategory.getDescription()!=null && !assetCategory.getDescription().isBlank()){
            savedCategory.setDescription(assetCategory.getDescription());
        }
        return savedCategory;
    }

}
