package com.group.seden.model;

/**
 * @author robbie neuhaus
 *
 * This class encrypts/decrypts messages using a user defined key.
 * Use Encryption.encrypt(String message, long key) to encrypt a message,
 * and Encryption.decrypt(String message, long key) to decrypt a message.
 */

import java.util.Random;

public class Encryption {

    //Encrypts a message passed to it by passing it to "swapChars" and then "cypher".
    //Uses the long passed to it to set a seed for a random number generator.
    public static void encrypt(Message msg, long seed) {
        char[] message;
        Random randomNo = new Random();
        message = msg.getMsgText().toCharArray();
        randomNo.setSeed(seed);
        int[] randomNos = setRandomNos(message.length, randomNo);
        String swapMessage = swapChars(message, randomNos);
        String encryptedMessage = (cypher(swapMessage, randomNo.nextInt(24) + 1));
        msg.setMsgText(encryptedMessage);
        msg.setIsEncrypted(true);
    }

    //Decrypts a message passed to it by passing it to "reverseSwapChars" and then "cypher".
    //Uses the long passed to it set a seed for a random number generator.
    public static void decrypt(Message msg, long seed) {
        char[] message;
        Random randomNo = new Random();
        message = msg.getMsgText().toCharArray();
        randomNo.setSeed(seed);
        int[] randomNos = setRandomNos(message.length, randomNo);
        String swapMessage = reverseSwapChars(message, randomNos);
        String decryptedMessage = (cypher(swapMessage, 25 - randomNo.nextInt(24)));
        msg.setMsgText(decryptedMessage);
        msg.setIsEncrypted(false);
    }

    //Returns an array of size "length" of randomly generated numbers.
    private static int[] setRandomNos(int length, Random randomNo) {
        int i = 0;
        int[] randomNos = new int[length];
        while (i < length) {
            randomNos[i] = randomNo.nextInt(length);
            i++;
        }
        return randomNos;
    }

    //Uses array "randomNos" to swap around the chars in "message".
    private static String swapChars(char[] message, int[] randomNos) {
        int i = 0;
        char temp;
        while (i < message.length) {
            int r = randomNos[i];
            temp = message[i];
            message[i] = message[r];
            message[r] = temp;
            i++;
        }
        return String.valueOf(message);
    }

    //Uses array "randomNos" to swap around the chars in "message" in the opposite direction
    //that "swapChars" does.
    private static String reverseSwapChars(char[] message, int[] randomNos) {
        int i = message.length - 1;
        char temp;
        while (i >= 0) {
            int r = randomNos[i];
            temp = message[i];
            message[i] = message[r];
            message[r] = temp;
            i--;
        }
        return String.valueOf(message);
    }

    //A Caesar cypher that shifts the individual characters in a give string.
    private static String cypher(String message, int s) {
        String result = "";
        int i;
        for (i = 0; i < message.length(); i++) {
            if (Character.isLetter(message.charAt(i))) {
                if (Character.isUpperCase(message.charAt(i))) {
                    char ch = (char) (((int) message.charAt(i) +
                            s - 65) % 26 + 65);
                    result += ch;
                } else {
                    char ch = (char) (((int) message.charAt(i) +
                            s - 97) % 26 + 97);
                    result += ch;
                }
            } else
                result += message.charAt(i);
        }
        return result;
    }

}
