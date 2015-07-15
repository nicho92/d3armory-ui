/**
 * Copyright (C) 2011 K Venkata Sudhakar <kvenkatasudhakar@gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pihen.d3restapi.test;

import java.util.Arrays;
import java.util.List;

import org.armory.d3.console.table.ASCIITable;
import org.armory.d3.console.table.impl.CollectionASCIITableAware;
import org.armory.d3.console.table.spec.IASCIITableAware;

/**
 * ASCII Table test cases.
 * 
 * @author K Venkata Sudhakar (kvenkatasudhakar@gmail.com)
 * @version 1.0
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		collectionTests();
	}
	
	
	private static void collectionTests() {
	
		Employee stud = new Employee("Sriram", 2, "Chess", false, 987654321.21d);
		Employee stud2 = new Employee("Sudhakar", 29, "Painting", true, 123456789.12d);
	    List<Employee> students = Arrays.asList(stud, stud2);
	 
	    IASCIITableAware asciiTableAware = 
	    	new CollectionASCIITableAware<Employee>(students, 
	    			"name", "age", "married", "hobby", "salary");  //properties to read
	    ASCIITable.getInstance(System.out).printTable(asciiTableAware);
	    
	    
	    asciiTableAware = 
	    	new CollectionASCIITableAware<Employee>(students, 
	    			Arrays.asList("name", "age", "married", "hobby", "salary"), //properties to read
	    			Arrays.asList("STUDENT_NAME", "HIS_AGE")); //custom headers
	    ASCIITable.getInstance(System.out).printTable(asciiTableAware);
	}

	public static class Employee {
	
		private String name;
		private int age;
		private String hobby;
		private boolean married;
		private double salary; 
	
		public Employee(String name, int age, String hobby, boolean married, double salary) {
			super();
			this.name = name;
			this.age = age;
			this.hobby = hobby;
			this.married = married;
			this.salary = salary;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public int getAge() {
			return age;
		}
	
		public void setAge(int age) {
			this.age = age;
		}
	
		public String getHobby() {
			return hobby;
		}
	
		public void setHobby(String hobby) {
			this.hobby = hobby;
		}
	
		public boolean isMarried() {
			return married;
		}

		public void setMarried(boolean married) {
			this.married = married;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}
	}
	
}
