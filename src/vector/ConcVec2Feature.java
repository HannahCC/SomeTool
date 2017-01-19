package vector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utils.FileUtils;

public class ConcVec2Feature {

	public static void main(String args[]) throws IOException {
		String curPath = args[0];
		int vecNum = Integer.parseInt(args[1]);
		String[] inputs = new String[vecNum];
		for(int i=0;i<vecNum;i++){
			inputs[i] = args[2+i];
		}
		String output = args[2+vecNum];
		int start = 0;
		Map<String, StringBuilder> feaMap = new HashMap<String, StringBuilder>();
		for(String input:inputs){
			File input_file = new File(curPath + "/" + input);
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			String line = br.readLine();
			int n = Integer.parseInt(line.split("\\s+")[1]);
			while (null != (line = br.readLine())) {
				String[] items = line.split("\\s+");
				String key = items[0];
				StringBuilder value = null;
				if(feaMap.containsKey(key)){
					value = feaMap.get(key);
				}else{
					value = new StringBuilder();
				}
				for (int i = 1; i <= n; i++) {
					value.append((start+i)+":"+items[i]+"\t");
				}
				feaMap.put(key, value);
			}
			br.close();
			start+=n;
		}
		FileUtils.writeMap(curPath + "/" + output, feaMap);
		System.out.println("------------------ConcVec2feature done-------------------------");
		
	}
}
