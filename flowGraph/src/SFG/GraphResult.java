package SFG;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;


public class GraphResult {


    ISFG sfg ;
    private ArrayList<Double> fpsGains ;
    private ArrayList<ArrayList<Integer>> forwardPaths ;
    private ArrayList<ArrayList<Integer>> loops ;
    private ArrayList<Double> loopsGains ;
    private ArrayList<ArrayList<ArrayList<Integer>>> NonTouchedLoops ;
    private ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> NonTouchedLoopsWithFP ;
    private double delta;
    private ArrayList<Double> fpDeltas ;
    @FXML
    private TextArea result;

    @FXML
    private TextArea TF;


    public GraphResult(){
        this.sfg = Controller.getSFG();
        this.fpsGains = this.sfg.getFpsGains();
        this.forwardPaths = this.sfg.getForwardPaths();
        this.loops = this.sfg.getLoops();
        this.loopsGains = this.sfg.getLoopsGains() ;
        this.delta = this.sfg.getDelta();
        this.fpDeltas = this.sfg.getFpDeltas();
        this.NonTouchedLoops = this.sfg.getNonTouchedLoops();
        this.NonTouchedLoopsWithFP = this.sfg.getNonTouchedLoopsWithFP();
    }

    @FXML
    private void showTF(){
        TF.clear();
        TF.appendText("Over all transfer Function is \n\n");
        TF.appendText(String.valueOf(this.sfg.getOverallTF()));

    }

    @FXML
    private void getForwardPathes(){
        this.result.clear();
        result.appendText("Forward pathes\n\n");
        for (int i = 0 ; i < this.forwardPaths.size() ; i++){
            result.appendText("M"+(i+1)+" = ");
            for (int j = 0 ; j< this.forwardPaths.get(i).size(); j++){
                result.appendText("N"+String.valueOf(this.forwardPaths.get(i).get(j) + 1) + "\t");
            }
            result.appendText(String.valueOf("\n\t- Path Gain  = " + this.fpsGains.get(i))+"\n\n");
        }
    }

    @FXML
    private void getIndividualLoops(){
        this.result.clear();	
        result.appendText("individual loops \n\n");
        for (int i = 0 ; i < this.loops.size() ; i++){
            result.appendText("L"+(i+1)+"=");
            for (int j = 0 ; j< this.loops.get(i).size(); j++){
                result.appendText("N"+String.valueOf(this.loops.get(i).get(j) + 1) + "  ");
            }
            result.appendText(String.valueOf("\t\t( Path Gain  = " + this.loopsGains.get(i))+" )\n");
        }
    }

    @FXML
    private void nTouchingLoops(){
        this.result.clear();
        result.appendText("Non touching Loops \n\n");
        for (int i = 1; i < this.NonTouchedLoops.size() ; i++ ){
            result.appendText((i+1) +"-Non-touched loops\n");
            for (int j = 0 ; j < this.NonTouchedLoops.get(i).size() ; j++){
                result.appendText("[");
                for (int k = 0 ; k <this.NonTouchedLoops.get(i).get(j).size() ; k++){
                    result.appendText("N"+String.valueOf(this.NonTouchedLoops.get(i).get(j).get(k)+1)+" ");
                }
                result.appendText("]\t\t");

            }
            result.appendText("\n");

        }

    }
    @FXML
    private void nTouchingLoopsWithFP(){
        this.result.clear();
        result.appendText("Non touching Loops with FP \n\n");
        for (int y = 0 ; y < this.NonTouchedLoopsWithFP.size() ; y++){
            for (int i = 0; i < this.NonTouchedLoopsWithFP.get(y).size() ; i++ ){
                result.appendText((i+1) +"-Non-touched loops with FP " + (y+1) + "\n");
                for (int j = 0 ; j < this.NonTouchedLoopsWithFP.get(y).get(i).size() ; j++){
                    result.appendText("[");
                    for (int k = 0 ; k <this.NonTouchedLoopsWithFP.get(y).get(i).get(j).size() ; k++){
                        result.appendText("N"+String.valueOf(this.NonTouchedLoopsWithFP.get(y).get(i).get(j).get(k))+" ");
                    }
                    result.appendText("]\t\t");

                }
                result.appendText("\n");

            }

        }

    }


    @FXML
    private void getDeltaValues(){
        this.result.clear();
        result.appendText("Delta values \n\n");
        result.appendText("Delta = " + this.delta + "\n");
        for (int i = 0 ; i < this.fpDeltas.size() ; i ++ ){
            result.appendText("D" + (i+1) + " = " + this.fpDeltas.get(i) + "\n");
        }

    }

}
