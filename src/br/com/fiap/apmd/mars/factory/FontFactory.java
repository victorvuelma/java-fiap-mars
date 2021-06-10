package br.com.fiap.apmd.mars.factory;

import java.awt.Font;

public class FontFactory {

	public static Font getBold(int size) {
		return new Font("", Font.BOLD, size);
	}
	
	public static Font getSize(int size) {
		return new Font("", Font.PLAIN, size);
	}
	
}
