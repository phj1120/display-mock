package com.displayapi.displayapi.util;

import org.junit.jupiter.api.Test;

class EncryptUtilsTest {
    @Test
    void test() {
        TestDto encryptTestDto = new TestDto();
        encryptTestDto.setField1(AESCipher.encrypt("field1"));
        encryptTestDto.setField2(AESCipher.encrypt("field2"));

        TestDto decryptTestDto = EncryptUtils.decryptObject(encryptTestDto, TestDto.class);

        System.out.println("decryptTestDto = " + decryptTestDto);
    }

}


