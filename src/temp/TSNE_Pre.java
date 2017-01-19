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
import java.util.Map.Entry;
import java.util.Set;

public class TSNE_Pre {
	static String rootpath = "/home/zps/some-tool/tsne_python/";

	public static void main(String[] args) throws IOException {
		Map<String, String> labelMap = new HashMap<String, String>();
		getLabel(labelMap, "blogcatalog_labels.txt","9", "20", "23");
		getEmb(labelMap, "blogcatalog_n5s10d100v3");
		getEmb(labelMap, "blogcatalog_dw_r40l40_m0n5w10d100i1");
		getEmb(labelMap, "blogcatalog_n2v_r40l40p0.25q0.25_m0n5w10d100i1");
		getEmb(labelMap,
				"blogcatalog_friedge1_n5d100i10b0f1_con_n2v_r40l40p2q1_m0n5w10d100i1_concFF");
	}

	private static void getEmb(Map<String, String> labelMap, String embFile) throws IOException {
		Map<String, String> embMap = new HashMap<String, String>();
		File f = new File(rootpath + embFile + ".feature");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String line;
		StringBuilder sbBuilder = new StringBuilder();
		while (null != (line = br.readLine())) {
			String[] items = line.split("\t");
			if(labelMap.containsKey(items[0])){
				for(int i=1;i<items.length;i++){
					sbBuilder.append(items[i].split(":")[1]+"   ");
				}
				embMap.put(items[0], sbBuilder.toString());
				sbBuilder.delete(0, sbBuilder.length());
			}
		}
		br.close();
		
		File f2 = new File(rootpath + embFile + ".emb");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
		for(Entry<String, String> entry : labelMap.entrySet()){
			bw.write(embMap.get(entry.getKey())+"\r\n");
		}
		bw.flush();
		bw.close();
	}

	private static void getLabel(Map<String, String> labelMap,String labelFileRes,
			String... labelFiles) throws IOException {
		// TODO Auto-generated method stub
		Set<String> idSet = new HashSet<String>();
		String line = null;
		for (String labelFile : labelFiles) {
			File f = new File(rootpath + labelFile + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
			while (null != (line = br.readLine())) {
				if (labelMap.containsKey(line)) {
					idSet.add(line);
				} else {
					labelMap.put(line, labelFile);
				}
			}
			br.close();
		}
		for (String idString : idSet) {
			labelMap.remove(idString);
		}
		File f2 = new File(rootpath  + labelFileRes);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
		for(Entry<String, String> entry : labelMap.entrySet()){
			bw.write(entry.getValue()+"\r\n");
		}
		bw.flush();
		bw.close();
	}
	
}
