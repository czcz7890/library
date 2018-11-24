/*
This program simulates the predictions of the differential cross section of photons scattered from a single free
electron by two group of physicists. The predicted outcome, called Thomson scattering and Klein-Nishina formula, is
compared with each other. As can be seen from the result, the difference between the two prediction is only
noticeable when the incoming electron is at high energy.

*/
public class cross_section {
    public static void main(String arg[]){
        double[] x = {0.0, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0, 110.0, 120.0, 130.0, 140.0, 150.0, 160.0, 170.0, 180.0, 190.0, 200.0, 210.0, 220.0, 230.0, 240.0, 250.0, 260.0, 270.0, 280.0, 290.0, 300.0, 310.0, 320.0, 330.0, 340.0, 350.0, 360.0};
        double[] y = new double[37];
        double[] z = new double[37];
        double[] k = new double[37];

        double E = 662.0;
        System.out.print("[");
        for(int l = 0; l < 15; l++){
            E = 50.0*l;
            for(int i=0; i < 37; i++) {
                y[i] = klein(E, x[i]);
                z[i] = thompson(E, x[i]);
                k[i] = (z[i] - y[i]) / y[i];
                System.out.print(k[i]+", ");
            }
            System.out.print(";"+"\n");
        }
        for(int i=0; i < 37; i++) {
            y[i] = klein(E, x[i]);
            z[i] = thompson(E, x[i]);
            k[i] = (z[i] - y[i]) / y[i];
        }
        System.out.print("Klein-Nishina: "+"\n"+"[");
        for(int j=0; j<37; j++){
            System.out.print(y[j]+", ");
        }
        System.out.println("]");
        System.out.print("Thompsons: "+"\n"+"[");
        for(int j=0; j<37;j++){
            System.out.print(z[j]+", ");
        }
        System.out.println("]");
        System.out.print("% difference respect to E: "+"\n"+"[");
        for(int j=0; j<37;j++){
            System.out.print(k[j]+", ");
        }
        System.out.println("]");

    }
    public static double klein(double En, double deg){
        double e = 2.82 * Math.pow(10, -15);
        double g = En/511.0;
        double output;
        double rad = (Math.PI/180.0)*deg;
        output = (0.5) * Math.pow(e, 2)
                * ((1.0 + Math.pow(Math.cos(rad), 2)) / Math.pow(1.0 + g * (1.0 - Math.cos(rad)), 2))
                * (1.0 + ((g * g * Math.pow(1.0 - Math.cos(rad), 2)) / ((1.0 + Math.pow(Math.cos(rad), 2)) * (1.0 + g * (1.0 - Math.cos(rad))))));
        return output;
    }
    public static double thompson(double En, double deg){
        double e = 2.82 * Math.pow(10, -15);
        double g = En/511.0;
        double output;
        double rad = (Math.PI/180.0)*deg;
        output = (0.5) * Math.pow(e, 2) * (1.0 + Math.pow(rad, 2));
        return output;

    }
}
