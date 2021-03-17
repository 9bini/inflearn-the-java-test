package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 테스트 이름 _를 제거한다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    // public juint4에서는 사용해야하지만
    // junit5에서는 생략해도 된다.
    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    void create_new_study() {
        Study study = new Study(10);
        assertThat(study.getLimit()).isGreaterThan(0);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = illegalArgumentException.getMessage();
        assertEquals("limit은 0보다 커야한다.", message);

        // assertAll을 사용하면 아래의 테스트 중 실패한 모든 테스트를 표시해준다.
        // 그외에서는 초음 실패한 테스트만 표현한다.
        assertAll(
                () -> assertNotNull(study),
                /*
                 메소드 순서 1,2은 달라도 괜찮지만
                 메소드 의도에 맞게
                 expected 기대값, actual 실제깂 순으로 넣자
                */
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디 처음 만들면 상태값이 DRAFT이다."),
                // 단순 문자열 타입으로 하는 것 보다 Supplier를 사용하면 message관련 연산을 필요한 시점에한다..
                // Supplier는 테스트를 실패할 때만 메시지 연산을 하지만 문자열 타입은 테스트를 실패해도 성공을해도 연산을 한다.
                // Supplier는 메시지 연산이 비용이 들때 사용하는 걸 추천 (상환에 맞에 선택해)

                () -> assertTrue(study.getLimit() > 0, "스터디 최대인원은 0보다 커야한다.")
        );
        // assertTimeout 단점 실행이 완료 될때까지 기다린다. 하지만 안전하다.
        // assertTimeoutPreemptively 100 초과하면 바로 종료
            // junit5에서 트랜잭션 처리가 기본적으로 롤백이 기본이지만
            // (트랜잭션 적용된 쓰레드가 아닌)쓰레드 로컬를 사용해서 트랜잭션을 적용이 안 될 수 있다
        assertTimeout(Duration.ofSeconds(100), ()->{
            new Study(10);
            Thread.sleep(3000);
        });
    }

    @Test
    @DisplayName("스터디 만들기 *%$#@!^&^%&^")
        // 여러 상황에 사용될 수 있다.
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