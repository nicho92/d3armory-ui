package org.armory.d3.ui;

import java.awt.Color;

import javax.swing.JLabel;

public class FormatedJLabel extends JLabel {
	
	Color chiffreColor;
	Color textColor;
	StringBuffer temp ;
	String texte;
	
	public FormatedJLabel(String text,Color numColor, Color textColor)
	{
		this.texte=text;
		chiffreColor=numColor;
		this.textColor=textColor;
	}
	
	public FormatedJLabel()
	{
		chiffreColor=Color.WHITE;
		textColor=Color.GRAY;
		this.texte=new String("");
	}
	
	public void setHtmlText(String string,String textColor, String numColor)  {
		this.texte=string;
		
		
		temp = new StringBuffer();
		temp.append("<html>");
		
		String[] detail = texte.split(" ");
		
		for(int i=0;i<detail.length;i++)
		{
			if(detail[i].startsWith("+") || detail[i].endsWith("%")){
				temp.append(" <font color='"+numColor+"'>").append(detail[i]).append("</font>");
			}
			else{
					if(detail[i].contains(","))
					{
						temp.append(" <font color='"+numColor+"'>").append(detail[i]).append("</font>");
					}
					else{
						try{
							Double.parseDouble(detail[i]);
							temp.append(" <font color='"+numColor+"'>").append(detail[i]).append("</font>");
							}
							catch(NumberFormatException e)
							{
								temp.append(" <font color='"+textColor+"'>").append(detail[i]).append("</font>");	
							}
						}
					}
			}
		temp.append("</html>");
		this.setText(temp.toString());
		
	}

	}

