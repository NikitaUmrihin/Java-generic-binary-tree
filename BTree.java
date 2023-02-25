public class BTree<E> 
{

//	main root node of binary tree
	
	Node<E> root;

	
//	default constructor	
	public BTree()
	{
		this.root=null;
	}
	
//	full constructor
	public BTree(Node<E> root)
	{
		this.root=root;
	}
	
//	returns the root of the tree
	public Node<E> getRoot()
	{
		return root;
	}
	
//	returns a binary tree with left son as our new root
//	basically gets the tree left of the root
	public BTree<E> getLeftTree(Node<E> root)
	{
		BTree<E> leftTree = new BTree<E>(root.getLeftSon());
		return leftTree;
	}
	
//	returns a binary tree with right son as our new root
//	basically gets the tree right of the root
	public BTree<E> getRightTree(Node<E> root)
	{
		BTree<E> rightTree = new BTree<E>(root.getRightSon());
		return rightTree;
	}

//	add a node to the tree
	public  void add(E data)
	{
		if (root==null)
		{
			root = new Node<E>(data);			
		}

		else if (root.getLeftSon()==null)
		{
			root.setLeftSon(new Node<E>(data));
		}

		else if (root.getRightSon()==null)
		{
			root.setRightSon(new Node<E>(data));
		}
		else if (Math.abs( getLeftTree(root).height() -  getRightTree(root).height()) <= 1)
		{
			getLeftTree(root).add(data);
		}

		else
		{
			getRightTree(root).add(data);
		}

	}

//	returns the height of our tree
	public int height()
	{
		if (root==null)
			return 0;
		else if (root.getLeftSon()==null && root.getRightSon()==null)
		{
			return 1;
		}
		else if (root.getLeftSon()!=null && root.getRightSon()==null)
		{
			return 1+getLeftTree(root).height();
		}
		else if (root.getLeftSon()==null && root.getRightSon()!=null)
		{
			return 1+getRightTree(root).height();
		}
		else if (getLeftTree(root)!=null && getRightTree(root)!=null)
		{
			if (getRightTree(root).height() > getLeftTree(root).height() )
			{
				return 1+getRightTree(root).height();
			}
			return 1+getLeftTree(root).height();
		}
		return height();

	}


//	returns string of pre order traversal
	public String pre()
	{	
		if (root.getLeftSon()!=null)
		{
			if (root.getRightSon()!=null)
			{
				return root.getData().toString()+"\t"+getLeftTree(root).pre()+"\t"+getRightTree(this.root).pre();
			}
			return root.getData().toString()+ "\t"+getLeftTree(root).pre();
		}		
		return this.root.getData().toString();
	}


	
//	returns string of in order traversal
	public String in()
	{
		if (root.getLeftSon()!=null)
		{
			if (root.getRightSon()!=null)
			{
				return getLeftTree(root).in()+root.getData().toString()+"\t"+getRightTree(root).in();
			}
			return getLeftTree(root).in()+root.getData().toString()+"\t";
		}
		else return this.root.getData().toString()+"\t";
	}


	
//	returns string of post order traversal
	public String post()
	{
		if (root.getLeftSon()!=null)
		{
			if (root.getRightSon()!=null)
			{
			 return getLeftTree(root).post()+getRightTree(root).post()+root.getData().toString()+"\t";
			}
			return getLeftTree(root).post()+root.getData().toString()+"\t";
		}
		if (root.getRightSon()!=null)
		{
		 return getLeftTree(root).post()+getRightTree(root).post()+root.getData().toString()+"\t";
		}
		return root.getData().toString()+"\t";
		
	}

//	returns the number of nodes in our tree
	public int size()
	{
		if (root==null)
			return 0;
		if (root.getLeftSon()==null && root.getRightSon()==null)
			return 1;
		if (root.getLeftSon()!=null && root.getRightSon()==null)
			return 1+getLeftTree(root).size();
		if (root.getLeftSon()==null && root.getRightSon()!=null)
			return 1+getRightTree(root).size();
		return 1+getLeftTree(root).size()+getRightTree(root).size();
	}


	
//	method gets length, and finds a path with length as number of nodes from root to leaf
	public String path(int length)
	{
		if (length>0 && length<=height())
		{
			if (getLeftTree(root).height()==length-1)
				return root.getData().toString()+", "+getLeftTree(root).path(length-1);
			if ( getRightTree( getLeftTree(root).getRoot() ).getRoot() !=null) 
					if( getRightTree(getLeftTree(root).getRoot()).height() == length-2)
				return root.getData().toString()+", "+getLeftTree(root).path(length-1);
			if (getRightTree(root).height()==length-1)
				return root.getData().toString()+", "+getRightTree(root).path(length-1);
		}
		return "";
 	}


}

