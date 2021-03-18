package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Test
@Tag("fast")
// 태그 값이 타입 세이프티 하지 않기 때문에 우리가 원한는 동작은 안할 수 있다.
// 커스텀한 에노테이션을 사용하는게 더 안전하다.
public @interface FastTest {
}
