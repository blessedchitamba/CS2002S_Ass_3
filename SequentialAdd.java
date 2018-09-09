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
   
   public static void main(String[] args) throws FileNotFoundException {
      File input = new File(inputFileName);
      Scanner scanner = new Scanner(input);
     
            String[] firstLine = scanner.nextLine().split(" ");
            xTerrainSize = Integer.parseInt(firstLine[0]); yTerrainSize = Integer.parseInt(firstLine[1]);
            
            //load the terrain matrix
            terrain = new float[xTerrainSize][yTerrainSize];
            //scanner.nextLine();
            counter = 0;
            for (int i=0; i<yTerrainSize; i++){
               for (int j=0; j<xTerrainSize; j++) {
                  terrain[i][j] = scanner.nextFloat();
               }
            }   
            
            //test
            for (int i=0; i<yTerrainSize; i++){
               for (int j=0; j<xTerrainSize; j++) {
                  System.out.print(terrain[i][j]+" ");
               }
               System.out.println();
            }
      }
}