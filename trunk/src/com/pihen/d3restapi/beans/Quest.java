package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class Quest implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1007780251284681783L;
		private String slug;
		private String name;
		public String getSlug() {
			return slug;
		}
		public void setSlug(String slug) {
			this.slug = slug;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	
}
