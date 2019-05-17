package fudan.se.lab4.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemInfo {


    private String position;
    private static SystemInfo systemInfo;
    private SystemInfo(){}

    /**
     * single instance model.
     * @return an instance
     */
    public static SystemInfo getInstance(){
        if(systemInfo == null){
            systemInfo = new SystemInfo();
        }
        return systemInfo;
    }



    /**
     * to get the order position.
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the fetched position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     *
     * @return the date of the syetem.
     */
    public String getDate(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }
}
