package com.pihen.d3restapi.service.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RemoteConfiguration {

	String url();
}
