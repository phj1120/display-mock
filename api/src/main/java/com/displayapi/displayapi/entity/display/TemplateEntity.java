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
@Table(name = "template")
@SequenceGenerator(
        name = "TEMPLATE_GENERATOR",
        sequenceName = "template_seq01",
        initialValue = 1,
        allocationSize = 1
)
public class TemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEMPLATE_GENERATOR")
    private Long templateNo;

    private String templateName;

    private String templateId;

    @OneToMany(mappedBy = "template", fetch = LAZY)
    private List<TemplateCornerEntity> cornerList = new ArrayList<>();
}
