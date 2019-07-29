package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import com.sliit.spm.utils.PropertyReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * calculete Cs of a line
 */
public class Cs {

    private static final Logger LOGGER = Logger.getLogger(Cs.class);

    private static Pattern numeric = Pattern.compile("\\d+");
    private static Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
    private static List<String> keywordsOne = Arrays.asList(PropertyReader.getInstance().getProperty("cs.one").split(","));
    private static List<String> keywordsTwo = Arrays.asList(PropertyReader.getInstance().getProperty("cs.two").split(","));
    private static List<String> keywordsTwoStartWith = Arrays.asList(PropertyReader.getInstance().getProperty("cs.two.start.with").split(","));

    public static void calcCs(Line lineObj, String line, List<String> methodsAndVariables) {

        int cs = 0;

        line = line.trim();
        try {
        /*
        find Text inside a pair of double quotes
         */
            Matcher q = textInsideDoubleQuoted.matcher(line);
            while (q.find()) {
                line = line.replace(q.group(0), "");
                cs++;
            }

        /*
        count of occurrences with score of 1
         */
            for (String keyword : keywordsOne) {
                if (!line.isEmpty() && line.toLowerCase().indexOf(keyword) != -1) {
                    int score = StringUtils.countMatches(line.toLowerCase(), keyword);
                    cs += score;
                }
            }

        /*
        count of occurrences with score of 2
         */
            for (String keyword : keywordsTwo) {
                if (!line.isEmpty() && line.toLowerCase().indexOf(keyword) != -1) {
                    int score = StringUtils.countMatches(line.toLowerCase(), keyword);
                    score *= 2;
                    cs += score;
                }
            }

        /*
        count of occurrences with score of 2 and check if the string starts with the given keyword
         */
            for (String keyword : keywordsTwoStartWith) {
                if (!line.isEmpty()) {
                    List<String> words = Arrays.asList(line.split(" "));
                    for (String word : words) {
                        if (word.startsWith(keyword)) {
                            cs += 2;
                        }
                    }
                }
            }

        /*
        find numeric values
         */
            Matcher n = numeric.matcher(line);
            while (n.find()) {
                cs++;
            }

        /*
        find methods and variables
         */
            for (String keyword : methodsAndVariables) {
                List<String> lineDataList = Arrays.asList(line.replaceAll("[\\(\\+\\-\\)\\:\\;\\[\\]\\.\\{\\}]", " ").split(" "));
                for (String lineData : lineDataList) {
                    if (lineData.toLowerCase().equals(keyword.toLowerCase())) {
                        cs += 1;
                    }
                }
            }

            // temp solution for bug
            List<String> remove = new ArrayList<>();
            remove.add("print");
            remove.add("println");
            for (String val : remove) {
                if (!line.isEmpty() && line.toLowerCase().indexOf(val) != -1) {
                    List<String> lineDataList = Arrays.asList(line.replaceAll("[\\(\\+\\-\\)\\:\\;\\[\\]\\.]", " ").split(" "));
                    for (String lineData : lineDataList) {
                        if (lineData.equals(val)) {
                            cs -= 1;
                        }
                    }
                }
            }
            lineObj.setCs(cs);
        } catch (Exception e) {
            String errMsg = "Error calculating Cs";
            LOGGER.error(errMsg);
        }
    }
}
