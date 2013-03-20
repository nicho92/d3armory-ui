package org.armory.d3.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Gem;
import org.armory.d3.beans.Item;

public class SocketLabel extends JLabel {


	private Item item;
	private boolean hasSocket;
	private int socketNum=0;
	private Gem gem;
	
	@Override
	public Icon getIcon() {
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		if(gem != null)
			try {
				URL url = new URL("http://media.blizzard.com/d3/icons/items/small/"+gem.getItem().getIcon()+".png");
				Image i = new ImageIcon(url).getImage();
				Image newimg = i.getScaledInstance(i.getWidth(null)-10, i.getHeight(null)-10,  java.awt.Image.SCALE_SMOOTH); 
				return new ImageIcon(newimg);
			} catch (Exception e1) {
				return new ImageIcon();
			}
		return super.getIcon();
	}

	public void paint( Graphics g )
	  {
		if(hasSocket)
		{	
			try {
				 Graphics2D g2d = ( Graphics2D )g;
				 Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/socket.png")).getImage();
				 int x = (this.getWidth() - i.getWidth(null)) / 2;
				 int y = (this.getHeight() - i.getHeight(null)) / 2;
				 g2d.drawImage(i, x, y, null);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		super.paint( g );
	  }

	public void setSocket(Gem gem2) {
		this.gem=gem2;
	}

	//TODO bug avec les socket lors des changements de personnages
	public void setItem(Item item, int i) {
		this.item = item;
		
		if(item!=null)
		{
			if(item.nbSockets()>0)
				hasSocket=true;
			else
				hasSocket=false;			
				
			if(item.nbGems()>0)
				setSocket(item.getGems()[i]);
			else
				setSocket(null);
		}
	}
	

}
