package vector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utils.FileUtils;

public class ConcFeature {

	public static void main(String args[]) throws IOException {
		String curPath = args[0];
		int vecNum = Integer.parseInt(args[1]);
		String[] inputs = new String[vecNum];
		int[] sizes = new int[vecNum];
		int k = 0;
		for (int i = 0; i < 2 * vecNum; i += 2) {
			inputs[k] = args[2 + i];
			sizes[k] = Integer.parseInt(args[3 + i]);
			k++;
		}
		String output = args[2 + vecNum*2];
		int start = 0;
		Map<String, StringBuilder> feaMap = new HashMap<String, StringBuilder>();
		concFirstInput(feaMap, curPath + "/" + inputs[0]);
		start += sizes[0];
		for (int i = 1; i < vecNum; i++) {
			concNextInput(feaMap, curPath + "/" + inputs[i], start);
			start += sizes[i];
		}
		FileUtils.writeMap(curPath + "/" + output, feaMap);
		System.out
				.println("------------------ConcFeature done-------------------------");

	}

	private static void concFirstInput(Map<String, StringBuilder> feaMap,
			String input) throws IOException {
		File input_file = new File(input);
		BufferedReader br = new BufferedReader(new FileReader(input_file));
		String line = null;
		while (null != (line = br.readLine())) {
			String[] items = line.split("\t", 2);
			String key = items[0];
			StringBuilder value = new StringBuilder();
			value.append(items[1]);
			feaMap.put(key, value);
		}
		br.close();
	}

	private static void concNextInput(Map<String, StringBuilder> feaMap,
			String input, int start) throws IOException {
		File input_file = new File(input);
		BufferedReader br = new BufferedReader(new FileReader(input_file));
		String line = null;
		while (null != (line = br.readLine())) {
			String[] items = line.split("\t");
			String key = items[0];
			StringBuilder value = null;
			if (feaMap.containsKey(key)) {
				value = feaMap.get(key);
			} else {
				value = new StringBuilder();
			}
			for (int i = 1, size = items.length; i < size; i++) {
				String[] items2 = items[i].split(":");
				int idx = start + Integer.parseInt(items2[0]);
				value.append(idx + ":" + items2[1] + "\t");
			}
			feaMap.put(key, value);
		}
		br.close();
	}

}
