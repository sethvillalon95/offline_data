public class Offline {

    public  String deviceLabel = "";
    public String time;
    public String duration;
    public String date;
    public String venue = "test venue";

    private String initial_time;
    private String final_time;
    private int hour = 0;

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
        double inSec =  Double.parseDouble(initial_t[2]);
        double fSec = Double.parseDouble(final_t[2]);

        if(inSec>fSec){
            fMin--;
            fSec+=60;

        }
        if(inMin>fMin){
            fHr--;
            fMin+=60;
        }

        int hr = Math.abs(fHr-inHr);
        int min = fMin-inMin;

        hour = inHr;




        double secs = fSec-inSec;
        if(secs<0){
            min-=1;
            secs +=60;
        }
        duration =Integer.toString(hr)+":"+Integer.toString(min)+":"+Double.toString(secs);
//        Main.say(duration);


    }
    public boolean isWithin(){
        String[] temp = venue.split(" ");
        switch (temp[0]){
            case "Islands":
                if(hour>14 && hour<21)
                    return true;
                break;
            case "Luau":
            case "Gateway":
                if(hour>18 && hour<23)
                    return true;
                break;
            case "Ha":
                if(hour>21 && hour<24)
                    return true;
                break;
            default:
                Main.say("Error at isWithin() Method");
                return false;
        }
        return false;
    }

    public void setDeviceLabel(String dl){
        deviceLabel = dl;
    }
    public void setVenue(String v){
        venue=v;
    }




}
