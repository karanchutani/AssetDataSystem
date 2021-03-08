package com.assignment.Assets.service;

import com.assignment.Assets.model.AssetCategory;
import com.assignment.Assets.repository.AssetCategoryRepository;
import com.assignment.Assets.service.impl.AssetCategoryServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AssetCategoryServiceImplTest {

    @Mock
    private AssetCategoryRepository assetCategoryRepository;

    @InjectMocks
    private AssetCategoryServiceImpl assetCategoryService;

    @Test
    public void testCreateCategory(){
        final AssetCategory assetCategory = new AssetCategory();
        assetCategory.setName("NAME");
        lenient().when(assetCategoryRepository.saveAndFlush(any(AssetCategory.class))).thenReturn(assetCategory);
        final AssetCategory returnCategory = assetCategoryService.createCategory(assetCategory);
        Assert.assertEquals(returnCategory.getName(),"NAME");
        verify(assetCategoryRepository, times(1)).saveAndFlush(any(AssetCategory.class));
    }

    @Test
    public void testFetchAllCategories(){

        lenient().when(assetCategoryRepository.findAll()).thenReturn(null);
        final List<AssetCategory> assetCategories = assetCategoryService.fetchAllCategories();
        Assert.assertNull(assetCategories);
        verify(assetCategoryRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateCategory(){
        final Long categoryId = 1L;
        final AssetCategory assetCategory = new AssetCategory();
        assetCategory.setName("NAME");
        final Optional<AssetCategory> assetCategoryOptional = Optional.of(assetCategory);
        lenient().when(assetCategoryRepository.findById(anyLong())).thenReturn(assetCategoryOptional);
        lenient().when(assetCategoryRepository.saveAndFlush(any(AssetCategory.class))).thenReturn(assetCategory);
        final AssetCategory returnCategory = assetCategoryService.updateCategory(assetCategory, categoryId);

        Assert.assertEquals(returnCategory.getName(),"NAME");
        verify(assetCategoryRepository, times(1)).saveAndFlush(any(AssetCategory.class));
    }
}
