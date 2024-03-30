import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Matris> Toplam = new ArrayList<>();
        ThreadDosya1 threadDosya1 = new ThreadDosya1();
        threadDosya1.start();
        ThreadDosya2 threadDosya2 = new ThreadDosya2();
        threadDosya2.start();
        ThreadDosya3 threadDosya3 = new ThreadDosya3();
        threadDosya3.start();

        while (threadDosya1.isAlive() || threadDosya2.isAlive() || threadDosya3.isAlive()) {

        }
        Toplam.addAll(threadDosya1.getMatris());

        Toplam.addAll(threadDosya2.getMatris());

        Toplam.addAll(threadDosya3.getMatris());

        for (int i = 0; i < Toplam.size(); i++) {
            for (int j = i + 1; j < Toplam.size(); j++) {
                if (Toplam.get(i).a.equals(Toplam.get(j).a)) {
                    Toplam.get(i).b += Toplam.get(j).b;
                    Toplam.remove(j);
                    j--;
                }
            }
        }
        for (Matris matris : Toplam) {
            System.out.println(matris.a + " " + matris.b);
        }

    }
}

class Dosya {
    public ArrayList<Matris> dosyaOku(String a) throws IOException {
        String dizin = System.getProperty("user.dir");
        String yol = dizin + "/src/" + a;
        BufferedReader reader = new BufferedReader(new FileReader(yol));
        System.out.println(yol);
        ArrayList<Matris> listB1 = new ArrayList<>();

        String isim;
        while ((isim = reader.readLine()) != null) {
            listB1.add(new Matris(isim.toLowerCase()));

        }
        reader.close();

        return listB1;
    }

    public ArrayList<Matris> say(String ad) throws IOException {

        ArrayList<Matris> List = this.dosyaOku(ad);

        int b = List.size();
        for (int i = 0; i <= b; i++) {
            for (int j = i + 1; j < List.size(); j++) {
                if (List.get(i).a.equals(List.get(j).a)) {
                    List.remove(j);
                    List.get(i).b++;
                    j--;
                }
            }
        }
        return List;
    }

}

class Matris {
    String a;
    int b = 1;

    public Matris(String a) {
        this.a = a;
    }

    public Matris(String a, int b) {
        this.a = a;
        this.b = b;
    }

}

class ThreadDosya1 extends Thread {
    ArrayList<Matris> matris = new ArrayList<Matris>();

    @Override
    public void run() {
        Dosya dosya = new Dosya();
        try {
            matris = dosya.say("bel1");
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public ArrayList<Matris> getMatris() {

        return matris;
    }
}

class ThreadDosya2 extends Thread {
    ArrayList<Matris> matris = new ArrayList<Matris>();

    @Override
    public void run() {
        Dosya dosya = new Dosya();
        try {
            matris = dosya.say("bel2");
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public ArrayList<Matris> getMatris() {

        return matris;
    }
}

class ThreadDosya3 extends Thread {
    ArrayList<Matris> matris = new ArrayList<Matris>();

    public void run() {
        Dosya dosya = new Dosya();
        try {
            matris = dosya.say("bel3");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Matris> getMatris() {

        return matris;
    }
}