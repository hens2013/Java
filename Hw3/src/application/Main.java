/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */

package application;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import memento.*;
import thread.*;
import singleton.*;
import collection.*;
import exception.*;

public class Main extends Application {

	/**
	 * variables
	 */
	private List<File> files;
	private File file;
	private int filePointer = 0;
	private int fileNextIndex = -1;
	private int textAreaPointer = 0;
	private int textAreaNextIndex = -1;
	private String lastSearch = "";
	private boolean isUndoClicked = false;
	private int mementoCount = 0;
	private SortedUserSet users = new SortedUserSet();
	private CareTaker careTaker = new CareTaker();
	private TotalCounter total;
	private User user;
	private Stage primaryStage;
	private BorderPane root;
	/**
	 * login pane
	 */
	private Label labelEMail = new Label("EMail / ID Number:");
	private Label labelPassword = new Label("Password:");
	private TextField textFieldEMail = new TextField();
	private PasswordField textFieldPassword = new PasswordField();
	private Label labelStatus = new Label("");
	private Button buttonLogin = new Button("Login");

	/**
	 * register pane
	 */
	private CheckBox chekboxidGen = new CheckBox("Id Generator");
	private Label labelId = new Label("Id:");
	private Label labelFName = new Label("First Name:");
	private Label labelLName = new Label("Last Name:");
	private TextField textFieldId = new TextField();
	private TextField textFieldFName = new TextField();
	private TextField textFieldLName = new TextField();
	private Button buttonRegister = new Button("Resister");

	/**
	 * top pane
	 */
	private Label labelFileName = new Label("File Name:");
	private TextField textFieldFileName = new TextField();
	private Button buttonLoadFile = new Button("Load");
	private Button buttonSelectFile = new Button("Select Files");
	private Button buttonLogout = new Button("Logout");

