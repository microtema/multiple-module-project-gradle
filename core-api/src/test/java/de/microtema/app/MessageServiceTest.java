package de.microtema.app;

import de.seven.fate.model.builder.annotation.Inject;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageServiceTest {

    @Inject
    MessageService sut;

    @Model
    String message;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void formatMessageWithNull() {

        assertEquals("Hello null", sut.formatMessage(null));
    }

    @Test
    public void formatMessage() {

        assertEquals("Hello " + message, sut.formatMessage(message));
    }
}
