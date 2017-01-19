package files;

import java.io.File;

public class ChangeName {

	public static void main(String args[]) {
		String dirname = "D:\\Project_DataMinning\\DataProcessd\\Sina_GenderPre_1635\\Follows\\";
		String old_str = "winR";
		String new_str = "wlr";
		if(args.length>0){
			dirname = args[0];
			old_str = args[1];
			new_str = args[2];
		}
		
		changeFilename(dirname,old_str,new_str);
	}
	private static void changeFilename(String dirname, String old_str, String new_str) {
		File dir = new File(dirname);
		if(dir.isDirectory()){
			File[] fs = dir.listFiles();
			File new_file = null;
			for(File f : fs){
				if(!f.getName().contains(old_str))continue;
				new_file = new File(f.getParent()+"//"+f.getName().replaceAll(old_str, new_str));
				if(!f.renameTo(new_file)){System.out.println(f.getName()+"修改失败。");}
			}
		}else{
			System.out.println(dirname+" is not a Directory.");
		}
	}
}

