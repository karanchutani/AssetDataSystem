package com.assignment.Assets.service;

import com.assignment.Assets.model.Asset;
import com.assignment.Assets.model.AssetCategory;
import com.assignment.Assets.model.dto.AssetRequestDTO;
import com.assignment.Assets.repository.AssetCategoryRepository;
import com.assignment.Assets.repository.AssetRepository;
import com.assignment.Assets.service.impl.AssetServiceImpl;
import com.assignment.Assets.util.Constant;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AssetServiceImplTest {

    @InjectMocks
    private AssetServiceImpl assetService;

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private AssetCategoryRepository assetCategoryRepository;

    @Test
    public void testCreateAsset(){

        final AssetRequestDTO assetRequestDTO = new AssetRequestDTO();
        assetRequestDTO.setName(Constant.ASSET);
        assetRequestDTO.setCategoryId(1L);
        AssetCategory assetCategory = new AssetCategory();
        Optional<AssetCategory> assetCategoryOptional = Optional.of(assetCategory);

        lenient().when(assetCategoryRepository
                .findById(anyLong())).thenReturn(assetCategoryOptional);

        lenient().when(assetRepository.saveAndFlush(any(Asset.class))).thenReturn(null);

        final Asset asset = assetService.createAsset(assetRequestDTO);
        verify(assetRepository, times(1)).saveAndFlush(any(Asset.class));
        verify(assetCategoryRepository, times(1)).findById(anyLong());
        Assert.assertNull(asset);
    }

    @Test
    public void testFetchAllAssets(){
        lenient().when(assetRepository.findAll()).thenReturn(null);
        final List<Asset> assetList = assetService.fetchAllAssets();
        verify(assetRepository, times(1)).findAll();
        Assert.assertNull(assetList);
    }

    @Test
    public void testSearchAssetByName(){
        final String name = "NAME";
        lenient().when(assetRepository.findByNameStartingWithIgnoreCase(anyString())).thenReturn(null);
        final List<Asset> assetList = assetService.searchAssetByName(name);
        verify(assetRepository, times(1)).findByNameStartingWithIgnoreCase(name);
        Assert.assertNull(assetList);
    }

    @Test
    public void testUpdateAsset(){

        final Long assetId = 1L;
        final AssetRequestDTO assetRequestDTO = new AssetRequestDTO();
        assetRequestDTO.setName(Constant.ASSET);
        assetRequestDTO.setCategoryId(1L);

        final AssetCategory assetCategory = new AssetCategory();
        Optional<AssetCategory> assetCategoryOptional = Optional.of(assetCategory);

        lenient().when(assetCategoryRepository
                .findById(anyLong())).thenReturn(assetCategoryOptional);

        final Asset asset = new Asset();
        final Optional<Asset> assetOptional = Optional.of(asset);

        lenient().when(assetRepository.findById(anyLong())).thenReturn(assetOptional);
        lenient().when(assetRepository.saveAndFlush(any(Asset.class))).thenReturn(null);
        final Asset asset1 = assetService.updateAsset(assetRequestDTO, assetId);
        Assert.assertNull(asset1);
        verify(assetRepository, times(1)).saveAndFlush(any(Asset.class));
        verify(assetRepository, times(1)).findById(anyLong());

    }

    @Test
    public void testAssignAsset(){

        final Long assetId = 1L;
        final Asset asset = new Asset();
        final Optional<Asset> assetOptional = Optional.of(asset);

        lenient().when(assetRepository.findById(anyLong())).thenReturn(assetOptional);
        lenient().when(assetRepository.saveAndFlush(any(Asset.class))).thenReturn(asset);
        final Asset asset1 = assetService.assignAsset(assetId);
        Assert.assertNotNull(asset1);
        verify(assetRepository, times(1)).saveAndFlush(any(Asset.class));
    }

    @Test
    public void testRecoverAsset(){

        final Long assetId = 1L;
        final Asset asset = new Asset();
        final Optional<Asset> assetOptional = Optional.of(asset);

        lenient().when(assetRepository.findById(anyLong())).thenReturn(assetOptional);
        lenient().when(assetRepository.saveAndFlush(any(Asset.class))).thenReturn(asset);
        final Asset asset1 = assetService.recoverAsset(assetId);
        Assert.assertNotNull(asset1);
        verify(assetRepository, times(1)).saveAndFlush(any(Asset.class));
    }

    @Test
    public void testDeleteAsset(){

        final Long assetId = 1L;
        final Asset asset = new Asset();
        asset.setAssignmentStatus("NA");
        final Optional<Asset> assetOptional = Optional.of(asset);

        lenient().when(assetRepository.findById(anyLong())).thenReturn(assetOptional);
        lenient().doNothing().when(assetRepository).delete(any(Asset.class));
        final Asset deletedAsset = assetService.deleteAsset(assetId);
        Assert.assertNotNull(deletedAsset);
        verify(assetRepository, times(1)).findById(anyLong());
    }
}
