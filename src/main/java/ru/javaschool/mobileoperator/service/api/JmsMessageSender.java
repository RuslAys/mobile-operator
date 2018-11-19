package ru.javaschool.mobileoperator.service.api;

import javax.jms.Destination;

public interface JmsMessageSender {
    /**
     * Send to default destination
     * @param text text to send
     */
    void send(final String text);

    /**
     * Send to specified destination
     * @param dest specified destination
     * @param text text to send
     */
    void send(final Destination dest, final String text);
}
