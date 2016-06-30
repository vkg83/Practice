package com.vkg.tools.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Sheet {
    String oldName();

    String newName();

    boolean aggregate() default false;

    boolean allowDuplicate() default false;
}
