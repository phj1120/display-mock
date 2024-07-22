package com.displayapi.displayapi.repository.goods;

public class GoodsAllTabTest {
    // 하고 싶은 것.
    // 조건에 해당하는 상픔의 개수가 있다.(하위 탭에 대한 정보만)
    // 탭을 상위 탭으로 묶고, 해당 뎁스의 전체를 보기 위한 탭도 추가해 아래 형식으로 반환 한다.
    //
    // List 상위 탭
    //  List 하위 탭
    //
    // + 아래 케이스도 고려 되어야 한다.
    // 1depth: 전체
    // 2depth: 전체, 2depth ...

    // 1depth 의 전체 값은 1depth 에 해당하는 조건이 null
    // 2depth 의 전체 값은 1depth 의 조건에 2depth 에 해당하는 조건이 null
    // => 이전 depth 의 조건과 동일

    // 카테고리 번호로 묶고, 해당 값을 하위로 갖는 상위 탭 생성
    // 상위 탭 생성 하며, 하위 탭에 상위 탭을 기반으로 한 전체 값 추가

    // depth 별로 기준이 뭔지 관리가 필요할까?
    // depthType: 중카, 대카, 적용가, 월할부금, 무이자개월
    // firstDepth:
    // secondDepth:
    // 검색 타입 별로, 1차, 2차 검색 조건 뭔지 Enum 으로 관리

    // vue
    // watch 1차 탭 변경
    // -> 상품 정보 초기화
    // -> 1차 탭에 담긴 하위 탭 정보로 2차 탭 변경
    // watch 2차 탭 변경
    // -> 2차 탭 정보 조합해 삼품 조회 API 요청
}
