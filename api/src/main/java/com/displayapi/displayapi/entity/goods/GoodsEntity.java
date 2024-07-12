package com.displayapi.displayapi.entity.goods;

import com.displayapi.displayapi.entity.display.CategoryEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "goods")
@NoArgsConstructor
@SequenceGenerator(
        name = "GOODS_GENERATOR",
        sequenceName = "goods_seq01",
        initialValue = 1,
        allocationSize = 1
)
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOODS_GENERATOR")
    private Long goodsNo; // 상품 번호

    private Long price; // 가격

    private Long installmentAmount; // 할부금액

    private Integer installmentMonth; // 할부개월

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity categoryEntity;

    private int rank; // 순위

    private boolean isDisplay; // 전시중

    private boolean isSale; // 판매중

    private LocalDateTime displayStartDtm; // 전시시작일시

    private LocalDateTime displayEndDtm; // 전시종료일시

    private LocalDateTime saleStartDtm; // 판매시작일시

    private LocalDateTime saleEndDtm; // 판매종료일시

    private boolean isInterestFreeGoods; // 무이자상품여부

    private boolean isInstallmentGoods; // 할부상품여부

    private boolean isLumpSumPayGoods; // 일시불상품여부
}
