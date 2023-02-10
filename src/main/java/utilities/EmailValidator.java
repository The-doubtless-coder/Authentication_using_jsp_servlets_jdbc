package utilities;

import java.util.regex.Pattern;

public class EmailValidator {
//    private static final String regexPattern = "^(.+)@(\\\\S+)$";
    public static boolean patternMatches(String emailAddress) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        if (emailAddress == null)
            return false;
        return pattern.matcher(emailAddress).matches();
    }
}
