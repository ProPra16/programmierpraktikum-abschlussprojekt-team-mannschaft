package main.java.tddt.features;

import vk.core.api.*;
/**
 * Created by renato on 28.06.16.
 */
public class Compile {

    public InternalCompiler comp;

    public Compile(CompilationUnit[] units){
        comp = new InternalCompiler(units);     //erstellen der zu kompilierenden Daten
    }

    public static String Compileresults(Compile c){
        c.comp.compileAndRunTests();
        String message = c.comp.getCompilerResult().toString();
        message = message + c.comp.getTestResult().toString();
        return message;
    }

}
