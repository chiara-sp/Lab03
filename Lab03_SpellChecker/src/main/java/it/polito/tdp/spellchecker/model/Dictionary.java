package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	
	private List<String> dizionario;
	private String lingua;

	public boolean loadDictionary(String language) {
		if(dizionario!= null && this.lingua.equals(language)) {
			return true;
			
		}
		dizionario = new ArrayList<String>();
		this.lingua=language;
		
		
		try {
            FileReader fr = new FileReader("src/main/resources/"+language+".txt");
                   BufferedReader br = new BufferedReader(fr);
            String word;
            while ((word = br.readLine()) != null) {
                         dizionario.add(word.toLowerCase());

            }
            Collections.sort(dizionario);

            br.close();

            fr.close();


            System.out.println("Dizionario in lingua "+language+" uploaded correttamente");

            return true;
       } catch (IOException e){
            System.out.println("Errore nella lettura del file");
            return false;
             }
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> parole = new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			RichWord r= new RichWord(s,false);
			if(dizionario.contains(s.toLowerCase())) {
				r.setCorretto(true);
			}
				
			else
				r.setCorretto(false);
			
			parole.add(r);
		}
		return parole;
	}
}
