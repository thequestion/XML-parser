/**
 * 
 */
package test;


import java.io.File;
import java.io.FileWriter;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.ranges.DocumentRange;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import common.Constant;

/**
 * @author YUAN
 *
 */
public class ParserDom {
	Document document;
	private Stack nodes;
	DefaultMutableTreeNode root;
		
	public void read(String filePath) {
		try{
			SAXReader reader=new SAXReader();
			document=reader.read(new InputSource(filePath));
		}catch( DocumentException ex){
			ex.printStackTrace();
		}		
	}
	public Element getRootElement(){
		return document.getRootElement();
	}
	public String getRootNode(){
		return getRootElement().getName();
	}
	
	public void add(Element parent,String elementName, String elementValue){
		Element element=parent;
		Element child=element.addElement(elementName);
		child.setText(elementValue);
	}
	
	public void modify(String name,String value){
		List list=document.selectNodes(name);
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Element element=(Element)iterator.next();
			element.setText(value);
		}
	}
	
	public boolean delete(String name){
		List list=document.selectNodes(name);
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Element element=(Element)iterator.next();
			Element parent=element.getParent();
			parent.remove(element);
			return true;
		}
		return false;
	}
	
	public void write(String fileName)throws Exception{
			XMLWriter writer=new XMLWriter(new FileWriter(new File(fileName)));
			writer.write(document);
			writer.close();
	}
	
	public void create(){
		root=new DefaultMutableTreeNode(getRootNode());
		for(Iterator i=getRootElement().elementIterator();i.hasNext();){
			String data=((Element)i.next()).getName().toString();
			MutableTreeNode grandparent=new DefaultMutableTreeNode(data);
			MutableTreeNode j=new DefaultMutableTreeNode("1");
			grandparent.insert(j, grandparent.getChildCount());
			root.add(grandparent);
			}
	}
	public String search(String name){
		String result="";
		List list=document.selectNodes(name);
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Element element=(Element)iterator.next();
			result=element.getTextTrim();
		}
		return result;
	}
	
	public Element getSelectedElement(String name){
		List list=document.selectNodes(name);
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Element result=(Element)iterator.next();	
			return result;
		}
		return null;
	
	}

		
	public void creatTree(){
		create();
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
	
	/*nodes=new Stack();
	for(Iterator i=getRootElement().elementIterator();i.hasNext();){
		String data=((Element)i.next()).getName().toString();
		MutableTreeNode node=new DefaultMutableTreeNode(data);
		try{
			MutableTreeNode parent=(MutableTreeNode) nodes.peek();
			parent.insert(node, parent.getChildCount());
		}catch(EmptyStackException ex){
			System.err.print("empty");
		}
		nodes.push(node);
		nodes.pop();
	}*/
	
	
	
	
	/*
	public static void main(String[]args) throws Exception{
		ParserDom dom=new ParserDom();
		String filePath="D:\\workspace\\JavaPracticeWorkSpace\\XMLreader\\test.xml";
		dom.read(filePath);
		String b=dom.search("/Orders/Order/Customer/Address/City");
		System.out.print(b);
		dom.ModifyXMLFile("/Orders/Order/Customer/Address/City", "Beijing");
		String a=dom.search("/Orders/Order/Customer/Address/City");
		System.out.print(a);
		String c=dom.search("/Orders/Order/Customer/Address/City");
		System.out.println(c);
		dom.write(filePath);
				
		Element parent=dom.getSelectedElement("/Orders/Order/Customer/Address/City");
		if(parent!=null){
			dom.add(parent, "County", "Chongming");
		}else{
			System.err.print(true);
		}
		String d=dom.search("/Orders/Order/Customer/Address/City/County");
		System.out.println(d);
		dom.delete("/Orders/Order/Customer/Address/State/County2");
		dom.write(filePath);
	}
	*/
}
