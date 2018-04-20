package com.group.seden.model;

import org.junit.Test;
import java.lang.*;

import static org.junit.Assert.*;

public class EncryptTest {

    @Test
    public void encrypt() {
        int password = 987654321;
        Message message = new Message();
        message.setMessage("This is a test message!");
        System.out.println(message.getMessage());
        Encrypt.encryptMessage(message, password);
        System.out.println(message.getMessage());
        Decrypt.decryptMessage(message, password);
        System.out.println(message.getMessage());
    }
}