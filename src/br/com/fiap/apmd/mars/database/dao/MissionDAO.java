package br.com.fiap.apmd.mars.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.apmd.mars.model.Mission;
import br.com.fiap.apmd.mars.util.JPAUtil;

public class MissionDAO {
	EntityManager manager = JPAUtil.getManager();

	public void create(Mission filme) {
		manager.getTransaction().begin();
		manager.persist(filme);
		manager.getTransaction().commit();
	}
	
	public List<Mission> buscarTodos() {
		TypedQuery<Mission> query = 
				manager.createQuery("SELECT m FROM Mission m", Mission.class);
		return query.getResultList();
	}
	
	public void apagar(Mission filme) {
		manager.getTransaction().begin();
		manager.remove(filme);
		manager.getTransaction().commit();
	}

	public void apagar(Long id) {
		apagar(buscarPorId(id));
	}

	public Mission buscarPorId(Long id) {
		return manager.find(Mission.class, id);
	}
	
	public void alterar(Mission filme) {
		manager.getTransaction().begin();
		manager.merge(filme);
		manager.getTransaction().commit();
	}

}
