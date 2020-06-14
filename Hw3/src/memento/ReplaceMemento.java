/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReplaceMemento implements Comparable<ReplaceMemento> {
	private String text;
	private String newtext;
	private int fileNextIndex;
	private int textAreaNextIndex;
	private LocalDateTime created;

	public ReplaceMemento(String text, String newText, int fileNextIndex, int textAreaNextIndex) {
		this.text = text;
		this.newtext = newText;
		this.fileNextIndex = fileNextIndex;
		this.textAreaNextIndex = textAreaNextIndex;
		this.created = LocalDateTime.now();
	}

	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}

	public String getNewText() {

		return this.newtext;
	}

	public int getFileNextIndex() {

		return this.fileNextIndex;
	}

	public int getTextAreaNextIndex() {
		return textAreaNextIndex;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return "Memento [created=" + created.format(formatter) + "]";
	}

	@Override
	public int compareTo(ReplaceMemento mem) {
		return created.compareTo(mem.getCreated());
	}
}
