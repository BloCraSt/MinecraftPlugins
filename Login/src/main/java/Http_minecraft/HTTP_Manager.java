package Http_minecraft;


import loginjw.loginjw.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTP_Manager {

     static boolean Debug;

    public static void SetDebugMode(boolean arg)
    {
       Debug  = arg;
    }
    public static void Request(final HTTP_Type http_type, String URl, final String jsondata, Callback<Response> respond_function) {

            Bukkit.getScheduler().runTaskAsynchronously(Main.Get_Instance(), new Runnable() {
                @Override
                public void run() {
                    final String result;
                    try {
                        URL url = new URL(URl); //parameter
                        StringBuffer response = new StringBuffer();
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        BufferedReader rd =null;
                        con.setConnectTimeout(5000);
                        con.setUseCaches(false);
                        con.setRequestProperty("Connection", "Keep-Alive");
                        con.setRequestProperty("Cache-Control", "no-cache");
                        con.setRequestProperty("Connection", "close");
                        if(http_type != HTTP_Type.GET)
                        {
                            con.setRequestProperty("Content-Type", "application/json");
                            con.setRequestProperty("Accept", "application/json");
                            con.setDoOutput(true);
                            con.setDoInput(true);
                            con.setRequestMethod(http_type.toString());
                            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
                            osw.write(String.format(jsondata));
                            osw.flush();
                            osw.close();
                        }
                        else
                        {
                            con.setRequestProperty("Content-Type", "application/json");
                            con.setDoOutput(true);
                            con.setRequestMethod("GET");
                        }

                        int statusCode = con.getResponseCode();
                        if (statusCode >= 200 && statusCode < 400) {
                            rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        }
                        else {
                            rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                        }

                           String inputLine;
                           while ((inputLine = rd.readLine()) != null) {
                               response.append(inputLine);
                           }
                           rd.close();
                           Response res = new Response();
                           res.Status = String.valueOf(con.getResponseCode());
                           res.Message = response.toString();
                         if(Debug)
                         {
                             Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Responnse: "+ChatColor.AQUA + res.Message +ChatColor.GREEN + " Status: " + ChatColor.AQUA +res.Status);
                         }


                           Bukkit.getScheduler().runTask(Main.Get_Instance(), new Runnable() {
                               @Override
                               public void run() {
                                   respond_function.OnSucess(res);
                               }
                           });
                    } catch (Exception e) {
                        Bukkit.getServer().getConsoleSender().sendMessage("LoginJW2 "+URl+" NodeJS Error: "+e.getMessage());
                    }


                }
            });


    }
}

