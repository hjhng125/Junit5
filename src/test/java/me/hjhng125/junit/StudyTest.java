package me.hjhng125.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static me.hjhng125.junit.StudyStatus.DRAFT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // 언더스코어를 공백으로 치환해주는 전략 클래스
class StudyTest {

    @Test
    @DisplayName("스터디 객체 만들기")
    void create_new_study() {
        Study study = new Study(10);

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(DRAFT, study.status(), "스터디를 처음 만들면 상태값이 DRAFT 이다."),
                () -> assertEquals(DRAFT, study.status(), () -> "스터디를 처음 만들면 상태값이 " + DRAFT + " 이다."), // lambda를 사용하면 문자열 연산이 실패했을때만 수행된다.
                () -> assertTrue(study.limit() > 0, "스터디 정원은 항상 양수이다.")
        );

        System.out.println("create1");
    }

    @Test
    @Disabled
    void create_new_study_again() {
        Study study = new Study(-10);
        assertNotNull(study);
        System.out.println("create2");
    }

    @Test
    void throw_exception_test() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String errorMessage = illegalArgumentException.getMessage();

        assertEquals("limit은 0보다 커야한다.", errorMessage);
    }

    @Test
    void timeout_test() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(300); // 이 코드 블럭이 실행된 후 시간을 계산하고 비교하고자 하는 timeout 시간과 비교한다.
            new Study(10);
        });
    }

    @Test
    void timeout_preemptively_test() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(300); // 위와 다르게 설정한 timeout 시간이 도래하면 바로 예외를 발생시키고 테스트를 종료한다.
            new Study(10);
        });

        // TODO ThreadLocal - 람다는 별도의 쓰레드에서 실행될 수 있기 때문에 ThreadLocal을 사용하는 코드는 원하는 결과를 얻을 수 없을 수 있다.
    }

    @Test
    @DisplayName("환경변수 TEST ENV가 LOCAL이면 실행 (assumeTrue)")
    void env_test() {
        String testEnv = System.getenv("TEST_ENV");
        System.out.println(testEnv);
        assumeTrue("LOCAL".equalsIgnoreCase(testEnv));

        System.out.println("----------");
        Study study = new Study(10);
        assertThat(study.limit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("환경변수 TEST ENV가 LOCAL이면 실행 (assumeThat)")
    void env_test_again() {
        String testEnv = System.getenv("TEST_ENV");
        System.out.println(testEnv);
        assumingThat("LOCAL".equalsIgnoreCase(testEnv), () -> {
            Study study = new Study(10);
            assertThat(study.limit()).isGreaterThan(0);
        });
    }

    @Test
    @EnabledOnOs(OS.MAC)
    @DisplayName("MAC에서만 실행")
    void os_test() {
        System.out.println("ON MAC");
    }

    @Test
    @DisabledOnOs(OS.MAC)
    @DisplayName("MAC이 아니어야 실행")
    void os_no_test() {
        System.out.println("ON MAC");
    }

    @Test
    @EnabledOnJre(value = {JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
    @DisplayName("jre가 8, 9, 10, 11이면 실행")
    void jre_test() {
        System.out.println("ON MAC");
    }

    @Test
    @DisabledOnJre(value = {JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
    @DisplayName("jre가 8, 9, 10, 11 아니면 실행")
    void jre_no_test() {
        System.out.println("ON MAC");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    @DisplayName("환경변수 TEST ENV가 LOCAL이면 실행 annotation")
    void enabled_if_environment_variable() {
        System.out.println("execute");
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    @DisplayName("환경변수 TEST ENV가 LOCAL이면 실행 annotation")
    void disabled_if_environment_variable() {
        System.out.println("execute");
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