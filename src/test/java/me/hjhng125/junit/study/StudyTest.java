package me.hjhng125.junit.study;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import me.hjhng125.junit.study.Study;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    void create1() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create1");
    }

    @Test
    @Disabled
    void create1_again() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create2");
    }

    @BeforeAll // private은 안되고 리턴타입은 void 여야 한다.
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}