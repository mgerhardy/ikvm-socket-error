package foo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.concurrent.TimeoutException;

public class Send {
	private final static String QUEUE_NAME = "hello";

	public static void main(final String[] argv) throws java.io.IOException, TimeoutException {
		try {
			final ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			factory.setSocketConfigurator(socket -> socket.setSoTimeout(0));
			final Connection connection = factory.newConnection();
			final Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println(" [*] Sending messages. To exit press CTRL+C");
			for (;;) {
				try {
					final String message = "Hello World!";
					channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
					System.out.println(" [x] Sent '" + message + "'");
					try {
						Thread.sleep(1000);
					} catch (final InterruptedException e) {
					}
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
