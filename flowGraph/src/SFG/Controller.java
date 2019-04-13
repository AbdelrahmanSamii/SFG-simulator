package SFG;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    static ISFG sfg ;


    @FXML
    private Pane pane ;

    @FXML
    private TextField Gain;

    @FXML
    private TextField from;
    @FXML
    private TextField to;

    @FXML
    private TextField numberOfNodes;

    @FXML
    private Stage result;

    private int NodesNum ;


    @FXML
	private void Result() throws IOException{
        this.sfg.doProcessing();
        result = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("graphResult.fxml"));
        result.setScene(new Scene(root));
        result.setResizable(false);
        result.show();
	}


    @FXML
    private void getNodesNumber() throws IOException {
        String text  = numberOfNodes.getText();
        this.NodesNum = Integer.parseInt(text);
        sfg = new SFG(NodesNum);
        this.numberOfNodes.clear();
        this.pane.getChildren().clear();
        DrawNodes();
    }
    private void DrawNodes() throws IOException{
        Circle circle;

        for (int i = 0 ; i < this.NodesNum ; i ++)
        {
            circle = new Circle();
            pane.getChildren().add(circle);
            circle.setCenterY(250);
            circle.setCenterX(50 + i*90);
            circle.setRadius(15);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
            String text = String.valueOf(i+1);
            Text num = new Text(text);
            pane.getChildren().add(num);
            num.setLayoutX(45 + i*90);
            num.setLayoutY(254);


        }
    }

    @FXML
    private void drawArc() throws IOException{
        String text  = this.from.getText();
        int from = Integer.parseInt(text);
        text = this.to.getText();
        int to = Integer.parseInt(text);
        text = this.Gain.getText();
        if (from > NodesNum || to > NodesNum)
            return;
        if ((from < 1 ) || (to < 1))
            return;
        double gain = Double.parseDouble(text);
        this.from.clear();
        this.to.clear();
        this.Gain.clear();
        text = String.valueOf(gain);
        Text edgeGain = new Text(text);
        if ((to - from) == 1){
            Line line = new Line();
            line.setStrokeWidth(3);
            line.setStroke(Color.GREEN);
            line.setStartX(50 + (from-1)*90 + 15);
            line.setStartY(250);
            line.setEndX(50 + (to-1)*90 - 15);
            line.setEndY(250);
            edgeGain.setLayoutX((90 + (from-1)*90 + (to-1)*90)/2 );
            edgeGain.setLayoutY(240);
            this.pane.getChildren().add(line);
        }
        else
        {
            QuadCurve arc = new QuadCurve();
            arc.setFill(null);
            arc.setStrokeWidth(3);
            if (from < to){
                arc.setStroke(Color.GREEN);
                arc.setStartX(50 + (from-1)*90 + 8.5);
                arc.setStartY(250 -8.5);
                arc.setEndX(50 + (to-1)*90 - 7.5);
                arc.setEndY(250 - 15);
                arc.setControlX((from*90 + to*90)/2);
                arc.setControlY(200 - Math.abs(from-to)*30);
                edgeGain.setLayoutX((from*90 + to*90  -35)/2);
                edgeGain.setLayoutY(240 - arc.getBoundsInParent().getHeight());
            }

            else{
                arc.setStroke(Color.RED);
                arc.setStartX(50 + (from-1)*90 - 9);
                arc.setStartY(250 + 10);
                arc.setEndX(50 + (to-1)*90 + 7.5);
                arc.setEndY(250 + 15);
                arc.setControlX((from*90 + to*90)/2);
                arc.setControlY(280 + Math.abs(from-to)*30);
                edgeGain.setLayoutX((from*90 + to*90  -35)/2);
                edgeGain.setLayoutY(270 + arc.getBoundsInParent().getHeight());
            }
            pane.getChildren().add(arc);

        }

        pane.getChildren().add(edgeGain);


        this.sfg.addLink(from - 1 ,to - 1,gain);
    }

    @FXML
	public static ISFG getSFG() {
		return sfg ;
		}


}
