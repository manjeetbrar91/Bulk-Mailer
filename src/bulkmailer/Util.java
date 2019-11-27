/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulkmailer;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MANJEET BRAR
 */
public class Util {

    static final String SETTINGS_FILE = "smtp.conf";

    static Settings loadSettings() {
        File f = new File(SETTINGS_FILE);
        if (f.exists()) {
            try {
                FileReader fileReader = new FileReader(f);
                BufferedReader br = new BufferedReader(fileReader);

                String str;
                StringBuilder buffer = new StringBuilder();
                if ((str = br.readLine()) != null) {
                    buffer.append(str);
                }

                try {
                    br.close();
                    fileReader.close();
                } catch (IOException e) {
                }
                Gson j = new Gson();
                Settings settings = j.fromJson(buffer.toString(), Settings.class);

                return settings;
            } catch (IOException e) {

            }

        }
        return null;
    }

    static void saveSettings(String json) {
        File f = new File(SETTINGS_FILE);
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
