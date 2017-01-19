package files;

import java.io.File;

public class ListFile {

	public static void main(String args[]) {
		/*
		 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		 * String dirname = "F:\\Youtube_31703\\cw_walks\\"; File dir = new
		 * File(dirname); if(dir.isDirectory()){ System.out.println("yes");
		 * File[] files = dir.listFiles(); for(int i=0,size =
		 * files.length;i<size;i++){
		 * System.out.println(files[i].getName()+"\t"+format.format(files[i].
		 * lastModified())); } }
		 */

		String dirname = "F:\\Flickr_5\\line_embeddings\\flickr_n5s10d100v3.emb";
		File dir = new File(dirname);
		if (dir.isDirectory()) {
			System.out.println("yes");
			String[] files = dir.list();
			for (int i = 0, size = files.length; i < size; i++) {
				System.out.println(files[i]);
			}
		}
	}
}
