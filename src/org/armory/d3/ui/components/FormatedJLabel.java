package org.armory.d3.ui.components;

import java.awt.Color;

import javax.swing.JLabel;

public class FormatedJLabel extends JLabel {
	
	Color chiffreColor;
	Color textColor;
	StringBuffer temp ;
	String texte;
	boolean puces;
	
	public FormatedJLabel(String text, int pos)
	{
		super(text,pos);
		this.setVerticalAlignment(JLabel.TOP);
		chiffreColor=Color.WHITE;
		textColor=Color.GRAY;
		init();
	}
	
	public FormatedJLabel()
	{
		this.setVerticalAlignment(JLabel.TOP);
		chiffreColor=Color.WHITE;
		textColor=Color.GRAY;
		init();
	}

	public void setPuces(boolean bool)
	{
		this.puces=bool;
	}
	
	public void init()
	{
		temp=new StringBuffer("<html>");
	}
	
	public void addText(String text,String t,String numColor)
	{
		if(t.equals("blue"))
			t="#5869D7";
		
		setHtmlText(text +" <br/> ",t,numColor);
	}
	
	public String getHtmlText()
	{
		return temp.append("</html>").toString();
	}
	
	public void applyText()
	{
		temp.append("</HTML>");
		this.setText(temp.toString());
	}
	
	private void setHtmlText(String string,String textColor, String numColor)  {
		this.texte=string;
		
		String[] detail = texte.split(" ");
		boolean spec=false;
		
		for(int i=0;i<detail.length;i++)
		{
			if(detail[i].startsWith("#")){
				temp.append(" <font color='white'>").append(detail[i].substring(1)).append("</font> ");
			}
			else
			if(detail[i].startsWith("+") || detail[i].endsWith("%")){
				temp.append(" <font color='"+numColor+"'>").append(detail[i]).append("</font>");
			}
			else{
					if(detail[i].contains(",") || detail[i].contains("."))
					{
						if(detail[i].endsWith(",") || (detail[i].endsWith(".") && !detail[i].endsWith("%.")))
							temp.append(" <font color='"+textColor+"'>").append(detail[i]).append("</font>");
						else
							temp.append(" <font color='"+numColor+"'>").append(detail[i]).append("</font>");
					}
					else
					{
						try{
							Double.parseDouble(detail[i]);
							temp.append(" <font color='"+numColor+"'>").append(detail[i]).append("</font>");
							}
							catch(NumberFormatException e)
							{
								if(detail[i].startsWith("("))
								{
									spec=true;
								}
								
								if(spec)
									temp.append(" <font color='red'>").append(detail[i]).append("</font>");
								else
									temp.append(" <font color='"+textColor+"'>").append(detail[i]).append("</font>");
								
								if(detail[i].endsWith(")"))
								{
									spec=false;
								}
							}
						}
					}
			}
		//temp.append("</html>");
		//this.setText(temp.toString());
		
	}

	}

