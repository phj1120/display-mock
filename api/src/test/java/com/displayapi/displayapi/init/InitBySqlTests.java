package com.displayapi.displayapi.init;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/initData.sql")
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})
public class InitBySqlTests {
    @Test
    void initData() {

    }
}
