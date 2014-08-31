package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.pihen.d3restapi.service.util.LegendaryGemEvolutionChance;

public class GemEvolutionChancePanel extends JPanel {
	private JTable evolChangeTab;
	private JTextField txtNiveauGem;
	private JTextField txtgriftLevel;

	
	public GemEvolutionChancePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panneauHaut = new JPanel();
		add(panneauHaut, BorderLayout.NORTH);
		
		JLabel lblNiveauDeGem = new JLabel("Gem Lvl: ");
		panneauHaut.add(lblNiveauDeGem);
		
		txtNiveauGem = new JTextField();
		panneauHaut.add(txtNiveauGem);
		txtNiveauGem.setColumns(10);
	
		JLabel lblNiveaurift = new JLabel("Grift Level : ");
		panneauHaut.add(lblNiveaurift);
		
		txtgriftLevel = new JTextField();
		panneauHaut.add(txtgriftLevel);
		txtgriftLevel.setColumns(10);
		
		JButton btnCalculate = new JButton("Chance ?");
		panneauHaut.add(btnCalculate);
		
		final JLabel lblResultat = new JLabel("New label");
		panneauHaut.add(lblResultat);
		
		JScrollPane panneauScrollTableau = new JScrollPane();
		add(panneauScrollTableau, BorderLayout.CENTER);
		
		evolChangeTab = new JTable(new LegendaryGemChanceModel());
		evolChangeTab.setSelectionForeground(Color.WHITE);
		evolChangeTab.setSelectionBackground(Color.CYAN);
		evolChangeTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		evolChangeTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		evolChangeTab.setCellSelectionEnabled(true);
		evolChangeTab.setColumnSelectionAllowed(true);
		evolChangeTab.setDefaultRenderer(Object.class, new ColorCellChanceRenderer());
		panneauScrollTableau.setViewportView(evolChangeTab);
		
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lvlRift = Integer.parseInt(txtgriftLevel.getText());
				int lvlgem = Integer.parseInt(txtNiveauGem.getText());
				lblResultat.setText("1="+LegendaryGemEvolutionChance.getChance(lvlRift, lvlgem) +"% 2=" + LegendaryGemEvolutionChance.getChance(lvlRift-1, lvlgem)+ "% 3="+LegendaryGemEvolutionChance.getChance(lvlRift-2, lvlgem)+"%");
			    evolChangeTab.changeSelection(lvlgem, lvlRift, true, false);				
			}
		});
		
		evolChangeTab.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = evolChangeTab.rowAtPoint(evt.getPoint());
		        int col = evolChangeTab.columnAtPoint(evt.getPoint());
		        txtgriftLevel.setText(String.valueOf(col));
		        txtNiveauGem.setText(String.valueOf(row));
		    }
		});
		
		
		
	}

	
}


class LegendaryGemChanceModel extends DefaultTableModel
{
	private int lvlMax=51;
	private int lvlRift=51;
	private int[][] tableau;
	
	public LegendaryGemChanceModel()
	{
		tableau = new int[lvlMax][lvlRift];
			for(int lvlg=0;lvlg<lvlMax;lvlg++)
				for(int lvlr=1;lvlr<lvlRift;lvlr++)
					tableau[lvlr][lvlg]=LegendaryGemEvolutionChance.getChance(lvlr, lvlg);
					
				
	}	
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
	public String getColumnName(int column) {
		
		if(column==0)
			return "Gem\\Grift";

		return String.valueOf(column);
	}
	
	@Override
	public int getColumnCount() {
		return lvlRift;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		if(column==0)
			return row;
		
		return tableau[column][row];
	}
	
	@Override
	public int getRowCount() {
		return lvlMax;
	};
}

class ColorCellChanceRenderer extends DefaultTableCellRenderer
{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Integer val = (Integer)value;
       
        if(column==0)
        {
        	cell.setBackground(new Color(239,238,238));
        }
        else
        {
	        if(val<=8)
	        	cell.setBackground(new Color(252,82,82));
	        if(val==15)
	        	cell.setBackground(new Color(252,136,63));
	        if(val==60)
	        	cell.setBackground(new Color(252,252,63));
	        if(val>=70 && val<=90)
	        	cell.setBackground(new Color(123,252,63));
	        if(val>90)
	        	cell.setBackground(new Color(22,223,35));
        }
         return cell;
    }
	
	@Override
	public int getWidth() {
		return 100;
	}
	
}


