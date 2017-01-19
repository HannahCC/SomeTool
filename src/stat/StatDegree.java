package stat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class StatDegree {

		public static void main(String args[]) throws IOException {
//			statEdgeNum("D:\\Project_DataMinning\\Data\\blogcatalog\\blogcatalog_network_user.txt");
//			statEdgeNum("D:\\Project_DataMinning\\Data\\flickr\\flickr_network_user.txt");
//			statEdgeNum("D:\\Project_DataMinning\\Data\\youtube\\youtube_network_user.txt");
	//
//			statEdgeNum("D:\\Project_DataMinning\\Data\\youtube\\youtubeSmall_network_user.txt");
	//
//			statEdgeNum("D:\\Project_DataMinning\\Data\\wiki_7\\wiki_7.edgelist");
//			statEdgeNum("D:\\Project_DataMinning\\Data\\dblp_4\\dblp_4.edgelist");
			statEdgeNum("D:\\Project_DataMinning\\Data\\dblp_3\\dblp_3.edgelist");
//			statEdgeNum("D:\\Project_DataMinning\\Data\\CA-GrQc\\CA-GrQc.txt");
//			statEdgeNum("D:\\Project_DataMinning\\Data\\Les Miserables\\jean.edgelist");

		}

		private static void statEdgeNum(String graphFile) throws IOException {
			Map<String, Integer> degree = new HashMap<String, Integer>();
			File f = new File(graphFile);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			int outlier = 0;
			int edges = 0;
			while (null != (line = br.readLine())) {
				String[] items = line.split("\\s+");
				if (items.length < 2) {
					outlier++;
					continue;
				}
				edges++;
				if (degree.containsKey(items[0])) {
					degree.put(items[0], degree.get(items[0]) + 1);
				} else {
					degree.put(items[0], 1);
				}

//				if (degree.containsKey(items[1])) {
//					degree.put(items[1], degree.get(items[1]) + 1);
//				} else {
//					degree.put(items[1], 1);
//				}

			}
			br.close();
			edges /= 2;
			int maxEdge = 0, minEdge = Integer.MAX_VALUE;
			double avgEdge = 0.0;
			for (int size : degree.values()) {
				if (maxEdge < size) {
					maxEdge = size;
				}
				if (minEdge > size) {
					minEdge = size;
				}
				avgEdge += size;
			}
			avgEdge /= degree.size();
			System.out.println(degree.size() + "\t" + edges + "\t" + avgEdge + "\t"
					+ maxEdge + "\t" + minEdge + "\t" + outlier);

		}
	}
