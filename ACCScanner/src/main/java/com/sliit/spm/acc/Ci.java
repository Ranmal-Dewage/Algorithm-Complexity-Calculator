/**
 * 
 */
package com.sliit.spm.acc;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sliit.spm.model.Line;

/**
 * @author Vimukthi Rajapaksha
 * @date Jul 29, 2019
 */
public class Ci {

	private static final List<String> keywords = Arrays.asList("extends ", "implements ", ",");
	private static int ci = 0;

	public static void calcCi(Line lineObj, String line) {
		line = line.trim();
		/*
		 * find if line contains extends or implements keywords
		 */
		if (!line.isEmpty() && line.contains("class ")) {
			ci = 2;
		}
		for (String keyword : keywords) {
			if (!line.isEmpty() && line.indexOf(keyword) != -1 && line.contains("class ")) {
				ci += StringUtils.countMatches(line, keyword);
			}
		}
		lineObj.setCi((ci != 0 && !line.isEmpty() && !line.contains("class ")) ? ci : 0);
	}

	public static void resetCi() {
		Ci.ci = 0;
	}

}
