package XmlReader;

import java.io.File;

public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		File file=new File("clean.xml");
		XmlTest.readXmlFile(file);
		XmlTest.outPut();
	}
}
