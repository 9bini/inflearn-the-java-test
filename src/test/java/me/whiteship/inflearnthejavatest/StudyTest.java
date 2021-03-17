package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;


// 테스트 이름 _를 제거한다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {


    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    @EnabledOnOs({OS.MAC, OS.LINUX})
    @EnabledOnJre({JRE.JAVA_11})
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void create_new_study() {
        // 특정한 os, 환경변수, java 버전등을 테스트해야할 때 사용
        String test_env = System.getenv("TEST_ENV");

        assumingThat("LOCAL".equalsIgnoreCase(test_env), ()->{
            System.out.println("test_env = " + test_env);
            Study study = new Study(100);
            assertThat(study.getLimit()).isGreaterThan(0);
        });

        assumingThat("9bin".equalsIgnoreCase(test_env), ()->{
            System.out.println("test_env = " + test_env);
            Study study = new Study(10);
            assertThat(study.getLimit()).isGreaterThan(0);
        });
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        // test_env가 local이 아니기 때문에 아래 테스트 코드가 동작하지 않는다.
        Study study = new Study(10);
        assertThat(study.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 *%$#@!^&^%&^")
    @EnabledOnJre(JRE.OTHER)
    void create1() {
        System.out.println("create1");
    }

    // test 실행 전 한번만 실행된다.
    // 작성 방법은 static 추가, 리턴 타입 void, 메소드명은 자유
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    // test 실행 전 한번만 실행된다.
    // 작성 방법은 BeforeAll과 동일하다.
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    // 각 테스트 실행전에 실행
    // static 없어도 ㄱㅊ
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    // 각 테스트 실행전에 실행
    // static 없어도 ㄱㅊ
    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }


}