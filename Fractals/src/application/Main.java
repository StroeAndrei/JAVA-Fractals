package application;

import fractals.KochKurve;
import fractals.TSquare;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Setam titlul aplicatiei in fereastra principala
		primaryStage.setTitle("Fractali");

		// Wrapper pentru elementele din fereastra
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// Text cu titlul aplicatiei in pagina
		Text scenetitle = new Text("Generator Fractali");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		scenetitle.setTextAlignment(TextAlignment.CENTER);
		// Adaugam textul la GridPane
		grid.add(scenetitle, 0, 0, 2, 1);

		// Label pentru TSquare
		Label minSizeLabel = new Label("Latura minima (3px-100px)");
		grid.add(minSizeLabel, 0, 1);

		// Valoare numerica pentru fractalul TSquare (min latura)
		TextField minSize = new TextField();
		grid.add(minSize, 0, 2);

		// Valoare numerica pentru fractalul KochCurve (nr de iteratii)

		// Buton pentru generare Fractal -> TSquare
		Button generateTSquare = new Button("Construieste T-square");
		generateTSquare.setMinWidth(150);
		generateTSquare.setMinHeight(30);
		// Adaugam butonul la GridPane
		grid.add(generateTSquare, 0, 3);

		// Text pentru mesajul de eroare
		Text errmsj = new Text();
		errmsj.setFill(Color.FIREBRICK);
		errmsj.setWrappingWidth(150);
		grid.add(errmsj, 0, 11);

		// Adaugam eventul de click pe butonul de generare si afisam rezultatul
		generateTSquare.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Facem conversia dimensiunii primite din input la Integer
				// si verificam daca este Integer. Tratam exceptia printr-un
				// mesaj de eroare
				int minSizeNumeric;
				TSquare tsquare = new TSquare();

				try {
					minSizeNumeric = Integer.parseInt(minSize.getText());

					// Setam latura minima intre 3 si 100 pixeli
					if (minSizeNumeric >= 3 && minSizeNumeric <= 100) {
						tsquare.makeWindowForTSquare(primaryStage, 900, 900, 400, minSizeNumeric);
						errmsj.setText("");
					} else {
						errmsj.setText("Valoarea introdusa nu este valida.");
					}

				} catch (NumberFormatException err) {
					errmsj.setText("Valoarea introdusa nu este valida.");
				}
			}
		});

		// Label pentru TSquare
		Label kochLabel = new Label("Numarul de parcurgeri");
		grid.add(kochLabel, 0, 4);

		// Construim butoanele pentru a obtine numarul de parcurgeri
		RadioButton radioButtonVal1 = new RadioButton("1");
		RadioButton radioButtonVal2 = new RadioButton("2");
		RadioButton radioButtonVal3 = new RadioButton("3");
		RadioButton radioButtonVal4 = new RadioButton("4");
		RadioButton radioButtonVal5 = new RadioButton("5");

		// Setam o valoare implicita pentru numarul de parcurgeri pentru a evita
		// eventuale erori
		radioButtonVal3.setSelected(true);

		// Ne asiguram ca preluam o singura valoare
		ToggleGroup radioGroup = new ToggleGroup();
		radioButtonVal1.setToggleGroup(radioGroup);
		radioButtonVal2.setToggleGroup(radioGroup);
		radioButtonVal3.setToggleGroup(radioGroup);
		radioButtonVal4.setToggleGroup(radioGroup);
		radioButtonVal5.setToggleGroup(radioGroup);

		grid.add(radioButtonVal1, 0, 5);
		grid.add(radioButtonVal2, 0, 6);
		grid.add(radioButtonVal3, 0, 7);
		grid.add(radioButtonVal4, 0, 8);
		grid.add(radioButtonVal5, 0, 9);

		// Buton pentru generare Fractal -> Koch Curve
		Button generateKochCurve = new Button("Construieste Koch Curve");
		generateKochCurve.setMinWidth(150);
		generateKochCurve.setMinHeight(30);
		// Adaugam butonul la GridPane
		grid.add(generateKochCurve, 0, 10);

		// Adaugam eventul de click pe butonul de generare si afisam rezultatul
		generateKochCurve.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int getKochIterations;
				KochKurve kochkurve = new KochKurve();

				RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
				getKochIterations = Integer.parseInt(selectedRadioButton.getText());

				kochkurve.makeWindowForKochKurve(primaryStage, 0, 500, 1600, 500, 2, 100 + (100 * getKochIterations));

			}
		});

		// Construim obiectul Scene si atasam GridPane cu dimensiunile precizate
		// Setam obiectul Scene si-l afisam
		Scene scene = new Scene(grid, 300, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
