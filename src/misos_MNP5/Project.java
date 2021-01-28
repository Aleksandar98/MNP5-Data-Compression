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
		 
		 System.out.println();
		 
		 System.out.println("Rezulta prvog algoritma:");
		 
		 System.out.println(rezultatPrvog);

		 System.out.println();
		 
		 String rezultatDrugog = drugaFazaAlgoritma(rezultatPrvog, table);
		 
		 System.out.println("Rezulta drugog algoritma:");
		 
		 System.out.println(rezultatDrugog);

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
					rezultat+="("+(brojacUzastopnihKaraktera-3)+")";
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
			if(i==unos.length()-1) {
				if(brojacUzastopnihKaraktera >= 3) {
				rezultat = rezultat.substring(0, rezultat.length()-brojacUzastopnihKaraktera+3);
				rezultat+="("+(brojacUzastopnihKaraktera-3)+")";
				brojacUzastopnihKaraktera=1;
				}
			}
		}
		return rezultat;
	}
	private static String funk (List<TableObject> table,int i,String input,int freqToUpdate) {
		String result=getCodeFromTable(table,input.charAt(i));
		
		updateFrequency(input.charAt(i),table,freqToUpdate);
		sortTable(table);
		//provera da li je doslo do max onda se sve freq dele sa 2 (ako je 2.5 zaokruzi na 3)
		if(reachedMax(table)) {
			cutDownTable(table);
		}
		return result;
	}
	private static boolean reachedMax(List<TableObject> table) {
		boolean reached = false;
		for(int i=0;i<table.size();i++)
		if(table.get(i).frequency == 256) {
			reached = true;
			break;
		}
		return reached;
	}


	private static void cutDownTable(List<TableObject> table) {
		for(int i=0;i<table.size();i++)
			table.get(i).setFrequency(table.get(i).getFrequency()/2);
		
	}


	private static String drugaFazaAlgoritma(String input,List<TableObject> table) {
		
		String result="";
		int brojJavljanja = 1;
		for(int i=0;i<input.length();i++) {
		
			if(i>0) {
				if(input.charAt(i)==input.charAt(i-1)) {
					brojJavljanja++;
					
					//ako se javio 3 puta
					if(brojJavljanja==3) {
						int j = i+2;
						String broj="";
						while(input.charAt(j)!=')') {
							broj+=input.charAt(j);
							j++;
						}

					//odstampas broj i ne updatujes tabelu za broj
					
					
					//update tabelu za taj char za 3
					result+= funk(table,i,input,1)+" ";
					//result+= input.charAt(i)+" ";
					result+=input.charAt(i)+"("+broj+")"+Integer.toBinaryString(Integer.valueOf(broj))+" ";
					// povecaj i tako da se preskoci ( + broj + )
					int brojBrojki = 1;
					if(Integer.valueOf(broj)>=10)
						brojBrojki = 2;
					
					i+=(2+brojBrojki);
					//System.out.println(input.charAt(i));
					}else {
						result+=funk(table,i,input,1)+" ";
						//result+= input.charAt(i)+" ";
					}
					
				}else {
					brojJavljanja=1;
					result+=funk(table,i,input,1)+" ";
					//result+= input.charAt(i)+" ";

				}
			}else {
				result+=funk(table,i,input,1)+" ";
				//result+= input.charAt(i)+" ";

			}
			
	}

		return result;
	}
	
	private static String getCodeFromTable(List<TableObject> table, char charAt) {
		String result="notFound";
		for(int i=0;i<table.size();i++)
			if(table.get(i).ascii == charAt) {
				result = table.get(i).token;
				break;
			}
		return result;
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

	public static void updateFrequency(char ch,List<TableObject> table,int num) {

		
		for(int i=0;i<table.size();i++)
		if(table.get(i).ascii == ch) {
			table.get(i).incFrequency(num);
		}

	}

	public static void sortTable(List<TableObject> table) {
		//replace pointers
		List<TableObject> helpTable = new ArrayList<TableObject>();
		for(int i=0;i<table.size();i++)
		helpTable.add(new TableObject(table.get(i)));


		//Collections.sort(table);
	    int n = helpTable.size(); 
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++) 
                if (helpTable.get(j).getFrequency()<helpTable.get(j+1).getFrequency())
                { 
                    // swap arr[j+1] and arr[j] 
                    TableObject temp = helpTable.get(j); 
                    helpTable.set(j, helpTable.get(j+1));
                    helpTable.set(j+1, temp);
                } 
     
        
		for(int i=0;i<table.size();i++) {
			char newAscii = helpTable.get(i).getAscii();
			int newFreq = helpTable.get(i).getFrequency();
			table.get(i).setAscii(newAscii);
			table.get(i).setFrequency(newFreq);
		}

	}

}
