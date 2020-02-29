package by.samtsov.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class UserPasswordService {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * Number of iteration to generate hash.
     * Value is random.
     * ATTENTION! changing this value will invalidate all hashed passwords
     *      * in database
     */
    private final int ITERATIONS = 2397;
    private final int KEY_LENGTH = 512;
    private final int SALT_LENGTH = 512;
    /**
     * Application-side salt.
     * ATTENTION! changing this value will invalidate all hashed passwords
     * in database
     */
    private final String PROGRAM_SIDE_SALT
            = "fuPCqnF@N*W_FGE#PZUDV#vVESbn83WYY3hn+Ue-s3mtf6wP!_5";

    public String generateSalt() {
        StringBuilder returnValue = new StringBuilder(SALT_LENGTH);
        for (int i = 0; i < SALT_LENGTH; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * generates secure password value for storage
     * @param password initial password value
     * @param salt external stored salt
     * @return secure password value
     */

    public String generateSecurePassword(String password, String salt) {

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        securePassword = hash(PROGRAM_SIDE_SALT.toCharArray(), securePassword);

        return Base64.getEncoder().encodeToString(securePassword);
    }

    public boolean verifyUserPassword(String providedPassword,
                                             String securedPassword, String salt) {

        return securedPassword.equalsIgnoreCase(
                generateSecurePassword(providedPassword, salt));
    }
}
