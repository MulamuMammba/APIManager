package com.mammba.APIManager.Services.Security;

import java.util.Random;

public class Generator{

public static String generateApiId() {
        return String.format("%08d", new Random().nextInt(100000000));
    }

}
