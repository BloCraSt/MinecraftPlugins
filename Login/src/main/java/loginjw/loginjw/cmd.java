// 
// Decompiled by Procyon v0.5.36
// 

package loginjw.loginjw;

import Http_minecraft.Callback;
import Http_minecraft.HTTP_Manager;
import Http_minecraft.HTTP_Type;
import Http_minecraft.Response;
import com.google.gson.JsonObject;

import org.apache.logging.log4j.message.Message;
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


        if (command.getName().equalsIgnoreCase("login")) {
            if (args.length == 0)
                return true;
            if(Main.LoginQueue.contains(p.getName()) == false)
            {
                Messages.AlreadyLogin(p); //juz jestes zalogowany
               return true;
            }

            JsonObject object = new JsonObject();
            object.addProperty("PlayerID",p.getUniqueId().toString());
            object.addProperty("P_Pass",args[0]);
            object.addProperty("P_Name",p.getName());
            String jsonobject = "["+object.toString()+"]";

            String URL = Main.URL+"/plugins/login";
            HTTP_Manager.Request(HTTP_Type.POST, URL, jsonobject, new Callback<Response>() {
                @Override
                public void OnSucess(Response data) {
                    if(data.Status.equalsIgnoreCase("200"))
                    {
                        Messages.OnJoin(p);
                        Main.LoginQueue.remove(p.getName());
                    }
                    if(data.Status.equalsIgnoreCase("406"))
                      Messages.Password_bad(p);
                    if(data.Status.equalsIgnoreCase("404"))
                       Messages.Password_Need_to_set(p);
                }
            });
        }

        if (command.getName().equalsIgnoreCase("haslo")) {

            if (args.length == 0 &&  Main.LoginQueue.contains(p.getName()) == false ) {
                return true;
            }
            JsonObject object = new JsonObject();
            object.addProperty("PlayerID",p.getUniqueId().toString());
            object.addProperty("P_Name",p.getName());
            object.addProperty("P_Pass",args[0]);
            object.addProperty("P_Online","1");
            String JsonData = "["+object.toString()+"]";
            String URL = Main.URL+"/plugins/login/register";

            HTTP_Manager.Request(HTTP_Type.POST, URL, JsonData, new Callback<Response>() {
                @Override
                public void OnSucess(Response data) {

                    if(data.Status.equalsIgnoreCase("200"))
                    {
                        Messages.CreatedAccount(p);
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
                        Messages.AlreadyLogin(p);
                    }
                }
            });
        }
        if (command.getName().equalsIgnoreCase("nowehaslo")) {
            if (args.length == 1 &&  Main.LoginQueue.contains(p.getName()) == false) {
                JsonObject object = new JsonObject();
                object.addProperty("PlayerID",p.getUniqueId().toString());
                object.addProperty("P_Pass",args[0]);
                String JsonData = "["+object.toString()+"]";
                String URL = Main.URL+"/plugins/login/password";
                p.sendMessage(URL);
                HTTP_Manager.Request(HTTP_Type.POST, URL, JsonData, new Callback<Response>() {
                    @Override
                    public void OnSucess(Response data) {

                        if(data.Status.equalsIgnoreCase("200"))
                        {
                            p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                            p.sendMessage(ChatColor.GREEN +      "! Haslo zostalo zmienione !");
                            p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                            Main.LoginQueue.remove(p.getName());
                        }
                        if(data.Status.equalsIgnoreCase("401"))
                        {
                            p.sendMessage(ChatColor.DARK_RED + "=================================");
                            p.sendMessage(ChatColor.DARK_RED + data.Message);
                            p.sendMessage(ChatColor.DARK_RED + "=================================");
                        }
                        if(data.Status.equalsIgnoreCase("406"))
                        {
                            p.sendMessage(ChatColor.DARK_RED + "=================================");
                            p.sendMessage(ChatColor.DARK_RED + "! haslo powinno byc inne   !");
                            p.sendMessage(ChatColor.DARK_RED + "=================================");
                        }
                    }
                });
            }
        }

        return false;
    }
}
