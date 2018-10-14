package controller;

import factory.JPAFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		// alerta do tipo imformação
				Alert alerta = new Alert(AlertType.INFORMATION);
				// setando titulo da janela
				alerta.setTitle("Informação");
				// titulo da mensagem no caso nao teve
				alerta.setHeaderText(null);
				// mensagem para usuario
				alerta.setContentText("Registro inserido com sucesso!");
				// mosta a janela/mensagem
				alerta.show();
				// chama o metodo para limpar campos
		return entity;
	}
	
	public void remove(T entity) {
		Repository<T> repository = 
				new Repository<T>(JPAFactory.getEntityManager());
		
		repository.getEntityManager().getTransaction().begin();
		repository.remove(entity);
		repository.getEntityManager().getTransaction().commit();
		repository.getEntityManager().close();
		// alerta do tipo imformação
				Alert alerta = new Alert(AlertType.INFORMATION);
				// setando titulo da janela
				alerta.setTitle("Informação");
				// titulo da mensagem no caso nao teve
				alerta.setHeaderText(null);
				// mensagem para usuario
				alerta.setContentText("Registro removido com sucesso!");
				// mosta a janela/mensagem
				alerta.show();
				// chama o metodo para limpar campos
	}
}
