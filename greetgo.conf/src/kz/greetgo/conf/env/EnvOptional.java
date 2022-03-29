package kz.greetgo.conf.env;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Используется вместе с @{@link EnvName} чтобы указать, что данная переменная окружения не обязательна
 */
@Documented
@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnvOptional {}
