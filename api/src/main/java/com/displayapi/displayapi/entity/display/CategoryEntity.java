package com.displayapi.displayapi.entity.display;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category")
@NoArgsConstructor
@SequenceGenerator(
        name = "CATEGORY_GENERATOR",
        sequenceName = "category_seq01",
        initialValue = 1,
        allocationSize = 1
)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_GENERATOR")
    private Long categoryNo;

    private String categoryName;

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }
}
