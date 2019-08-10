package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cnc {

    private static final Logger LOGGER = Logger.getLogger(Cnc.class);

    private static Pattern forPattern = Pattern.compile("(for\\s*\\()([a-zA-Z]*\\s*\\w*\\s*=?\\s*[a-zA-Z0-9]*;+\\s*)(\\w+\\s*[><=!][=]*\\s*[a-zA-Z0-9]+)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!][=]*\\s*[a-zA-Z0-9]+))*(;\\s*[a-zA-Z]+\\+\\+)(\\)\\s*\\{)");
    private static Pattern whilePattern = Pattern.compile("(while\\s*\\()(\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\s*\\{)");
    private static Pattern doWhileTopPattern = Pattern.compile("(do\\s*\\{)");
    private static Pattern doWhileBottomPattern = Pattern.compile("(\\}\\s*while\\s*\\()(\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\;)");
    private static Pattern forEachPattern = Pattern.compile("(for\\s*\\()([a-zA-Z]*\\s*\\w+\\s*:\\s*\\w+)(\\)\\s*\\{)");
    private static Pattern ifPattern = Pattern.compile("(\\w*\\s*if\\s*\\()(\\(*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*\\)*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\(*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*\\)*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\s*\\{)");
    private static Pattern switchPattern = Pattern.compile("(switch\\s*\\()(\\w+)(\\)\\s*\\{)");
    private static Pattern elsePattern = Pattern.compile("(else\\s*\\{)");


    public static void calcCnc(Line lineObj, String line) {

        int cnc = 0;
        //int ctc = 0;

        line = line.trim();

        try {
            Matcher forMatcher = forPattern.matcher(line);

            if (forMatcher.matches() && line.startsWith(forMatcher.group(1))) {
                //ctc = ctc + 2;
                List<String> forWords = Arrays.asList(line.replaceAll("[\\(\\+\\=\\)\\;\\>\\<\\!]", " ").split(" "));
                for (String forChar : forWords) {
                    if (forChar.equals("&&") || forChar.equals("&") || forChar.equals("||") || forChar.equals("|")) {
                        //ctc = ctc + 2;
                    }
                    if (forChar.equals("{")) {
                        FileHandler.stack.push("{");
                    }
                }
            }

            Matcher whileMatcher = whilePattern.matcher(line);

            if (whileMatcher.matches() && line.startsWith(whileMatcher.group(1))) {
                //ctc = ctc + 2;
                List<String> whileWords = Arrays.asList(line.replaceAll("[\\(\\.\\=\\)\\>\\<\\!\\\"]", " ").split(" "));
                for (String whileChar : whileWords) {
                    if (whileChar.equals("&&") || whileChar.equals("&") || whileChar.equals("||") || whileChar.equals("|")) {
                        //ctc = ctc + 2;
                    }
                    if (whileChar.equals("{")) {
                        FileHandler.stack.push("{");
                    }
                }
            }

            Matcher doWhileTopMatcher = doWhileTopPattern.matcher(line);

            if (doWhileTopMatcher.matches() && line.startsWith(doWhileTopMatcher.group(1))) {
                //ctc = ctc + 2;
                List<String> doWhileTopWords = Arrays.asList(line.split(" "));
                for (String doWhileTopChar : doWhileTopWords) {
                    if (doWhileTopChar.equals("{") || doWhileTopChar.contains("{")) {
                        FileHandler.stack.push("{");
                    }
                }
            }

            Matcher elseMatcher = elsePattern.matcher(line);

            if (elseMatcher.matches() && line.startsWith(doWhileTopMatcher.group(1))) {
                List<String> elseWords = Arrays.asList(line.split(" "));
                for (String elseChar : elseWords) {
                    if (elseChar.equals("{") || elseChar.contains("{")) {
                        FileHandler.stack.push("{");
                    }
                }
            }

            Matcher doWhileBottomMatcher = doWhileBottomPattern.matcher(line);

            if (doWhileBottomMatcher.matches() && line.startsWith(doWhileBottomMatcher.group(1))) {
                //ctc = ctc + 2;
                List<String> doWhileBottomWords = Arrays.asList(line.replaceAll("[\\(\\.\\=\\)\\>\\<\\!\\\"\\;]", " ").split(" "));
                for (String doWhileBottomChar : doWhileBottomWords) {
                    if (doWhileBottomChar.equals("&&") || doWhileBottomChar.equals("&") || doWhileBottomChar.equals("||") || doWhileBottomChar.equals("|")) {
                        //ctc = ctc + 2;
                    }
                }
            }

            Matcher forEachMatcher = forEachPattern.matcher(line);

            if (forEachMatcher.matches() && line.startsWith(forEachMatcher.group(1))) {
                //ctc = ctc + 2;
                List<String> forEachWords = Arrays.asList(line.replaceAll("[\\(\\)\\:]", " ").split(" "));
                for (String forEachChar : forEachWords) {
                    if (forEachChar.equals("&&") || forEachChar.equals("&") || forEachChar.equals("||") || forEachChar.equals("|")) {
                        //ctc = ctc + 2;
                    }
                    if (forEachChar.equals("{")) {
                        FileHandler.stack.push("{");
                    }
                }
            }

            Matcher ifMatcher = ifPattern.matcher(line);

            if (ifMatcher.matches() && line.startsWith(ifMatcher.group(1))) {
                //ctc = ctc + 1;
                List<String> ifWords = Arrays.asList(line.replaceAll("[\\(\\.\\=\\)\\>\\<\\!\\\"]", " ").split(" "));
                for (String ifChar : ifWords) {
                    if (ifChar.equals("&&") || ifChar.equals("&") || ifChar.equals("||") || ifChar.equals("|")) {
                        //ctc = ctc + 1;
                    }
                    if (ifChar.equals("{")) {
                        FileHandler.stack.push("{");
                    }
                }
            }

            Matcher switchMatcher = switchPattern.matcher(line);

            if (switchMatcher.matches() && line.startsWith(switchMatcher.group(1))) {
                List<String> switchWords = Arrays.asList(line.replaceAll("[\\(\\)]", " ").split(" "));
                for (String switchChar : switchWords) {
                    if (switchChar.equals("{")) {
                        FileHandler.stack.push("{");
                    }
                }
            }



            if (FileHandler.stack.peek() != 0 && (line.startsWith("}") || line.endsWith("}"))) {
                String val = FileHandler.stack.pop();
            }

            cnc = cnc + FileHandler.stack.peek();
            lineObj.setCnc(cnc);
            //System.out.println(cnc);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
