package de.microtema.impl;

import de.seven.fate.model.builder.annotation.Inject;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultMessageServiceTest {

    @Inject
    DefaultMessageService sut;

    @Model
    String message;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void formatMessageWithNUllArg() {

        assertEquals("Default null", sut.formatMessage(null));
    }

    @Test
    public void formatMessage() {

        assertEquals("Default " + message, sut.formatMessage(message));
    }
}
