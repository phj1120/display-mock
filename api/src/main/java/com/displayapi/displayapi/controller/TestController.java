package com.displayapi.displayapi.controller;

import com.displayapi.displayapi.entity.TestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/display")
public class TestController {

    @GetMapping("/test")
    public TestEntity test() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1L);

        return testEntity;
    }
}
