package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {

	
	@Override
	public List< Member > importMembers(File inputFile) throws Exception {

		     String FixedLengths = "12,25,25,30,20,4,5";
             List<String> items = Arrays.asList(FixedLengths.split("\\s*,\\s*"));
             
             List<Member> membersFromFile=new ArrayList<>();

		try (BufferedReader br = new BufferedReader( new FileReader( inputFile ) )) {
			 String line;
	            while ((line = br.readLine()) != null) {
	                // process the line.

	                int n = 0;
	                String line1 = "";
	                for (String i : items) {
	              
	                    if (i == items.get(items.size() - 1)) {
	                        line1 = line1 + line.substring(n, n + Integer.parseInt(i)).trim();
	                    } else {
	                        line1 = line1 + line.substring(n, n + Integer.parseInt(i)).trim() + ",";
	                    }
	                    n = n + Integer.parseInt(i);
	                }
	  
	                String[] values=line1.split(",");
	                Member member=new Member();
	                member.setId(values[0]);
	                member.setLastName(values[1]);
	                member.setFirstName(values[2]);
	                member.setAddress(values[3]);
	                member.setCity(values[4]);
	                member.setState(values[5]);
	                member.setZip(values[6]);
	      
	                membersFromFile.add(member);
	                	                
	            }

			}
		catch(Exception e) {
			e.printStackTrace();
		}

		return membersFromFile;
	}
	
	

}
