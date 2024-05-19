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
public class CornerEntity {
    @Id
    @GeneratedValue
    private Long cornerNo;

    private String cornerName;

    @OneToMany(fetch = LAZY, mappedBy = "corner")
    private List<TemplateCornerEntity> templateCornerList = new ArrayList<>();
}
