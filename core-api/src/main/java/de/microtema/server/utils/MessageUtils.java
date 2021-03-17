package de.microtema.server.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtils {

    String wrap(String str) {

        return "!" + str + "!";
    }
}
