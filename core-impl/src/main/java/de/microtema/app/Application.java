package de.microtema.app;

public class Application {

    public static void main(String[] args) {

        MessageService service = new MessageService();

        String key = args != null && args.length > 0 ? args[0] : "Foo";

        String message = service.formatMessage(key);

        System.out.println(message);
    }
}
