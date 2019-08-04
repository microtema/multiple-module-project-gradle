package de.microtema.impl;

import de.microtema.server.MessageService;

public class DefaultMessageService implements MessageService {

    @Override
    public String formatMessage(String messageKey) {

        return "Default " + messageKey;
    }
}
