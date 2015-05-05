package de.moso.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sandro on 05.05.15.
 */
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.SOURCE)
public @interface OutputElement {
}
