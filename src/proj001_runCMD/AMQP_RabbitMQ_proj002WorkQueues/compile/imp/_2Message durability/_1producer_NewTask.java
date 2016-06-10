/* PRODUCER */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class _1producer_NewTask {

  //ATTRIBUTES
  private final static String QUEUE_NAME = "task_queue_v1";

  //MAIN  
  public static void main(String[] argv) throws Exception {
    //CONNECTION AND CHANNEL
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    //DECLARE QUEUE
    boolean durable = true;
    channel.queueDeclare("task_queue_v1", durable, false, false, null);
    
    //PUBLISH VALUE IN QUEUE (HARDCODED)
    //String message = "Hello World!";
    //PUBLISH VALUE IN QUEUE (ARGS)
    String message = getMessage(argv);


    //channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    channel.basicPublish("", "task_queue_v1", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }

	private static String getMessage(String[] strings){
		if (strings.length < 1)
			return "Hello World!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
	    	int length = strings.length;
	    	if (length == 0) return "";
	    	StringBuilder words = new StringBuilder(strings[0]);
	    	for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
	    	}
	    	return words.toString();
	}


}
