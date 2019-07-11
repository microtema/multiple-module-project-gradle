package de.microtema.app

import de.microtema.api.MessageService

class Application {

    static void main(String[] args) {

        def service = new MessageService();

        def key = args ? args[0] : "Foo"

        def message = service.getMessage(key)

        println(message)
    }
}
