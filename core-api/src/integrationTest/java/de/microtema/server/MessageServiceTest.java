package de.microtema.server;

import de.seven.fate.model.builder.annotation.Inject;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MessageServiceTest {

    @Inject
    MessageServiceImpl sut;

    @Model
    String message;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void test() {

        assertNotNull(sut);
    }

    static public class MessageServiceImpl implements MessageService{}
}
