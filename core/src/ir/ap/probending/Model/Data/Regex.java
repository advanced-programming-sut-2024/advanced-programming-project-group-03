package ir.ap.probending.Model.Data;

import java.util.regex.Pattern;

public enum Regex {
    USERNAME("[a-zA-Z0-9-]+"),
    PASSWORD("[a-zA-Z0-9-#@$%&!]+"),
    EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"),
    NUMBER("[0-9]+"),
    ALPHABET("[a-zA-Z]+"),
    SPECIAL_CHARACTERS("[#@$%&!]+");



    private String pattern;
    Regex(String pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return Pattern.compile(pattern);
    }

    public boolean matches(String text) {
        return getPattern().matcher(text).matches();
    }

    public boolean find(String text) {
        return getPattern().matcher(text).find();
    }

    public String getPatternString() {
        return pattern;
    }

}
