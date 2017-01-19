package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Pretraining {
	/*
	 * 1.1.1:MY,NP;MY,MB 1.1.2:MY,ME;ME,MB 1.1.3:MY
	 * 1.1.4:MY,ME;MY,CL;MY,GE;MY,MC;MY,MB 1.1.5:MY,MB,ME 1.1.6:ME,MY
	 * 1.1.7:MY,CV;MY,MB,ME 1.1.8:SN,MY 1.1.9:MB 1.1.10:MY,GG 1.1.11:MY
	 */
	public static void main(String[] args) throws IOException {
		File f = new File("/home/zps/dataset/sgb_jean/jean.dat");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		Map<String, String> func = new HashMap<>();
		Map<String, Set<String>> edges = new HashMap<String, Set<String>>();
		while (null != (line = br.readLine())) {
			if (line.startsWith("*"))
				continue;
			if (line.equals(""))
				break;
			edges.put(line.split(" ")[0], new HashSet<String>());
			func.put(line.split(" ")[0], func.size()+"");
		}
		while (null != (line = br.readLine())) {
			String[] tmps = line.split(":");
			if(tmps.length<2)continue;
			String[] items = tmps[1].split(";");
			for (String item : items) {
				String[] nodes = item.split(",");
				if (nodes.length < 2)
					continue;
				setEdge(edges, nodes);
			}
		}
		br.close();
		
		
		File f2 = new File("/home/zps/dataset/sgb_jean/jean.edgelist");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
		String top = null;
		for(Entry<String, Set<String>> entry : edges.entrySet()){
			top = entry.getKey();
			if(entry.getValue().size()==0){
				System.out.println(top);
			}
			for(String node : entry.getValue()){
				bw.write(func.get(top)+"\t"+func.get(node)+"\r\n");
			}
		}
		bw.flush();
		bw.close();
		

		File f3 = new File("/home/zps/dataset/sgb_jean/jean.map");
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(f3));
		for(Entry<String, String> entry : func.entrySet()){
				bw2.write(entry.getKey()+"\t"+entry.getValue()+"\r\n");
		}
		bw2.flush();
		bw2.close();
	}

	private static void setEdge(Map<String, Set<String>> edges, String[] nodes) {
		int l = nodes.length;
		if (l == 2) {
			edges.get(nodes[0]).add(nodes[1]);
			edges.get(nodes[1]).add(nodes[0]);
		} else {
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < l; j++) {
					if (i == j)
						continue;
					edges.get(nodes[i]).add(nodes[j]);
				}
			}
		}
	}
}
