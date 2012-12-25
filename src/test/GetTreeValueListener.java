/**
 * 
 */
package test;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.dom4j.Element;

import Main.UserInterface;

/**
 * @author YUAN
 *
 */
public class GetTreeValueListener implements TreeSelectionListener {

	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	
	private static String value;
	private String path;
	private ParserDom dom;
	
	@Override
	public void valueChanged(TreeSelectionEvent e)  {
		// TODO Auto-generated method stub
		JTree tree=(JTree)e.getSource();
		DefaultMutableTreeNode selectionNode=
		(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		TreeNode[] list=new TreeNode[7];
		list=selectionNode.getPath();
		
		String nodeName=selectionNode.toString();
		StringBuffer result=new StringBuffer();
		try{for(int i=0; i<7;i++)
		{
			if (!list[i].equals("")){
				result.append("/"+list[i].toString());
			}else{
				continue;
			}
		}}catch(ArrayIndexOutOfBoundsException  ex){
			System.err.print(ex);
		}
		
		String a=result.toString();
		path=a;
		
		dom=new ParserDom();
		dom.read(UserInterface.getFilePath());
		
		
		Object[] options = { "check", "modify","add","delete","cancle" };
		int selection=
		JOptionPane.showOptionDialog(null, "Select your option.", "", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		null, options, null); 
		switch(selection){
		case 0:
			check(); break;
		case 1:
			modify();	break;
		case 2:
			add(); break;
		case 3:
			delete(); break;
		default:
			break;
		}
		try {
			dom.write(UserInterface.getFilePath());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
	
	private void check(){
		value=dom.search(path);
		JOptionPane.showMessageDialog(null, value);
	}
	
	private void modify(){
		String message=JOptionPane.showInputDialog("Please enter the value: ");
		dom.modify(path, message);
	}
	
	private void add(){
		String name=JOptionPane.showInputDialog("Please enter the name of the node: ");
		String value=JOptionPane.showInputDialog("Please enter the value of the node: ");
		Element parent=dom.getSelectedElement(path);
		if(parent!=null){
			dom.add(parent, name, value);
		}else{
			JOptionPane.showMessageDialog(null, "Can not find a parent.");
		}
	}
	
	private void delete(){
		if(dom.delete(path)){
			JOptionPane.showMessageDialog(null, "Done");
		}else{
			JOptionPane.showMessageDialog(null, "Try again!");
		}
			
		
	}
	
	public static String getValue(){
		return value;
	}

	
	

}
