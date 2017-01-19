package vector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Vec2Feature {

	public static void main(String args[]) throws IOException {
		String curPath = args[0];
		String input = args[1];
		String output = args[2];
		File input_file = new File(curPath + "\\" + input);
		File output_file = new File(curPath + "\\" + output);
		
		if(!input_file.exists()){
			System.out.println(input_file.getAbsolutePath()+" is not exsit");
		}
		
		BufferedReader br = new BufferedReader(new FileReader(input_file));
		BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));
		String line = br.readLine();
		int n = Integer.parseInt(line.split("\\s+")[1]);
		while (null != (line = br.readLine())) {
			String[] items = line.split("\\s+");
			bw.write(items[0] + "\t");
			for (int i = 1; i <= n; i++) {
				bw.write(i + ":" + items[i] + "\t");
			}
			bw.write("\r\n");
		}
		br.close();
		bw.flush();
		bw.close();

		System.out.println("------------------Vec2feature done-------------------------");
		
	}
}
