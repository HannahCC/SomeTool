package utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import temp.Node;


public class FileUtils {

	static List<BufferedWriter> bws = null;

	public static void readGraph(String graphFile, Node[] nodes,
			Set<Integer> idset) throws IOException {
		File f = new File(graphFile);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		while (null != (line = br.readLine())) {
			String[] items = line.split("\\s+");
			int idx1 = Integer.parseInt(items[0]);
			int idx2 = Integer.parseInt(items[1]);
			if (idset.contains(idx1 - 1) && idset.contains(idx2 - 1)) {
				nodes[idx1 - 1].addAdjacent(idx2 - 1);
			}
		}
		br.close();
	}

	public static void readLine(String filename, Set<Integer> idset)
			throws IOException {

		File f = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		while (null != (line = br.readLine())) {
			String[] items = line.split("\\s+");
			int idx1 = Integer.parseInt(items[0]);
			idset.add(idx1 - 1);
		}
		br.close();
	}

	public static void writeMap(String filename, Map<String, StringBuilder> map)
			throws IOException {

		File output_file = new File(filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));
		for (Entry<String, StringBuilder> entry : map.entrySet()) {
			bw.write(entry.getKey() + "\t" + entry.getValue().toString().trim()
					+ "\r\n");
		}
		bw.flush();
		bw.close();

	}

	public static int readVector(File vector_file,
			HashMap<String, double[]> vector_map) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new FileReader(vector_file));
		String line = br.readLine();
		int n = Integer.parseInt(line.split("\\s+")[1]);
		while (null != (line = br.readLine())) {
			double[] vector = new double[n];
			String[] items = line.split("\\s+");
			String key = items[0];
			for (int i = 1; i <= n; i++) {
				vector[i - 1] = Double.parseDouble(items[i]);
			}
			vector_map.put(key, vector);
		}
		br.close();
		return n;
	}

	public static void readGraph(String graphFile,
			Map<String, Set<String>> neighbour_map) throws IOException {
		File file = new File(graphFile);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while (null != (line = br.readLine())) {
			String[] items = line.split("\\s+");
			String id1 = items[0];
			String id2 = items[1];
			if (neighbour_map.containsKey(id1)) {
				Set<String> neighbours = neighbour_map.get(id1);
				neighbours.add(id2);
			} else {
				Set<String> neighbours = new HashSet<>();
				neighbours.add(id2);
				neighbour_map.put(id1, neighbours);
			}
		}
		br.close();
	}

	public static int readEdgeVector(String graphFile, String edgeVectorFile,
			Map<String, Set<String>> neighbour_map,
			Map<String, double[]> edgeVectors) throws IOException {
		File file1 = new File(graphFile);
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		File file2 = new File(edgeVectorFile);
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		String line = null, item = null;
		int size = 0;
		while (null != (line = br1.readLine())) {
			String[] items = line.split("\\s+");

			String id1str = items[0];
			String id2str = items[1];
			if (neighbour_map.containsKey(id1str)) {
				Set<String> neighbours = neighbour_map.get(id1str);
				neighbours.add(id2str);
			} else {
				Set<String> neighbours = new HashSet<>();
				neighbours.add(id2str);
				neighbour_map.put(id1str, neighbours);
			}

			int id1 = Integer.parseInt(id1str);
			int id2 = Integer.parseInt(id2str);
			item = id1 < id2 ? id1 + "_" + id2 : id2 + "_" + id1;
			line = br2.readLine();
			if (edgeVectors.containsKey(item)) {
				continue; // 重复边
			} else {
				items = line.split("\\s+");
				size = items.length;
				double[] vector = new double[size];
				for (int i = 0; i < size; i++) {
					vector[i] = Double.parseDouble(items[i]);
				}
				edgeVectors.put(item, vector);
			}
		}
		br1.close();
		br2.close();
		return size;
	}

	public static void writeMap(String filename,
			Map<String, StringBuilder> map, int size) throws IOException {
		File output_file = new File(filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));
		bw.write(map.size() + " " + size + "\r\n");
		for (Entry<String, StringBuilder> entry : map.entrySet()) {
			bw.write(entry.getKey() + " " + entry.getValue().toString().trim()
					+ "\r\n");
		}
		bw.flush();
		bw.close();

	}
}
