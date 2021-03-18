package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;


// 테스트 이름 _를 제거한다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    // fast 태그만 달린 테스트 메소드만 실행하고 싶으면
    // 실행 설정을 클래스에서 태그로 설정을 바꾸고 테스트 하고 싶은 태그명을 입력해야한다.

    // ./mvnw test -P ci


    @DisplayName("스터디 만들기 fast")
    @FastTest
    void create_new_study() {
        Study study = new Study(10);
        assertThat(study.getLimit()).isGreaterThan(0);
    }

    @DisplayName("스터디 만들기 slow")
    @SlowTest
    void create_new_study_again() {
        System.out.println("create1");
    }

    @DisplayName("스터디 만들기 RepeatedTest")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    // Junit 5에서는 그냥 지원해주고
    // Junit 4는 서브 파티를 이용해야 사용 가능하다
    // @ParameterizedTest

    // test 실행 전 한번만 실행된다.
    // 작성 방법은 static 추가, 리턴 타입 void, 메소드명은 자유
    @DisplayName("스터디 만들기 ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
//    @ValueSource(ints = {10, 20, 40})
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
//    void parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {
//            System.out.println("study.getLimit() = " + study.getLimit());

    /*void parameterizedTest(int limit, String name){
        System.out.println(new Study(limit, name));*/

    void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study){
        System.out.println("study = " + study);
    }

    // 제약 조건 public class 이야하고 static을 붙여줘야한다.
    static class StudyAggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
            return study;
        }
    }

    // 하나만 적용 가능하다.
    static class StudyConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

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