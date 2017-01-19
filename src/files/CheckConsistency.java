package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CheckConsistency {

	public static void main(String args[]) throws IOException{
		String filename1 = "F:\\Blogcatalog_10312\\classifiers\\(train_70percent)\\class_18\\10fold_all\\6\\train.txt";//args[0];
		String filename2 =  "\\\\tsclient\\D\\Project_DataMinning\\DataProcessd\\Blogcatalog_10312\\classifiers\\(train_70percent)\\class_18\\10fold_all\\6\\train.txt";//args[1];
		File f1 = new File(filename1);
		BufferedReader br = new BufferedReader(new FileReader(f1));
		String line = null;
		int lineNum1 = 0;
		int lineNum2 = 0;
		Set<String> set = new HashSet<String>();
		while(null!=(line=br.readLine())){
			set.add(line);
			lineNum1++;
		}
		br.close();
		boolean flag = true;
		File f2 = new File(filename2);
		BufferedReader br2 = new BufferedReader(new FileReader(f2));
		while(null!=(line=br2.readLine())){
			if(!set.contains(line)){flag = false;
			System.out.println(line);
			break;}
			lineNum2++;
		}
		br2.close();
		if(flag&&lineNum1==lineNum2){
			System.out.println("true");
		}else{

			System.out.println("false");
		}
		
	}
}
