package com.displayapi.displayapi.entity.display;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "template_corner")
@SequenceGenerator(
        name = "TEMPLATE_CORNER_GENERATOR",
        sequenceName = "template_corner_seq01",
        initialValue = 1,
        allocationSize = 1
)
public class TemplateCornerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEMPLATE_CORNER_GENERATOR")
    private Long templateCornerNo;

    private String templateCornerName;

    @ManyToOne(fetch = LAZY)
    private TemplateEntity template;

    @ManyToOne(fetch = LAZY)
    private CornerEntity corner;
}
