package de.veiron.launcher.manager;

import de.veiron.launcher.VeironLauncher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginManager {

    private VeironLauncher launcher;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

}
