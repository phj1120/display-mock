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
public class TemplateCornerEntity {
    @Id
    @GeneratedValue
    private Long templateCornerNo;

    private String templateCornerName;

    @ManyToOne(fetch = LAZY)
    private TemplateEntity template;

    @ManyToOne(fetch = LAZY)
    private CornerEntity corner;
}
