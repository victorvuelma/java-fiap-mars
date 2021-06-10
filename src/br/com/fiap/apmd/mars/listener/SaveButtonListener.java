package br.com.fiap.apmd.mars.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.com.fiap.apmd.mars.App;
import br.com.fiap.apmd.mars.components.ChooseOptionComponent;
import br.com.fiap.apmd.mars.components.DateFieldComponent;
import br.com.fiap.apmd.mars.components.NumberFieldComponent;
import br.com.fiap.apmd.mars.database.dao.MissionDAO;
import br.com.fiap.apmd.mars.model.Mission;
import br.com.fiap.apmd.mars.util.DateUtil;

public class SaveButtonListener implements ActionListener {

	private ChooseOptionComponent droneOptionsChoose;
	private JComboBox<String> marsMonthCombo;
	private NumberFieldComponent daysField;
	private DateFieldComponent earthDateField;

	private NumberFieldComponent longitudeField;
	private NumberFieldComponent maxTempField;
	private NumberFieldComponent minTempField;
	private NumberFieldComponent atmPressureField;

	private App app;

	public SaveButtonListener(ChooseOptionComponent droneOptionsChoose, JComboBox<String> marsMonthCombo,
			NumberFieldComponent daysField, DateFieldComponent earthDateField, NumberFieldComponent longitudeField,
			NumberFieldComponent maxTempField, NumberFieldComponent minTempField, NumberFieldComponent atmPressureField,
			App app) {
		this.droneOptionsChoose = droneOptionsChoose;
		this.marsMonthCombo = marsMonthCombo;
		this.daysField = daysField;
		this.earthDateField = earthDateField;
		this.longitudeField = longitudeField;
		this.maxTempField = maxTempField;
		this.minTempField = minTempField;
		this.atmPressureField = atmPressureField;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Mission mission = new Mission();
			mission.setDrone(this.droneOptionsChoose.getValue());
			mission.setMarsMonth((String) this.marsMonthCombo.getSelectedItem());
			mission.setMarsDays(this.daysField.getNumber().intValue());
			mission.setEarthDate(DateUtil.fromString(this.earthDateField.getText()));
			mission.setLongitude(this.longitudeField.getNumber().floatValue());
			mission.setMaxTemp(this.maxTempField.getNumber().floatValue());
			mission.setMinTemp(this.minTempField.getNumber().floatValue());
			mission.setAtmPressure(this.atmPressureField.getNumber().floatValue());

			MissionDAO dao = new MissionDAO();
			dao.create(mission);

			JOptionPane.showMessageDialog(null, "Missão criada com sucesso!");

			this.droneOptionsChoose.clearSelection();
			this.marsMonthCombo.setSelectedItem(null);
			this.daysField.setText(null);
			this.earthDateField.setText(null);
			this.longitudeField.setText(null);
			this.maxTempField.setText(null);
			this.minTempField.setText(null);
			this.atmPressureField.setText(null);

			app.goToList();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
