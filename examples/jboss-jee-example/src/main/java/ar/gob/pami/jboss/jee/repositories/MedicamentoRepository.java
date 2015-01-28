package ar.gob.pami.jboss.jee.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import ar.gob.pami.jboss.jee.model.Medicamento;

@Stateless
public class MedicamentoRepository extends Repository<Medicamento> {

	@SuppressWarnings("unchecked")
	public List<Medicamento> all() {
		logger.info("Get Todos los medicamentos");
		return this.getEntityManager().createQuery("from Medicamentos")
				.getResultList();
	}

}
