package com.pihen.d3restapi.beans;

import javax.swing.ImageIcon;

public class Ladder {

		private int rank;
		private int levelrift;
		private String profile;
		private String time;
		private String date;
		private String name;
		private ImageIcon icon;
		
		
		
		public ImageIcon getIcon() {
			return icon;
		}
		public void setIcon(ImageIcon icon) {
			this.icon = icon;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public int getLevelrift() {
			return levelrift;
		}
		public void setLevelrift(int levelRift) {
			this.levelrift = levelRift;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
		public void setDate(String date) {
			this.date=date;
		}
		
		public String getDate() {
			return date;
		}
}
