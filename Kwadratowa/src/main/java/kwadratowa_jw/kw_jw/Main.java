// 
// Decompiled by Procyon v0.5.36
// 

package kwadratowa_jw.kw_jw;

import Http_minecraft.Callback;
import Http_minecraft.HTTP_Manager;
import Http_minecraft.HTTP_Type;
import Http_minecraft.Response;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
    public static String Path;
    public static String URL = "http://jacwolnnode.eu-4.evennode.com//plugins/kwadratowa";
    public static Map<String, Date> DeadPlayers;
    public static ArrayList<String> PlayersMaxHealths;
    public static JavaPlugin plugin;
    public void onEnable() {


        plugin = this;

        HTTP_Manager.SetDebugMode(true);
        HTTP_Manager.Request(HTTP_Type.GET,"http://jacwolnnode.eu-4.evennode.com/","[]", new Callback<Response>() {
            @Override
            public void OnSucess(Response data) {
                if (data.Status.equalsIgnoreCase("200") == false) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Kwadratowa_JW Problem polaczenia z serverem " + data.Message);
                } else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Kwadratowa_JW Pomyslnie podlaczony");
                    plugin.getServer().getPluginManager().registerEvents((Listener) new Events((Main) plugin), plugin);
                    new Cmd((Main) plugin);
                    DeadPlayers = new HashMap<String, Date>();
                    PlayersMaxHealths = new ArrayList<String>();
                }
            }
        });
    }

    public static JavaPlugin Get_Instance()
    {
        return plugin;
    }

    public void onDisable() {

    }




}
