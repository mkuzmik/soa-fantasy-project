package jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;

@ApplicationScoped
public class MessageService {

  @Resource(mappedName = "java:/ConnectionFactory")
  private ConnectionFactory cf;

  @Resource(mappedName = "java:jboss/exported/jms/topic/elf")
  private Topic topic;

  private Connection connection;

  public void sendMessage(String message) {
    try {
      connection = cf.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer publisher = session.createProducer(topic);
      connection.start();
      TextMessage textMessage = session.createTextMessage(message);
      publisher.send(textMessage);
    } catch (JMSException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException e) {
          e.printStackTrace();
        }
        connection = null;
      }
    }
  }
}
