
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
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Main extends Application {

	/**
	 * variables
	 */
	private File file;
	private int filePointer = 0;
	private int fileNextIndex = -1;
	private int textAreaPointer = 0;
	private int textAreaNextIndex = -1;
	private String lastSearch = "";
	private SortedUserSet users = new SortedUserSet();
	
	private Stage primaryStage;
	private BorderPane root;

	/**
	 * login pane
	 */
	private Label labelEMail = new Label("EMail:");
	private Label labelPassword = new Label("Password:");
	private TextField textFieldEMail = new TextField();
	private PasswordField textFieldPassword = new PasswordField();
	private Label labelStatus = new Label("");
	private Button buttonLogin = new Button("Login");

	/**
	 * register pane
	 */
	private Label labelFName = new Label("First Name:");
	private Label labelLName = new Label("Last Name:");
	private TextField textFieldFName = new TextField();
	private TextField textFieldLName = new TextField();
	private Button buttonRegister = new Button("Resister");

	/**
	 * top pane
	 */
	private Label labelFileName = new Label("File Name:");
	private TextField textFieldFileName = new TextField();
	private Button buttonLoadFile = new Button("Load");
	private Button buttonSelectFile = new Button("Select File");
	private Button buttonLogout = new Button("Logout");

	/**
	 * center pane
	 */
	private Label labelFind = new Label("Find:");
	private Label labelReplace = new Label("Replace:");
	private Label labelNextIndex = new Label("Next Index: ");
	private Label labelNextIndexValue = new Label("");
	private Label labelCount = new Label("Count: ");
	private TextField textFieldFind = new TextField();
	private TextField textFieldReplace = new TextField();
	private Button buttonFind = new Button("Find");
	private Button buttonReplace = new Button("Replace");
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
			loadDataFile();
			primaryStage.setOnCloseRequest(event -> saveDataFile());
			loginWindow();
			System.out.println(users);
		} catch (Exception e) {
		}
	}

	private void loginWindow() {
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
			
			User user = users.isUser(textFieldEMail.getText(), textFieldPassword.getText());
			if (user != null) {
				startEdidot();
				primaryStage.setTitle("Text Editor - User " + user.getFName());
			}else
				labelStatus.setText("User with this email not found!");

		} catch (EMailException | PasswordException | LoginException e) {
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
		registerPane.add(labelFName, 0, 1);
		registerPane.add(textFieldFName, 1, 1);
		registerPane.add(labelLName, 0, 2);
		registerPane.add(textFieldLName, 1, 2);
		registerPane.add(labelEMail, 0, 3);
		registerPane.add(textFieldEMail, 1, 3);
		registerPane.add(labelPassword, 0, 4);
		registerPane.add(textFieldPassword, 1, 4);
		registerPane.add(buttonRegister, 0, 5);
		registerPane.add(labelStatus, 0, 6, 4, 2);
		return registerPane;
	}

	private void register() {
		try {
			User user = users.isUser(textFieldEMail.getText(), textFieldPassword.getText());
			if (user == null) {
				user = new User(textFieldFName.getText(), textFieldLName.getText(), textFieldEMail.getText(),
						textFieldPassword.getText());
				users.add(user);
				root.setCenter(getLogin(user.getEMail() + " Registered Successfully!"));
			} else
				labelStatus.setText("User with this email alrady registered!");

		} catch (EMailException | PasswordException e) {
			labelStatus.setText(e.getMessage());
		} catch (LoginException e) {
			labelStatus.setText("User with this email alrady registered!");
		}
	}

	private Node getTop() {
		buttonLoadFile.setOnAction(e -> loadFile());
		buttonSelectFile.setOnAction(e -> selectFile());
		buttonLogout.setOnAction(e -> loginWindow());

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
		centerPane.add(textFieldFind, 1, 0);
		centerPane.add(labelReplace, 0, 1);
		centerPane.add(textFieldReplace, 1, 1);
		centerPane.add(labelCount, 0, 2);
		centerPane.add(labelNextIndex, 0, 3);
		centerPane.add(labelNextIndexValue, 1, 3);
		labelNextIndexValue.setMaxWidth(100);
		HBox hb = new HBox(10);
		buttonFind.setOnAction(e -> find());
		buttonReplaceAll.setOnAction(e -> replaceAll());
		buttonReset.setOnAction(e -> initialize());
		hb.getChildren().addAll(buttonFind, buttonReplace, buttonReplaceAll, buttonReset);
		centerPane.add(hb, 0, 4, 4, 1);
		return centerPane;
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
		file = new File(textFieldFileName.getText());
		if (file.exists()) {
			setDisableTop(true);
			setDisableCenter(false);
			setDisableBottom(false);
			showFileData();
		} else {
		}
	}

	private void selectFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		file = fileChooser.showOpenDialog(primaryStage);
		if (file != null)
			textFieldFileName.setText(file.getPath());
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
		labelCount.setText("");
		labelCount.setDisable(value);
		textFieldFind.setText("");
		textFieldFind.setDisable(value);
		textFieldReplace.setText("");
		textFieldReplace.setDisable(value);
		buttonFind.setDisable(value);
		buttonReplace.setDisable(true);
		buttonReplaceAll.setDisable(true);
		buttonReset.setDisable(value);

	}

	private void setDisableBottom(boolean value) {
		textArea.setText("");
		textArea.setDisable(value);
	}

	private void showFileData() {
		byte[] data = readFileData(file);
		textArea.setText(new String(data));
	}

	private byte[] readFileData(File file) {
		byte[] data = new byte[0];
		//////////////////////////// HW01 solution ////////////////////////////
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			data = new byte[(int) raf.length()];
			raf.read(data);
		} catch (IOException e) {
		}
		//////////////////////////// end solution ////////////////////////////
		return data;
	}

	private boolean find() {
		String text = textFieldFind.getText();
		boolean isFound = false;

		//////////////////////////// HW01 solution ////////////////////////////
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
		//////////////////////////// end solution ////////////////////////////

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

		//////////////////////////// HW01 solution ////////////////////////////
		while (find())
			replace();
		//////////////////////////// end solution ////////////////////////////

	}

	private void replace() {
		String foundText = textFieldFind.getText();
		String newText = textFieldReplace.getText();
		buttonReplace.setDisable(true);
		buttonReplaceAll.setDisable(true);

		//////////////////////////// HW01 solution ////////////////////////////
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.seek(fileNextIndex + foundText.length());
			byte[] temp = new byte[(int) (raf.length() - fileNextIndex)];
			raf.read(temp);
			raf.setLength(fileNextIndex);
			raf.write(newText.getBytes());
			raf.write(temp);
		} catch (IOException e) {
		}
		//////////////////////////// end solution ////////////////////////////

		showFileData();
		textArea.selectRange(textAreaNextIndex, textAreaNextIndex + newText.length());
		resetIndexes();
	}
	public void saveDataFile() {
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
			if (users.size() == 0)
				oOut.writeInt(0);
			else
				oOut.writeInt(users.size());
			for (int i = 0; i <users.size(); i++) {
				oOut.writeObject(users.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Save File Exception: File Not Found. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Save File Exception:" + e.getMessage());
		}
		System.out.println("File Updated!");
	}

	public void loadDataFile() {
		File data = new File("C:\\Text Editor\\data.obj");
		if (data.exists()) {
			try (ObjectInputStream oIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(data)))) {
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
