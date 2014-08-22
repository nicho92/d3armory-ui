package org.armory.d3.ui.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Item;

public class SocketLabel extends JLabel {


	private Item item;
	private boolean hasSocket;
	
	private Gem gem;
	private int position;
	
	public SocketLabel()
	{
		super();
		this.position=0;
	}
	
	
	public SocketLabel(int position)
	{
		super();
		this.position=position;
	}
	
	
	@Override
	public Icon getIcon() {
		
		setHorizontalAlignment(position);
		setVerticalAlignment(JLabel.CENTER);

		if(gem != null && hasSocket)
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
				 
				 if(position==JLabel.LEFT)
					 g2d.drawImage(i, -5, 0, null);//TODO Position du socket
				 else
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

	public void setItem(Item item, int i) {
		this.item = item;
		
		if(item!=null)
		{
			if(item.nbSockets()>0)
				hasSocket=true;
			else
				hasSocket=false;			
				
			try{		
				setSocket(item.getGems().get(i));
			}catch(Exception e){
				setSocket(null);
			}
		}
		else
		{
			hasSocket=false;
		}
	}
	
	

}
