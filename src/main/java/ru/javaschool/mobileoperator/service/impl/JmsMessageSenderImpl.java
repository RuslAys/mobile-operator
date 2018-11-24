package ru.javaschool.mobileoperator.service.impl;

import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.service.api.JmsMessageSender;

import javax.jms.Destination;

@Service("jmsMessageSender")
public class JmsMessageSenderImpl implements JmsMessageSender {

//    @Autowired
//    private JmsTemplate jmsTemplate;

    @Override
    public void send(String text) {
//        this.jmsTemplate.send((Session session) -> {
//            Message message = session.createTextMessage(text);
//            message.setJMSReplyTo(new ActiveMQQueue("Recv2Send"));
//            return message;
//        });
    }

    @Override
    public void send(Destination dest, String text) {
//        this.jmsTemplate.send(dest, (Session session) -> {
//            Message message = session.createTextMessage(text);
//            return message;
//        });
    }
}
