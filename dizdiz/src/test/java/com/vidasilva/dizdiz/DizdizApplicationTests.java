package com.vidasilva.dizdiz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.profiles.active=test")
class DizdizApplicationTests {

    @Test
    void contextLoads() {
    }

}
