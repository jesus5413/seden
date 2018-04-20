package com.group.seden.model;

import java.util.Random;


public class Decrypt {

    public static void decryptMessage(Message msg, long seed){
        char[] message;
        Random randomNo = new Random();
        message = msg.getMessage().toCharArray();

        randomNo.setSeed(seed);
        int[] randomNos = setRandomNos(message.length, randomNo);
        String swapString = swapChars(message, randomNos);
        msg.setMessage(swapString);
    }

    private static int[] setRandomNos(int length, Random randomNo){
        int i = 0;
        int[] randomNos = new int[length];
        while (i < length){
            randomNos[i] = randomNo.nextInt(length);
            i++;
        }
        return randomNos;
    }

    private static String swapChars(char[] message, int[] randomNos) {

        String swapString = String.valueOf(message);
        int i;
        int s = 22;
        String result = "";

        for (i = 0; i < swapString.length(); i++) {
            if (Character.isLetter(swapString.charAt(i))) {
                if (Character.isUpperCase(swapString.charAt(i))) {
                    char ch = (char) (((int) swapString.charAt(i) +
                            s - 65) % 26 + 65);
                    result += ch;
                } else {
                    char ch = (char) (((int) swapString.charAt(i) +
                            s - 97) % 26 + 97);
                    result += ch;
                }

            }
            else
                result += swapString.charAt(i);
        }

        message = result.toCharArray();

        i = message.length - 1;
        char temp;
        while (i >= 0) {
            int r = randomNos[i];
            temp = message[i];
            message[i] = message[r];
            message[r] = temp;
            i--;
        }

        result = String.valueOf(message);

        return result;

    }
}
