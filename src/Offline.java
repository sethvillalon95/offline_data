public class Offline {

    public  String deviceLabel = "";
    public String time;
    public String duration;
    public String date;
    public String venue = "test venue";

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
        int inMin =Integer.parseInt(initial_t[1]);
        int fMin = Integer.parseInt(final_t[1]);
        int inHr = Integer.parseInt(initial_t[0]);
        int fHr= Integer.parseInt(final_t[0]);

        if(inMin>fMin){
            fHr--;
            fMin+=60;
        }

        int hr = fHr-inHr;
        int min = fMin-inMin;


        double secs = Double.parseDouble(final_t[2])- Double.parseDouble(initial_t[2]);
        if(secs<0){
            min-=1;
            secs +=60;
        }
        duration =Integer.toString(hr)+":"+Integer.toString(min)+":"+Double.toString(secs);


    }

    public void setDeviceLabel(String dl){
        deviceLabel = dl;
    }




}
