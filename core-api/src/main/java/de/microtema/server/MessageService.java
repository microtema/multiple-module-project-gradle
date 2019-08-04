package de.microtema.server;

public interface MessageService {

    default String formatMessage(String messageKey) {

        return "Hello " + messageKey;
    }
}
