/**
 *
 */
package com.sliit.spm.acc;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.sliit.spm.model.Line;

/**
 * @author Vimukthi Rajapaksha
 * @date Jul 30, 2019
 */
public class Ctc {

	private static final List<String> ccsKeywords = Arrays.asList("if(", "catch(", "if (", "catch (");
	private static final List<String> icsKeywords = Arrays.asList("for(", "while(", "for (", "while (");
	private static final List<String> bitwiseKeywords = Arrays.asList(" && ", " || ", " & ", " | ");
	static Line switchLine = null;
	static int switchCtc = 0;

	/**
	 * This method will add ctc value of a line to lineObj
	 * 
	 * @param lineObj
	 * @param line
	 */
	public static void calcCtc(Line lineObj, String line) {
		int ctc = 0;
		line = line.trim();

		for (String keyword : ccsKeywords) { // check if line contains any ccskeywords
			if (!line.isEmpty() && line.indexOf(keyword) != -1) {
				ctc += StringUtils.countMatches(line, keyword); // Increment ctc by 1
				for (String bw : bitwiseKeywords) {
					ctc += StringUtils.countMatches(line, bw);
				}
			}
		}
		for (String keyword : icsKeywords) { // check if line contains any ccskeywords
			if (!line.isEmpty() && line.indexOf(keyword) != -1) {
				ctc += StringUtils.countMatches(line, keyword) * 2; // Increment ctc by 2
				for (String bw : bitwiseKeywords) {
					ctc += StringUtils.countMatches(line, bw) * 2;
				}
			}
		}
		if (!line.isEmpty() && line.contains("switch")) { // Maintaining a static value, because need to use lines after
															// switch
			setSwitchCtc();
			switchLine = lineObj;
		}
		switchCtc += StringUtils.countMatches(line, "case");
		lineObj.setCtc(ctc);
	}

	/**
	 * This method will set switch ctc value and will reset switch ctc value
	 */
	public static void setSwitchCtc() {
		if (Objects.nonNull(switchLine) && switchCtc > 0) { // if file contains a switch, increment ctc value.
			switchLine.setCtc(switchCtc);
		}
		switchLine = null;
		switchCtc = 0;
	}
}
