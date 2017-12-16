import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    static Random random = new SecureRandom();
    final static public String[] SYMBOLS = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "D", "X", "Y", "Z", "q", "w", "e", "r", "t", "y", "u",
            "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k",
            "l", "z", "x", "c", "v", "b", "n", "m", "0", "1",
            "2", "3", "4", "5", "6", "7", "8", "9",};

    public static String generatePassword() {
        int passwordLength = 8 + random.nextInt(5);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            String c = SYMBOLS[random.nextInt(SYMBOLS.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
