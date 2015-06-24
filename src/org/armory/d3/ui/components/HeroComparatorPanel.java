package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultRowSorter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.model.ItemsDetailModel;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.Tag;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.util.HeroComparator;
import com.pihen.d3restapi.service.util.StuffCalculator;

public class HeroComparatorPanel extends JPanel {
	private JTable tableStatComparator;
	private JTable tableStuffComparator1;

	
	private Configuration conf;
	private HeroComparator comparator;
	D3ArmoryControler c;
	private JTable tableStuffComparator2;
	
	public HeroComparatorPanel() {
		setLayout(new BorderLayout(0, 0));
		final JComboBox cboListHero = new JComboBox();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Stat Comparator", null, scrollPane, null);
		
		tableStatComparator = new JTable();
		scrollPane.setViewportView(tableStatComparator);
		
		JSplitPane splitPane = new JSplitPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tableStuffComparator1 = new JTable();
		tableStuffComparator1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(tableStuffComparator1);

		JScrollPane scrollPane_2 = new JScrollPane();
			tableStuffComparator2 = new JTable();
			tableStuffComparator2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(tableStuffComparator2);
		
		splitPane.setLeftComponent(scrollPane_2);
		splitPane.setRightComponent(scrollPane_1);
		
		
		tabbedPane.addTab("Stuff Comparator", null, splitPane, null);
		
	
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JComboBox<Tag> cboListTag = new JComboBox<Tag>(D3ArmoryControler.getInstance().getListTags().toArray(new Tag[D3ArmoryControler.getInstance().getListTags().size()]));
		
		
		cboListTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				Tag t = ((Tag)((JComboBox)evt.getSource()).getSelectedItem());
				try {
					conf = D3ArmoryControler.getInstance().getConfiguration(t);
					c = new D3ArmoryControler();
					c.setConf(conf);
					Profile p = c.getProfil(t);
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
					
					
					//tableStuffComparator.setModel(new ItemComparatorModel(comparator.getCalculator1(), comparator.getCalculator2()));
					ItemsDetailModel mod1 = new ItemsDetailModel();
					mod1.setCalc(comparator.getCalculator1());
							tableStuffComparator1.setModel(mod1);
							tableStuffComparator1.setBackground(Color.BLACK);
							tableStuffComparator1.setForeground(Color.WHITE);
//							DefaultRowSorter sorter2 = new TableRowSorter(tableStuffComparator1.getModel());
//							sorter2.toggleSortOrder(0);
//							tableStuffComparator1.setRowSorter(sorter2);

					ItemsDetailModel mod2 = new ItemsDetailModel();
					mod1.setCalc(comparator.getCalculator2());
					tableStuffComparator2.setModel(mod2);
					tableStuffComparator2.setBackground(Color.BLACK);
					tableStuffComparator2.setForeground(Color.WHITE);
					
//					//DefaultRowSorter sorter3 = new TableRowSorter(tableStuffComparator2.getModel());
//					//sorter3.toggleSortOrder(0);
//					tableStuffComparator2.setRowSorter(sorter2);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1,"ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblCompareWith = new JLabel("Compare with : ");
		panel.add(lblCompareWith);
		
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


