package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultRowSorter;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.util.HeroComparator;
import com.pihen.d3restapi.service.util.StuffCalculator;

public class HeroComparatorPanel extends JPanel {
	private JTable tableStatComparator;
	private JTable tableStuffComparator;

	
	private Configuration conf;
	private HeroComparator comparator;
	D3ArmoryControler c;
	
	public HeroComparatorPanel() {
		setLayout(new BorderLayout(0, 0));
		final JComboBox cboListHero = new JComboBox();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Stat Comparator", null, scrollPane, null);
		
		tableStatComparator = new JTable();
		scrollPane.setViewportView(tableStatComparator);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Stuff Comparator", null, scrollPane_1, null);
		tableStuffComparator = new JTable();
		scrollPane_1.setViewportView(tableStuffComparator);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JComboBox cboListTag = new JComboBox(D3ArmoryControler.getInstance().getListTags().toArray());
		cboListTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				String tag[] = ((String)((JComboBox)evt.getSource()).getSelectedItem()).split("#");
				try {
					conf = D3ArmoryControler.getInstance().getConfiguration(tag[2]+".battle.net", tag[0], Long.parseLong(tag[1]));
					c = new D3ArmoryControler();
					c.setConf(conf);
					Profile p = c.getProfil(tag[2]+".battle.net", tag[0], Long.parseLong(tag[1]));
					cboListHero.removeAll();
					for(Hero h : p.getHeroes())
					{
						cboListHero.addItem(h);
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		});
		
		
		cboListHero.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Hero h = ((Hero)((JComboBox)e.getSource()).getSelectedItem());
				try {
					comparator = new HeroComparator();
					comparator.initComparatedHero(c, h);
					comparator.recalculate();
					
					tableStatComparator.setModel(new StatComparatorModel(comparator.getCalculator1(), comparator.getCalculator2()));
					tableStatComparator.setDefaultRenderer(Object.class, new StuffComparCellRenderer());
					tableStatComparator.setBackground(Color.BLACK);
					DefaultRowSorter sorter = new TableRowSorter(tableStatComparator.getModel());
					sorter.toggleSortOrder(0);
					tableStatComparator.setRowSorter(sorter);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		panel.add(cboListTag);
		panel.add(cboListHero);
	}

}


class StatComparatorModel extends DefaultTableModel
{
	StuffCalculator one,two;
	
	public StatComparatorModel(StuffCalculator one, StuffCalculator two)
	{
		this.one=one;
		this.two=two;
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	public String getColumnName(int column) {
			switch(column)
			{
			case 0:return "Stat";
			case 1:return "You "+one.getHero().getName();
			case 2:return two.getHero().getName();
			case 4:return "Difference";
			default : return "";
			}
	}
	
	@Override
	public int getRowCount() {
		return StuffCalculator.KEY.values().length;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		if(column==0)
			return StuffCalculator.KEY.values()[row];
		if(column==1)
			return one.getStats().get(StuffCalculator.KEY.values()[row]);
		if(column==2)
			return two.getStats().get(StuffCalculator.KEY.values()[row]);
		if(column==3)
		{
			double vala = one.getStats().get(StuffCalculator.KEY.values()[row]);
			double valb= two.getStats().get(StuffCalculator.KEY.values()[row]);
			
			if((Double)getValueAt(row, 1)>(Double)getValueAt(row, 2))
				return "+"+StuffCalculator.format(valb-vala)+ " ("+StuffCalculator.format(((valb - vala) / valb ) * 100)+"%)";
			else
			if((Double)getValueAt(row, 1)<(Double)getValueAt(row, 2))
				return "-"+StuffCalculator.format(valb-vala)+ " ("+StuffCalculator.format(((valb - vala) / valb ) * 100)+"%)";
			else
				return "0";
		}
		
		
		return null;
	}
}


