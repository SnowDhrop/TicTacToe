package org.sample.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static void main(String[] args) {
        launch(TicTacToe.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        BorderPane layout = new BorderPane();

        Label informationText = new Label("Turn: X");
        informationText.setFont(Font.font("Monospaced", 40));

        layout.setTop(informationText);

        GridPane grid = new GridPane();

        String[][] buttonArray = new String[3][3];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Button buttonGrid = new Button(" ");
                buttonGrid.setFont(Font.font("Monospaced", 40));
                grid.add(buttonGrid, x, y);

                int column = x;
                int row = y;

                buttonGrid.setOnAction((e) -> {

                    if (!buttonGrid.getText().trim().isEmpty()) {
                        return;
                    }

                    if (informationText.getText().equals("The end!")) {
                        return;
                    }

                    if (informationText.getText().equals("Turn: X")) {
                        buttonGrid.setText("X");
                        buttonArray[column][row] = "X";

                        if (this.checkIfWin(buttonArray, column, row, "X")) {
                            informationText.setText("The end!");
                        } else {
                            informationText.setText("Turn: O");
                        }

                    } else {
                        buttonGrid.setText("O");
                        buttonArray[column][row] = "O";

                        if (this.checkIfWin(buttonArray, column, row, "O")) {
                            informationText.setText("The end!");
                        } else {
                            informationText.setText("Turn: X");
                        }
                    }
                });
            }
        }

        layout.setCenter(grid);

        Scene index = new Scene(layout);
        window.setScene(index);
        window.show();
    }

    private boolean checkIfWin(String[][] buttonArray, int column, int row, String btn) {

        if (checkColumn(buttonArray, column, row, btn)) {
            return true;
        };

        if (checkRow(buttonArray, column, row, btn)) {
            return true;
        }

        if (checkDiagonal(buttonArray, column, row, btn)) {
            return true;
        }

        return false;
    }

    private boolean checkColumn(String[][] buttonArray, int column, int row, String btn) {
        int isSame = 0;

        int newColumn = column;
        int iteration = 0;

        while (iteration < 2) {
            newColumn++;

            if (newColumn > 2) {
                newColumn = 0;
            }

            if (btn.equals(buttonArray[newColumn][row])) {
                isSame++;
            }
            iteration++;
        }

        if (isSame == 2) {
            return true;
        }

        return false;
    }

    private boolean checkRow(String[][] buttonArray, int column, int row, String btn) {
        int isSame = 0;

        int newRow = row;
        int iteration = 0;

        // CHECK COLUMN
        while (iteration < 2) {
            newRow++;

            if (newRow > 2) {
                newRow = 0;
            }

            if (btn.equals(buttonArray[column][newRow])) {
                isSame++;
            }
            iteration++;
        }

        if (isSame == 2) {
            return true;
        }

        return false;
    }

    private boolean checkDiagonal(String[][] buttonArray, int column, int row, String btn) {
        int isSame = 0;

        int newRow = row;
        int newColumn = column;
        int iteration = 0;

        // CHECK COLUMN
        while (iteration < 2) {
            newRow++;
            newColumn++;

            if (newRow > 2) {
                newRow = 0;
            }

            if (newColumn > 2) {
                newColumn = 0;
            }

            if (btn.equals(buttonArray[newColumn][newRow])) {
                isSame++;
            }
            iteration++;
        }

        if (isSame == 2) {
            return true;
        }

        return false;
    }
}