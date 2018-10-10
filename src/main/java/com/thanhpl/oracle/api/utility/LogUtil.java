package com.thanhpl.oracle.api.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;

public class LogUtil {

	public static String toString(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	public static void error(Logger log, Exception e) {
		log.error(toString(e));
	}
}
