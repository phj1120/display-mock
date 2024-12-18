package com.displayapi.displayapi.util;

import org.junit.jupiter.api.Test;

class CryptUtilsTest {
    @Test
    void test() {
        TestDto testDto = new TestDto();
        testDto.setField1("field1");
        testDto.setField2("field2");
        testDto.setField3("1");
        testDto.setIntField1(10);

        TestDto encryptObject = CryptUtils.encryptObject(testDto, TestDto.class);
        System.out.println("encryptObject = " + encryptObject);

        TestDto decryptTestDto = CryptUtils.decryptObject(encryptObject, TestDto.class);
        System.out.println("decryptTestDto = " + decryptTestDto);
    }

}


