package controller;

import factory.JPAFactory;
import model.DefaultEntity;
import repository.Repository;

public class Controller<T extends DefaultEntity<? super T>> {
	
	public T save(T entity) {
		Repository<T> repository = 
				new Repository<T>(JPAFactory.getEntityManager());
		
		// iniciando a transacao
		repository.getEntityManager().getTransaction().begin();
		entity = repository.save(entity);
		repository.getEntityManager().getTransaction().commit();
		repository.getEntityManager().close();
		
		return entity;
	}
	
	public void remove(T entity) {
		Repository<T> repository = 
				new Repository<T>(JPAFactory.getEntityManager());
		
		repository.getEntityManager().getTransaction().begin();
		repository.remove(entity);
		repository.getEntityManager().getTransaction().commit();
		repository.getEntityManager().close();
	}
}
