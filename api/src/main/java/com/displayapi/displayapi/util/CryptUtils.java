package com.displayapi.displayapi.util;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;

public class CryptUtils {
    public static <T> T encryptObject(T original, Class<? extends T> clazz) {
        try {
            T encryptObj = clazz.getDeclaredConstructor().newInstance();
            ReflectionUtils.doWithFields(clazz, (field) -> {
                ReflectionUtils.makeAccessible(field);
                Object fieldObject = ReflectionUtils.getField(field, original);
                if (fieldObject instanceof String fieldString) {
                    ReflectionUtils.setField(field, encryptObj, AESCipher.encrypt(fieldString));
                }
            });

            return encryptObj;
        } catch (InvocationTargetException | InstantiationException
                 | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T decryptObject(T original, Class<? extends T> clazz) {
        try {
            T decryptObj = clazz.getDeclaredConstructor().newInstance();
            ReflectionUtils.doWithFields(clazz, (field) -> {
                ReflectionUtils.makeAccessible(field);
                Object fieldObject = ReflectionUtils.getField(field, original);
                if (fieldObject instanceof String fieldString) {
                    ReflectionUtils.setField(field, decryptObj, AESCipher.decrypt(fieldString));
                }
            });

            return decryptObj;
        } catch (InvocationTargetException | InstantiationException
                 | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
