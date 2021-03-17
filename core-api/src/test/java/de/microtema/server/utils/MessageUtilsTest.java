package de.microtema.server.utils;

import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageUtilsTest {

    MessageUtils sut;

    @Model
    String msg;

    @BeforeEach
    void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    void wrap() {

        String answer = MessageUtils.wrap(msg);

        assertEquals("!" + msg + "!", answer);
    }
}
