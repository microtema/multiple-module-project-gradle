package de.microtema.api

import spock.lang.Specification

class MessageServiceTest extends Specification {

    def sut = new MessageService();

    def "GetMessage"() {

        expect: "Message to be correct translated"
        sut.getMessage(key) == message

        where: ""
        key   | message
        "Foo" | "Hello Foo"
        "Bar" | "Hello Bar"
        "Baz" | "Hello Baz"
    }
}
