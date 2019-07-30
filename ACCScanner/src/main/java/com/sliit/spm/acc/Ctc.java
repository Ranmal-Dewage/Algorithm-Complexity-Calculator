/**
 *
 */
package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public static void calcCtc(Line lineObj, String line) {
        int ctc = 0;
        line = line.trim();

        for (String keyword : ccsKeywords) {
            if (!line.isEmpty() && line.indexOf(keyword) != -1) {
                ctc += StringUtils.countMatches(line, keyword);
                for (String bw : bitwiseKeywords) {
                    ctc += StringUtils.countMatches(line, bw);
                }
            }
        }
        for (String keyword : icsKeywords) {
            if (!line.isEmpty() && line.indexOf(keyword) != -1) {
                ctc += StringUtils.countMatches(line, keyword) * 2;
                for (String bw : bitwiseKeywords) {
                    ctc += StringUtils.countMatches(line, bw) * 2;
                }
            }
        }
        if (!line.isEmpty() && line.contains("switch")) {
            setSwitchCtc();
            switchLine = lineObj;
        }
        switchCtc += StringUtils.countMatches(line, "case");
        lineObj.setCtc(ctc);
    }

    public static void setSwitchCtc() {
        if(Objects.nonNull(switchLine) && switchCtc > 0) {
            switchLine.setCtc(switchCtc);
        }
        switchLine = null;
        switchCtc = 0;
    }
}
