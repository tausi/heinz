/**
 * Stores the node data for the node in the twoDTree node
 *
 *Pre-condition: A node TwoDTreeNode object exists 
 *Post-conditino: a new node data object with the data below is created
 */
public class NodeData {

	protected double x;
	protected double y;
	protected String time;
	protected String street;
	protected String offence;
	protected String date;
	protected String tract;
	protected double lat;
	protected double lon;

	/**
	 * Constructor
	 *
	 * @param x
	 *            the x coordinate for the point
	 * @param y
	 *            the y coordinate of the point
	 * @param lat
	 *            the latitude of the point
	 * @param lon
	 *            the longitude of the point
	 */
	public NodeData(double x, double y, double lat, double lon) {
		this.x = x;
		this.y = y;
		this.lat = lat;
		this.lon = lon;

	}

}