package com.assignment.Assets.model;

import com.assignment.Assets.util.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Constant.ASSET_CATEGORY)
@EntityListeners(AuditingEntityListener.class)
@Data
public class AssetCategory extends Auditable<String> {

    private String name;

    private String description;

}
