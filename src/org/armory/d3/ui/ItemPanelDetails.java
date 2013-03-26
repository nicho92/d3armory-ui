package org.armory.d3.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Gem;
import org.armory.d3.beans.Item;
import org.jdesktop.application.Application;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ItemPanelDetails extends JPanel {

	Item item;
	private JLabel lblNomItem;
	
	private JLabel getLblNomItem() {
		if(lblNomItem == null) {
			lblNomItem = new JLabel();
			lblNomItem.setVisible(true);
			lblNomItem.setHorizontalAlignment(JLabel.CENTER);
			lblNomItem.setBounds(0, 0, 274, 39);
		}
		return lblNomItem;
	}

	public ItemPanelDetails()
	{
		Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		this.setLayout(null);
		this.add(getLblNomItem());
	}
	
	 public void paint( Graphics g )
	  {
		 	super.paint( g );
		 try {
			 Graphics2D g2d = ( Graphics2D )g;
			 Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/itemBackground.png")).getImage();
			 g2d.drawImage(i, 0, 0, null);
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			
		 	if(item!=null)
			{
			  getLblNomItem().setText(item.getName());
			  lblNomItem.setForeground(ItemLabel.toColor(item.getDisplayColor()));
			}
	  }

	public void setItem(Item item) {
		this.item=item;

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
