package com.saad.library_management_system.util;

import java.time.Instant;

public class Utils {

    public static int getRandomInt() {
        return Instant.now().getNano();
    }
}
