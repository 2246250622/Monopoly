import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The GUI class
 * @author Kevin Wang
 *
 * There are plenty of room to refactor GUIGameboard to better reuse the code written in Gameboard.
 * Yet, this may probably increase the difficulty for students to understand the class Gameboard.
 * Thus this is written as at the current stage.
 *
 *
 */
public class GUIGameboard extends Application  {
    private static final int CELL_SIZE = 22;
    private Cell[] cells;
    private int turn = 0; //whos turn
    private Player[] players = {new Player("Kevin"), new Player("Sandy"), new Player("Emily")};
    private TextFieldStreamer tfs;
    private CellLabel[] labelCells = new CellLabel[CELL_SIZE];


    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField;

    @FXML
    private Label labelPlayers;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView tableView;

    @FXML
    void onChange(ActionEvent event) {
        tfs.actionPerformed(event);
    }

    public static boolean kickoff() {
        try {
            GUIGameboard.launch(GUIGameboard.class);
        } catch (Exception e) {
            System.out.println("Error in loading the GUI");
            return false;
        }
        return true;
    }

    @FXML
    public void initialize() {
        cells = Gameboard.initCell();
        for (int i = 0; i < CELL_SIZE; i++) {
            labelCells[i] = new CellLabel(cells[i]);
            anchorPane.getChildren().add(labelCells[i]);
            labelCells[i].setPrefSize(90,130);
            labelCells[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
        for (int i = 0; i < 9; i++) {
            labelCells[i].setLayoutX(90 * i);
            labelCells[i].setLayoutY(0);

        }
        labelCells[9].setLayoutX(720);
        labelCells[9].setLayoutY(130);
        labelCells[10].setLayoutX(720);
        labelCells[10].setLayoutY(260);
        for (int i = 11; i < 20; i++) {
            labelCells[i].setLayoutX(90 * (19-i));
            labelCells[i].setLayoutY(390);
        }
        labelCells[20].setLayoutX(0);
        labelCells[20].setLayoutY(260);
        labelCells[21].setLayoutX(0);
        labelCells[21].setLayoutY(130);

        ((TableColumn)tableView.getColumns().get(0)).setCellValueFactory( new Callback<TableColumn.CellDataFeatures<Player, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Player, String> cd) {
                SimpleStringProperty sp = new SimpleStringProperty();
                sp.setValue(cd.getValue().getName());
                return sp;
            }

        });
        ((TableColumn)tableView.getColumns().get(1)).setCellValueFactory( new Callback<TableColumn.CellDataFeatures<Player, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Player, String> cd) {
                SimpleStringProperty sp = new SimpleStringProperty();
                sp.setValue(cd.getValue().getMoney()+"");
                return sp;
            }

        });

        tableView.getItems().add(players[0]);
        tableView.getItems().add(players[1]);
        tableView.getItems().add(players[2]);

        tfs = new TextFieldStreamer(textField);
        System.setIn(tfs);
        PrintStream p = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(p);

        print();
        System.err.println("end 1st print");
        System.err.println("eg?");
        System.err.println(labelCells[0]== null);

        Button b = new Button("Start");
        b.setOnAction(Event -> {
            b.setVisible(false);
            Thread th = new Thread() {
                @Override
                public void run() {
                runOnce();
            }};
            th.setDaemon(true);
            th.start();
        });
        anchorPane.getChildren().add(b);
    }


    public void runOnce() {
        print();
        while (!gameOver()) {

            Player currentPlayer = players[turn];
            currentPlayer.roll();
            print();

            Cell currentCell = cells[currentPlayer.getPosition()];

            currentCell.event(currentPlayer, cells);
            turn = (turn + 1) % players.length; //next Player
        }
        System.out.println("Game over");
    }

    private void print() {
        tableView.refresh();
        for (int i = 0; i < CELL_SIZE; i++) {
            labelCells[i].setCell(cells[i]);
            String playersName = "";
            for (Player p : players) {
                if (p.getPosition() == i)
                    playersName += p.getName() + "\n";
            }
            labelCells[i].setPlayerLabel(playersName);
        }

    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("../../../Desktop/COMP2026-2122PA3-master/guigameboard.fxml"));
            Scene scene = new Scene(root, 810, 520);

            stage.setTitle("GUI Gameboard");
            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {System.out.println("can't load fxml"); System.err.println(e);}
     }
    private boolean gameOver() {
        for (int i = 0; i < players.length; i++)
            if (players[i].getMoney() < 0)
                return true;
        return false;
    }
}

class CellLabel extends VBox {
    Label titleLabel;
    Label ownerLabel;
    Label costLabel;
    Label playerLabel;
    public void setCell(Cell c) {
        Platform.runLater( () -> {
            titleLabel.setText(c.getName());
            if (c instanceof PropertyCell) {
                PropertyCell p = (PropertyCell) c;
                String ownerText = (p.getOwner() == null) ? "" : p.getOwner().getName();
                if (p.getHouse() != 0) {
                    ownerText += " Hse: " + p.getHouse();
                }
                ownerLabel.setText(ownerText);
                costLabel.setText("$" + p.getCost());
                if (p.getOwner() != null) {
                    this.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"),null,null)));
                }
            }
            if (c instanceof FunctionCell)
                this.setBackground(new Background(new BackgroundFill(Paint.valueOf("yellow"),null,null)));
        });
    }
    public void setPlayerLabel(String p) {
        Platform.runLater(() -> playerLabel.setText(p));
    }
    public CellLabel(Cell c) {

        titleLabel = new Label();
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        ownerLabel = new Label();
        ownerLabel.setTextAlignment(TextAlignment.CENTER);
        costLabel = new Label();
        costLabel.setTextAlignment(TextAlignment.CENTER);

        playerLabel = new Label();
        setCell(c);
        getChildren().add(titleLabel);
        getChildren().add(ownerLabel);
        getChildren().add(costLabel);
        getChildren().add(playerLabel);

    }
}


//ref: https://stackoverflow.com/questions/9244108/redirect-system-in-to-swing-component
class TextFieldStreamer extends InputStream {

    private TextField tf;
    private String str = null;
    private int pos = 0;

    public TextFieldStreamer(TextField tf) {
        this.tf = tf;
    }

    public void actionPerformed(ActionEvent e) {
        str = tf.getText() + "\n";
        pos = 0;
        tf.setText("");
        synchronized (this) {
            //maybe this should only notify() as multiple threads may
            //be waiting for input and they would now race for input
            this.notify();
        }
    }

    @Override
    public int read() {
        //test if the available input has reached its end
        //and the EOS should be returned
        if(str != null && pos == str.length()){
            str =null;
            //this is supposed to return -1 on "end of stream"
            //but I'm having a hard time locating the constant
            return java.io.StreamTokenizer.TT_EOF;
        }
        //no input available, block until more is available because that's
        //the behavior specified in the Javadocs
        while (str == null || pos >= str.length()) {
            try {
                //according to the docs read() should block until new input is available
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        //read an additional character, return it and increment the index
        return str.charAt(pos++);
    }
}

// ref: https://stackoverflow.com/questions/5107629/how-to-redirect-console-content-to-a-textarea-in-java
class CustomOutputStream extends OutputStream {
    private TextArea textArea;

    public CustomOutputStream(TextArea textArea) {
        this.textArea = textArea;
        textArea.setWrapText(true);

    }
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        Platform.runLater( () -> {
            textArea.setText(textArea.getText() + String.valueOf((char) b));
            textArea.setScrollTop(Double.MAX_VALUE);
        });
    }
}