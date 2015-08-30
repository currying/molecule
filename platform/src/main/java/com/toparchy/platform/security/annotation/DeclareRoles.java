package com.toparchy.platform.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

import org.apache.deltaspike.security.api.authorization.SecurityBindingType;
import com.toparchy.platform.security.model.ApplicationRole;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@SecurityBindingType
public @interface DeclareRoles {
	@Nonbinding
	ApplicationRole[] value() default {};
}
