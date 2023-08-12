package Practice;

import java.util.Scanner;

public class Scanning {

	public static void main(String[] args) {
		//there's a way to ignore inputs with .skip method on scanner object
		String s = "34\n78\nHello There";
	    Scanner scan = new Scanner(s);
	    //scan.skip("(hi|hello)?");
	    System.out.println(Integer.valueOf(scan.nextLine())); //need scan whole nextLine to include newline character and then convert into an integer
	    System.out.println(Integer.valueOf(scan.nextLine())); //if only scan.nextInt(), you would be accounting the newline character from previous line
	    System.out.println(scan.nextLine()); 
	    scan.close(); 

	}

}
