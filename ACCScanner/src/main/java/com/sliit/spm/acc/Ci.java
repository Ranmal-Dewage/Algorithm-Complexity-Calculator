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

	private static final List<String> keywords = Arrays.asList("extends ", "implements ", ",", ":");
	private static final List<String> excludedKeywords = Arrays.asList("else ", "class ");
	private static int ci = 0;

	private Ci() {
	}

	/**
	 * This method will add ci value of a line to lineObj
	 * 
	 * @param lineObj
	 * @param line
	 */
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
		lineObj.setCi((ci != 0 && !line.isEmpty() && !isExcludedListValue(line)) ? ci : 0);
	}

	/**
	 * This method will reset static ci value
	 */
	public static void resetCi() {
		Ci.ci = 0;
	}

	private static boolean isExcludedListValue(String line) {
		try {
			line = line.trim();
			if ("}".equals(line)) {
				return true;
			}

			for (String item : excludedKeywords) {
				if (line.contains(item)) {
					return true;
				}
			}

		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
