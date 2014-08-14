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
import java.awt.FlowLayout;

public class EHPPanel extends JPanel {
	private JTable table;
	EHPCalculatorModel calc;
	
	
	public EHPPanel() {
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		table = new JTable();
		
		JSplitPane panneauSplit = new JSplitPane();
		panneauSplit.setEnabled(false);
		panneauSplit.setBackground(Color.BLACK);
		panneauSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(panneauSplit);
		
		JPanel panneauHaut = new JPanel();
		panneauHaut.setBackground(Color.BLACK);
		panneauSplit.setLeftComponent(panneauHaut);
		panneauHaut.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMonsterLevel = new JLabel("Monster Level");
		lblMonsterLevel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMonsterLevel.setForeground(Color.WHITE);
		panneauHaut.add(lblMonsterLevel);
		
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
		panneauHaut.add(comboBox);
		
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
