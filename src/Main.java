import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    byte bytes[]= null;

    public Main(){
        try {
//            parseFile("test.txt");
            scanFile("test.txt");

        }catch (IOException e){
            e.printStackTrace();
        }

//        for(byte b: bytes){
//            say(b);
//        }
    }

    private void parseFile(String filename) throws IOException {
        double start = System.currentTimeMillis();
        say("The file is "+ filename);
        FileInputStream fis = new FileInputStream(filename);
        bytes = fis.readAllBytes();
        fis.close();

        double end = System.currentTimeMillis();
        double total = (end-start)/1000;
        say("Finished with "+ " "+total);


    }

    private void scanFile(String filename) throws FileNotFoundException {
        int counter = 0;
        double start = System.currentTimeMillis();
        File f = new File(filename);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()){
            String test = s.nextLine();

            if(test.contains("Error")){
                String[] array = test.split(" ");
                say(array[0]+" the time is  "+array[1]);
                counter++;
//                say(test);
            }
        }
        s.close();



        double end = System.currentTimeMillis();
        double total = (end-start)/1000;
        say("Finished with "+ " "+total+" "+ "the number of times is "+counter);

    }


    public static void say(Object o){
        System.out.println(o);
    }




    public static void main(String args[]){
        new Main();
    }

}