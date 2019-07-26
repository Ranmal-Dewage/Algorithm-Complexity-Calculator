package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import com.sliit.spm.utils.PropertyReader;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Cs {
    public static void calcCs(Line lineObj, String line) {

        int cs = 0;
        List<String> keywordsOne = Arrays.asList(PropertyReader.getInstance().getProperty("cs.one").split(","));
        List<String> keywordsTwo = Arrays.asList(PropertyReader.getInstance().getProperty("cs.two").split(","));
        List<String> keywordsTwoStartWith = Arrays.asList(PropertyReader.getInstance().getProperty("cs.two.start.with").split(","));

        for (String keyword : keywordsOne) {
            if (!line.isEmpty() && line.toLowerCase().indexOf(keyword) != -1) {
                int score = StringUtils.countMatches(line.toLowerCase(), keyword);
                cs += score;
            }
        }

        for (String keyword : keywordsTwo) {
            if (!line.isEmpty() && line.toLowerCase().indexOf(keyword) != -1) {
                int score = StringUtils.countMatches(line.toLowerCase(), keyword) * 2;
                cs += score;
            }
        }

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
        lineObj.setCs(cs);
    }
}
