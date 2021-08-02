import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<Offline> offline_list;
    byte bytes[]= null;
    String deviceLabel = "";

    public Main() throws IOException {
        offline_list = new ArrayList<>();
        say("What is the device label:");
        try {
            Scanner sc = new Scanner(System.in);
            deviceLabel = sc.nextLine();
//            say("Drag the file here:");
//            String file = sc.nextLine();
//            sc.close();
//            scanFile(file);
            scanFile("https-debug-2.txt");
            updateSheet(deviceLabel+".csv");
            say("Thank you please come again! Please don't to format time.");
            sc.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void parseFile(String filename) throws IOException {
        double start = System.currentTimeMillis();
//        say("The file is "+ filename);
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
            if(test.contains("Error ")){
                String[] message = test.split(" ");
                String tmpdate = message[0];
                String tmptime= message[1];
                String[] tempt = tmptime.split(":");
                int hrOf = Integer.parseInt(tempt[0]);
                if(hrOf>15 && hrOf<23){
                    while( s.hasNextLine()){
                        test = s.nextLine();
                        if(test.contains("OK")){
                            break;
                        }
                    }
                    message = test.split(" ");
                    Offline tmp = new Offline(tmpdate, tmptime, message[1]);
                    tmp.setDeviceLabel(deviceLabel);
                    offline_list.add(tmp);
                    counter++;
                }



            }
        }
        s.close();
        double end = System.currentTimeMillis();
        double total = (end-start)/1000;
        say("Finished with "+ " "+total+" "+ "the number of times is "+counter);

    }

    public void updateSheet(String fname) throws IOException {
        File f = new File(fname);
        FileOutputStream fos = new FileOutputStream(f);
        FileWriter fw = new FileWriter(f,true);

        for(Offline ofd:offline_list){
            String temp_data = ofd.deviceLabel+","+ofd.date+","+ofd.time+","+ofd.duration;
            fw.append(ofd.deviceLabel+","+ofd.date+","+ofd.time+","+ofd.duration+"\n");
        }
        fw.close();

    }
    public static void say(Object o){
        System.out.println(o);
    }




    public static void main(String args[]) throws IOException {
        new Main();
    }

}