import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Project 3: Binary Search Trees related algorithm, 2-d tree implementation  
 * 
 * @author Georgina Tolgos
 *
 */
public class Main {

    public static void main(String[] args) {
    	 
    	TwoDTree twoDT = new TwoDTree("./CrimeLatLonXY.csv");
    	
    	
    	System.out.println("Crime file loaded into 2-d tree with "+ twoDT.getSize() + " records");
    	System.out.println("What would you like to do?" +"\n"+ 
				"1. Inorder" + "\n"+
				"2. PreOrder" + "\n"+
				"3. LevelOrder" + "\n"+
				"4. PostOrder" + "\n"+
				"5. Search for points within rectangle"+ "\n"+
				"6. Search for nearest neighbour" + "\n"+
				"7. Quit");
    	
    	Scanner scn = new Scanner(System.in);
    	int entry = scn.nextInt();
    	do {
    		switch(entry) {
    		
    		case 1:
    			twoDT.inOrderPrint();
    			break;
    			
    		case 2:
    			twoDT.preOrderPrint();
    			break;
    			
    		case 3:
    			twoDT.levelOrderPrint();
    			break;
			
    		case 4:
    			twoDT.postOrderPrint();
    			break;
    			
    		case 5:
    			System.out.println("Enter a rectangle bottom left (x1, y1) and top right (x2, y2) as four doubles each separated by a space");
    			String x1 = scn.next();
    			String y1 = scn.next();
    			String x2 = scn.next();
    			String y2 = scn.next();
    			twoDT.findPointsInRange( Double.parseDouble(x1), Double.parseDouble(y1), Double.parseDouble(x2), Double.parseDouble(y2));
    			System.out.println("Searching for points within ("+x1+","+y1+")"+" and ("+x2+","+y2+")");
    			System.out.println("Examined "+twoDT.getNodesTrav()+" nodes during search");
    			System.out.println("Found "+ twoDT.getList().getSize() + " crimes");
    	    	System.out.println("The list of crimes is: "+ "\n" +  twoDT.getList().toString());
    	    	try {
					PrintWriter out = new PrintWriter(new FileWriter("./PGHCrimes.KML"));
					out.println(twoDT.getList().toKML());
					out.close();
					System.out.println("The list of crimes data has been written to PGHCrimes.KML. It is viewable in Google Earth Pro");
				} catch (IOException e) {
					System.out.println("Unable to print to location specified. Please confirm path.");
				}    	    	
    	    	break;
    	    	
    		case 6: 
    			System.out.println("Enter a point to find the nearest crime. Separate with a single space");
    			String x = scn.next();
    			String y = scn.next();
    			System.out.println(twoDT.nearestNeighbour(Double.parseDouble(x), Double.parseDouble(y), null));
    			break;   
    			
    		case 7:
    			System.out.println("Thank you for exploring Pittsburgh crimes in the 1990’s");
    			break;
    		}
    	
    		System.out.println("What would you like to do?" +"\n"+ 
    				"1. Inorder" + "\n"+
    				"2. PreOrder" + "\n"+
    				"3. LevelOrder" + "\n"+
    				"4. PostOrder" + "\n"+
    				"5. Search for points within rectangle"+ "\n"+
    				"6. Search for nearest neighbour" + "\n"+
    				"7. Quit");
            entry = scn.nextInt();  
            
    		} while (entry != 7);
    
    }
}
