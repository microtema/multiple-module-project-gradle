package de.microtema.app;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    Application sut;

    PrintStream out;

    OutputStream outputStream = new ByteArrayOutputStream(1024);

    PrintStream printStream = new PrintStream(outputStream);

    @Model
    String argument;

    @Before
    public void setUp() throws Exception {
        FieldInjectionUtil.injectFields(this);

        out = System.out;

        System.setOut(printStream);
    }

    @After
    public void tearDown() {

        //reset
        System.setOut(out);
    }

    @Test
    public void main() {

        Application.main(new String[]{argument});

        String actual = outputStream.toString().replace(System.lineSeparator(), "");
        assertEquals("Hello " + argument, actual);
    }

    @Test
    public void test() {

        Application.main(new String[]{});

        String actual = outputStream.toString().replace(System.lineSeparator(), "");
        assertEquals("Hello Foo", actual);
    }
}
