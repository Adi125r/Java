import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static javafx.scene.shape.StrokeType.INSIDE;



public class TicTacToeApp extends Application {

    private static boolean ifFirst = true;
    private boolean playable = true;
    private boolean turnX = true;
    private Tile[][] board = new Tile[4][4];
    private List<Combo> combos = new ArrayList<>();
    ChoiceBox choiceBox = new ChoiceBox();
    ChoiceBox choiceBox2 = new ChoiceBox();
    public native String Random( String tab);
    public native String MyAlg(String tab);

    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    private Pane root = new Pane();

    private Parent createContent() {
        root.setPrefSize(400, 500);

        choiceBox.setTranslateX(200);
        choiceBox.setTranslateY(450);
        choiceBox2.setTranslateX(50);
        choiceBox2.setTranslateY(450);
        root.getChildren().addAll(choiceBox,choiceBox2);

        choiceBox2.getItems().add("JavaScript");
        choiceBox2.getItems().add("C++");

        choiceBox2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(choiceBox2.getItems().get((Integer) number2));

                String path="C:\\Users\\adimo\\Desktop\\java\\Lab-9\\js\\";


                if(choiceBox2.getItems().get((Integer) number2)=="JavaScript"){


                File directory = new File(path);
                File[] children = directory.listFiles();
                for (File child : children) {
                    if (child.isFile() && child.getName().contains(".js")) {
                        choiceBox.getItems().add(child.getName());
                    }


                }
            }
            else {
                    choiceBox.getItems().add("Random");
                    choiceBox.getItems().add("Obrony");


                }


            }
        });

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 100);
                tile.setTranslateY(i * 100);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }

        // horizontal
        for (int y = 0; y < 4; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
            combos.add(new Combo(board[1][y], board[2][y], board[3][y]));
        }

        // vertical
        for (int x = 0; x < 4; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
            combos.add(new Combo(board[x][1], board[x][2], board[x][3]));
        }

        // diagonals
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));
        combos.add(new Combo(board[1][0], board[2][1], board[3][2]));
        combos.add(new Combo(board[3][0], board[2][1], board[1][2]));
        combos.add(new Combo(board[0][1], board[1][2], board[2][3]));
        combos.add(new Combo(board[2][1], board[1][2], board[0][3]));
        combos.add(new Combo(board[1][1], board[2][2], board[3][3]));
        combos.add(new Combo(board[3][1], board[2][2], board[1][3]));

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private boolean checkState() {
        for (Combo combo : combos) {
            if (combo.isComplete()) {
                playable = false;
                playWinAnimation(combo);
                return true;
            }
        }
        return false;
    }

    private void playWinAnimation(Combo combo) {
        Line line = new Line();
        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndX(combo.tiles[2].getCenterX());
        line.setEndY(combo.tiles[2].getCenterY());
        line.setStrokeWidth(7);
        line.setStroke(Color.RED);

        root.getChildren().add(line);
    }

    private class Combo {
        private Tile[] tiles;
        public Combo(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty())
                return false;

            return tiles[0].getValue().equals(tiles[1].getValue())
                    && tiles[0].getValue().equals(tiles[2].getValue());
        }

    }





    private class Tile extends StackPane {
        private Text text = new Text();
        long cnt=0;

        public Tile() {
            Rectangle border = new Rectangle(100, 100);
            border.setFill(Color.color(0,1,0));
            border.setStroke(Color.BLACK);
            border.setStrokeType(INSIDE);

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (!playable)
                    return;


                if(!text.getText().isEmpty())
                    return;
                cnt=0;
                drawX();
                turnX = false;


                if(!checkState()){
                int[] tab =enemyTurn();

               board[tab[0]][tab[1]].text.setText("O");
                turnX = true;
                checkState();}

            });
        }

        public double getCenterX() {
            return getTranslateX() + 50;
        }

        public double getCenterY() {
            return getTranslateY() + 50;
        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() {
            text.setText("X");
        }

        private void drawO() {
            text.setText("O");
        }


        public int[] enemyTurn()
        {

            String miniMaxBoard = new String();


            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    miniMaxBoard += board[j][i].text.getText()+" ";

                }
            }

            Object result=null;

            int[] intArray=null;
            if(choiceBox2.getSelectionModel().getSelectedItem().toString()=="JavaScript") {
                try {
                    engine.eval(new FileReader("C:\\Users\\adimo\\Desktop\\java\\Lab-9\\js\\"+choiceBox.getSelectionModel().getSelectedItem().toString()));
                    Invocable invocable = (Invocable) engine;
                    result = invocable.invokeFunction("algorithm", miniMaxBoard);
                } catch (FileNotFoundException | NoSuchMethodException | ScriptException e) {
                    e.printStackTrace();
                }

                String str = (String) result;

                String strArray[] = str.split(" ");

                intArray = new int[strArray.length];
                for (int i = 0; i < strArray.length; i++) {
                    intArray[i] = Integer.parseInt(strArray[i]);
                }
            }
            if(choiceBox2.getSelectionModel().getSelectedItem().toString()=="C++") {
                System.loadLibrary("Tic");
                TicTacToeApp t = new TicTacToeApp();
                String str;
            if (choiceBox.getSelectionModel().getSelectedItem().toString()=="Random") {
                    str = t.Random(miniMaxBoard);
            }
            else {
                str = t.MyAlg(miniMaxBoard);


            }

                String strArray[] = str.split(" ");

                intArray = new int[strArray.length];
                for (int i = 0; i < strArray.length; i++) {
                    intArray[i] = Integer.parseInt(strArray[i]);
                }
               // System.out.println(miniMaxBoard);
                System.out.println(str);


            }




            return intArray;
        }


    }




    public static void main(String[] args) {

        launch(args);
    }
}