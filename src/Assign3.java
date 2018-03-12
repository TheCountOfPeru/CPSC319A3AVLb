import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assign3 {
	
	/**
	 * Adapted from https://stackoverflow.com/a/21974043
	 * @param aString - The name of a text file.
	 * @return The file extension if it exists, blank otherwise
	 */
	public static String getFileExtension(String aString) {
	    try {
	        return aString.substring(aString.lastIndexOf("."));
	    } catch (Exception e) {
	        return "";
	    }
	}
	public static void main(String[] args) {
		File fileIn;
		Scanner scanner;
		PrintWriter pw;
		//Command line argument verification 
		/*
		if(args.length != 3) {
			System.out.println("Incorrect number of inputs. Quitting...");
			System.exit(-1);
		}
		if(!getFileExtension(args[0]).equals(".txt") || !getFileExtension(args[1]).equals(".txt") || !getFileExtension(args[2]).equals(".txt")) {
			System.out.println("Unable to use files that are not text files. Check your file names. Quitting...");
			System.exit(-1);
		}
		*/
		fileIn = new File("a3input1.txt");//args[0]);
		BST mybst = new BST();
		Node current; 
		String temp = "";
		char opc;
		int snum;
		String lnam;
		String dep;
		String prg;
		int year;
		try {
			System.out.println("Scanning text file for records...");
			scanner = new Scanner(fileIn);
			while(scanner.hasNextLine()){ 
				temp = scanner.nextLine();
				opc = temp.charAt(0);
				snum = Integer.parseInt(temp.substring(1, 7));
				lnam = temp.substring(8, 32);
				dep = temp.substring(33, 36);
				prg = temp.substring(37, 40);
				year = Integer.parseInt(temp.substring(41));
				System.out.println(lnam.trim());
				mybst.insert(opc, snum, lnam.trim(), dep, prg, year);											
			}
			scanner.close();
		}catch(FileNotFoundException e){
			System.out.println("Failed to read the text file. Quitting...");
			System.exit(-1);
		}
		current = mybst.getroot();
		System.out.println("BST created successfully.");
		
		try {
			pw = new PrintWriter("output1.txt");
			pw.println("Depth first traversal:");
			mybst.depthfirst(current, pw);
			System.out.println("Depth first traversal success.");
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening text for depth traversal output. File not found.");
		}
		try {
			pw = new PrintWriter("output2.txt");
			pw.println("Breadth first traversal:");
			mybst.breadthfirst(pw);
			System.out.println("Breadth first traversal success.");
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening text for breadth traversal output. File not found.");
		}
		System.out.println("The program has completed. Quitting...");
	}

}
