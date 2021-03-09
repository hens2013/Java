
public class Data implements Comparable<Data>  {
	
	private String word;
	private LinkedList wordLocations;
	 
	
	public Data(String word,int value)
	{
		this.word=word;
		this.wordLocations=new LinkedList();
		wordLocations.add(value);
	}

	public String getWord() {
		return word;
	}
	
	public void addWordLocation(int value) {
		wordLocations.add(value);
	}

	public LinkedList getWordLocations() {
		return wordLocations;
	}

	@Override
	public int compareTo(Data o) {
		return word.compareTo(o.word);
		
	}

	@Override
	public String toString() {
		
		return word + " -> "+wordLocations.toString();
	}
	

}
