package ar.gob.pami.jboss.jee.logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

public class LoggerProducer {

	@Produces
	public Logger produce(InjectionPoint injectionPoint) {

		Logger logger = Logger.getLogger(injectionPoint.getMember()
				.getDeclaringClass().getName());

		return logger;

	}

}
