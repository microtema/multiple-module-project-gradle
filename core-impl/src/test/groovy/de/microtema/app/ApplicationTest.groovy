package de.microtema.app

import spock.lang.Specification

class ApplicationTest extends Specification {

    def 'Calling the entry point'() {

        setup: 'Re-route standard out'
        def buf = new ByteArrayOutputStream(1024)
        System.out = new PrintStream(buf)

        when: 'The entrypoint is executed'
        Application.main('Foo')

        then: 'The correct message is output'
        buf.toString() == "Hello Foo\n".denormalize()
    }
}
