/**
 * This is a class to operate files.
 */
package Main;

/**
 * @author YUAN
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
public class FileOperation{
	private String fileLine[]=new String[100];
	private String wholeFileText;
	private int fileLength=0;
	public void readFile(String filePath) throws IOException {
		try{
			BufferedReader inputObject=
					new BufferedReader(new FileReader(filePath));
			int i=0;
			String line[]=new String[100];
			String temp=inputObject.readLine();
			while(temp!=null){
				line[i]=temp;
				temp=inputObject.readLine();
				fileLine[i]=line[i];
				//System.out.println(fileLine[i]);//trace
				i++;		
			}
			fileLength=i;
			inputObject.close();
			//System.out.println(fileLine[0]);//trace
			//System.out.println(fileLine[1]);//trace
		}catch(FileNotFoundException e){
			e.getMessage();
			System.exit(0);
		}
	}
	public void readFile(File theFile) throws IOException {
		try{
			BufferedReader inputObject=
					new BufferedReader(new FileReader(theFile));
			int i=0;
			String line[]=new String[100];
			String temp=inputObject.readLine();
			while(temp!=null){
				line[i]=temp;
				temp=inputObject.readLine();
				fileLine[i]=line[i];
				//System.out.println(fileLine[i]);//trace
				i++;
			}
			fileLength=i;
			inputObject.close();
			//System.out.println(fileLine[0]);//trace
			//System.out.println(fileLine[1]);//trace
		}catch(FileNotFoundException e){
			e.getMessage();
			System.exit(0);
		}
	}
	public void writeFile(String filePath,String fileContext) throws IOException{
		PrintWriter outputStream=null;
		try{
			outputStream=
					new PrintWriter(new FileOutputStream(filePath));
			outputStream.print(fileContext);
			outputStream.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error opening the file Practice.txt.");
			System.exit(0);
		}
	}
	public String getWholeFileText(){
		try{
			String temp="";
			for(int i=0;i<getFileLength()-1;i++){
				temp+=(getFileLine(i)+"\n");
			}
			wholeFileText=temp;
			return wholeFileText;
		}catch(ArrayIndexOutOfBoundsException e1){
			e1.getMessage();
			System.exit(0);
			return null;
		}
	}
	public String getFileLine(int i){
		try{
			if(i>fileLength){
				throw new ArrayIndexOutOfBoundsException();
			}else{
			return fileLine[i];
			}
		}catch(ArrayIndexOutOfBoundsException e1){
			e1.getMessage();
			System.exit(0);
			return null;
		}
	}
	public int getFileLength(){
		return fileLength;
	}

}
