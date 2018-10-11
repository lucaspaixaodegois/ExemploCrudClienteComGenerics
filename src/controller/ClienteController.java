package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import javax.persistence.EntityManager;
//import javax.persistence.Query;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import repository.ClienteRepository;

public class ClienteController extends Controller implements Initializable {

	private Cliente cliente;

	@FXML
	private TextField tfCpf, tfNome, tfEndereco, tfEmail;

    @FXML
    private DatePicker dpAniversario;

	@FXML
	private Button btLimpar, btIncluir;
	@FXML
	private Button btExcluir;

	@FXML
	private Button btAlterar;

	@FXML
	private TableView<Cliente> tvClientes;

	@FXML
	private TableColumn<Cliente, Integer> tcIdClientes;

	@FXML
	private TableColumn<Cliente, String> tcCpfClientes;

	@FXML
	private TableColumn<Cliente, String> tcNomeClientes;

	@FXML
	private TableColumn<Cliente, String> tcEnderecoClientes;

	@FXML
	private TableColumn<Cliente, String> tcEmailClientes;

	@FXML
	private TextField tfPesquisar;

	@FXML
	private TabPane tpAbas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// setando o focus no text field cpf
		tfCpf.requestFocus();

		// configurando as colunas das tabelas conforme os atributos da classe Cliente
		tcIdClientes.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcCpfClientes.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomeClientes.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcEnderecoClientes.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcEmailClientes.setCellValueFactory(new PropertyValueFactory<>("email"));

	}

	@FXML
	void handlePesquisar(ActionEvent event) {
		ClienteRepository repository = 
				new ClienteRepository(JPAFactory.getEntityManager());
		List<Cliente> lista = repository.getClientes(tfPesquisar.getText());
		
		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados.");
			alerta.show();
		}
		tvClientes.setItems(FXCollections.observableList(lista));
	}

	@FXML
	void handleMouseClicked(MouseEvent event) {
		// verificando se eh o botao principal
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// verificando a quantidade de cliques
			if (event.getClickCount() == 2) {
				cliente = tvClientes.getSelectionModel().getSelectedItem();
				tfCpf.setText(cliente.getCpf());
				tfNome.setText(cliente.getNome());
				tfEndereco.setText(cliente.getEndereco());
				tfEmail.setText(cliente.getEmail());
				dpAniversario.setValue(cliente.getDataAniversaio());

				// selecionando a primeira aba
				tpAbas.getSelectionModel().select(0);

				// setando o focus no cpf
				tfCpf.requestFocus();
				atualizarBotoes();
			}
		}

	}

	@FXML
	void hadleIncluir(ActionEvent event) {
		cliente = new Cliente(tfCpf.getText(), 
							  tfNome.getText(), 
							  tfEndereco.getText(), 
							  tfEmail.getText(),
							  dpAniversario.getValue());

		super.save(cliente);
		
		handleLimpar(event);
	}

	@FXML
	void handleAlterar(ActionEvent event) {
		cliente.setCpf(tfCpf.getText());
		cliente.setNome(tfNome.getText());
		cliente.setEndereco(tfEndereco.getText());
		cliente.setEmail(tfEmail.getText());
		cliente.setDataAniversaio(dpAniversario.getValue());

		save(cliente);
		
		handleLimpar(event);
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		super.remove(cliente);
		handleLimpar(event);
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		tfCpf.setText("");
		tfNome.setText("");
		tfEndereco.setText("");
		tfEmail.setText("");
		dpAniversario.setValue(null);
		// limpando as informacoes do cliente
		cliente = new Cliente();
		// setando o focus no cpf
		tfCpf.requestFocus();

		atualizarBotoes();
	}

	private void atualizarBotoes() {
		btIncluir.setDisable(cliente.getId() != null);
		btAlterar.setDisable(cliente.getId() == null);
		btExcluir.setDisable(cliente.getId() == null);
	}

}
