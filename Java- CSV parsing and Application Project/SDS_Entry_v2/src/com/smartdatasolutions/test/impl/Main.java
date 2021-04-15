package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;



import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		MemberExporter memberExporter=new MemberExporterImpl();
		
		return memberExporter;
	}

	@Override
	protected MemberImporter getMemberImporter() {
		MemberImporter memberImporter=new MemberImporterImpl();
		return memberImporter;
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {
		Set<Member> s = new TreeSet<Member>(new Comparator<Member>() {

	        @Override
	        public int compare(Member m1, Member m2) {
	            return (m1.getId().compareTo(m2.getId()));
	        }
	    });
	            s.addAll(membersFromFile);
	    List<Member> updated = new ArrayList<>();
	    updated.addAll(s);

		return updated;
	}
	
	

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		Map<String, List<Member>> membersPerState =
			    validMembers.stream().collect(Collectors.groupingBy(Member::getState));
		
		return membersPerState;
	}
	
	

	public static void main( String[] args ) {
		//TODO
		
		Main m =new Main();
		File inputFile=new File("Members.txt");
		String outputFilePath="C:\\Users\\acer\\Downloads\\Java- Quickclaim and Quality Developer (1)(1)\\SDS_Entry_v2\\output";
		String outputFileName="outputFile.csv";
//		
		
		try {
			m.convert(inputFile, outputFilePath, outputFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Success");
	 
		
	}

}
