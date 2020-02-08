// 
// Decompiled by Procyon v0.5.36
// 

package loginjw.loginjw;

import Http_minecraft.Callback;
import Http_minecraft.HTTP_Manager;
import Http_minecraft.HTTP_Type;
import Http_minecraft.Response;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;

public final class Main extends JavaPlugin
{
    //= "http://jacwolnnode.eu-4.evennode.com";
    public static String URL = "http://localhost:5000";

    public static ArrayList<String> LoginQueue = new ArrayList<String>();
    public static JavaPlugin instance;
    public void onEnable() {
        instance = this;
        HTTP_Manager.SetDebugMode(true);


        HTTP_Manager.Request(HTTP_Type.GET,URL+"/","[]", new Callback<Response>() {
            @Override
            public void OnSucess(Response data) {
               if(data.Status.equalsIgnoreCase("200") == false)
               {
                   Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Login_JW Problem polaczenia z serverem "+data.Message);
                //   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
               }
               else
               {
                   Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Login_JW Pomyslnie podlaczony");
                   instance.getServer().getPluginManager().registerEvents((Listener)new event(),instance);
                   new cmd((Main) instance);
               }
            }
        });



    }

    public  static JavaPlugin  Get_Instance()
    {
        return instance;
    }


    public void onDisable()
    {

    }


}
