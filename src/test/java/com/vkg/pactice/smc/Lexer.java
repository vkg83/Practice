package com.vkg.pactice.smc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    TokenCollector collector;
    private int position;
    public Lexer(TokenCollector collector) {
        this.collector = collector;
    }
    public void lexLine(String line) {
        for(position = 0; position < line.length();) {
            lexToken(line);
        }
    }

    private void lexToken(String line) {
        if(!findToken(line)) {
            collector.error(position);
            position++;
        }
    }

    private boolean findToken(String line) {
        return findWhiteSpace(line) || findLogicEnd(line) ||
                findKeywordToken(line) ||
                findName(line);
    }

    private boolean findLogicEnd(String line) {
        if(line.charAt(position) == '.') {
            collector.logicEnd(position);
            position++;
            return true;
        }
        return false;
    }

    private static final Pattern namePattern = Pattern.compile("^\\w+");
    private boolean findName(String line) {
        Matcher matcher = namePattern.matcher(line.substring(position));
        if(matcher.find()) {
            collector.name(matcher.group(0), position);
            position += matcher.end();
            return true;
        }

        return false;
    }

    String[] keys = {"I", "A", "F", "V"};
    private boolean findKeywordToken(String line) {
        for (String keyword : keys) {
            if(line.substring(position).startsWith(keyword + " ")) {
                Matcher matcher = namePattern.matcher(line.substring(position + 2));
                if(matcher.find()) {
                    collector.keyword(keyword, matcher.group(0), position + 2);
                    position += matcher.end() + 2;
                    return true;
                }
            }
        }
        return false;
    }

    private static final Pattern whitePattern = Pattern.compile("^\\s+");
    private boolean findWhiteSpace(String line) {
        Matcher matcher = whitePattern.matcher(line.substring(position));
        if(matcher.find()) {
            position += matcher.end();
            return true;
        }

        return false;
    }
}
