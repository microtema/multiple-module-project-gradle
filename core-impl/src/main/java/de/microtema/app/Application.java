package de.microtema.app;

import de.microtema.api.MessageService;

public class Application {

    public static void main(String[] args) {

        MessageService service = new MessageService();

        String key = args !=null ? args[0] : "Foo";

        String message = service.formatMessage(key);

        System.out.println(message);
    }
}
