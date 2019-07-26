package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import com.sliit.spm.utils.PropertyReader;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * calculete Cs of a line
 */
public class Cs {

    private static Pattern numeric = Pattern.compile("\\d+");
    private static Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");

    public static void calcCs(Line lineObj, String line) {

        int cs = 0;
        List<String> keywordsOne = Arrays.asList(PropertyReader.getInstance().getProperty("cs.one").split(","));
        List<String> keywordsTwo = Arrays.asList(PropertyReader.getInstance().getProperty("cs.two").split(","));
        List<String> keywordsTwoStartWith = Arrays.asList(PropertyReader.getInstance().getProperty("cs.two.start.with").split(","));

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
                int score = StringUtils.countMatches(line.toLowerCase(), keyword) * 2;
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
        while(n.find()) {
            cs++;
        }

        /*
        find Text inside a pair of double quotes
         */
        Matcher q = textInsideDoubleQuoted.matcher(line);
        while(q.find()) {
            cs++;
        }

        lineObj.setCs(cs);
    }
}
