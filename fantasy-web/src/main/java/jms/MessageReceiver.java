package jms;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "MessageReceiver", activationConfig = {
  @ActivationConfigProperty(propertyName =
    "destinationType", propertyValue = "javax.jms.Topic"),
  @ActivationConfigProperty(propertyName =
    "destination", propertyValue = "java:jboss/exported/jms/topic/elf"),
  @ActivationConfigProperty(propertyName =
    "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class MessageReceiver implements MessageListener {


  @Override
  public void onMessage(Message message) {
    TextMessage tm = (TextMessage) message;
    try {
      System.out.println(tm.getText());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}