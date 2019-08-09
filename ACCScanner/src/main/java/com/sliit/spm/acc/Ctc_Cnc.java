package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import com.sliit.spm.model.Stack;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ctc_Cnc {

    private static Stack stack = new Stack();
    private static Pattern forPattern = Pattern.compile("(for\\s*\\()([a-zA-Z]*\\s*\\w*\\s*=?\\s*[a-zA-Z0-9]*;+\\s*)(\\w+\\s*[><=!][=]*\\s*[a-zA-Z0-9]+)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!][=]*\\s*[a-zA-Z0-9]+))*(;\\s*[a-zA-Z]\\+\\+)(\\)\\s*\\{)");
    private static Pattern whilePattern = Pattern.compile("(while\\s*\\()(\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\s*\\{)");
    private static Pattern doWhileTopPattern = Pattern.compile("(do\\s*\\{)");
    private static Pattern doWhileBottomPattern = Pattern.compile("(\\}\\s*while\\s*\\()(\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\;)");
    private static Pattern forEachPattern = Pattern.compile("(for\\s*\\()([a-zA-Z]*\\s*\\w+\\s*:\\s*\\w+)(\\)\\s*\\{)");


    public static void calc_Ctc_Cnc(Line lineObj, String line, List<String> methodsAndVariables) {

        int cnc = 0;
        int ctc = 0;

        line = line.trim();

        Matcher forMatcher = forPattern.matcher(line);

        if (line.startsWith(forMatcher.group(1))) {
            ctc = ctc + 2;
            System.out.println("for");
            List<String> forWords = Arrays.asList(line.replaceAll("[\\(\\+\\=\\)\\;\\>\\<\\!]", " ").split(" "));
            for (String forChar : forWords) {
                if (forChar.equals("&&") || forChar.equals("&") || forChar.equals("||") || forChar.equals("|")) {
                    ctc = ctc + 2;
                }
                if (forChar.equals("{")) {
                    stack.push("{");
                }
            }
        }

        Matcher whileMatcher = whilePattern.matcher(line);

        if (line.startsWith(whileMatcher.group(1))) {
            ctc = ctc + 2;
            System.out.println("while");
            List<String> whileWords = Arrays.asList(line.replaceAll("[\\(\\.\\=\\)\\>\\<\\!\\\"]", " ").split(" "));
            for (String whileChar : whileWords) {
                if (whileChar.equals("&&") || whileChar.equals("&") || whileChar.equals("||") || whileChar.equals("|")) {
                    ctc = ctc + 2;
                }
                if (whileChar.equals("{")) {
                    stack.push("{");
                }
            }
        }

        Matcher doWhileTopMatcher = doWhileTopPattern.matcher(line);

        if (line.startsWith(doWhileTopMatcher.group(1))) {
            ctc = ctc + 2;
            System.out.println("do while start");
            List<String> doWhileTopWords = Arrays.asList(line.split(" "));
            for (String doWhileTopChar : doWhileTopWords) {
                if (doWhileTopChar.equals("{")) {
                    stack.push("{");
                }
            }
        }

        Matcher doWhileBottomMatcher = doWhileBottomPattern.matcher(line);

        if (line.startsWith(doWhileBottomMatcher.group(1))) {
            ctc = ctc + 2;
            System.out.println("do while end");
            List<String> doWhileBottomWords = Arrays.asList(line.replaceAll("[\\(\\.\\=\\)\\>\\<\\!\\\"\\;]", " ").split(" "));
            for (String doWhileBottomChar : doWhileBottomWords) {
                if (doWhileBottomChar.equals("&&") || doWhileBottomChar.equals("&") || doWhileBottomChar.equals("||") || doWhileBottomChar.equals("|")) {
                    ctc = ctc + 2;
                }
            }
        }

        Matcher forEachMatcher = forEachPattern.matcher(line);

        if (line.startsWith(forEachMatcher.group(1))) {
            ctc = ctc + 2;
            System.out.println("for each");
            List<String> forEachWords = Arrays.asList(line.replaceAll("[\\(\\)\\:]", " ").split(" "));
            for (String forEachChar : forEachWords) {
                if (forEachChar.equals("&&") || forEachChar.equals("&") || forEachChar.equals("||") || forEachChar.equals("|")) {
                    ctc = ctc + 2;
                }
                if (forEachChar.equals("{")) {
                    stack.push("{");
                }
            }
        }



        if(line.startsWith("}") || line.endsWith("}")){
            String val = stack.pop();
            System.out.println(val);
        }


    }
}
