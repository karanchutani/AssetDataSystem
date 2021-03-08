package com.assignment.Assets.service;

import com.assignment.Assets.model.Asset;
import com.assignment.Assets.model.dto.AssetRequestDTO;

import java.util.List;

public interface AssetService {

    Asset createAsset(AssetRequestDTO assetRequestDTO);

    List<Asset> fetchAllAssets();

    List<Asset> searchAssetByName(String name);

    Asset updateAsset(AssetRequestDTO assetRequestDTO, Long assetId);

    Asset assignAsset(Long assetId);

    Asset recoverAsset(Long assetId);

    Asset deleteAsset(Long assetId);
}
