package stat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StatFreq {

	private static double sample = 1e-3;
	public static void main(String[] args) throws IOException {
		File res = new File("F:\\stat\\STAT3_new.Txt");
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(res));
		bWriter.write(sample+"\r\n");
		freqStat(bWriter,"F:\\Blogcatalog_10312\\dw_walks\\blogcatalog_dw_r10l80.walks");
		freqStat(bWriter,
				"E:\\cyq\\chenli_experiment\\data\\experiment\\blogcatalog_10312\\n2v_walks\\blogcatalog_n2v_r10l80p0.25q0.25.walks");
		freqStat(bWriter,"F:\\Blogcatalog_10312\\clusters\\blogcatalog_friedge1.cluster");
		freqStat(bWriter,"F:\\Blogcatalog_10312\\clusters\\blogcatalog_closure0.cluster");
		
		/*
		freqStat(bWriter,"F:\\Flickr_80513\\dw_walks\\flickr_dw_r10l80.walks");
		freqStat(bWriter,
				"E:\\cyq\\chenli_experiment\\data\\experiment\\Flickr_27393_10per\\n2v_walks\\flickr_n2v_r10l80p0.25q4.walks");
		freqStat(bWriter,"F:\\Flickr_80513\\cw_walks\\flickr_cw4_r1l80.walks.t90f9");
		
		
		
		freqStat(bWriter,"F:\\Youtube_22693\\dw_walks\\youtubeSmall_dw_r10l80.walks");
		freqStat(bWriter,
				"E:\\cyq\\chenli_experiment\\data\\experiment\\Youtube_22693_10per\\n2v_walks\\youtube_n2v_r10l80p0.25q0.25.walks");
		freqStat(bWriter,"F:\\Youtube_22693\\cw_walks\\youtubeSmall_cw4_r1l80.walks.t90f9");
		freqStat(bWriter,"F:\\Youtube_22693\\cw_walks\\youtubeSmall_cw4_r10l80.walks.t90f9");*/
		bWriter.flush();
		bWriter.close();
	}

	private static void freqStat(BufferedWriter bWriter,String filename) throws IOException {
		bWriter.write(filename+"\r\n");
		int train_words = 0;
		Map<String, Integer> freq = new HashMap<>();
		File file = new File(filename);
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String line = null;
		while (null != (line = bReader.readLine())) {
			String[] words = line.split("\\s+");
			train_words += words.length;
			updateMap(freq, words);
		}
		bReader.close();
		List<Double> res = new ArrayList<>(freq.size());
		double avg = 0;
		for (Entry<String, Integer> entry : freq.entrySet()) {
			double f = entry.getValue();
			double r = (Math.sqrt(f / (sample * train_words)) + 1) * (sample * train_words / f);
			if(r>1)continue;
			res.add(r);
			avg += r;
		}
		avg /= res.size();
		Collections.sort(res);
		bWriter.write(res.toString()+"\r\n");
		bWriter.write(avg+"\t"+res.size()+"\r\n");
	}

	private static void updateMap(Map<String, Integer> freq, String[] words) {
		for (String word : words) {
			if (freq.containsKey(word)) {
				freq.put(word, freq.get(word) + 1);
			} else {
				freq.put(word, 1);
			}
		}
	}
}
