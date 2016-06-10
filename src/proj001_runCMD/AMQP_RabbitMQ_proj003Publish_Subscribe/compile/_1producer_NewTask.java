/* PRODUCER */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class _1producer_NewTask {

  //ATTRIBUTES
   private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv){

  try {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    //Exchanges
    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

    String message = getMessage(argv);

    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  } catch(Exception e) {
	e.printStackTrace();
  }
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
