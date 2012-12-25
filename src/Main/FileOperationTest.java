/**
 * This is a test case for Class FileOperation.
 */
package Main;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

/**
 * @author YUAN
 *
 */
public class FileOperationTest extends TestCase {
	public void testCreate() throws IOException {
		final String testPath= "D:\\workspace\\JavaPracticeWorkSpace\\XMLreader\\TestFile.txt";  //file missing
		final File testFile;
		final int fileLength=6;
		FileOperation tryFile=new FileOperation();
		tryFile.readFile(testPath);
		assertEquals(fileLength,tryFile.getFileLength());
	}

}
