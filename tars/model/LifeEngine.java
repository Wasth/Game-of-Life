package tars.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tim on 20.01.16.
 */
public class LifeEngine {
    private String field [] [];
    private String fieldTmp [] [];
    private ArrayList<Integer> cX;
    private ArrayList<Integer> cY;
    private ArrayList<Boolean> changes;
    int changesConut = 0;
    private int maxSizeX;
    private int maxSizeY;
    public LifeEngine(int x,int y){
        maxSizeX = x;
        maxSizeY = y;
        System.out.printf("Размеры поля %d на %d\n",maxSizeX,maxSizeY);
        this.field = new String[maxSizeX][maxSizeY];
        this.fieldTmp = new String[maxSizeX][maxSizeY];
        cX = new ArrayList<Integer>();
        cY = new ArrayList<Integer>();
        changes = new ArrayList<Boolean>();
        changesConut = 0;
        for(int i = 0;i < maxSizeX;i++){
            for(int j = 0;j < maxSizeY;j++){
                field[i][j] = "dead";
                fieldTmp[i][j] = field[i][j];
            }
        }

    }

    public String[][] nextGeneration(){
        for(int i = 0; i < maxSizeX;i++){
            for(int j = 0; j < maxSizeY;j++){
                if(i == 0 & j == 0){
                    int numOfneigh = 0;
                    if(field[i+1][j].equals("alive")) numOfneigh++;
                    if(field[i+1][j+1].equals("alive")) numOfneigh++;
                    if(field[i][j+1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }
                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }
                    }
                }else if(i == (maxSizeX-1) & j == (maxSizeY-1)){
                    int numOfneigh = 0;
                    if(field[i-1][j].equals("alive")) numOfneigh++;
                    if(field[i-1][j-1].equals("alive")) numOfneigh++;
                    if(field[i][j-1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }
                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }
                    }

                }else if(i == 0 & j == (maxSizeY-1)){
                    int numOfneigh = 0;
                    if(field[i+1][j].equals("alive")) numOfneigh++;
                    if(field[i+1][j-1].equals("alive")) numOfneigh++;
                    if(field[i][j-1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }
                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }
                    }

                }else if(i == (maxSizeX-1) & j == 0) {
                    int numOfneigh = 0;
                    if(field[i-1][j].equals("alive")) numOfneigh++;
                    if(field[i-1][j+1].equals("alive")) numOfneigh++;
                    if(field[i][j+1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }
                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }
                    }
                }else if(i == 0 & j != (maxSizeY-1) & j != 0){
                    int numOfneigh = 0;
                    if(field[i+1][j].equals("alive")) numOfneigh++;
                    if(field[i+1][j+1].equals("alive")) numOfneigh++;
                    if(field[i+1][j-1].equals("alive")) numOfneigh++;
                    if(field[i][j-1].equals("alive")) numOfneigh++;
                    if(field[i][j+1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }

                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }

                    }

                }else if(j == 0 & i != (maxSizeX-1) & i != 0){
                    int numOfneigh = 0;
                    if(field[i-1][j].equals("alive")) numOfneigh++;
                    if(field[i+1][j].equals("alive")) numOfneigh++;
                    if(field[i-1][j+1].equals("alive")) numOfneigh++;
                    if(field[i+1][j+1].equals("alive")) numOfneigh++;
                    if(field[i][j+1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }
                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }

                    }

                }else if(i == (maxSizeX-1) & j != 0 & j != (maxSizeY-1)){
                    int numOfneigh = 0;
                    if(field[i][j+1].equals("alive")) numOfneigh++;
                    if(field[i-1][j+1].equals("alive")) numOfneigh++;
                    if(field[i-1][j].equals("alive")) numOfneigh++;
                    if(field[i-1][j-1].equals("alive")) numOfneigh++;
                    if(field[i][j-1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }

                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }

                    }

                }else if(j == (maxSizeY-1) & i != 0 & i != (maxSizeX-1)) {
                    int numOfneigh = 0;
                    if(field[i-1][j].equals("alive")) numOfneigh++;
                    if(field[i-1][j-1].equals("alive")) numOfneigh++;
                    if(field[i][j-1].equals("alive")) numOfneigh++;
                    if(field[i+1][j-1].equals("alive")) numOfneigh++;
                    if(field[i+1][j].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }

                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }

                    }

                }else{
                    int numOfneigh = 0;
                    if(field[i][j+1].equals("alive")) numOfneigh++;
                    if(field[i+1][j+1].equals("alive")) numOfneigh++;
                    if(field[i+1][j].equals("alive")) numOfneigh++;
                    if(field[i+1][j-1].equals("alive")) numOfneigh++;
                    if(field[i][j-1].equals("alive")) numOfneigh++;
                    if(field[i-1][j-1].equals("alive")) numOfneigh++;
                    if(field[i-1][j].equals("alive")) numOfneigh++;
                    if(field[i-1][j+1].equals("alive")) numOfneigh++;
                    System.out.printf("Клетка "+i+" "+j+" мертва. Кол-во соседей: "+numOfneigh);
                    if(field[i][j].equals("dead")){
                        if(numOfneigh == 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(true);
                            System.out.printf(". Становится живой.\n");
                        }else{
                            System.out.printf(". Становится мертвой.\n");
                        }

                    }else if(field[i][j].equals("alive")){
                        if(numOfneigh != 2 & numOfneigh != 3){
                            cX.add(i);
                            cY.add(j);
                            changes.add(false);
                            System.out.printf(". Становится мертвой.\n");
                        }else{
                            System.out.printf(". Становится живой.\n");
                        }
                    }

                }
            }
        }
        for(int i = 0;i < changes.size();i++){
            if(changes.get(i)) {
                field[cX.get(i)][cY.get(i)] = "alive";
            }else{
                field[cX.get(i)][cY.get(i)] = "dead";
            }
        }

        return field;
    }
    public int getMaxSizeX(){
        return maxSizeX;
    }
    public int getMaxSizeY(){
        return maxSizeY;
    }
    public String[][] getField() {
        return field;
    }
    public void setField(String[][] field) {
        this.field = field;
    }
    public void setValue(int x,int y,String value){
        this.field[x][y] = value;
    }
    public void clearField(){
        this.field = null;
    }
}
