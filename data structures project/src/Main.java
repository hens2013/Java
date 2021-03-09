
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @authors Hen Shiryon & Bar Zada
 */


public class Main extends Application {

	public void start(Stage stage) throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File fileName = fileChooser.showOpenDialog(stage);
		System.out.println("The selected file is in path: " + fileName);
		String delimiter = " ";
		BinarySearchTree tree = null;
		
		String resultFileName = "output.txt"; 
		File resultFile = new File(resultFileName);
		PrintWriter pw = new PrintWriter(resultFile);

		int counterLines = 0;
		BufferedReader reader;
	
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while (line != null) {

				counterLines++;
				if (line.equals(delimiter) || line.isEmpty()) {
					line = reader.readLine();

					continue;
				}
				
				String[] words = line.split(" "); 
				String regex ="[^A-Za-z' ]";//clean punctuation

			
				for (int i = 0; i < words.length; i++) {	 
					words[i] = words[i].replaceAll(regex, "").toLowerCase(); 
					
					if (words[i].endsWith("'s")) {
						words[i] = words[i].substring(0,words[i].length()-2);// delete  's   extensions
					}
						words[i]=words[i].replaceAll("'", ""); 
					
				}
				
				tree = insertToTree(tree, counterLines, words);
				line = reader.readLine();

			}
			reader.close();
		
		}
			catch (IOException e) {
			e.getMessage();
		}
	

		inorder_Recursive(pw, tree);
		pw.close();
		JOptionPane.showMessageDialog(null, "the Concordance was written into output file in the project");
		System.out.println("finished");

	}



	static BinarySearchTree insertToTree(BinarySearchTree root, int lineNumber, String[] wordsArray) {

		for (int i = 0; i < wordsArray.length; i++) {
			if (wordsArray[i].length() != 0 && !(wordsArray[i].startsWith("'") || wordsArray[i].endsWith("'"))) {
				root = Insert(root, wordsArray[i], lineNumber);
				}
			}
	
		return root;
	}
	static void inorder_Recursive(PrintWriter pw, BinarySearchTree root) {
		if (root != null) {
			inorder_Recursive(pw, root.left);
			pw.println(root.data.toString()+"\n");
			inorder_Recursive(pw, root.right);
		}
	}
	static BinarySearchTree Insert(BinarySearchTree root, String key, int lineNumber) {
		// tree is empty
		if (root == null) {
			root = new BinarySearchTree(key, lineNumber);
			return root;
		}
		// traverse the tree
		if (key.compareTo(root.data.getWord()) < 0) // insert in the left subtree
			root.left = Insert(root.left, key, lineNumber);
		else if (key.compareTo(root.data.getWord()) > 0) // insert in the right subtree
			root.right = Insert(root.right, key, lineNumber);
		else
			root.data.addWordLocation(lineNumber);
		return root;
	}
	

	public static void main(String[] args) throws IOException {
		launch(args);

	}

}