	/**
	 * center pane
	 */
	private Label labelFind = new Label("Find:");
	private Label labelReplace = new Label("Replace:");
	private Label labelNextIndex = new Label("Next Index: ");
	private Label labelNextIndexValue = new Label("");
	private Label labelCount = new Label("Count: ");
	private Label labelCountValue = new Label("");
	private TextField textFieldSearch = new TextField();
	private TextField textFieldReplace = new TextField();
	private Button buttonCountAll = new Button("Count All");
	private Button buttonFind = new Button("Find");
	private Button buttonReplace = new Button("Replace");
	private Button buttonUndo = new Button("Undo");
	private Button buttonReplaceAll = new Button("Replace All");
	private Button buttonReset = new Button("Reset");
	/**
	 * bottom pane
	 */
	private TextArea textArea = new TextArea();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			primaryStage = stage;
			loadUsers();
			primaryStage.setOnCloseRequest(event -> saveUsers());
			loginWindow();
			System.out.println(users);
		} catch (Exception e) {
		}
	}

	private void loginWindow() {
		careTaker = new CareTaker();
		primaryStage.setTitle("Text Editor");
		primaryStage.setResizable(false);
		root = new BorderPane();
		root.setPrefSize(450, 600);
		root.setCenter(getLogin(""));
		Scene scene = new Scene(root, 550, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initialize() {
		resetIndexes();
		setDisableTop(false);
		setDisableCenter(true);
		setDisableBottom(true);
	}

	private void resetIndexes() {
		filePointer = 0;
		fileNextIndex = -1;
		textAreaPointer = 0;
		textAreaNextIndex = -1;
		labelNextIndexValue.setText("");
	}

	private Node getLogin(String status) {
		labelEMail = new Label("EMail / ID Number:");
		labelStatus.setText(status);
		buttonLogin.setOnAction(e -> login());
		textFieldPassword.setText("");
		GridPane loginPane = new GridPane();
		loginPane.setMinSize(400, 200);
		loginPane.setPadding(new Insets(10, 10, 10, 10));
		loginPane.setVgap(10);
		loginPane.setHgap(10);
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setStyle("-fx-border-color: black");

		HBox title = new HBox(10);
		Label labelTitle = new Label("LOGIN OR");
		Hyperlink link = new Hyperlink("REGISTER");
		link.setOnAction(e -> root.setCenter(getRegister("")));
		title.setPadding(new Insets(10, 10, 10, 10));
		title.setAlignment(Pos.CENTER);
		title.getChildren().addAll(labelTitle, link);

		loginPane.add(title, 0, 0, 4, 1);
		loginPane.add(labelEMail, 0, 1);
		loginPane.add(textFieldEMail, 1, 1);
		loginPane.add(labelPassword, 0, 2);
		loginPane.add(textFieldPassword, 1, 2);
		loginPane.add(buttonLogin, 0, 3);
		loginPane.add(labelStatus, 0, 4, 4, 2);
		return loginPane;
	}

	private void login() {
		try {
			user = null;
			User user1 = users.isUser(textFieldEMail.getText(), textFieldPassword.getText());
			if (user1 != null) {
				startEdidot();
				user = user1;
				primaryStage.setTitle("Text Editor - User " + user.getFName());
			} else
				labelStatus.setText("User with this email or ID not found!");

		} catch (EMailException | PasswordException | IDException | LoginException e) {
			labelStatus.setText(e.getMessage());
		}
	}

	private void startEdidot() {
		root.setTop(getTop());
		root.setCenter(getCenter());
		root.setBottom(getBottom());
		initialize();
	}

	private Node getRegister(String status) {
		labelEMail = new Label("EMail:");

		chekboxidGen.setOnAction(e -> genId());
		String eMail = textFieldEMail.getText();
		if (!eMail.contains("@")) {
			chekboxidGen.setSelected(false);
			textFieldId.setText(eMail);
		} else
			chekboxidGen.setSelected(true);
		genId();
		labelStatus.setText(status);
		buttonRegister.setOnAction(e -> register());

		GridPane registerPane = new GridPane();
		registerPane.setMinSize(400, 200);
		registerPane.setPadding(new Insets(10, 10, 10, 10));
		registerPane.setVgap(10);
		registerPane.setHgap(10);
		registerPane.setAlignment(Pos.CENTER);
		registerPane.setStyle("-fx-border-color: black");

		HBox title = new HBox(10);
		Label labelTitle = new Label("REGISTER OR");
		Hyperlink link = new Hyperlink("LOGIN");
		link.setOnAction(e -> root.setCenter(getLogin("")));
		title.setPadding(new Insets(10, 10, 10, 10));
		title.setAlignment(Pos.CENTER);
		title.getChildren().addAll(labelTitle, link);

		registerPane.add(title, 0, 0, 4, 1);
		registerPane.add(labelId, 0, 1);
		registerPane.add(textFieldId, 1, 1);
		registerPane.add(chekboxidGen, 2, 1);
		registerPane.add(labelFName, 0, 2);
		registerPane.add(textFieldFName, 1, 2);
		registerPane.add(labelLName, 0, 3);
		registerPane.add(textFieldLName, 1, 3);
		registerPane.add(labelEMail, 0, 4);
		registerPane.add(textFieldEMail, 1, 4);
		registerPane.add(labelPassword, 0, 5);
		registerPane.add(textFieldPassword, 1, 5);
		registerPane.add(buttonRegister, 0, 6);
		registerPane.add(labelStatus, 0, 7, 4, 2);
		return registerPane;
	}

	private void genId() {
		String id = "";
		if (chekboxidGen.isSelected()) {
			id = String.format("au%05d", SingletonIDGenerator.getInstance().getId());
			textFieldId.setText(id);
			textFieldId.setDisable(true);
		} else {
			textFieldId.setDisable(false);
		}
	}

	private void register() {
		try {
			User user = users.isUser(textFieldEMail.getText(), textFieldPassword.getText());
			String userId = textFieldId.getText();
			if (user == null) {
				user = new User(userId, textFieldFName.getText(), textFieldLName.getText(), textFieldEMail.getText(),
						textFieldPassword.getText());
				if (chekboxidGen.isSelected())
					SingletonIDGenerator.getInstance().getNext();
				else if (userId.startsWith("au"))
					throw new IDException("ID can not start with 'au' string!");
				users.add(user);
				root.setCenter(getLogin(user.getEMail() + " Registered Successfully!"));
			}
		} catch (EMailException | PasswordException | IDException e) {
			labelStatus.setText(e.getMessage());
		} catch (LoginException e) {
			labelStatus.setText("User with this email or ID already registered!");
		}
	}

	private Node getTop() {
		buttonLoadFile.setOnAction(e -> loadFile());
		buttonSelectFile.setOnAction(e -> selectFiles());
		buttonLogout.setOnAction(e -> loginWindow());
		textFieldFileName.setEditable(false);

		HBox topPane = new HBox(10);
		topPane.setPadding(new Insets(10, 10, 10, 10));
		topPane.setAlignment(Pos.CENTER);
		topPane.setStyle("-fx-border-color: black");
		topPane.getChildren().addAll(labelFileName, textFieldFileName, buttonLoadFile, buttonSelectFile, buttonLogout);
		return topPane;
	}

	private Node getCenter() {
		GridPane centerPane = new GridPane();
		centerPane.setMinSize(400, 200);
		centerPane.setPadding(new Insets(10, 10, 10, 10));
		centerPane.setVgap(10);
		centerPane.setHgap(10);
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setStyle("-fx-border-color: black");
		centerPane.add(labelFind, 0, 0);
		centerPane.add(textFieldSearch, 1, 0);
		centerPane.add(labelReplace, 0, 1);
		centerPane.add(textFieldReplace, 1, 1);
		centerPane.add(labelCount, 0, 2);
		centerPane.add(labelCountValue, 1, 2);
		centerPane.add(buttonCountAll, 2, 2);
		centerPane.add(labelNextIndex, 0, 3);
		centerPane.add(labelNextIndexValue, 1, 3);
		labelNextIndexValue.setMaxWidth(100);
		HBox hb = new HBox(10);
		buttonCountAll.setOnAction(e -> countAll());
		buttonFind.setOnAction(e -> isFound());
		buttonReplaceAll.setOnAction(e -> replaceAll());
		buttonReset.setOnAction(e -> initialize());
		hb.getChildren().addAll(buttonFind, buttonReplace, buttonUndo, buttonReplaceAll, buttonReset);
		centerPane.add(hb, 0, 4, 4, 1);
		return centerPane;
	}

	private void countAll() {
		if (textFieldSearch.getText() != null && !textFieldSearch.getText().equals("")) {
			total = new TotalCounter(files, textFieldSearch.getText());
			labelCountValue.setText("" + total.getCounter());
		}

	}

	private Node getBottom() {
		HBox hbButtons = new HBox(10);
		hbButtons.setPadding(new Insets(10, 10, 10, 10));
		hbButtons.setAlignment(Pos.CENTER);
		hbButtons.setStyle("-fx-border-color: black");
		textArea.setPrefWidth(400);
		textArea.setPrefSize(400, 300);
		textArea.setPrefColumnCount(20);
		textArea.setPrefRowCount(10);
		hbButtons.getChildren().addAll(textArea);
		return hbButtons;
	}

	private void loadFile() {
		if (files != null && files.size() > 0 && files.get(0).exists()) {
			file = files.get(0);
			setDisableTop(true);
			setDisableCenter(false);
			setDisableBottom(false);
			showFileData();
		}
	}

	private void selectFiles() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		files = fileChooser.showOpenMultipleDialog(primaryStage);
		if (files != null) {
			String fileNames = "";
			for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
				File file = (File) iterator.next();
				fileNames += file.getName() + "; ";
			}
			textFieldFileName.setText(fileNames);
		} else {
			textFieldFileName.setText("");
		}
	}

	private void setDisableTop(boolean value) {
		labelFileName.setDisable(value);
		textFieldFileName.setDisable(value);
		buttonLoadFile.setDisable(value);
		buttonSelectFile.setDisable(value);
	}

	private void setDisableCenter(boolean value) {
		labelFind.setDisable(value);
		labelReplace.setDisable(value);
		labelNextIndex.setDisable(value);
		labelNextIndexValue.setText("");
		labelNextIndexValue.setDisable(value);
		labelCount.setDisable(value);
		labelCountValue.setText("");
		labelCountValue.setDisable(value);
		textFieldSearch.setText("");
		textFieldSearch.setDisable(value);
		textFieldReplace.setText("");
		textFieldReplace.setDisable(value);
		buttonCountAll.setDisable(value);
		buttonFind.setDisable(value);
		buttonReplace.setDisable(true);
		buttonUndo.setDisable(true);
		buttonReplaceAll.setDisable(true);
		buttonReset.setDisable(value);

	}

	private void setDisableBottom(boolean value) {
		textArea.setText("");
		textArea.setDisable(value);
	}

	private void showFileData() {
		primaryStage.setTitle("Text Editor - User " + user.getFName() + " - " + file.getName());
		byte[] data = readFileData(file);
		textArea.setText(new String(data));
	}

	public static byte[] readFileData(File file) {
		byte[] data = new byte[0];
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			data = new byte[(int) raf.length()];
			raf.read(data);
		} catch (IOException e) {
		}
		return data;
	}

	private boolean isFound() {
		String text = textFieldSearch.getText();
		boolean isFound = false;

		if (!lastSearch.equals(text)) {
			lastSearch = text;
			resetIndexes();
		}
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			byte[] temp = new byte[text.length()];
			raf.seek(filePointer);
			while (raf.read(temp) != -1) {
				if (text.equals(new String(temp))) {
					filePointer = (int) raf.getFilePointer();
					fileNextIndex = filePointer - text.length();
					isFound = true;
					break;
				} else {
					filePointer++;
					if (filePointer >= raf.length())
						if (fileNextIndex == -1) {
							labelNextIndexValue.setText("Not Found!");

						} else {
							resetIndexes();
						}
					raf.seek(filePointer);
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		if (isFound) {
			labelNextIndexValue.setText("" + fileNextIndex);
			textAreaNextIndex = textArea.getText().indexOf(text, textAreaPointer);
			textAreaPointer = textAreaNextIndex + text.length();
			textArea.selectRange(textAreaNextIndex, textAreaPointer);
			buttonReplace.setDisable(false);
			buttonReplaceAll.setDisable(false);
			buttonReplace.setOnAction(e -> replace());
			buttonReplaceAll.setOnAction(e -> replaceAll());
			return true;
		} else
			return false;
	}

	private void replaceAll() {
		while (isFound())
			replace();
	}

	private void replace() {
		if (!isUndoClicked) {
			careTaker.saveMemento(new ReplaceMemento(textFieldSearch.getText(), textFieldReplace.getText(), fileNextIndex,
					textAreaNextIndex));
			mementoCount++;
		}
		String text = textFieldSearch.getText();
		String newText = textFieldReplace.getText();
		buttonReplace.setDisable(true);
		buttonUndo.setDisable(false);
		buttonUndo.setOnAction(e -> undo());
		buttonReplaceAll.setDisable(true);
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.seek(fileNextIndex + text.length());
			byte[] temp = new byte[(int) (raf.length() - fileNextIndex)];
			raf.read(temp);
			raf.setLength(fileNextIndex);
			raf.write(newText.getBytes());
			raf.write(temp);
		} catch (IOException e) {
		}

		showFileData();
		textArea.selectRange(textAreaNextIndex, textAreaNextIndex + newText.length());
		resetIndexes();
	}

	private void undo() {
		isUndoClicked = true;
		ReplaceMemento memento = careTaker.restoreMemento();
		String text = memento.getText();
		String newText = memento.getNewText();
		textFieldSearch.setText(newText);
		textFieldReplace.setText(text);
		fileNextIndex = memento.getFileNextIndex();
		textAreaNextIndex = memento.getTextAreaNextIndex();
		replace();
		textFieldSearch.setText(text);
		textFieldReplace.setText(newText);
		lastSearch = text;
		isFound();
		isUndoClicked = false;
		mementoCount--;
		if (mementoCount < 1)
			buttonUndo.setDisable(true);
	}

	public void saveUsers() {
		File dir = new File("C:\\Text Editor");
		File data = new File("C:\\Text Editor\\data.obj");
		try {
			if (!dir.exists())
				dir.mkdirs();
			if (!data.exists())
				data.createNewFile();
		} catch (IOException e) {
			System.out.println("Save File Exception: " + e.getMessage());
		}
		try (ObjectOutputStream oOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(data)))) {
			if (users.size() == 0) {
				oOut.writeInt(1);
				oOut.writeInt(0);
			} else {
				oOut.writeInt(SingletonIDGenerator.getInstance().getId());
				oOut.writeInt(users.size());
			}
			for (int i = 0; i < users.size(); i++) {
				oOut.writeObject(users.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Save File Exception: File Not Found. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Save File Exception:" + e.getMessage());
		}
		System.out.println("File Updated!");
	}

	public void loadUsers() {
		File data = new File("C:\\Text Editor\\data.obj");
		if (data.exists()) {
			try (ObjectInputStream oIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(data)))) {
				SingletonIDGenerator.getInstance().setId(oIn.readInt());
				int size = oIn.readInt();
				while (size > 0) {
					users.add((User) oIn.readObject());
					size--;
				}
			} catch (FileNotFoundException e) {
				System.out.println("Load File Exception: File Not Found. " + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("Load File Exception: Class Not Found. " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Load File Exception: " + e.getMessage());
			}
		}
	}

}
