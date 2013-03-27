package org.armory.d3.ui;

import java.awt.Color;

import javax.swing.JLabel;

public class FormatedJLabel extends JLabel {
	
	Color chiffreColor;
	Color textColor;
	StringBuffer temp ;
	String texte;
	boolean puces;
	
	
	
	public FormatedJLabel()
	{
		this.setVerticalAlignment(JLabel.TOP);
		chiffreColor=Color.WHITE;
		textColor=Color.GRAY;
		
	}

	public void setPuces(boolean bool)
	{
		this.puces=bool;
	}
	
	
	
	public void setHtmlText(String string,String textColor, String numColor)  {
		this.texte=string;
		temp = new StringBuffer();
		temp.append("<html>");
		
		String[] detail = texte.split(" ");
		boolean spec=false;
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
		temp.append("</html>");
		this.setText(temp.toString());
		
	}

	}

