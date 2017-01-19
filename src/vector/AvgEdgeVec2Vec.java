package vector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import utils.FileUtils;
import utils.MathUtils;

public class AvgEdgeVec2Vec {

	public static DecimalFormat DF = new java.text.DecimalFormat("#.######");

	public static void main(String args[]) throws IOException {

		String curPath = args[0];
		String graphFile = curPath + "/" + args[1];
		String edgeVectorFile = curPath + "/" + args[2];
		String outputFile = curPath + "/" + args[3];

		Map<String, Set<String>> neighbour_map = new HashMap<>();
		Map<String, double[]> edgeVectors = new HashMap<>();
		int size = FileUtils.readEdgeVector(graphFile, edgeVectorFile, neighbour_map, edgeVectors);
		String item = null;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputFile)));
		bw.write(neighbour_map.size()+" "+size+"\r\n");
		for (Entry<String, Set<String>> entry : neighbour_map.entrySet()) {
			double[] feature = new double[size];
			int id1 = Integer.parseInt(entry.getKey());
			int sum = entry.getValue().size();
			for (String neghbour : entry.getValue()) {
				int id2 = Integer.parseInt(neghbour);
				item = id1 < id2 ? id1 + "_" + id2 : id2 + "_" + id1;
				double[] nei_vector = edgeVectors.get(item);
				if (nei_vector == null) {
					sum--;
					continue;
				}
				MathUtils.arrayAdd(nei_vector, feature);
			}
			MathUtils.arrayDivision(feature, sum);
			bw.write(id1 + " ");
			for (int i = 1; i <= size; i++) {
				bw.write(DF.format(feature[i - 1]) + " ");
			}
			bw.write("\r\n");
		}
		bw.flush();
		bw.close();

		System.out
				.println("------------------AvgEdgeVec2Vec done-------------------------");

	}
}
