package com.group.seden.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionTest {
    String testString = "This is a super secret message! Don't tell anybody or I'll kill you!";
    Message message = new Message(testString);
    long password = 1234567891234567L;

    @Test
    public void encrypt() {
        System.out.println("Original Message: " + testString);
        Encryption.encrypt(message, password);
        System.out.println("Encrypted Message: " + message.getMsgText());
        assertNotEquals(testString, message.getMsgText());
    }

    @Test
    public void decrypt() {
        Encryption.encrypt(message, password);
        Encryption.decrypt(message, password);
        System.out.println("Decrypted Message: " + message.getMsgText());
        assertEquals(testString, message.getMsgText());
    }
}