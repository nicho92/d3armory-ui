package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import org.armory.d3.services.BNetLadderRetriever;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.model.LadderModel;

public class LadderPanel extends JPanel {
	private JTable ladderTable;
	BNetLadderRetriever ret ;
	
	
	public LadderPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panneauHaut = new JPanel();
		add(panneauHaut, BorderLayout.NORTH);
		
		final JComboBox cboRegion = new JComboBox(new DefaultComboBoxModel(new String[] {"us", "eu","ch","kr","tw"}));
		panneauHaut.add(cboRegion);
		
		final JComboBox cboClazz = new JComboBox();
		cboClazz.setModel(new DefaultComboBoxModel(new String[] {"dh", "crusader", "barbarian", "wd", "monk", "wizard","2","3","4"}));
		panneauHaut.add(cboClazz);
		
		final JComboBox cboErea = new JComboBox();
		cboErea.setModel(new DefaultComboBoxModel(new String[] {"Era", "1", "2"}));
		cboErea.setSelectedItem(D3ArmoryControler.getInstance().getSeason());
		//TODO
		
		panneauHaut.add(cboErea);
		
		final JCheckBox boxSeason = new JCheckBox("Season ?");
		panneauHaut.add(boxSeason);
		
		final JCheckBox boxHc = new JCheckBox("hardcore ?");
		panneauHaut.add(boxHc);
		
		JButton btnGetLadder = new JButton("Ladder !");
		panneauHaut.add(btnGetLadder);
		JScrollPane pane = new JScrollPane();
		ladderTable = new JTable();
		ladderTable.setColumnSelectionAllowed(false);
		ladderTable.setRowSelectionAllowed(true);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItemAdd = new JMenuItem("Add Tag");
		
		menuItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			String tag = ladderTable.getValueAt(ladderTable.getSelectedRow(),1).toString();
			D3ArmoryControler.getInstance().addTags(tag);
			
			}
		});
		popupMenu.add(menuItemAdd);
		
		ladderTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent event) {
				 Point point = event.getPoint();
			     int currentRow = ladderTable.rowAtPoint(point);
			     ladderTable.setRowSelectionInterval(currentRow, currentRow);
			     
			     if(SwingUtilities.isRightMouseButton(event))
			     {
			    	 popupMenu.show(ladderTable, (int)point.getX(), (int)point.getY());
			     }
			     
			     
			}
		});
		
		
		
		
		pane.setViewportView(ladderTable);
		add(pane, BorderLayout.CENTER);
		
		
		btnGetLadder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					ret = new BNetLadderRetriever(cboRegion.getSelectedItem().toString(), cboClazz.getSelectedItem().toString(), boxSeason.isSelected(), boxHc.isSelected(),cboErea.getSelectedItem().toString());
					ret.init();
					LadderModel d = new LadderModel(ret);
					
					ladderTable.setModel(d);
					
					DefaultRowSorter sorter = new TableRowSorter(d);
					ladderTable.setRowSorter(sorter);
				
					
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1,"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
	}
	public BNetLadderRetriever getRet() {
		return ret;
	}
	public void setRet(BNetLadderRetriever ret) {
		this.ret = ret;
	}
	public JTable getLadderTable() {
		return ladderTable;
	}
	public void setLadderTable(JTable ladderTable) {
		this.ladderTable = ladderTable;
	}

}



