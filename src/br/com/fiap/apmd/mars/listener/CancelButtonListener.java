package br.com.fiap.apmd.mars.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import br.com.fiap.apmd.mars.components.ChooseOptionComponent;
import br.com.fiap.apmd.mars.components.DateFieldComponent;
import br.com.fiap.apmd.mars.components.NumberFieldComponent;

public class CancelButtonListener implements ActionListener {

	private ChooseOptionComponent droneOptionsChoose;
	private JComboBox<String> marsMonthCombo;
	private NumberFieldComponent daysField;
	private DateFieldComponent earthDateField;

	private NumberFieldComponent longitudeField;
	private NumberFieldComponent maxTempField;
	private NumberFieldComponent minTempField;
	private NumberFieldComponent atmPressureField;

	public CancelButtonListener(ChooseOptionComponent droneOptionsChoose, JComboBox<String> marsMonthCombo,
			NumberFieldComponent daysField, DateFieldComponent earthDateField, NumberFieldComponent longitudeField,
			NumberFieldComponent maxTempField, NumberFieldComponent minTempField,
			NumberFieldComponent atmPressureField) {
		this.droneOptionsChoose = droneOptionsChoose;
		this.marsMonthCombo = marsMonthCombo;
		this.daysField = daysField;
		this.earthDateField = earthDateField;
		this.longitudeField = longitudeField;
		this.maxTempField = maxTempField;
		this.minTempField = minTempField;
		this.atmPressureField = atmPressureField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.droneOptionsChoose.clearSelection();
		this.marsMonthCombo.setSelectedItem(null);
		this.daysField.setText(null);
		this.earthDateField.setText(null);
		this.longitudeField.setText(null);
		this.maxTempField.setText(null);
		this.minTempField.setText(null);
		this.atmPressureField.setText(null);
	}

}
