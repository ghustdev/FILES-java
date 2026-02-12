package application;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Learn {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		// --- Read Files ---
		
		// Instance of the file, encapsulation
		File file = new File("/tmp/FILES-java-projetc/text.txt");
		Scanner sc = null;
		
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
		}
		// Use try because can be created exception
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}
		
		// Another method (open Streams):
		String path = file.getPath(); // or "/tmp/FILES-java-projetc/text.txt"
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			// Accelerates process of reading
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			try {
				if (fr != null) {
					fr.close();
				}
				if (br != null) {
					br.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// --- FileWriter and model optimized ---
		String[] lines = new String[] {"Morning", "Afternoon", "Night"};
		String path2 = "/tmp/FILES-java-projetc/out.txt";
		
		// Recria todos os dados de arquivo, porém com o true, ele adiciona ao final
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path2, true))) {
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
