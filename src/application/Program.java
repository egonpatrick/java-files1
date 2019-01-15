package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String[] infoProduct;
		Product product;
		
				
		System.out.println("Enter a path of file(.csv): ");
		String strPath = sc.nextLine();
		File path = new File(strPath);
		
		String summary = path.getParent() + "\\out\\summary.csv";
		boolean success = new File(path.getParent() + "\\out").mkdir();
		
		try(BufferedReader br = new BufferedReader(new FileReader(strPath))){
			String line = br.readLine();
			
			while (line != null) {				
				infoProduct = line.split(",");	
				product = new Product(infoProduct[0], Double.parseDouble(infoProduct[1]), Integer.parseInt(infoProduct[2]));
				
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(summary, true))) {
					bw.write(product.toString());
					bw.newLine();					
				} catch (IOException e) {
					e.printStackTrace();
				}				
				line = br.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}

}
