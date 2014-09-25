package com.pihen.d3restapi.beans;

public class Ladder {

		private int rank;
		private int levelRift;
		private String profile;
		private String time;
		private String name;
		
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
		public int getLevelRift() {
			return levelRift;
		}
		public void setLevelRift(int levelRift) {
			this.levelRift = levelRift;
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
		
		@Override
		public String toString() {
		return getProfile();
		}
}
