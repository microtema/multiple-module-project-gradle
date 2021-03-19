package de.microtema.impl;

import de.microtema.server.service.MessageService;

public class DefaultMessageService implements MessageService {

    @Override
    public String formatMessage(String messageKey) {

        return "Custom " + messageKey;
    }
}
