package org.armory.d3.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.armory.d3.beans.Item;


public class ItemPanelDetails extends JPanel {

	private Item item;
	private JLabel lblNomItem;
	
	private JLabel getLblNomItem() {
		if(lblNomItem == null) {
			lblNomItem = new JLabel();
			lblNomItem.setHorizontalAlignment(JLabel.CENTER);
			lblNomItem.setBounds(46, 10, 274, 39);
			lblNomItem.setName("lblNomItem");
		}
		return lblNomItem;
	}

	public ItemPanelDetails()
	{
		this.setLayout(null);
		this.add(getLblNomItem());
	}
	
	 public void paintComponent(Graphics g)
	  {
		 Graphics2D g2d = ( Graphics2D )g;
		 Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/itemBackground.png")).getImage();
		 g2d.drawImage(i, 0, 0, null);
		 
	  }

	public void showItem(Item item) {
		this.item=item;
		getLblNomItem().setText(item.getName());
		getLblNomItem().setForeground(ItemLabel.toColor(item.getDisplayColor()));
		getLblNomItem().setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		
		
		
		
		
//		System.out.println(item.getName() + " ( "+ item.getTypeName() + ")");
// 		System.out.println("Niveau nécessaire " + item.getRequiredLevel());
// 		System.out.println("Niveau objet " + item.getItemLevel());
// 		
// 		if(item.isArmor())
// 			System.out.println("Armor " + item.getArmor());
// 		
// 		if(item.isWeapon()){
// 			System.out.println("DPS " + item.getDps());
// 			System.out.println("AttakSpeed " + item.getAttacksPerSecond());
// 			System.out.println("Min/Max damage " + item.getMinDamage().getMoyenne() + " " + item.getMaxDamage().getMoyenne());
// 		}
// 		for(int i=0;i<item.getAttributes().length;i++)
//			{
//				System.out.println(item.getAttributes()[i]);
//			}
// 		
// 		System.out.println("Socket " + item.nbSockets());
// 		
// 		if(item.nbGems()>0)
// 		{
// 			for(int i=0;i<item.getGems().length;i++)
//				{
//					Gem gem = item.getGems()[i];
// 				System.out.print(gem.getItem().getName() + " ");
// 				for(int j=0;j<gem.getAttributes().length;j++)
//					{
//						System.out.println(gem.getAttributes()[j]);
//					}
//				}
// 		}
		
	}
	
}
