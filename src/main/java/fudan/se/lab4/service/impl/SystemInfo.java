package fudan.se.lab4.service.impl;

/**
 * @author: jiaxing liu
 * @Date: 2019/5/15 9:07
 */
public class SystemInfo {

    //TODO:get and set the date. Considering that the systemInfo is unique, single instance model is used here.
    private String position;
    private String date;
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
     * offer the api for functional programming
     * @param position the fetched position
     * @return the object itself
     */
    public SystemInfo setPosition(String position) {
        this.position = position;
        return this;
    }

}
