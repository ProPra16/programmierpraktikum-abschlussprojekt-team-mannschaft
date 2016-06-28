package main.java.tddt.data;

import java.util.ArrayList;
import java.util.List;

public class LogList {

    private List<Log> loglist = new ArrayList<>();
    private String projektPath ;


    public LogList(String projektPath){

        this.projektPath=projektPath;
    }
    public void add(Log l){
        loglist.add(l);

    }
    public void delete(){

        for ( int i = 0; i < loglist.size(); i++ ) {

                loglist.remove(o)   ;
        }
        }

    }
    public Log getLog(int i){
        return Log(int phase ,time,classText,testText,compileMessage)(i); //Log-Objekt(i);
    }

}
