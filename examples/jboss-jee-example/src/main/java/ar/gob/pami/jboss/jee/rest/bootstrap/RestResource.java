package ar.gob.pami.jboss.jee.rest.bootstrap;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Esta clase es uno de los métodos utilizados para crear una aplicación rest.
 * De alguna manera esta clase reemplaza la declaración de los filtros en el
 * web.xml. Desde JEE6 en adelante, no es necesario el web.xml para la
 * declaracion de los servicios rest.
 * 
 * @author aparedes
 *
 */
@ApplicationPath("/pami")
public class RestResource extends Application {

}
