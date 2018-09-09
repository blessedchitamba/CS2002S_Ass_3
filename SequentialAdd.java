import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.File;

public class SequentialAdd {
   private static float[][] terrain;
   private static String inputFileName = "test_inp.txt";
   private static String outputFileName;
   private static int xTerrainSize;
   private static int yTerrainSize;
   private static int counter;
   private static int numTrees;
   private static int treeXindex, treeYindex, extension;
   private static float treeTotal, grandTotal;
   
   public static void main(String[] args) throws FileNotFoundException {
      File input = new File(inputFileName);
      Scanner scanner = new Scanner(input);
     
            String[] firstLine = scanner.nextLine().split(" ");
            xTerrainSize = Integer.parseInt(firstLine[0]); yTerrainSize = Integer.parseInt(firstLine[1]);
            
            //load the terrain matrix
            terrain = new float[xTerrainSize][yTerrainSize];
            for (int i=0; i<yTerrainSize; i++){
               for (int j=0; j<xTerrainSize; j++) {
                  terrain[i][j] = scanner.nextFloat();
               }
            }   
            
            //deal with the trees now. take number of trees
            numTrees = scanner.nextInt();
            
            //calculate average sunlight for each tree
            counter = 1;
            treeTotal = 0;
            grandTotal = 0;
            
            while(counter<=numTrees) {
               treeXindex = scanner.nextInt();
               treeYindex = scanner.nextInt();
               extension = scanner.nextInt();
               treeTotal = 0;
               
               //deal with the 4 different cases of trees extending beyond the terrain size
               if(treeXindex+extension < xTerrainSize && treeYindex+extension < yTerrainSize) {
                  for(int i=treeYindex; i < treeYindex+extension; i++){
                    for(int j=treeXindex; j < treeXindex+extension; j++){ 
                       treeTotal += terrain[i][j];
                       grandTotal += terrain[i][j];
                    }
                  }
               } else if (treeXindex+extension > xTerrainSize && treeYindex+extension > yTerrainSize) {
                    for(int i=treeYindex; i < yTerrainSize; i++){
                       for(int j=treeXindex; j < xTerrainSize; j++){ 
                          treeTotal += terrain[i][j];
                          grandTotal += terrain[i][j];
                       }
                  }
               } else if (treeXindex+extension > xTerrainSize) {
                     for(int i=treeYindex; i < treeYindex+extension; i++){
                       for(int j=treeXindex; j < xTerrainSize; j++){ 
                          treeTotal += terrain[i][j];
                          grandTotal += terrain[i][j];
                       }
                  }
               } else {
                     for(int i=treeYindex; i < yTerrainSize; i++){
                       for(int j=treeXindex; j < treeXindex+extension; j++){ 
                          treeTotal += terrain[i][j];
                          grandTotal += terrain[i][j];
                       }
                  }
               }

               System.out.println("Tree "+counter+": "+treeTotal+" hours of sunlight.");
               counter++;
            } //end of while loop
      } //end of main method
}