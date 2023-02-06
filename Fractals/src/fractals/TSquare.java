package fractals;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TSquare {
	public void makeWindowForTSquare(Stage primaryStage, int windowX, int windowY, int lineInitialSize,
			int lineMinSize) {
		// Construim un element GridPane care va contine Canvas
		GridPane secondGrid = new GridPane();
		secondGrid.setAlignment(Pos.CENTER);

		// Atasam obiectului Scene elementul GridPane, dimensiunile precizate + culoarea
		// de background
		Scene canvasScene = new Scene(secondGrid, windowX, windowY, Color.WHITE);

		// Construim elementul Canvas si setam contextul la 2D
		Canvas canvas = new Canvas(windowX, windowY);
		GraphicsContext context = canvas.getGraphicsContext2D();

		// Desenam patratul
		drawTSquare(context, (windowX / 2) - 400 / 2, (windowY / 2) - 400 / 2, lineInitialSize, lineMinSize);

		// Atasam elementul Canvas la GridPane
		secondGrid.getChildren().add(canvas);

		// Construim al doilea Stage si-i adaugam Scene format din Canvas
		Stage newWindow = new Stage();
		newWindow.setTitle("TSquare");
		newWindow.setScene(canvasScene);

		// Setam pozitia noii ferestre in raport cu fereastra initiala primita
		// ca argument
		newWindow.setX(primaryStage.getX() + 150);
		newWindow.setY(primaryStage.getY() - 100);

		// Afisam fereastra
		newWindow.show();
	}

	private void drawTSquare(GraphicsContext context, double startX, double startY, int initLSize, int minLSize) {
		// Formatari initiale pentru grafica
		context.setStroke(Color.web("#000"));
		context.setFill(Color.web("#000"));
		context.setLineWidth(1);
		context.fillRect(startX, startY, initLSize, initLSize);
		context.strokeRect(startX, startY, initLSize, initLSize);

		// Apelam in mod recursiv metoda drawTSquare
		if (initLSize > minLSize) {
			// Stanga sus
			drawTSquare(context, startX - initLSize / 4, startY - initLSize / 4, initLSize / 2, minLSize);
			
			// Dreapta sus
			drawTSquare(context, (startX + initLSize) - initLSize / 4, startY - initLSize / 4, initLSize / 2, minLSize);
																												
			// Stanga jos
			drawTSquare(context, (startX - initLSize / 4), (startY + initLSize) - initLSize / 4, initLSize / 2, minLSize);
			
			// Dreapta jos
			drawTSquare(context, (startX + initLSize) - initLSize / 4, (startY + initLSize) - initLSize / 4, initLSize / 2, minLSize);
		}
	}
}
