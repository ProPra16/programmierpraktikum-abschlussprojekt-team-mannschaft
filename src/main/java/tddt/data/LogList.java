package main.java.tddt.data;

import java.util.ArrayList;
import java.util.List;

public class LogList {

    private List<Log> loglist = new ArrayList<Log>();
    private String projektPath ;


    public LogList(String projektPath){

        this.projektPath=projektPath;
    }
    public void add(Log l){
        loglist.add(l);

    }
    public void delete(){

        for ( int i = 0; i < loglist.size(); i++ ) {
            loglist.remove(i)   ;
        }

    }
    public Log getLog(int i){
        return loglist.get(i); //Log-Objekt(i);
    }

}
