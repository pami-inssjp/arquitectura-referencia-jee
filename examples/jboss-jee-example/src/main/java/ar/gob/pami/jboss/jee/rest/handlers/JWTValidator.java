package ar.gob.pami.jboss.jee.rest.handlers;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import ar.gob.pami.jboss.jee.exceptions.TokenInvalidoException;

public class JWTValidator {

	@Inject
	private Logger logger;

	/**
	 * Revisa si el token es válido
	 * 
	 * @param token
	 * @param secret
	 * @return
	 */
	public boolean validate(JsonWebSignature jws) {
		try {
			logger.info("Validando token");
			boolean result;
			result = jws.verifySignature();
			logger.info(String.format("Token Valido? = %s", result));
			return result;
		} catch (JoseException e) {
			throw new TokenInvalidoException("3000",
					"El token enviado no es valido", e);
		}

	}

	private JsonWebSignature getJsonWebSignature(String token, byte[] secret) {
		try {
			JsonWebSignature jws = new JsonWebSignature();
			jws.setCompactSerialization(token);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(secret);
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(
					spec);
			jws.setKey(publicKey);
			return jws;
		} catch (JoseException | InvalidKeySpecException
				| NoSuchAlgorithmException exception) {
			logger.error("Error al intentar de validar el token", exception);
			throw new TokenInvalidoException("2000",
					"Error al intentar de validar el token", exception);
		}
	}

	public String getPayload(String token, byte[] secret) {
		try {

			logger.info("Validando token");
			JsonWebSignature jws = this.getJsonWebSignature(token, secret);
			this.validate(jws);
			String payload = jws.getPayload();
			logger.info(payload);
			return payload;
		} catch (JoseException e) {
			logger.error("Error al intentar de extraer el payload del token", e);
			throw new TokenInvalidoException("2100",
					"Error al intentar de extraer el payload del token", e);
		}

	}
}
