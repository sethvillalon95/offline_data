public class Offline {

    public  String deviceLabel;
    public String time;
    public String duration;
    public String date;

    private String initial_time;
    private String final_time;

    public Offline(){

    }

    public Offline(String d, String t, String ft){
        date = d;
        time = t;
        initial_time = t;
        final_time = ft;
        calculateDuration();
    }

    public void calculateDuration(){
        String[] initial_t = initial_time.split(":");
        String[] final_t = final_time.split(":");

        int hr = Integer.parseInt(final_t[0])-Integer.parseInt(initial_t[0]);
        int min = Integer.parseInt(final_t[1])-Integer.parseInt(initial_t[1]);
        double secs = Double.parseDouble(final_t[2])- Double.parseDouble(initial_t[2]);
        if(secs<0){
            min-=1;
            secs +=60;

        }
//        Main.say("The final is"+ final_t[2]);
        duration ="Duration: "+Integer.toString(hr)+":"+Integer.toString(min)+":"+Double.toString(secs);
        Main.say(duration);
//        Main.say(time +" was the time");


    }

//    public void

}
