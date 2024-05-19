package com.displayapi.displayapi.entity.display;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TemplateEntity {
    @Id
    @GeneratedValue
    private Long templateNo;

    private String templateName;

    @OneToMany(mappedBy = "template", fetch = LAZY)
    private List<ShopTemplateEntity> shopTemplateList = new ArrayList<>();

    @OneToMany(mappedBy = "template", fetch = LAZY)
    private List<TemplateCornerEntity> cornerList = new ArrayList<>();
}
