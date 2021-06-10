package br.com.fiap.apmd.mars.components;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class NumberFieldComponent extends JFormattedTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754393724343342766L;

	public NumberFieldComponent(boolean isInt) {
		super(getFormat(isInt));
	}

	private static NumberFormat getFormat(boolean isInt) {
		NumberFormat format = NumberFormat.getInstance();
		if (isInt) {
			format.setMaximumFractionDigits(0);
		} else {
			format.setMinimumFractionDigits(2);
		}

		return format;
	}

	public Number getNumber() {
		return ((Number) getValue());
	}

}
