package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.model.EHPCalculatorModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EHPPanel extends JPanel {
	private JTable table;
	EHPCalculatorModel calc;
	
	
	public EHPPanel() {
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		table = new JTable();
		
		JSplitPane panneauSplit = new JSplitPane();
		panneauSplit.setBackground(Color.BLACK);
		panneauSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(panneauSplit);
		
		JPanel panneauHaut = new JPanel();
		panneauHaut.setBackground(Color.BLACK);
		panneauSplit.setLeftComponent(panneauHaut);
		GridBagLayout gbl_panneauHaut = new GridBagLayout();
		gbl_panneauHaut.columnWidths = new int[]{348, 0, 0, -85, 86, 0};
		gbl_panneauHaut.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panneauHaut.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panneauHaut.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panneauHaut.setLayout(gbl_panneauHaut);
		
		JLabel lblMonsterLevel = new JLabel("Monster Level");
		lblMonsterLevel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMonsterLevel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblMonsterLevel = new GridBagConstraints();
		gbc_lblMonsterLevel.anchor = GridBagConstraints.EAST;
		gbc_lblMonsterLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonsterLevel.gridx = 0;
		gbc_lblMonsterLevel.gridy = 0;
		panneauHaut.add(lblMonsterLevel, gbc_lblMonsterLevel);
		
		String[] mod = new String[]{"70 - Monster","73 - Elites", "79 - Ubber","60 - Monster"}; //TODO
		JComboBox comboBox = new JComboBox(mod);
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String choice = ((JComboBox)e.getSource()).getSelectedItem().toString();
				int monsterLevel = Integer.valueOf(choice.split("-")[0].trim());
				((EHPCalculatorModel)table.getModel()).getCalculator().setMonsterLevel(monsterLevel);
				((EHPCalculatorModel)table.getModel()).getCalculator().calculate();
				((EHPCalculatorModel)table.getModel()).fireTableDataChanged();
				
				
			}
		});
			
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridwidth = 4;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panneauHaut.add(comboBox, gbc_comboBox);
		
		JLabel lblAmorDamageReduction = new JLabel("Amor Damage Reduction");
		lblAmorDamageReduction.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblAmorDamageReduction = new GridBagConstraints();
		gbc_lblAmorDamageReduction.anchor = GridBagConstraints.WEST;
		gbc_lblAmorDamageReduction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmorDamageReduction.gridx = 0;
		gbc_lblAmorDamageReduction.gridy = 1;
		panneauHaut.add(lblAmorDamageReduction, gbc_lblAmorDamageReduction);
		
		JLabel lblArmorReducValue = new JLabel("");
		lblArmorReducValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblArmorReducValue = new GridBagConstraints();
		gbc_lblArmorReducValue.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblArmorReducValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblArmorReducValue.gridx = 2;
		gbc_lblArmorReducValue.gridy = 1;
		panneauHaut.add(lblArmorReducValue, gbc_lblArmorReducValue);
		
		JLabel lblResistanceDamageReduction = new JLabel("Resistance Damage Reduction");
		lblResistanceDamageReduction.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblResistanceDamageReduction = new GridBagConstraints();
		gbc_lblResistanceDamageReduction.anchor = GridBagConstraints.WEST;
		gbc_lblResistanceDamageReduction.insets = new Insets(0, 0, 5, 5);
		gbc_lblResistanceDamageReduction.gridx = 0;
		gbc_lblResistanceDamageReduction.gridy = 2;
		panneauHaut.add(lblResistanceDamageReduction, gbc_lblResistanceDamageReduction);
		
		JLabel lblResistValue = new JLabel("");
		lblResistValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblResistValue = new GridBagConstraints();
		gbc_lblResistValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblResistValue.gridx = 2;
		gbc_lblResistValue.gridy = 2;
		panneauHaut.add(lblResistValue, gbc_lblResistValue);
		
		JLabel lblDodgeDamageReduction = new JLabel("Dodge Damage Reduction");
		lblDodgeDamageReduction.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDodgeDamageReduction = new GridBagConstraints();
		gbc_lblDodgeDamageReduction.anchor = GridBagConstraints.WEST;
		gbc_lblDodgeDamageReduction.insets = new Insets(0, 0, 0, 5);
		gbc_lblDodgeDamageReduction.gridx = 0;
		gbc_lblDodgeDamageReduction.gridy = 3;
		panneauHaut.add(lblDodgeDamageReduction, gbc_lblDodgeDamageReduction);
		
		JLabel lblDodgeValue = new JLabel("");
		lblDodgeValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDodgeValue = new GridBagConstraints();
		gbc_lblDodgeValue.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDodgeValue.insets = new Insets(0, 0, 0, 5);
		gbc_lblDodgeValue.gridx = 2;
		gbc_lblDodgeValue.gridy = 3;
		panneauHaut.add(lblDodgeValue, gbc_lblDodgeValue);
		
		JScrollPane scrollPane = new JScrollPane();
		panneauSplit.setRightComponent(scrollPane);
		
		
		
		scrollPane.setViewportView(table);
		
	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}

}
