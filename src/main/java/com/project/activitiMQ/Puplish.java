package com.project.activitiMQ;

import com.project.pojo.Student;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Puplish  {
    private ConnectionFactory factory;
    private  Connection connection;
    private Session session;
    MessageProducer  messageProducer =null;




    public  Puplish(){
        getMessageProducer();
    }

    private  void getMessageProducer(){
        try {
            factory= new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

            ((ActiveMQConnectionFactory) factory).setTrustAllPackages(true);
            //获取连接
            connection=factory.createConnection();

            session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
             messageProducer =session.createProducer(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void sendMssage(Object object){
        try {   // 创建主题
               Destination destination= session.createTopic("Topic001");
               Message message=null;
               if (object instanceof  String){
                   message=  session.createTextMessage(object.toString());
               }else if(object instanceof Student){
                   Student object1 = (Student) object;
                   message= session.createObjectMessage(object1);
               }
               messageProducer.send(destination,message);
               System.out.println("发送成功");
        }catch ( Exception e){
            e.printStackTrace();
        }
    }
    public  static  void main(String[] args){
        Student student=new Student();
        student.setTid(1);
        student.setTname("peter");
        student.setTage(19);
        new Puplish().sendMssage(student);
    }
}
