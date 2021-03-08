package com.assignment.Assets.model;

import com.assignment.Assets.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Constant.ASSET)
@EntityListeners(AuditingEntityListener.class)
@Data
public class Asset extends Auditable<String> {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    private String conditionNotes;

    private String assignmentStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = Constant.CATEGORY_ID, nullable = false)
    private AssetCategory assetCategory;

}
