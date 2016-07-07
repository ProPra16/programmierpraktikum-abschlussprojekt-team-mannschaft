package main.java.tddt.data;

import main.java.tddt.gui.dialogs.CreateExerciseController;

import java.io.*;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    creates a local copy of the exercises-catalog
    after that, the user can select and create exercises
 */
public class CreateExerciseCatalog {

    /*
        constructor of CreateExerciseCatalog
        creates catalogFile and copys all files from resFile to catalogFile
     */
    public CreateExerciseCatalog(File resFile, File catalogFile) throws Exception {
        File[] files = resFile.listFiles();
        catalogFile.mkdirs();
        for (File file : files) {
            copyFile(file, new File(catalogFile.getAbsolutePath() + System.getProperty("file.separator") + file.getName()));
        }
    }

    /*
        copys file to dest
     */
    private static void copyFile(File file, File dest) throws Exception {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest, true));
        int bytes = 0;
        while ((bytes = in.read()) != -1) {
            out.write(bytes);
        }
        in.close();
        out.close();
    }
}
