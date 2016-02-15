package poc;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FirstLevelDirectories {

	public static void main(String[] args) {
		File file = new File("D:\\vishal");
		
		System.out.println(file.listFiles());
		
		/*String[] directories = file.list(new FilenameFilter() {
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		System.out.println(Arrays.toString(directories));*/
	}
}
