package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Temp {

	public static void main(String[] args) throws IOException{
		//String input = "/home/zps/experiment/n2v_walks/blogcatalog_friedge_m0n5d128i10f1_n2v_r10l80p0.25q0.25.walks";
		//String output = "/home/zps/experiment/n2v_walks/blogcatalog_friedge_m0n5d128i10f1_n2v_r10l80p0.25q0.25.edges";
		String input = "/home/zps/experiment/n2v_walks/blogcatalog_n2v_r10l80p0.25q0.25.walks";
		String output = "/home/zps/experiment/n2v_walks/blogcatalog_n2v_r10l80p0.25q0.25.edges";
		
		File file = new File(input);
		BufferedReader brBufferedReader = new BufferedReader(new FileReader(file));
		File file2 = new File(output);
		BufferedWriter bwWriter = new BufferedWriter(new FileWriter(file2));
		String line = null;
		StringBuilder sBuilder = new StringBuilder();
		while(null!=(line = brBufferedReader.readLine())){
			sBuilder = new StringBuilder();
			String[] items = line.split("\\s+");
			for(int i=1,size =items.length;i<size;i++){
				int id0 = Integer.parseInt(items[i-1]);
				int id1 = Integer.parseInt(items[i]);
				String item = id0<id1?items[i-1]+"_"+items[i]+" ":items[i]+"_"+items[i-1]+" ";
				sBuilder.append(item);
			}
			bwWriter.write(sBuilder.toString()+"\r\n");
		}
		bwWriter.flush();
		bwWriter.close();
		brBufferedReader.close();
	}
}
