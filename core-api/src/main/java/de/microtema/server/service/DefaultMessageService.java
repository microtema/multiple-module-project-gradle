package de.microtema.server.service;

public class DefaultMessageService implements MessageService {

    @Override
    public String formatMessage(String messageKey) {

        return "Default " + messageKey;
    }
}
