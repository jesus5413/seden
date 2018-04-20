package com.group.seden.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionTest {
    String testString = "This is a test message!";
    Message message = new Message(testString);
    int password = 987654321;

    @Test
    public void encrypt() {
        System.out.println("Original Message: " + testString);
        Encryption.encrypt(message, password);
        System.out.println("Encrypted Message: " + message.getMessage());
        assertNotEquals(testString, message.getMessage());
    }

    @Test
    public void decrypt() {
        Encryption.encrypt(message, password);
        Encryption.decrypt(message, password);
        System.out.println("Decrypted Message: " + message.getMessage());
        assertEquals(testString, message.getMessage());
    }
}