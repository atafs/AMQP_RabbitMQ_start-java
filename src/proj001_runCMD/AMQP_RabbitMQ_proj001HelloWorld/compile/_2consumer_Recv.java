/* CONSUMER */
import com.rabbitmq.client.*;

import java.io.IOException;

public class _2consumer_Recv {

  //ATTRIBUTES
  private final static String QUEUE_NAME = "hello";

  //MAIN    
  public static void main(String[] argv) throws Exception {
    
    //CONNECTION AND CHANNEL
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    //DECLARE QUEUE  
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    //CONSUME RESOURCE
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}
