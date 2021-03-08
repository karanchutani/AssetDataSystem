package com.assignment.Assets.repository;

import com.assignment.Assets.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByNameStartingWithIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}
