package fractals;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KochKurve {
	public void makeWindowForKochKurve(Stage primaryStage, double startX, double startY, double endX, double endY,
			double size, double lineWidth) {
		// Construim un nou GridPane care sa contina elementul canvas
		GridPane secondGrid = new GridPane();
		secondGrid.setAlignment(Pos.CENTER);

		// Atasam obiectului Scene elementul GridPane, dimensiunile precizate + culoarea
		// de bkg
		Scene canvasScene = new Scene(secondGrid, 1600, 800, Color.WHITE);

		// Construim elementul Canvas si setam contextul la 2D
		Canvas canvas = new Canvas(1600, 800);
		GraphicsContext context = canvas.getGraphicsContext2D();
		context.setFill(Color.WHITE);
		context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		drawKochCurve(context, startX, startY, endX, endY, size, lineWidth);

		// Atasam elementul Canvas la GridPanes
		secondGrid.getChildren().add(canvas);

		// Construim a doua podea si-i adaugam scena formata din Canvas
		Stage newWindow = new Stage();
		newWindow.setTitle("Koch Curve");
		newWindow.setScene(canvasScene);

		// Setam pozitia noii ferestre in raport cu fereastra initiala primita
		// ca argument in metoda
		newWindow.setX(primaryStage.getX() - 550);
		newWindow.setY(primaryStage.getY() - 100);

		// Afisam fereastra
		newWindow.show();
	}

	private void drawKochCurve(GraphicsContext context, double startX, double startY, double endX, double endY,
			double size, double width) {
		double deltaX, deltaY, newXStart, newYStart, newXEnd, newYEnd, topXCoord, topYCoord;

		// Apelam recursiv pana la o anumita dimensiune a dreptei noastre
		// Tinem cont ca dreapta se imparte in 4 sectiuni de fiecare data
		if (width > 100) {

			deltaX = endX - startX;
			deltaY = endY - startY;

			// Noile coordonate finale pentru prima dreapta
			newXStart = startX + deltaX / 3;
			newYStart = startY + deltaY / 3;

			// Noile coordonate finale pentru a doua dreapta -> varful triunghiului
			// echilateral
			topXCoord = (int) (0.5 * (startX + endX) - (Math.sqrt(3) / 6) * (startY - endY));
			topYCoord = (int) (0.5 * (startY + endY) - (Math.sqrt(3) / 6) * (endX - startX));

			// Noile coordonate pentru a treia dreapta
			newXEnd = startX + 2 * deltaX / 3;
			newYEnd = startY + 2 * deltaY / 3;

			// Apelurile recursive vor genera pe rand cele 4 drepte
			drawKochCurve(context, startX, startY, newXStart, newYStart, size, width - 100);
			drawKochCurve(context, newXStart, newYStart, topXCoord, topYCoord, size, width - 100);
			drawKochCurve(context, topXCoord, topYCoord, newXEnd, newYEnd, size, width - 100);
			drawKochCurve(context, newXEnd, newYEnd, endX, endY, size, width - 100);
		} else {
			context.setLineWidth(size);
			context.strokeLine(startX, startY, endX, endY);
		}
	}
}
