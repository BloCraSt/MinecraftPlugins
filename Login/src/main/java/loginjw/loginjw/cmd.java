// 
// Decompiled by Procyon v0.5.36
// 

package loginjw.loginjw;

import Http_minecraft.Callback;
import Http_minecraft.HTTP_Manager;
import Http_minecraft.HTTP_Type;
import Http_minecraft.Response;
import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class cmd implements CommandExecutor
{


    cmd(Main plugin) {
        plugin.getCommand("login").setExecutor((CommandExecutor)this);
        plugin.getCommand("haslo").setExecutor((CommandExecutor)this);
        plugin.getCommand("nowehaslo").setExecutor((CommandExecutor)this);
        plugin.getCommand("test").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        final Player p= (Player)sender;


        if (command.getName().equalsIgnoreCase("test")) {

            Messages.OnJoin(p);
        }

        if (command.getName().equalsIgnoreCase("login")) {
            if (args.length == 0) {
                return true;
            }
            if(Main.LoginQueue.contains(p.getName()) == false)
            {
                p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                p.sendMessage(ChatColor.GREEN + "!       Juz jestes zalogowany     !");
                p.sendMessage(ChatColor.DARK_GREEN + "=================================");
               return true;
            }

            JsonObject object = new JsonObject();
            object.addProperty("PlayerID",p.getUniqueId().toString());
            object.addProperty("P_Pass",args[0]);
            object.addProperty("P_Name",p.getName());
            String jsonobject = "["+object.toString()+"]";

            String URL = Main.URL+"/Plugins/Login";
            HTTP_Manager.Request(HTTP_Type.POST, URL, jsonobject, new Callback<Response>() {
                @Override
                public void OnSucess(Response data) {
                    if(data.Status.equalsIgnoreCase("200"))
                    {
                        p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                        p.sendMessage(ChatColor.GREEN + "!        Witamy na serverze     !");
                        p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                        Main.LoginQueue.remove(p.getName());
                    }
                    if(data.Status.equalsIgnoreCase("406"))
                    {
                        p.sendMessage(ChatColor.DARK_RED + "=================================");
                        p.sendMessage(ChatColor.DARK_RED + "!    Wprowadzono zle haslo      !");
                        p.sendMessage(ChatColor.DARK_RED + "=================================");
                    }
                    if(data.Status.equalsIgnoreCase("404"))
                    {
                        p.sendMessage(ChatColor.DARK_RED + "=======================================");
                        p.sendMessage(ChatColor.DARK_RED + "!    Musisz ustawic haslo /haslo      !");
                        p.sendMessage(ChatColor.DARK_RED + "=======================================");
                    }
                }
            });
        }

        if (command.getName().equalsIgnoreCase("haslo")) {

            if (args.length == 0 &&  Main.LoginQueue.contains(p.getName()) == false ) {
                return true;
            } JsonObject object = new JsonObject();
            object.addProperty("PlayerID",p.getUniqueId().toString());
            object.addProperty("P_Name",p.getName());
            object.addProperty("P_Pass",args[0]);
            object.addProperty("P_Online","1");
            String JsonData = "["+object.toString()+"]";
            String URL = Main.URL+"/Plugins/Login";

            HTTP_Manager.Request(HTTP_Type.PUT, URL, JsonData, new Callback<Response>() {
                @Override
                public void OnSucess(Response data) {

                    if(data.Status.equalsIgnoreCase("200"))
                    {
                        p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                        p.sendMessage(ChatColor.GREEN +      "! Udalo ci sie zalorzyc konto !");
                        p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                        Main.LoginQueue.remove(p.getName());
                    }
                    if(data.Status.equalsIgnoreCase("406"))
                    {
                        p.sendMessage(ChatColor.DARK_RED + "=================================");
                        p.sendMessage(ChatColor.DARK_RED + data.Message);
                        p.sendMessage(ChatColor.DARK_RED + "=================================");
                    }
                    if(data.Status.equalsIgnoreCase("409"))
                    {
                        p.sendMessage(ChatColor.DARK_RED + "=================================");
                        p.sendMessage(ChatColor.DARK_RED + "! Juz jestes zarejestrowany   !");
                        p.sendMessage(ChatColor.DARK_RED + "=================================");
                    }
                }
            });
        }
        if (command.getName().equalsIgnoreCase("nowehaslo")) {
            if (args.length == 0) {
              //  p.sendMessage(PHP_Data.pobierz_wszystko());
                return true;
            }
            if (args.length == 1) {
               // p.sendMessage(PHP_Data.Get_haslo(args[0]));
            }
        }

        return false;
    }
}
