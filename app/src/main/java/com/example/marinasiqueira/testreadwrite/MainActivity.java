package com.example.marinasiqueira.testreadwrite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result = read();
        result = result + "/n #Marina test";
       // writeFile(result);
        if(write(result))
            write21();
    }

    public String read(){

        BufferedReader br = null;
        String response = null;

        try {

            StringBuffer output = new StringBuffer();
            //String fpath = "/sdcard/"+fname+".txt";
            String fpath = "system/etc/gps.conf";
            Log.d("main", "going to create BufferedReader");
            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line);
                output.append(System.getProperty("line.separator"));
                Log.d("main", "line: " + line);
            }
            response = output.toString();

        } catch (IOException e) {
            Log.d("main", "exception");
            e.printStackTrace();
            return null;

        }
        return response;

    }


    public Boolean write(String fcontent){
        try {

            String fpath = getFilesDir() + "/gps.conf";
            Log.d("main", "fpath = " + fpath);
            //new BufferedWriter(new FileWriter(getFilesDir() + FILENAME))

            File file = new File(fpath);

            // If file does not exists, then create it
            if (!file.exists()) {
                Log.d("main", "write - file does not exist");
                file.createNewFile();
            }
            Log.d("main", "write - file exists");

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            Log.d("main", "write - created FileWriter");
            BufferedWriter bw = new BufferedWriter(fw);
            Log.d("main", "write - created BR");
            bw.write(fcontent);
            Log.d("main", "write - writed content");
            bw.close();

            Log.d("Suceess","Sucess");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    void writeFile(String data) {
        try {


        File outFile = new File("system/etc/gps.conf");
        FileOutputStream out = new FileOutputStream(outFile, false);
        byte[] contents = data.getBytes();
        out.write(contents);
        out.flush();
        out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Boolean write2(){
        Log.d("main","começando write 2");
        try{


            String filename = getFilesDir() + "/gps.conf";//f.getAbsolutePath();
            Log.d("main","filename = " + filename);
            Runtime r = Runtime.getRuntime();
            //r.exec("adb root");
            Log.d("main", "adb root");

            Process suProcess = r.exec("su");
                   // new ProcessBuilder()
                     //       .command("/system/xbin/su")
                       //     .redirectErrorStream(true).start();
            Log.d("main","exec su");
            DataOutputStream dos = new DataOutputStream(suProcess.getOutputStream());
            Log.d("main","data output stream");
            dos.writeBytes("chown 0.0 " + filename + "\n");
            Log.d("main","chown");
            dos.flush();
            dos.writeBytes("chmod 644 " + filename + "\n");
            Log.d("main","chmod");
            dos.flush();
            //"system/etc/gps.conf"
            dos.writeBytes("mv " + filename + " system/etc/gps.conf\n");
            Log.d("main","mv file");
            dos.flush();
            Log.d("main","finish - success");
        }catch(Exception e){
            Log.d("main", "exception write 2");
            e.printStackTrace();

        }

        return true;
    }

    public Boolean write21(){
        Log.d("main","começando write 21");
        try{
            String filename = getFilesDir() + "/gps.conf";//f.getAbsolutePath();
            Log.d("main","filename = " + filename);
            Process process = Runtime.getRuntime().exec("adb root");
            DataOutputStream dos = new DataOutputStream(process.getOutputStream());
            dos.writeBytes("mv " + filename + " system/etc/gps.conf\n");
            Log.d("main","mv file");
            dos.flush();
            Log.d("main","finish - success");
            /*
            Runtime r = Runtime.getRuntime();
            //r.exec("adb root");
            Log.d("main", "adb root");

            Process suProcess =
             new ProcessBuilder()
                   .command("/system/xbin/su")
                 .redirectErrorStream(true).start();
            Log.d("main","exec su");
            DataOutputStream dos = new DataOutputStream(suProcess.getOutputStream());
            Log.d("main","data output stream");
            dos.writeBytes("chown 0.0 " + filename + "\n");
            Log.d("main","chown");
            dos.flush();
            dos.writeBytes("chmod 644 " + filename + "\n");
            Log.d("main","chmod");
            dos.flush();
            //"system/etc/gps.conf"
            dos.writeBytes("mv " + filename + " system/etc/gps.conf\n");
            Log.d("main","mv file");
            dos.flush();
            Log.d("main","finish - success");*/
        }catch(Exception e){
            Log.d("main", "exception write 2");
            e.printStackTrace();

        }

        return true;
    }
}
