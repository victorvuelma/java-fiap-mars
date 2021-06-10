package br.com.fiap.apmd.mars;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.apmd.mars.components.ButtonComponent;
import br.com.fiap.apmd.mars.components.ChooseOptionComponent;
import br.com.fiap.apmd.mars.components.DateFieldComponent;
import br.com.fiap.apmd.mars.components.GridPanel;
import br.com.fiap.apmd.mars.components.LabelComponent;
import br.com.fiap.apmd.mars.components.NumberFieldComponent;
import br.com.fiap.apmd.mars.database.dao.MissionDAO;
import br.com.fiap.apmd.mars.listener.CancelButtonListener;
import br.com.fiap.apmd.mars.listener.SaveButtonListener;
import br.com.fiap.apmd.mars.model.Mission;
import br.com.fiap.apmd.mars.util.DateUtil;

public class App extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1659278897985887518L;

	public static void main(String[] args) {
		App app = new App();
		app.init();
	}

	private JTabbedPane tabsPane;

	private ChooseOptionComponent droneOptionsChoose;
	private JComboBox<String> marsMonthCombo;
	private NumberFieldComponent daysField;
	private DateFieldComponent earthDateField;

	private NumberFieldComponent longitudeField;

	private NumberFieldComponent maxTempField;
	private NumberFieldComponent minTempField;
	private NumberFieldComponent atmPressureField;

	private JPanel formButtonPanel;
	private JButton saveButton;
	private JButton updateButton;

	private DefaultTableModel appTableModel = new DefaultTableModel();
	private JTable table = new JTable(appTableModel);
	private MissionDAO dao = new MissionDAO();

	private Mission changeMission;

	public App() {
		super("Fiap Mars");
	}

	public void init() {
		tabsPane = new JTabbedPane();

		JPanel registerPanel = new GridPanel(1, 2, 16, 0);

		JPanel dronePanel = new GridPanel(9, 1, 0, 8);

		JLabel droneLabel = new LabelComponent("Drone");
		dronePanel.add(droneLabel);
		String[] droneOptions = { "Brave", "Mike", "Rodric" };
		droneOptionsChoose = new ChooseOptionComponent(droneOptions);
		dronePanel.add(droneOptionsChoose);

		JLabel monthLabel = new LabelComponent("Mês marciano");
		String[] monthOptions = { "Mes 01", "Mes 02", "Mes 03", "Mes 04", "Mes 05", "Mes 06", "Mes 07", "Mes 08",
				"Mes 09", "Mes 10", "Mes 11", "Mes 12" };
		marsMonthCombo = new JComboBox<String>(monthOptions);
		dronePanel.add(monthLabel);
		dronePanel.add(marsMonthCombo);

		JLabel daysLabel = new LabelComponent("Dias Marcianos");
		daysField = new NumberFieldComponent(true);
		dronePanel.add(daysLabel);
		dronePanel.add(daysField);

		JLabel earthDateLabel = new LabelComponent("Data (terra)");
		earthDateField = new DateFieldComponent();
		dronePanel.add(earthDateLabel);
		dronePanel.add(earthDateField);

		registerPanel.add(dronePanel);

		// INFO PANEL
		JPanel infoPanel = new GridPanel(9, 1, 0, 8);

		JLabel longitudeLabel = new LabelComponent("Longitude");
		longitudeField = new NumberFieldComponent(false);
		infoPanel.add(longitudeLabel);
		infoPanel.add(longitudeField);

		JLabel maxTempLabel = new LabelComponent("Temp. Máxima");
		maxTempField = new NumberFieldComponent(false);
		infoPanel.add(maxTempLabel);
		infoPanel.add(maxTempField);

		JLabel minTempLabel = new LabelComponent("Temp. Mínima");
		minTempField = new NumberFieldComponent(false);
		infoPanel.add(minTempLabel);
		infoPanel.add(minTempField);

		JLabel atmPressureLabel = new LabelComponent("Pressão ATM");
		atmPressureField = new NumberFieldComponent(false);
		infoPanel.add(atmPressureLabel);
		infoPanel.add(atmPressureField);

		registerPanel.add(infoPanel);

		formButtonPanel = new GridPanel(1, 2, 8, 0);

		saveButton = new ButtonComponent("Salvar");

		formButtonPanel.add(saveButton);

		updateButton = new ButtonComponent("Salvar");
		updateButton.addActionListener(this);

		JButton abortButton = new ButtonComponent("Cancelar");
		formButtonPanel.add(abortButton);

		saveButton.addActionListener(new SaveButtonListener(droneOptionsChoose, marsMonthCombo, daysField,
				earthDateField, longitudeField, maxTempField, minTempField, atmPressureField, this));
		abortButton.addActionListener(new CancelButtonListener(droneOptionsChoose, marsMonthCombo, daysField,
				earthDateField, longitudeField, maxTempField, minTempField, atmPressureField));

		infoPanel.add(formButtonPanel);

		tabsPane.add("Cadastro", registerPanel);

		JPanel listPanel = new JPanel(new BorderLayout());
		JScrollPane listScroll = new JScrollPane(this.table);

		initTable();
		loadTable();

		listPanel.add(listScroll);

		JPanel listButtonsPanel = new GridPanel(1, 3, 8, 8);

		JButton listDeleteButton = new ButtonComponent("Apagar");
		listDeleteButton.addActionListener(this);
		listButtonsPanel.add(listDeleteButton);

		JButton listEditButton = new ButtonComponent("Alterar");
		listEditButton.addActionListener(this);
		listButtonsPanel.add(listEditButton);

		JButton listRefreshButton = new ButtonComponent("Atualizar");
		listRefreshButton.addActionListener(this);
		listButtonsPanel.add(listRefreshButton);

		listPanel.add(listButtonsPanel, BorderLayout.SOUTH);

		tabsPane.add("Lista", listPanel);

		this.add(tabsPane);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 450);
		this.setVisible(true);
	}

	private void initTable() {
		appTableModel.addColumn("Id");
		appTableModel.addColumn("Data");
		appTableModel.addColumn("Drone");
		appTableModel.addColumn("Dias Marcianos");
		appTableModel.addColumn("Longitude");
		appTableModel.addColumn("Mês");
		appTableModel.addColumn("Temp. Min.");
		appTableModel.addColumn("Temp. Max.");
		appTableModel.addColumn("Pressão ATM");
	}

	private void loadTable() {
		appTableModel.setRowCount(0);

		List<Mission> missions = dao.buscarTodos();
		for (Mission mission : missions) {
			String[] linha = { mission.getId() + "", DateUtil.toString(mission.getEarthDate()), mission.getDrone(),
					mission.getMarsDays() + " dias", mission.getLongitude() + "", mission.getMarsMonth() + "",
					mission.getMinTemp() + "", mission.getMaxTemp() + "", mission.getAtmPressure() + "" };
			appTableModel.addRow(linha);
		}
	}

	private void apagar() {
		int linha = table.getSelectedRow();
		String id = table.getValueAt(linha, 0).toString();
		int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que quer apagar a missão selecionada?");
		if (resposta == JOptionPane.YES_OPTION) {
			Mission mission = dao.buscarPorId(Long.valueOf(id));
			dao.apagar(mission);
			loadTable();
		}
	}

	private void alterar() {
		int linha = table.getSelectedRow();
		String id = table.getValueAt(linha, 0).toString();
		int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que quer alterar a missão selecionada?");
		if (resposta == JOptionPane.YES_OPTION) {
			changeMission = dao.buscarPorId(Long.valueOf(id));

			this.droneOptionsChoose.setValue(changeMission.getDrone());
			this.marsMonthCombo.setSelectedItem(changeMission.getMarsMonth());
			this.daysField.setValue(changeMission.getMarsDays());
			this.earthDateField.setText(DateUtil.toString(changeMission.getEarthDate()));
			this.longitudeField.setValue(changeMission.getLongitude());
			this.maxTempField.setValue(changeMission.getMaxTemp());
			this.minTempField.setValue(changeMission.getMinTemp());
			this.atmPressureField.setValue(changeMission.getAtmPressure());

			formButtonPanel.remove(saveButton);
			formButtonPanel.add(updateButton);

			tabsPane.setSelectedIndex(0);
		}
	}

	private void salvarAlteracao() {
		try {
			changeMission.setDrone(this.droneOptionsChoose.getValue());
			changeMission.setMarsMonth((String) this.marsMonthCombo.getSelectedItem());
			changeMission.setMarsDays(this.daysField.getNumber().intValue());
			changeMission.setEarthDate(DateUtil.fromString(this.earthDateField.getText()));
			changeMission.setLongitude(this.longitudeField.getNumber().floatValue());
			changeMission.setMaxTemp(this.maxTempField.getNumber().floatValue());
			changeMission.setMinTemp(this.minTempField.getNumber().floatValue());
			changeMission.setAtmPressure(this.atmPressureField.getNumber().floatValue());

			dao.alterar(changeMission);

			formButtonPanel.remove(updateButton);
			formButtonPanel.add(saveButton);

			this.droneOptionsChoose.clearSelection();
			this.marsMonthCombo.setSelectedItem(null);
			this.daysField.setText(null);
			this.earthDateField.setText(null);
			this.longitudeField.setText(null);
			this.maxTempField.setText(null);
			this.minTempField.setText(null);
			this.atmPressureField.setText(null);
			

			JOptionPane.showMessageDialog(null, "Missão alterada com sucesso!");

			goToList();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goToList() {
		tabsPane.setSelectedIndex(1);

		loadTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton source = (JButton) e.getSource();

			if (source.getText().equals("Apagar")) {
				apagar();
			} else if (source.getText().equals("Atualizar")) {
				loadTable();
			} else if (source.getText().equals("Alterar")) {
				alterar();
			} else if (source.getText().equals("Salvar")) {
				salvarAlteracao();
			}
		}
	}

}
