package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import org.armory.d3.ui.SwingMainFrame;
import org.armory.d3.ui.model.EHPCalculatorModel;

import com.pihen.d3restapi.service.util.StuffCalculator.KEY;

public class EHPPanel extends JPanel {
	private JTable table;
	EHPCalculatorModel calc;
	JLabel lblToughness = new JLabel("Toughness :");
	
	
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
		
		
		List list = new ArrayList();
		for(int i=1;i<80;i++)
			list.add(i);
			
			
		JComboBox comboBox = new JComboBox(list.toArray());
		
		comboBox.setSelectedIndex(69);
		
		
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String choice = ((JComboBox)e.getSource()).getSelectedItem().toString();
				int monsterLevel = Integer.valueOf(choice);
				((EHPCalculatorModel)table.getModel()).getCalculator().setMonsterLevel(monsterLevel);
				((EHPCalculatorModel)table.getModel()).getCalculator().calculate();
				((EHPCalculatorModel)table.getModel()).fireTableDataChanged();
				lblToughness.setText("Toughness : " + SwingMainFrame.formatRessourceVisibleValue(((EHPCalculatorModel)table.getModel()).getCalculator().getStats().get(KEY.TOUGHNESS)));
				
			}
		});
		panneauHaut.add(comboBox);
		
		final JCheckBox chckbxElites = new JCheckBox("Elites ?");
		chckbxElites.setBackground(Color.BLACK);
		chckbxElites.setForeground(Color.WHITE);
		panneauHaut.add(chckbxElites);
		
		lblToughness.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblToughness.setForeground(Color.GREEN);
		panneauHaut.add(lblToughness);
		
		chckbxElites.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				((EHPCalculatorModel)table.getModel()).setElite(chckbxElites.isSelected());
				((EHPCalculatorModel)table.getModel()).getCalculator().calculate();
				((EHPCalculatorModel)table.getModel()).fireTableDataChanged();
			}
		});
		
		
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
