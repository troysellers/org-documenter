package uk.co.force.documenter.workers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.Channel;
import com.rabbitmq.client.AMQP.Connection;
import com.rabbitmq.client.ConnectionFactory;

import uk.co.force.documenter.common.RabbitMQConfig;

/**
 * Starting point for worker to handle incoming queue messages
 * 
 * @author tsellers
 *
 */
public class QueueListener {

	private Logger logger;
	
	public static void main(String[] args) {
		
		QueueListener listener = new QueueListener();
		listener.run();
	}
	
	private void run() {
	
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("We have a QueueListener");
		
		RabbitMQConfig config = new RabbitMQConfig();
		ConnectionFactory factory = config.connectionFactory();
		
		Connection conn = null;
		Channel channel = null;
		
	}
}
