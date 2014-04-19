package com.cinco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestBed {

	public void TestBedSet(String Location){
		
		

			try {
				
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(Location));
				
				//InvoiceData id = new InvoiceData();

				String line = null;
				int lineNumber = 0;
				
				while((line = br.readLine()) != null){
					line.trim();

					if(lineNumber != 0 && line.compareTo(" ") !=0)
						

					lineNumber++;
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			

		
		}
	}

