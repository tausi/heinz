import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 2-d Data structure. This class stores one crime record per node. Each record
 * contains an X, Y coordinate pairs and latitude and longitude coordinates.
 *
 * @Author Georgina Tolgos
 *
 * @Date 02-14-2017
 */
public class TwoDTree {

	private TwoDTreeNode root;
	private int size = 0;
	private int nodesTrav = 0;
	private ListOfCrimes list;

	/**
	 * Constructs the 2-d tree from the file from the path provided.
	 *
	 * Pre-condition: The filepath contains the path to a .csv file containing X,Y
	 * and Lat, Long coordinates Post-conditions: The 2-d tree is constructed
	 *
	 * @param filepath
	 *            path to a .csv file with X, Y, Latitude, and Longitude coordinates
	 */
	public TwoDTree(String filepath) {

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;

		try {

			fileReader = new FileReader(filepath);
			bufferedReader = new BufferedReader(fileReader);

			// current line in file
			String currentLine;
			// the data after parsing the current line
			NodeData nodeData;

			while ((currentLine = bufferedReader.readLine()) != null) {
				nodeData = parseLine(currentLine);
				if (nodeData != null) {
					addToTree(nodeData);
					size++;
				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bufferedReader != null)
					bufferedReader.close();

				if (fileReader != null)
					fileReader.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

	/**
	 * Adds a new node to the tree. The root is the first node in the tree
	 * 
	 * Pre-condition: The tree has been created
	 * 
	 * Post condition: A new node is added to the tree
	 * 
	 * @param data the data that will be contained in the newly created node
	 */
	public void addToTree(NodeData data) {
		root = addToTree(root, data);
	}

	private TwoDTreeNode addToTree(TwoDTreeNode node, NodeData data) {
		// if the root is null, this node becomes the root
		if (node == null) {
			node = new TwoDTreeNode(data, null, null);

		}
		// otherwise add to the tree
		else {
			if (node.getRight() == null) {
				node.setRight(addToTree(node.getRight(), data));
			} else {
				node.setLeft(addToTree(node.getLeft(), data));
			}

		}

		return node;
	}

	/**
	 * This method parses the line from the file containing the crimes. Each line is a point in space
	 * The first two entries are the x, and y coordinates respectively
	 * The final two are the latitude and longitude coordinates
	 *  
	 *  Pre-condition: the file exists 
	 *  Post-condition: N/A
	 *  
	 * @param str the string containing crime data for one point in space
	 */
	private NodeData parseLine(String str) {

		// point coordinates
		double x, y, lat, lon;

		// node Data. Object that holds the nodes data
		NodeData nData = null;
		// add the items to the string array as one point
		String[] strArray = str.split(",");

		// if i is not a number then continue
		try {
			x = Double.parseDouble(strArray[0]);
			y = Double.parseDouble(strArray[1]);
			lat = Double.parseDouble(strArray[strArray.length - 2]);
			lon = Double.parseDouble(strArray[strArray.length - 1]);

			// new node data
			nData = new NodeData(x, y, lat, lon);

		} catch (NumberFormatException e) {
			return null;
		}

		return nData;
	}

	/**
	 * Pre-order traversal of the tree
	 * 
	 * Pre-condition: The String filepath contains the path to a file formatted in the exact same way as CrimeLatLonXY.csv
	 * 
	 * Post-condition: The 2-d tree is constructed and may be printed or queried
	 */
	public void preOrderPrint() {

		preOrderPrint(root);
	}

	/**
	 * Helper for the pre-order print above. Takes the root and recursively goes through the nodes in the tree to print them
	 * root -> left -> right
	 * @param node the node as the root
	 */
	private void preOrderPrint(TwoDTreeNode node) {

		if (node != null) {
			System.out.println(
					node.getData().x + " " + node.getData().y + " " + node.getData().lat + " " + node.getData().lon);
			preOrderPrint(node.getLeft());
			preOrderPrint(node.getRight());
		}
	}

	/**
	 * in-order traversal of the tree, the tree is traversed in order, 
	 *  left -> root -> right
	 *  Pre-condition: The 2-d tree has been constructed.
	 *  
	 *  Post-condition: The 2-d tree is displayed with an in-order traversal. 
	 *  
	 */
	public void inOrderPrint() {
		inOrderPrint(root);
	}

	/**
	 * 
	 * Helper for the in-order print above. Takes the root and recursively goes through the nodes in the tree to print them
	 * left -> root -> right
	 * @param node the node as the root
	 */
	private void inOrderPrint(TwoDTreeNode node) {

		if (node != null) {
			inOrderPrint(node.getLeft());
			System.out.println(
					node.getData().x + " " + node.getData().y + " " + node.getData().lat + " " + node.getData().lon);
			inOrderPrint(node.getRight());
		}
	}

	/**
	 * Post order traversal of the tree.
	 * 
	 * 
	 */
	public void postOrderPrint() {

		postOrderPrint(root);
	}

	/**
	 * pre-condition: The 2-d tree has been constructed.
	 * post-condition: The 2-d tree is displayed with a post-order traversal. 
	 * @param node the node where the traversal begins, which is the root
	 */
	private void postOrderPrint(TwoDTreeNode node) {

		if (node != null) {
			postOrderPrint(node.getLeft());
			postOrderPrint(node.getRight());
			System.out.println(
					node.getData().x + " " + node.getData().y + " " + node.getData().lat + " " + node.getData().lon);
		}
	}

	/**
	 * Level-order traversal of the tree uses a queue with an array as the
	 * underlying ADT
	 * pre-condition: The 2-d tree has been constructed.
	 * post-condition: The 2-d tree is displayed with a level-order traversal. 
	 * 
	 */
	public void levelOrderPrint() {

		TwoDTreeNode node = root;
		Queue q = new Queue(getSize());
		
		while (node != null) {
			q.EnQueue(node);
			q.EnQueue(node.getLeft());
			q.EnQueue(node.getRight());
			node = node.getLeft();
			
		}
		
		//get the depth of the tree
		int depth = treeDepth(node);
		
		System.out.println("Tree depth is: "+ depth);
		
		for(int i = 0; i< getSize(); i++) {
			TwoDTreeNode n = q.DeQueue();
			NodeData data =  n.getData();			
			
			System.out.println(data.x + ", "+ data.y + "," + data.lat + "," + data.lon);
		}

}

	

	/**
	 * Returns the depth of the tree relative to the node passed
	 * 
	 * @param node the node used as a reference point
	 * @return the depth of the tree from this node
	 */
	private int treeDepth(TwoDTreeNode node) {
		int left = node.getLeftmostDepth(node);
		int right = node.getRightmostDepth(node);
		
		System.out.println("Leftmost depth is: "+ left);
		System.out.println("Rightmost depth is: "+ right);
		
		if(left > right){
			return left;
		}
		else {
			return right;
		}
	}

	
	/**
	 * Returns the points contained within the rectangle in the given coordinates
	 * which represent the crimes contained in the coordinates
	 * 
	 * @return
	 */
	public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2) {
		
		list = new ListOfCrimes();
		inOrderAdd(root, x1, y1, x2, y2);

		return list;

	}

	/**
	 * Adds the nodes in the area to the linked list
	 * 
	 * @param node
	 *            the node in the tree to be added
	 * @param x1
	 *            the left bottom x coordinate of the rectangle
	 * @param y1
	 *            the left bottom y coordinate of the rectangle
	 * @param x2
	 *            the right top x coordinate of the rectangle
	 * @param y2
	 *            the right top y coordinate of the rectangle
	 */
	private void inOrderAdd(TwoDTreeNode node, double x1, double y1, double x2, double y2) {

		nodesTrav++;
		if (node != null) {
			
			inOrderAdd(node.getLeft(), x1, y1, x2, y2);
			if (node.getData().x >= x1 && node.getData().x <= x2 && node.getData().y >= y1 && node.getData().y <= y2) {
				list.insert(node.getData());				
			}
			inOrderAdd(node.getRight(), x1, y1, x2, y2);
		}

	}

	/**
	 * Finds the nearest crime relative to the given point. 
	 * The user enters the coordinates and an empty node to store the result
	 * 
	 * Pre-condition: the 2-d tree has been constructed. The (x1,y1) pair represents a point in space near Pittsburgh and in the state plane coordinate system. 
	 * 			The parameter “nearest” holds a reference to the result node (with no data)
	 * post-condition: the distance in feet to the nearest node is returned. The reference parameter now has the nearest neighbor's data
	 * 
	 * @param x1 the x coordinate of the point
	 * @param y1 the y coordinate of the point
	 * @param nearest the nearest crime to the point entered
	 * @return the distance to the nearest crime from the point entered
	 */
	public double nearestNeighbour (double x1, double y1, TwoDTreeNode nearest) {
		
		return nearestNeighbor(x1, y1, root);
		
	}
	/**
	 * The nearest neighbour helper method. 
	 * @param x1
	 *            x coordinate of point in space
	 * @param y1
	 *            y coordinate of point in space
	 * @param nearest
	 *            reference to the result node with no data, that is the closest to
	 *            the point in space (x1, y1)
	 * @return distance to the nearest neighbor
	 */
	private double nearestNeighbor(double x1, double y1, TwoDTreeNode nearest) {
		
		double nearestNode = 0.000;		
		
		if(nearest != null) {
			//get the distance from the root to the point
			double distance1 = distanceCalc(nearest.getData().x, nearest.getData().y, x1, y1); 
			//get the distance from the root.left to the point
			double distance2 = distanceCalc(nearest.getLeft().getData().x, nearest.getLeft().getData().y, x1, y1); 
			//get the distance from the root.right to the point
			double distance3 = distanceCalc(nearest.getRight().getData().x, nearest.getRight().getData().y, x1, y1);
			//compare and either recursively go right or left depending on the shorter distance
			
			if(distance1 > distance2) {
				//go left
				nearest = nearest.getLeft();
				nearestNode = distance2;
				nearestNeighbor(x1, y1, nearest);
			}
			else if(distance1 > distance3 ) {
				//go right
				nearest = nearest.getRight();
				nearestNode = distance3;
				nearestNeighbor(x1, y1, nearest);
			}
			
			else {
				//the node is the closest
				return distance1;
			}
		}
		
		return nearestNode;

	}
	
	/**
	 * Using Pythagorea's theorem to get the distance between two points in space
	 * 
	 * Pre-condition: N/A
	 * Post-conditino: N/A
	 * 
	 * @param x1 x coordinate of the first point
	 * @param y1 y coordinate of the second point
	 * @param x2 x coordinate of the second point
	 * @param y2 y coordinate of the second point
	 * @return the distance between the two points
	 * 
	 */
	private double distanceCalc(double x1, double y1, double x2, double y2) {
		
		double x = Math.pow((x2 - x1), 2);
		double y = Math.pow((y2 - y1), 2);
		double distance = Math.sqrt(y+x);
		
		return distance;		
		
	}

	/**
	 * Gets the number of nodes in the tree
	 * 
	 * @return the number of nodes in the tree
	 */

	public int getSize() {
		
		System.out.println("Size is: "+ size);
		return size;
	}
	
	/**
	 * Gets the list of the crimes
	 * 
	 * @return the list
	 */
	public ListOfCrimes getList() {
		return list;
	}

	/**
	 * 
	 * Gets the number of nodes traversed in a list
	 * @return the nodesTrav
	 */
	public int getNodesTrav() {
		return nodesTrav;
	}


};
