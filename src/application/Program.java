package application;

import entities.FilesOut;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		File file = new File("/tmp/FILES-java-projetc/data.csv");
		Scanner sc = null;
		
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] dataLines = line.split(",");
				
				String name  = dataLines[0];
				double price  = Double.parseDouble(dataLines[1]);
				int quantity = Integer.parseInt(dataLines[2]);
				
				System.out.println(name + " " + price + " " + quantity);
				
				FilesOut lineFile = new FilesOut(name, price, quantity);
				
				double totalValue = lineFile.calculateTotal();
				
				String path = "/tmp/FILES-java-projetc/out/summary.csv";
				
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
					String lineOut = String.format("%s,%s", lineFile.getName(), totalValue);
					
					bw.write(lineOut);
					bw.newLine();
				}
				catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error Write: " + e.getMessage());
				}
			}
		}
		catch (IOException e) {
			System.out.println("Error Read: " + e.getMessage());
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}
