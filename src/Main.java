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
    String venue="";

    public Main() throws IOException {
        offline_list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(System.in);
            say("What is the device venue (please enter a number):");
            say("[1] Islands of Polynesia");
            say("[2] Luau");
            say("[3] Gateway Buffet");
            say("[4] Ha Breath of Life");
//            say("");
            int choice = sc.nextInt();
            say("choice is: " + choice);
            switch (choice){
                case 1:
                    venue = "Islands of Polynesia";
                    break;
                case 2:
                    venue = "Luau";
                    break;
                case 3:
                    venue = "Gateway";
                    break;
                case 4:
                    venue = "Ha Breath Of Life";
                    break;
                default:
                    venue = null;
            }

            System.out.println("What is the device label?");
            sc.nextLine();
            deviceLabel = sc.nextLine();

            say("deviceLabel is " + deviceLabel);
//            say("Drag the file here:");
//            String file = sc.nextLine();
//            file.replaceAll(replace the back space);
//            sc.close();
//            scanFile(file);
            scanFile("https-debug.txt");
            say("Thank you please come again! Please don't forget to format time.");
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

    private void scanFile(String filename) throws IOException {
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
                    tmp.setVenue(venue);
                    offline_list.add(tmp);
                    counter++;
                }



            }
        }
        s.close();
        updateSheet(deviceLabel+".csv");
        double end = System.currentTimeMillis();
        double total = (end-start)/1000;
        say("Finished with "+ " "+total+" "+ "the number of times is "+counter);

    }

    public void updateSheet(String fname) throws IOException {
        File f = new File(fname);
        FileOutputStream fos = new FileOutputStream(f);
        FileWriter fw = new FileWriter(f,true);
        fw.append("Device Label, Date, Time, Duration"+"\n");

        for(Offline ofd:offline_list){
            String temp_data = ofd.deviceLabel+","+ofd.date+","+ofd.time+","+ofd.duration+"\n";
            if(ofd.isWithin())
                fw.append(temp_data);
//            fw.append(ofd.deviceLabel+","+ofd.date+","+ofd.time+","+ofd.duration+"\n");
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