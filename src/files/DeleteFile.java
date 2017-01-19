package files;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteFile {

	public static void main(String args[]) {
		// delete1();
		delete2();
		//delete3();
	}

	private static void delete3() {
		/*String[] ps = { "0.25", "0.5", "1", "2", "4" };
		String rootPath = "F:\\Blogcatalog_1398_10per\\classifiers\\";

		List<String> dirnames = new ArrayList<>();
		for (String p : ps) {
			for (String q : ps) {
				dirnames.add("blogcatalog_friedge_m0n5d128i10f1_n2v_r10l80p" + p + "q" + q + "_m0n5w10d128i1");
			}
		}
		 */
		 String rootPath = "F:\\Flickr_5\\classifiers\\"; 
		 String[] dirnames = {"flickr_n5s10d100v3","flickr_dw_r40l40_m0n5w10d100i1" };
		
		/*
		 * String rootPath = "F:\\Youtube_22693\\classifiers\\"; String[]
		 * dirnames =
		 * {"youtubeSmall_dw_r10l80_m0n5w10d128i1","youtubeSmall_n5s10d128v3",
		 * "youtubeSmall_cw4_r10l80_m0n5w10d128i1"};
		 */
			int[] train_size_arr = { 1,10 };
		//int[] train_size_arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		int classSize = 195;
		for (int train_size : train_size_arr) {
			for (int classNum = 1; classNum <= classSize; classNum++) {
				for (String dirname : dirnames) {
					File dir = new File(
							rootPath + "(train_" + train_size + "percent)\\class_" + classNum + "\\" + dirname);
					if (dir.isDirectory()) {
						deleteDir(dir);
					}

				}

			}
		}
	}

	private static void delete2() {
		//String rootPath = "F:\\Blogcatalog_5\\classifiers\\";

		String rootPath = "F:\\Flickr_5s\\classifiers\\";

		//String rootPath = "F:\\Youtube_5s\\classifiers\\";
		Set<String> filters = new HashSet<>();
		filters.add("5fold_all");
		filters.add("10fold_all");
		//filters.add("flickr_friedge1_n5d100i10b0f1_con_n2v_r40l40p2q1_m0n5w10d100i1_concFF");
		//filters.add("blogcatalog_n2v_r10l80p0.25q0.25_m0n5w10d128i1_f1_avg");

		int[] train_size_arr = { 1,2, 3, 4,5,6,7,8,9,10};
		int classSize = 195;
		for (int train_size : train_size_arr) {
			for (int classNum = 1; classNum <= classSize; classNum++) {
				File dirnames = new File(rootPath + "(train_" + train_size + "percent)\\class_" + classNum);
				if(!dirnames.exists())continue;
				File[] files = dirnames.listFiles();
				for (File file : files) {
					if (filters.contains(file.getName()))
						continue;
					if (file.isDirectory()) {
						deleteDir(file);
					}
				}
			}
		}
	}

	private static void deleteDir(File dir) {
		System.out.println("deleting " + dir.getAbsolutePath());
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				deleteDir(f);
			} else {
				f.delete();
			}
		}
		dir.delete();
	}

	private static void delete1() {
		// String filename =
		// "F:\\Youtube_31703\\cw_walks\\youtube_cw4_r1l80.walks.t";
		String filename = "F:\\Flickr_80513\\cw_walks\\youtube_cw4_r1l80.walks.t";
		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				File f = new File(filename + i * 10 + "f" + j);
				if (f.exists()) {
					System.out.println(f.getName() + " will be delete.");
					f.delete();
				}
			}
		}
	}

}
