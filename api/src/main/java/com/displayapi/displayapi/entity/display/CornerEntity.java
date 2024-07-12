package com.displayapi.displayapi.entity.display;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "corner")
@NoArgsConstructor
@SequenceGenerator(
        name = "CORNER_GENERATOR",
        sequenceName = "corner_seq01",
        initialValue = 1,
        allocationSize = 1
)
public class CornerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CORNER_GENERATOR")
    private Long cornerNo;

    private String cornerId;

    private String cornerName;
}
