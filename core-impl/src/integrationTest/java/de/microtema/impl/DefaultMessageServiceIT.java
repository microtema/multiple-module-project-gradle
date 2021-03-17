package de.microtema.impl;

import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DefaultMessageServiceIT {

    @Inject
    DefaultMessageService sut;

    @Model
    String message;

    @BeforeEach
    void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    void formatMessageWithNullArg() {

        String answer = sut.formatMessage(null);

        assertEquals("Default null", answer);
    }

    @Test
    void formatMessage() {

        String answer = sut.formatMessage(message);

        assertEquals("Default " + message, answer);
    }
}
