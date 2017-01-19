package vector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utils.FileUtils;

public class ConcVector {

	public static void main(String args[]) throws IOException {
		/*String curPath = args[0];
		int vecNum = Integer.parseInt(args[1]);
		String[] inputs = new String[vecNum];
		for (int i = 0; i < vecNum; i++) {
			inputs[i] = args[2 + i];
		}
		String output = args[2 + vecNum];*/
		String curPath = "/home/zps/experiment";
		String[] inputs = {
				"n2v_embeddings/jean_friedge1_n5d16i10b0f1_con_n2v_r10l10p2q1_m0n5w10d16i5.emb",
				"clusters/jean_friedge1_n5d16i10b0f1_cenAvg.emb",
				"clusters/jean_friedge1_n5d16i10b0f1_conAvg.emb"};
		String output = "n2v_embeddings/jean_friedge1_n5d16i10b0f1_con_n2v_r10l10p2q1_m0n5w10d16i5_concFF.emb";
		
		Map<String, StringBuilder> feaMap = new HashMap<String, StringBuilder>();
		for (String input : inputs) {
			File input_file = new File(curPath + "/" + input);
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			String line = br.readLine();
			while (null != (line = br.readLine())) {
				String[] items = line.split("\\s+", 2);
				String key = items[0];
				StringBuilder value = null;
				if (feaMap.containsKey(key)) {
					value = feaMap.get(key);
				} else {
					value = new StringBuilder();
					feaMap.put(key, value);
				}
				value.append(items[1]);
			}
			br.close();
		}
		FileUtils.writeMap(curPath + "/" + output, feaMap);
		System.out
				.println("------------------ConcVec2feature done-------------------------");

	}
}
