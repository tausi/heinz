/**
 * Singly linked list implementation
 * 
 * @author Georgina Tolgos
 *
 */
public class ListOfCrimes {

	private Node head;
	private int size;

	/**
	 * Constructor
	 * 
	 */
	public ListOfCrimes() {

		head = null;
		size = 0;

	}
	
	/**
	 * inserts this node to the list
	 * 
	 * @param node
	 */
	public void insert(NodeData data) {
		
		Node node = new Node();
		node.setData(data);
		
		if (head == null) {
			head = node;
		}
		
		else {
			node.next = head;
			head = node;
			size++;
		}
	}
	
	
	/**
	 * 
	 * @param node
	 * @return the data from the 
	 */
	public NodeData getNodeData(NodeData node) {
		
		Node n = new Node();
		n = head;
		while(head != null) {
			if (n.equals(node)) {
				return n.data;
			}
		}
		
		//will be null if n is null. If n is null and we try to get data it will return a null pointer exception
		return null;
	}
	
	/**
	 * retrieves the size of the list
	 * 
	 * @return the size of the list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Checks if the list is empty. 
	 * 
	 * @return true if the list is empty and false if it is not
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	

	/**
	 * Returns the string representation of the list
	 * 
	 * @return the string representation for the linked list
	 */
	public String toString() {
		
		Node node = new Node();
		node = head;
		String listToString = "";
		 while (node!= null) {
			 listToString+=(node.getData().x + " " + node.getData().y + " " + node.getData().lat + " " + node.getData().lon + "\n");
			 node = node.next;
		 }
		
		 return listToString;
	}
	
	/**
	 * Returns a KML representation of the list
	 * For loading into file that will be rendered by google earth 
	 * 
	 * @return
	 */
	public String toKML(){
		
		Node node = new Node();
		node = head;
		String KMLString = "";
	
		KMLString += 	"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n"+ 
							"<kml xmlns=\"http://earth.google.com/kml/2.2\">" + "\n" +
							"<Document>"+"\n"+
							"<Style id=\"style1\">"+"\n"+
							"<IconStyle>"+"\n"+
							"<Icon>"+
							"<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>"+ "\n" +
							"</Icon>"+"\n"+
							"</IconStyle>" + "\n" +
							"</Style>"+ "\n";
		//code to add each as a placemark item
		while (node!= null) {
			KMLString+= 	"<Placemark>"+"\n"+
						     "<styleUrl>#style1</styleUrl>"+ "\n" +
						     "<Point>"+"\n" +
						     "<coordinates>"+ node.getData().lon +","+ node.getData().lat +","+ 0.000000 +"</coordinates>"+"\n"+ 						     
						     "</Point>"+"\n"+ 
						     "</Placemark>" + "\n";
			
			node = node.next;
		 }
			
		//add ending tag
			KMLString+=		"</Document>"+"\n"+
							"</kml>";
		
			
			return KMLString;
		
	}

	/**
	 * The node class contains the building block of our linked list. 
	 * Each node contains a next pointer and the data cotined in the node
	 * 
	 * @author Georgina
	 *
	 */
	private class Node {

		private Node next;
		private NodeData data;
		
		private Node() {
			next = null;
			data = null;
		}

		private Node getNext() {
			return next;
		}

		private void setNext(Node next) {
			this.next = next;
		}

		private NodeData getData() {
			return data;
		}

		private void setData(NodeData data) {
			this.data = data;
		}		

	}

}

