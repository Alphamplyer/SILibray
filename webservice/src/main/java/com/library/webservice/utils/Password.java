package com.library.webservice.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// Vous pouvez obtenir ce code sur : http://appsdeveloperblog.com/encrypt-user-password-example-java/
// Pour sécurisé les mot de passe en base de données

public class Password {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    /**
     * génère un code d'un longueur données
     * @param length logueur du code
     * @return le code
     */
    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    /**
     * hash le mot de passe en fonction d'un code donné
     * @param password le mot de passe
     * @param salt le code
     * @return le mot de passe hashé
     */
    public static byte[] hash(char[] password, byte[] salt) {
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
     * Génère un mot de passe sécurisé avec un mot de passe et code donnée
     * @param password le mot de passe
     * @param salt le code
     * @return un mot de passe sécurisé
     */
    public static String generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    /**
     * vérifie si le mot de base brut correpond au même mot de passe que celui sécurisé.
     * @param providedPassword le mot de passe brut
     * @param securedPassword le mot de passe sécurisé
     * @param salt le code
     * @return true si égal, false sinon
     */
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
        boolean returnValue = false;

        // Générer un nouveau mot de passe avec le même salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);

        // Vérifier si les deux mot de passe sont égaux
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

        return returnValue;
    }
}