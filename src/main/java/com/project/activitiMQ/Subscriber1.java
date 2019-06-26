package com.project.activitiMQ;

import com.project.pojo.Student;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Subscriber1 {
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;

    public Subscriber1() {
        try {
            factory =
                    new ActiveMQConnectionFactory("admin", "admin",
                            "failover:(tcp://localhost:61616)?Randomize=false");
            ((ActiveMQConnectionFactory) factory).setTrustAllPackages(true);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive() throws Exception {
        Destination topic = session.createTopic("Topic001") ;
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new Listener());
    }

    class Listener implements MessageListener {
        public void onMessage(Message message) {
            System.out.println(message);
           try {
                ObjectMessage objectMessage=(ObjectMessage)message;
               Student student=(Student)objectMessage.getObject();
                System.out.println("Subscriber1 Received message: "+student.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Subscriber1 subscriber = new Subscriber1();
        subscriber.receive();
    }
}
