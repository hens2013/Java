/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package thread;

import java.io.File;
import application.Main;

/**
 * you can add extends or implemantation
 */
public class TextCounter implements Runnable{
	private File file;
	private String search;
	private TotalCounter totalCounter;

	public TextCounter(File file, String search, TotalCounter totalCounter) {
		this.file = file;
		this.search = search;
		this.totalCounter = totalCounter;
	}
//	@Override
//	public void run() {
//		String txt = new String(Main.readFileData(file)); // read file data to string 
//		int fromIndex = 0, count = 0;
//		
//		 //counting search word in file
//		while ((fromIndex = txt.indexOf(search, fromIndex)) != -1 ){
//			count++;
//			fromIndex++;
//
//		}
//		
//		//add count to total counter 
//		totalCounter.add(count);
//
//	}
	
	@Override
	public void run() {
		byte []b=Main.readFileData(file); // read file data to string 
		byte []a=this.search.getBytes();
		
		int fromIndex = 0, count = 0;
		for (int i = 0; i < b.length-a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if(b[i+j]==a[j])
					count++;
			}
			if(count==a.length)
				fromIndex++;
			count=0;
		} 
		totalCounter.add(fromIndex);
	}

}
