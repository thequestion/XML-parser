package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


import common.Constant;

import test.ParserDom;
import test.TreeViewer;

/**
 * 
 * @author YUAN
 *Last changed:5/11/2012
 *主要功能实现：文件打开，读取，展现, 验证良构性, SAX解析, 节点操作Dom4j操作（显示, 修改，添加,删除),刷新
 *待实现功能：修改记录日志
 *待解决问题：关闭Jtree时只关闭JTree，不终止程序;  最终文件展现
 *
 */

public class UserInterface extends JFrame{
	
	public static final int CHAR_PER_LINE=10;
	public static final int LINES=20;
	
	private static String filePath;
	
	private JButton refreshButton;
	private JButton  openFileButton;
	private JButton validateFileButton;
	private JButton browseFileButton;
	private JButton testSAXFileButton;
	private JTextArea showFileTextField;
	private JTextField showPathField;
	private JTextArea showResultField;
	private JTextField showStatusField;
	
	public UserInterface(){
		setTitle("XML READER v.1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Constant.WIDTH,Constant.HEIGHT);
		Container contentPane=getContentPane();
		contentPane.setLayout(new BorderLayout());
		//Buttons
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		browseFileButton=new JButton("Browse");
		browseFileButton.addActionListener(new BrowseFileActionListener());
		
		openFileButton=new JButton("Open");
		openFileButton.addActionListener(new OpenFileActionListener());
		
		
		validateFileButton=new JButton("Validate");
		validateFileButton.addActionListener(new ValidateFileActionListener());
		
		testSAXFileButton=new JButton("Test");
		testSAXFileButton.addActionListener(new TestSAXFileActionListener());
		
		refreshButton=new JButton("Refresh");
		refreshButton.addActionListener(new RefreshFileActionListener());
		
		buttonPanel.add(browseFileButton);
		buttonPanel.add(openFileButton);
		buttonPanel.add(validateFileButton);
		buttonPanel.add(testSAXFileButton);
		buttonPanel.add(refreshButton);
		
		
		JPanel textFieldPanel=new JPanel();
		textFieldPanel.setLayout(new GridLayout(2,1));
		
		showPathField=new JTextField();
		showPathField.setBackground(Color.LIGHT_GRAY);
		textFieldPanel.add(showPathField,BorderLayout.NORTH);
		
		showStatusField=new JTextField();
		showStatusField.setBackground(Color.LIGHT_GRAY);
		textFieldPanel.add(showStatusField,BorderLayout.CENTER);
		
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		contentPane.add(textFieldPanel,BorderLayout.NORTH);
		
		
		//TextField
		JPanel textPanel=new JPanel();
		textPanel.setLayout(new GridLayout(1,2));
		
		showFileTextField=new JTextArea(LINES,CHAR_PER_LINE);
		showFileTextField.setBackground(Color.WHITE);
		
		JScrollPane scrolledShowText=new JScrollPane(showFileTextField);
		scrolledShowText.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrolledShowText.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		showResultField=new JTextArea();
		showResultField.setBackground(Color.WHITE);
		
		JScrollPane scrolledResultText=new JScrollPane(showResultField);
		scrolledResultText.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrolledResultText.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		textPanel.add(showFileTextField);
		//textPanel.add(showResultField);
		contentPane.add(textPanel,BorderLayout.CENTER);
	}
	
	public static String getFilePath(){
		return filePath;
	}


	//@InnerClass
	private class BrowseFileActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFileChooser  chooseFile=new JFileChooser();
			int choice=chooseFile.showOpenDialog(null);
			if(choice==chooseFile.APPROVE_OPTION){
				chooseFile.setVisible(true);
				filePath=chooseFile.getSelectedFile().getAbsolutePath();
				showPathField.setText(filePath);
			}else if(choice==chooseFile.CANCEL_OPTION){
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}			
	}
	private class OpenFileActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				FileOperation getFile=new FileOperation();
				getFile.readFile(filePath);
				String fileText=getFile.getWholeFileText();
				showFileTextField.setText(fileText); //sadf
				}catch(IOException ex){
					showStatusField.setText(ex.getMessage());
				}
			}
	}
	private class RefreshFileActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				XMLReader parser=XMLReaderFactory.createXMLReader();
				ContentHandler handler=new TreeViewer();
				parser.setContentHandler(handler);
				parser.parse(new InputSource(filePath));
				}catch(Exception ex){
					showStatusField.setText(ex.getMessage());
				}
		}
	}


	private class TestSAXFileActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(!showStatusField.getText().equals("OK to parse now!"))
				JOptionPane.showMessageDialog(null, "You must make sure this file is valid!");
			else{
				try{
					XMLReader parser=XMLReaderFactory.createXMLReader();
					ContentHandler handler=new TreeViewer();
					parser.setContentHandler(handler);
					parser.parse(new InputSource(filePath));
				}catch(Exception ex){
					showStatusField.setText(ex.getMessage());
				}
			}
		}
	}
	private class ValidateFileActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				XMLReader parser=XMLReaderFactory.createXMLReader();
				parser.parse(new InputSource(filePath));
				JOptionPane.showMessageDialog(null, "This file is well-formed! ");
				showStatusField.setText("OK to parse now!");
			}catch(SAXException ex){
				showStatusField.setText("This file is not well-formed!");
			}catch(IOException ex){
				showStatusField.setText("Due to IO exception, the parser could not check ");
			}	
		}
	}


	public static void main(String[] args){
		UserInterface tryInterface=new UserInterface();
		tryInterface.setVisible(true);
		
	}

}

/*
private class TestDOM4JButtonActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		try{
			//getFilePath=showPathField.getText();
			ParserDom parser=new ParserDom();
			parser.read(filePath);
			//parser.ModifyXMLFile();
			parser.write("C:\\Users\\YUAN\\Desktop\\order.xml");
			showStatusField.setText("Done");
			
			FileOperation getFile=new FileOperation();
			getFile.readFile(filePath);
			String fileText=getFile.getWholeFileText();
			//System.out.println(fileText);
			showResultField.setText(fileText); 
			
		}catch(Exception ex){
			showStatusField.setText(ex.getMessage());
		}
	}
}
*/
/*
private class ModifyFileActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		try{
			//getFilePath=showPathField.getText();
			String getFile=showFileTextField.getText();
			showResultField.setText(getFile);
			FileOperation operation =new FileOperation();
			operation.writeFile(filePath, getFile);
			showStatusField.setText("Done!");
			}catch(Exception ex){
			showStatusField.setText(ex.getMessage());
			}
	}
}*/
