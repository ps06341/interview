package prj.interview.wilmar.usermanagement.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import prj.interview.wilmar.usermanagement.config.WebAppInitializer;

public class BaseController {

	// base logger
	protected final Logger log = LoggerFactory.getLogger(WebAppInitializer.class);

	/***
	 * method generate and return stacktrace as String
	 * @param throwable the {@code Throwable} as {@code Exception} which was catched
	 */
	protected String getExceptionStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		throwable.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();

		return exceptionAsString;
	}
}
