package by.zastr.cafe.util;

import java.util.ResourceBundle;

public enum MessageManager {
    /**
     * Message manager.
     */
    MESSAGE(ResourceBundle.getBundle("message"));

    /**
     * The Bundle.
     */
    private final ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Gets message.
     *
     * @param messageName the message name
     * @return the message
     */
    public String getMessage(String messageName) {
        return bundle.getString(messageName);
    }
}