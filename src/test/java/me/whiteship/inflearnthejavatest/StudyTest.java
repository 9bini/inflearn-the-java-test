package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// 테스트 이름 _를 제거한다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    // public juint4에서는 사용해야하지만
    // junit5에서는 생략해도 된다.
    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    void create(){
        Study study = new Study();
        assertNotNull(study);
    }
    @Test
    @DisplayName("스터디 만들기 *%$#@!^&^%&^")
    // 여러 상황에 사용될 수 있다.
    void create1(){
        System.out.println("create1");
    }

    // test 실행 전 한번만 실행된다.
    // 작성 방법은 static 추가, 리턴 타입 void, 메소드명은 자유
    @BeforeAll
    static void beforeAll(){
        System.out.println("before all");
    }
    // test 실행 전 한번만 실행된다.
    // 작성 방법은 BeforeAll과 동일하다.
    @AfterAll
    static void afterAll(){
        System.out.println("after all");
    }

    // 각 테스트 실행전에 실행
    // static 없어도 ㄱㅊ
    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    // 각 테스트 실행전에 실행
    // static 없어도 ㄱㅊ
    @AfterEach
    void afterEach(){
        System.out.println("after each");
    }




}