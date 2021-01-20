package misos_MNP5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<TableObject> table = popuniTabelu();
		
		 Scanner myObj = new Scanner(System.in); 
		 System.out.println("Unesite tekst za kompresiju");

		 String unos = myObj.nextLine(); 
		
		 
		 String rezultatPrvog = prvaFazaAlgoritma(unos);
		 
		 System.out.println(rezultatPrvog);

//		for(int i=0;i<unos.length();i++) {
//			System.out.println(unos.charAt(i)); 
//			findInTableAndUpdate(unos.charAt(i),table);
//			sortTable(table);
//		}

//		for(int i=0;i<256;i++)
//		System.out.println(table.get(i));
		
	}


	private static String prvaFazaAlgoritma(String unos) {
		// TODO Auto-generated method stub
		String rezultat="";
		int brojacUzastopnihKaraktera = 0;
		for(int i=0;i<unos.length();i++) {
			
			if(i>0) {
			if(unos.charAt(i)==unos.charAt(i-1)) {
				brojacUzastopnihKaraktera++;
				rezultat+=unos.charAt(i);
			}else {

				if(brojacUzastopnihKaraktera >= 3) {
					rezultat = rezultat.substring(0, rezultat.length()-brojacUzastopnihKaraktera+3);
					rezultat+="("+brojacUzastopnihKaraktera+")";
					rezultat+=unos.charAt(i);
					brojacUzastopnihKaraktera=1;
				}else {
					brojacUzastopnihKaraktera=1;
				rezultat+=unos.charAt(i);
			}
			}
			
		}else {
			brojacUzastopnihKaraktera++;
			rezultat+=unos.charAt(i);
		}
		}
		return rezultat;
	}


	public static List<TableObject> popuniTabelu(){
		List<TableObject> table = new ArrayList<TableObject>();
		List<String> tokens = new ArrayList<String>();
		
		for(int i=0;i<8;i++) {
				if(i==0) {
					tokens.add("0000");
					tokens.add("0001");
				}else {
				 String result2 = Integer.toBinaryString(i);
				 String resultWithPadding2 = String.format("%3s", result2).replaceAll(" ", "0");
				 
				for(int j=0;j<Math.pow(2, i);j++) {
					 String result = Integer.toBinaryString(j);
					
					 String resultWithPadding;
					 if(i>0) {
					 String format = "%"+i+"s";
					  resultWithPadding = String.format(format, result).replaceAll(" ", "0");
					 }else {
					 resultWithPadding =result;
					 }
				
					
					//System.out.println("Za i= "+resultWithPadding2+" imam= " +resultWithPadding);
					tokens.add(resultWithPadding2+resultWithPadding);
				}

				}
		}
		//pretposlednji
		String pretposlednji = tokens.get(tokens.size()-1)+"0";
		String poslednji = tokens.get(tokens.size()-1)+"1";
		
		tokens.remove(tokens.size()-1);
		tokens.add(pretposlednji);
		tokens.add(poslednji);
		
		for(int i=0;i<=256;i++) {
			table.add(new TableObject(0, tokens.get(i), (char)i));
		}
		
		return table;
	}

	public static void findInTableAndUpdate(char ch,List<TableObject> table) {

		
		for(int i=0;i<256;i++)
		if(table.get(i).ascii == ch) {
			table.get(i).incFrequency();
		}

	}

	public static void sortTable(List<TableObject> table) {
		//replace pointers
	}

}
