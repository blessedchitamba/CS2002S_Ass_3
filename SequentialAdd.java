import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.File;

public class SequentialAdd {
   private static float[][] terrain;
   private static String inputFileName = "sample_input.txt";
   private static String outputFileName = "test_outp.txt";
   private static int xTerrainSize;
   private static int yTerrainSize;
   private static int counter;
   private static int numTrees;
   private static int treeXindex, treeYindex, extension, treeXend, treeYend;
   private static float treeTotal, grandTotal;
   private static float[] totals;
   
   public static void main(String[] args) throws FileNotFoundException {
            //record the start time of the program execution
            long startTime = System.currentTimeMillis();
            
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
            
            //deal with the trees now. take number of trees and initialize totals[]
            numTrees = scanner.nextInt();
            totals = new float[numTrees];
            
            //calculate average sunlight for each tree
            counter = 1;
            treeTotal = 0;
            grandTotal = 0;
            
            while(counter<=numTrees) {
               treeYindex = scanner.nextInt();
               treeXindex = scanner.nextInt();
               extension = scanner.nextInt();
               treeXend = treeXindex+extension;
               treeYend = treeYindex+extension;
               
               //deal with the different cases of trees extending beyond the terrain size
               if (treeXend > xTerrainSize){
                  treeXend = xTerrainSize;
               }
               if (treeYend > yTerrainSize){
                  treeYend = yTerrainSize;
               }
               treeTotal = 0;
               
                  for(int i=treeYindex; i < treeYend; i++){
                    for(int j=treeXindex; j < treeXend; j++){ 
                       treeTotal += terrain[i][j];
                       grandTotal += terrain[i][j];
                    }
                  }
               
               //add the total to totals[] and increment counter
               totals[counter-1] = treeTotal;
               counter++;
            } //end of while loop
            
            //write things to file
            try {
               writeToFile(totals);
            }
            catch (IOException e){}
                        
            //record elapsed time
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Time taken while executing: "+elapsedTime+" millisec.");
      } //end of main method
      
      public static void writeToFile(float[] array) throws IOException {
         FileWriter writer = new FileWriter(outputFileName, true);
         BufferedWriter bf = new BufferedWriter(writer);
         
         bf.write(Float.toString(grandTotal/numTrees));
         bf.newLine();
         bf.write(Integer.toString(numTrees));
         bf.newLine();
         
         //write tree totals
         for(float t: array){
            bf.write(Float.toString(t));
            bf.newLine();
         }
         bf.close();
      }
      
}