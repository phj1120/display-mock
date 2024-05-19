package com.displayapi.displayapi.repository.display;

import com.displayapi.displayapi.entity.display.CornerEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.displayapi.displayapi.entity.display.QCornerEntity.cornerEntity;

@RequiredArgsConstructor
public class CornerRepositoryCustomImpl implements CornerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public CornerEntity findByCornerName(String cornerName) {
        return queryFactory
                .selectFrom(cornerEntity)
                .where(cornerEntity.cornerName.eq(cornerName))
                .fetchOne();
    }
}
