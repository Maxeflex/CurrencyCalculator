public class W_Umrechner {
    
    String[] Waehrungen;
    double[] kurse;

    public W_Umrechner(){
        Waehrungen = new String[10];
        kurse = new double[10] ;
        bestandswaehrungen();
    }

    private void bestandswaehrungen(){
        
        Waehrungen[0] = "EUR";
        kurse[0] = 1.0;

        Waehrungen[1] = "USD";
        kurse[1] = 1.1;

        Waehrungen[2] = "GBP";
        kurse[2] = 0.85;

        Waehrungen[3] = "JPY";
        kurse[3] = 160.0;

        Waehrungen[4] = "CHF";
        kurse[4] = 0.95;

    }


    public double umrechnen(String w1, String w2, double betrag){
        
        double kurs1=-1;double kurs2=-1;

        for (int i = 0; i < Waehrungen.length; i++) {
            
            if (w1.equals(Waehrungen[i])) {
                kurs1 = kurse[i];
            }
            if (w2.equals(Waehrungen[i])) {
                kurs2 = kurse[i];
            }

            if (kurs1 != -1 && kurs2 != -1) {
                break;
            }
        }

        if(kurs1==-1||kurs2==-1){
            return -1;
        }
            
        return betrag / kurs1 * kurs2;
    }

    public int neueWaehrung(String bezeichnung, double kurs){
        
        for (int i = 0; i < Waehrungen.length; i++){
            if(bezeichnung.equals(Waehrungen[i])) {
                return -1;
            } 
        }
        
        for (int i = 0; i < Waehrungen.length; i++) { 
            if (Waehrungen[i]==null){
                Waehrungen[i]=bezeichnung;
                kurse[i]=kurs;
                return 1;
            }
        }
        return -1;
    }

    public int kursWechsel(String bezeichnung,double neuerKurs){

        for (int i = 0; i < Waehrungen.length; i++) {
            if (bezeichnung.equals(Waehrungen[i])){
                kurse[i]=neuerKurs;
                return 1;
            }
        }
        return -1;
    }

}
