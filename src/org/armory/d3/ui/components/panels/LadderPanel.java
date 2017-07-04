package org.armory.d3.ui.components.panels;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.impl.BNetLadderRetriever;
import org.armory.d3.ui.model.LadderModel;

public class LadderPanel extends JPanel {
	private JTable ladderTable;
	BNetLadderRetriever ret ;
	JCheckBox boxSeason;
	JComboBox cboRegion;
	JComboBox cboClazz; 
	JComboBox cboErea;
	JCheckBox boxHc; 
	
	
	public LadderPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panneauHaut = new JPanel();
		add(panneauHaut, BorderLayout.NORTH);
		
		cboRegion = new JComboBox(new DefaultComboBoxModel(new String[] {"us", "eu","ch","kr","tw"}));
		panneauHaut.add(cboRegion);
		
		cboClazz = new JComboBox();
		cboClazz.setModel(new DefaultComboBoxModel(new String[] {"dh", "crusader", "barbarian", "wd", "monk", "wizard","necromancer","2","3","4"}));
		panneauHaut.add(cboClazz);
		
		cboErea = new JComboBox();
		
		
		List<String> seasons = new ArrayList<String>();
		for(int i=D3ArmoryControler.getInstance().getSeason();i>=1;i--)
			seasons.add(String.valueOf(i));

		cboErea.setModel(new DefaultComboBoxModel(seasons.toArray()));
		//TODO
		panneauHaut.add(new JLabel("Era : "));
		panneauHaut.add(cboErea);
		
		boxSeason = new JCheckBox("Season ?");
		panneauHaut.add(boxSeason);
		
		boxHc = new JCheckBox("hardcore ?");
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
					ret.retrieveLadder();
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
	public JCheckBox getBoxSeason() {
		return boxSeason;
	}
	public void setBoxSeason(JCheckBox boxSeason) {
		this.boxSeason = boxSeason;
	}
	public JComboBox getCboRegion() {
		return cboRegion;
	}
	public void setCboRegion(JComboBox cboRegion) {
		this.cboRegion = cboRegion;
	}
	public JComboBox getCboClazz() {
		return cboClazz;
	}
	public void setCboClazz(JComboBox cboClazz) {
		this.cboClazz = cboClazz;
	}
	public JComboBox getCboErea() {
		return cboErea;
	}
	public void setCboErea(JComboBox cboErea) {
		this.cboErea = cboErea;
	}
	public JCheckBox getBoxHc() {
		return boxHc;
	}
	public void setBoxHc(JCheckBox boxHc) {
		this.boxHc = boxHc;
	}

}



