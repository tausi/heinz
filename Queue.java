/**
 * Data structure to store tree nodes for the level order traversal of the tree
 * 
 * It uses an array, using two pointers to keep track of the first and last items in the queue.
 * 
 * @author Georgina Tolgos
 *
 */
public class Queue {
	
	TwoDTreeNode[] myTree; 
	int front;
	int rear;
	int treeItems = 0;

	public Queue(int size) {
		myTree = new TwoDTreeNode[size];
		front = 0;
		rear = 0;
		treeItems = 0;
	}
	
	/**
	 * Adds a node to the queue
	 * @param node
	 */
	public void EnQueue(TwoDTreeNode node) {
		
		if(treeItems == 0) { 
			front = 0;
			rear = 0;
		}
		else {
			rear = (rear + 1) % myTree.length;
			myTree[rear] = node;
			treeItems++;
		}		 

		
	}
	
	/**
	 * Removes the foremost node in the queue
	 * @return
	 */
	public TwoDTreeNode DeQueue() {
		
		TwoDTreeNode node = myTree[front];
	    front = (front + 1) % myTree.length;
	    treeItems--;	    
	    return node;

	}
	

}
