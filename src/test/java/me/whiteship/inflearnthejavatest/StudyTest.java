package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


// Junit5의 새로운 기능
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {
    // @TestInstance 설정 전 == Junit 기본 전략
    // create_new_study, create_new_study_again
    // 각 인스턴스로 실행하여 i 출력값 이 전부 1이 된다.
    int i = 1;

    @Order(2)
    @DisplayName("스터디 만들기 fast")
    @FastTest
    void create_new_study() {
        Study study = new Study(10);
        System.out.println(i++);
        System.out.println(this);
        assertThat(study.getLimit()).isGreaterThan(0);
    }
    @Order(1)
    @DisplayName("스터디 만들기 slow")
    @SlowTest
    void create_new_study_again() {
        System.out.println(i++);
        System.out.println(this);
        System.out.println("create1");
    }

    @Order(4)
    @DisplayName("스터디 만들기 RepeatedTest")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @Order(3)
    @DisplayName("스터디 만들기 ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
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