package ar.gob.pami.jboss.jee.repositories;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ar.gob.pami.jboss.jee.model.Model;

public class Repository<T extends Model> {

	@PersistenceContext(unitName = "pami")
	private EntityManager entityManager;

	@Inject
	protected Logger logger;

	@SuppressWarnings("unchecked")
	public Class<T> getGenericType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public T findById(Long id) {
		logger.info(String.format("Get %s = %s", getGenericType()
				.getSimpleName(), id));
		return this.getEntityManager().find(getGenericType(), id);
	}

	public T save(T entity) {
		logger.info(String.format("Saving %s = %s", getGenericType(), entity));
		this.getEntityManager().persist(entity);
		return entity;
	}
}
