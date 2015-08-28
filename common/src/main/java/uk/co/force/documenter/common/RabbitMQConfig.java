package uk.co.force.documenter.common;

import java.net.URI;
import java.net.URISyntaxException;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {

	public static final String CREATE_RIDE_QUEUE = "createRideQueue";
	public static final String CREATE_CASE_QUEUE = "createCaseQueue";
	
	public ConnectionFactory connectionFactory() {
		final URI rabbitMqUrl;
		try {
			rabbitMqUrl = new URI(System.getenv("CLOUDAMQP_URL"));
			System.out.println("Rabbit URL ["+rabbitMqUrl+"]");
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(rabbitMqUrl.getUserInfo().split(":")[0]);
		factory.setPassword(rabbitMqUrl.getUserInfo().split(":")[1]);
		factory.setHost(rabbitMqUrl.getHost());
		factory.setPort(rabbitMqUrl.getPort());
		factory.setVirtualHost(rabbitMqUrl.getPath().substring(1));
		factory.setRequestedHeartbeat(60);
		return factory;
	}
}
