/**
 * 
 */
package test;

import java.util.EmptyStackException;
import java.util.Stack;

import test.ParserDom;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


import common.Constant;

/**
 * @author YUAN
 *
 */
public class TreeViewer extends DefaultHandler{

	/**
	 * @param args
	 */
	private Stack nodes;
	public void startDocument() throws SAXException{
		nodes=new Stack();
	}	
	private TreeNode root;
	
	public void startElement(String namespaceURI,String localName, String qualifiedName, 
			Attributes atts){
		String data;
		
		if(namespaceURI.equals("")){
			data=localName;
			
			}else{
			data='{'+namespaceURI+'}'+qualifiedName;		//proof
			//data=localName;
		}
		MutableTreeNode node=new DefaultMutableTreeNode(data);

		try{
			MutableTreeNode parent=(MutableTreeNode) nodes.peek();
			parent.insert(node, parent.getChildCount());
			
		}catch(EmptyStackException ex){
			root=node;
		}
		nodes.push(node);
	}
	public void endElement(String namespaceURI, String localName,String qualifiedName){
		nodes.pop();
	}
	public void endDocument(){
		JTree tree=new JTree(root);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new GetTreeValueListener());
		JScrollPane treeView=new JScrollPane(tree);
		JFrame f=new JFrame("XML tree");
		
		f.setSize(Constant.WIDTH,Constant.HEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(treeView);
		f.pack();
		f.setAlwaysOnTop(true);
		f.setVisible(true);
		
		}
		
	
	/*
	private DefaultMutableTreeNode root, currentNode;
	
	private Stack nodes;
	public void startDocument() throws SAXException{
		nodes=new Stack();
	}
	public DefaultMutableTreeNode getRoot(){
		return root;
	}
	
	public void startElement(String namespaceURI,String localName, String qualifiedName, Attributes attrs ){
		String elementName=localName;
		if("".equals(elementName))
			elementName=qualifiedName;
		Tag tag=new Tag(elementName,attrs);
		DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(tag);
		if(currentNode==null){
			root=newNode;
		}else{
			currentNode.add(newNode);
		}
	}
	
	public void endElement(String namespaceURI, String localName,String qualifiedName){
		currentNode=(DefaultMutableTreeNode)currentNode.getParent();
	}
	
	public void characters(char buf[],int offset,int len){
		String s=new String(buf,offset,len).trim();
		((Tag)currentNode.getUserObject()).addData(s);
	}
	
	public class Tag{
		private String name;
		private String data;
		private Attributes attrs;
		
		public Tag(String theName, Attributes theAttrs){
			this.name=theName;
			this.attrs=theAttrs;
		}
		public String getName(){
			return name;
		}
		public Attributes getAttributes(){
			return attrs;
		}
		public void setData(String theData){
			this.data=theData;
		}
		public String getDate(){
			return data;
		}
		public void addData(String theData){
			if(data==null){
				setData(theData);
			}else{
				data+=theData;
			}
		}

		public String getAttributesAsString(){
			StringBuffer buf=new StringBuffer(256);
			for(int i=0;i<attrs.getLength();i++){
				buf.append(attrs.getQName(i));
				buf.append("=\"");
				buf.append(attrs.getValue(i));
				buf.append("=\"");
			}
			return buf.toString();
		}
	}
	public void endDocument(){
		JTree tree=new JTree(root);
		JScrollPane treeView=new JScrollPane(tree);
		JFrame f=new JFrame("XML tree");
		
		f.setSize(Constant.WIDTH,Constant.HEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(treeView);
		f.pack();
		f.setAlwaysOnTop(true);
		f.setVisible(true);
	}
	*/
}
