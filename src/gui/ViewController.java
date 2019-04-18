package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import model.entities.CarRental;
import model.entities.Carros;
import model.entities.FakeDatabase;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;
import model.services.USATaxService;

public class ViewController {

	@FXML
	private ComboBox combo;

	@FXML
	protected void initialize() {

		FakeDatabase db = new FakeDatabase();

		for (Carros c : db.all()) {
			combo.getItems().add(c);
		}
	}

	@FXML
	private TextField txtRetirada;

	@FXML
	private TextField txtDevolucao;

	@FXML
	private TextField txtPrecoPorHora;

	@FXML
	private TextField txtPrecoPorDia;

	@FXML
	private Label labelValorBasico;

	@FXML
	private Label labelTaxa;

	@FXML
	private Label labelValorTotal;

	@FXML
	private RadioButton radioBrasil;

	@FXML
	private RadioButton radioEUA;

	@FXML
	private Button buttonCalcular;

	public void onButtonCalcularAction() throws ParseException {

		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		int carModel = combo.getSelectionModel().getSelectedIndex();
		Date start = sdf.parse(txtRetirada.getText());
		Date finish = sdf.parse(txtDevolucao.getText());

		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		double pricePerHour = 0.00;
		double pricePerDay = 0.00;

		if (carModel==0) {
			pricePerHour = 12.00;
			pricePerDay = 135.00;
			txtPrecoPorHora.setText(Double.toString(pricePerHour));
			txtPrecoPorDia.setText(Double.toString(pricePerDay));
		}
		if (carModel==1) {
			pricePerHour = 15.00;
			pricePerDay = 140.00;
			txtPrecoPorHora.setText(Double.toString(pricePerHour));
			txtPrecoPorDia.setText(Double.toString(pricePerDay));
		}

		if (carModel==2) {
			pricePerHour = 25.00;
			pricePerDay = 160.00;
			txtPrecoPorHora.setText(Double.toString(pricePerHour));
			txtPrecoPorDia.setText(Double.toString(pricePerDay));
		}
		if (carModel==3) {
			pricePerHour = 8.00;
			pricePerDay = 120.00;
			txtPrecoPorHora.setText(Double.toString(pricePerHour));
			txtPrecoPorDia.setText(Double.toString(pricePerDay));
		}
		
		if (radioBrasil.isSelected()) {
			RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
			rentalService.processInvoice(cr);
		} else {
			RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new USATaxService());
			rentalService.processInvoice(cr);

		}

		labelValorBasico.setText(String.format("%.2f", cr.getInvoce().getBasicPayment()));
		labelTaxa.setText(String.format("%.2f", cr.getInvoce().getTax()));
		labelValorTotal.setText(String.format("%.2f", cr.getInvoce().getTotalPayment()));

	}

}
