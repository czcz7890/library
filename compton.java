/*
The program takes in an spe files which contains 1000 data points of detected emission from 4 different radioactive
sources to the photo-detector. The data is subtracted with the background light in order to diminish the effect of
light from the light bulbs. With the corrected data points, the program estimates the distribution of scattered light
through gauss distribution. The result of the experiment agrees with Compton Scattering effect which states that
scattering of a photon by a charged particle results in a decrease in energy.
 */
import java.io.*;
import java.util.Scanner;

public class compton{
    public static void main(String args[]) throws FileNotFoundException, IOException{
        System.out.println("State the sample (Cs-137,Ba-131,co-57,Na-22): ");
        Scanner s = new Scanner(System.in);
        int sample = s.nextInt();
        String INPUT_FILE_PATH;

        if(sample == 2){
            INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//2-2-2017//ba-133.spe";
        }else if(sample == 4){
            INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//2-2-2017//Na-22.spe";
        }else if(sample == 3){
            INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//2-2-2017//co-57.spe";
        }else if(sample == 1){
            INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//2-2-2017//cs-137_3.spe";
        }else{
            System.out.println("State the scatterer (Al large, Al small, Cu, None): ");
            sample = s.nextInt();
            if(sample == 1){    
                INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//Al_large//";
                System.out.println("State the scattering angle: ");
                sample = s.nextInt();
                INPUT_FILE_PATH = INPUT_FILE_PATH+sample+" degrees.spe";
                System.out.println(INPUT_FILE_PATH);
            } else if (sample == 2) {
                INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//Al_small//";
                System.out.println("State the scattering angle: ");
                sample = s.nextInt();
                INPUT_FILE_PATH = INPUT_FILE_PATH+sample+" degrees.spe";
                System.out.println(INPUT_FILE_PATH);
            }else if (sample == 3) {
                INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//Cu//";
                System.out.println("State the scattering angle: ");
                sample = s.nextInt();
                INPUT_FILE_PATH = INPUT_FILE_PATH+sample+" degrees.spe";
                System.out.println(INPUT_FILE_PATH);
            }else{
                INPUT_FILE_PATH = "C://Users//Dong//Documents//C2//LAB//senior lab//2-2-2017//cs-137_no scatter.spe";
            }
        }
        String line;
        String sub;
        int sum = 0;
        int index =0;
        File inFile = new File(INPUT_FILE_PATH);
        File backg = new File("C://Users//Dong//Documents//C2//LAB//senior lab//2-2-2017//background.spe");
        BufferedReader read = new BufferedReader(new FileReader(inFile));
        BufferedReader background = new BufferedReader(new FileReader(backg));
        int[] data= new int[2047];
        int[] subtract = new int[2047];
        System.out.println("State the start of the data: ");
        Scanner input = new Scanner(System.in);
        int in1 = input.nextInt();
        System.out.println("State the end of the data: ");
        input = new Scanner(System.in);
        int in2 = input.nextInt();
        in2 = in2 +1;
        for(int i=0; i < 12; i++){
            line = read.readLine();
            sub = background.readLine();
        }

        for(int number = 0; number < 2047 ; number++){
            line = read.readLine();
            sub = background.readLine();
            String digit = "";
            String digitsub = "";
            for(int k = 0; k < line.length(); k++){
                if(Character.isDigit(line.charAt(k))){
                    digit += line.charAt(k);
                }
            }
            for(int m = 0; m < sub.length(); m++){
                if(Character.isDigit(sub.charAt(m))) {
                    digitsub += sub.charAt(m);
                }
            }
            data[index] = Integer.parseInt(digit);
            subtract[index] = Integer.parseInt(digitsub);
            data[index] = Math.min(Math.abs(data[index]-subtract[index]),data[index]);
            index++;
        }
        int max = 0,min = 0;
        System.out.print("x= [");
        for(int h = in1; h < in2; h++){
            sum = sum + data[h];
            if(data[h] > max){
                max = data [h];
            }if(data[h] < min){
                min = data [h];
            }
            System.out.print(h+",");
        }
        System.out.println("]");
        double avg = sum/((double)in2-(double)in1);
        double variance=0;
        System.out.print("y= [");
        for(int h = in1; h < in2; h++){
            variance = variance + (data[h]-avg)*(data[h]-avg);
            System.out.print(data[h]+",");
        }
        System.out.println("]");
        variance = variance/((double)in2-(double)in1);
        double dev = (double) max - avg;
        System.out.println("max: "+max);
        System.out.println("sum: "+sum);
        System.out.println("mean: "+avg);
        System.out.println("variance: "+variance);


    }
}
