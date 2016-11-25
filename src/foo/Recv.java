package foo;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;

public class Recv {
	private final static String QUEUE_NAME = "hello";

	public static void main(final String[] argv) throws Exception {
		final ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setSocketConfigurator(socket -> socket.setSoTimeout(0));
		System.out.println(" [*] Connecting...");
		final Connection connection = factory.newConnection();
		System.out.println(" [*] Create channel...");
		final Channel channel = connection.createChannel();

		System.out.println(" [*] Create queue...");
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		final Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(final String consumerTag, final Envelope envelope, final AMQP.BasicProperties properties,
					final byte[] body) throws IOException {
				final String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
		System.in.read();
	}
}
