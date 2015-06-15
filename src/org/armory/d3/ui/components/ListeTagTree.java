package org.armory.d3.ui.components;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.armory.d3.services.D3ArmoryControler;

public class ListeTagTree extends JTree {
	public ListeTagTree() {
		
		
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
//		 DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
//         renderer.setLeafIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/loot.png")));
//         
//         setCellRenderer(renderer);
		
		
		setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("BattleTags") {
				{
					DefaultMutableTreeNode node_1;
				
					node_1 = new DefaultMutableTreeNode("us");
					for(String s : D3ArmoryControler.getInstance().getListTags("us"))
						node_1.add(new DefaultMutableTreeNode(s.substring(0, s.lastIndexOf("#"))));
					add(node_1);
					
					node_1 = new DefaultMutableTreeNode("eu");
					for(String s : D3ArmoryControler.getInstance().getListTags("eu"))
						node_1.add(new DefaultMutableTreeNode(s.substring(0, s.lastIndexOf("#"))));
					add(node_1);
					
					node_1 = new DefaultMutableTreeNode("ch");
					for(String s : D3ArmoryControler.getInstance().getListTags("ch"))
						node_1.add(new DefaultMutableTreeNode(s.substring(0, s.lastIndexOf("#"))));
					add(node_1);
					
					node_1 = new DefaultMutableTreeNode("kr");
					for(String s : D3ArmoryControler.getInstance().getListTags("kr"))
						node_1.add(new DefaultMutableTreeNode(s.substring(0, s.lastIndexOf("#"))));
					add(node_1);
					
					node_1 = new DefaultMutableTreeNode("tw");
					for(String s : D3ArmoryControler.getInstance().getListTags("tw"))
						node_1.add(new DefaultMutableTreeNode(s.substring(0, s.lastIndexOf("#"))));
					add(node_1);
				}
			}
		));
		
		for (int i = 0; i < getRowCount(); i++) {
	         expandRow(i);
	}
		
	
	}

}
