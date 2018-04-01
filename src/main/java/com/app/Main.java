package com.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the Anagram Finder");
		System.out.println("------------------------------");
		List<String> list = null;
		try {
			FileHelper fh = new FileHelper();
			if (args.length > 0) {
				long start = System.currentTimeMillis();
				list = fh.read(args[0]);
				long end = System.currentTimeMillis();
				System.out.println(" Dictionary loaded in " + (end - start) + " ms \n");
			} else {
				System.out.println(" Must contain path to dictionary ");
				System.exit(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		AnagramDetector ad = new AnagramDetector(list,args.length == 2 );

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(" Enter a word : ");
		String inputWord;
		while(true) {
			inputWord = reader.readLine();
			if(inputWord !=null){
				if (inputWord.toLowerCase().equals("exit")) {
					System.out.println(" Good bye ! ");
					break;
				}
				long start = System.currentTimeMillis();
				List<String> res = ad.find(inputWord);
				long end = System.currentTimeMillis();
				if (res == null || res.size() == 0) {
					System.out.println(" No anagram found for " + inputWord + " in " + (end - start) + " ms");
				} else {
					StringBuilder sb = new StringBuilder();
					int ct = 1;
					int size = res.size();
					System.out.println(" " + size + " anagrams found for " + inputWord + " in " + (end - start) + " ms");
					for(String r: res) {
						sb.append(r);
						if(ct < size) sb.append(',');
						ct++;
					}
					System.out.println(sb.toString());
				}

				System.out.print("\n Enter a word : ");
			}
		}

	}
}
