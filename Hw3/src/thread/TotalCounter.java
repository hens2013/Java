/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package thread;

import java.io.File;
import java.util.Iterator;
import java.util.List;


// total counter class
public class TotalCounter {
	private List<File> files;
	private String search;
	private int counter;
	private int finished;

	public TotalCounter(List<File> files, String search) {
		this.files = files;
		this.search = search;
		this.counter = 0;
		this.finished = 0;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getFinished() {
		return finished;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	private void count() {
		for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
			new Thread(new TextCounter(iterator.next(), search, this)).start();
		}

	}

	public synchronized void add(int value) {
		finished++;
		counter += value;
		System.out.printf("%s finished.\t Number of finished threads:%d\tTread counter:%d\tTotal counter:%d\n", Thread.currentThread().getName(), finished, value, counter);
		notify();
	}

	public synchronized int getCounter() {
		count();
		while (files.size() > finished) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return counter;
	}

}
