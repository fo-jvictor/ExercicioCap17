package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Main {

	public static void main(String[] args) {

		List<Product> list = new ArrayList<Product>();

		String folderPath = "C:\\Nova pasta";
		String srcPath = "C:\\Nova pasta\\arquivo.txt";
		String outputPath = "summary.csv";

		File sourceFile = new File(srcPath);
		String sourceFolderStr = sourceFile.getParent();

		System.out.println(sourceFolderStr);

		boolean success = new File(sourceFolderStr + "\\out").mkdir();

		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(srcPath))) {

			String line = br.readLine();
			while (line != null) {

				String[] stringSplit = line.split(",");
				String productName = stringSplit[0];
				double productPrice = Double.parseDouble(stringSplit[1]);
				int productQuantity = Integer.parseInt(stringSplit[2]);
				list.add(new Product(productName, productPrice, productQuantity));
				line = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
				for (Product item : list) {
					bw.write(item.getName() + "," + String.format("%.2f", item.totalPrice()));
					bw.newLine();
				}
				
				System.out.println("File created successfully!");

			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reader file: " + e.getMessage());
		}
	}
}
