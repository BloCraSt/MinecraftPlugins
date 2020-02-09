// 
// Decompiled by Procyon v0.5.36
// 

package kwadratowa_jw.kw_jw;

import Http_minecraft.Callback;
import Http_minecraft.HTTP_Manager;
import Http_minecraft.HTTP_Type;
import Http_minecraft.Response;
import Messages_Minecraft.Messages;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Cmd implements CommandExecutor {
    JavaPlugin main;

    public Cmd(JavaPlugin plugin) {
        plugin.getCommand("KM_admin").setExecutor((CommandExecutor) this);
        plugin.getCommand("KM").setExecutor((CommandExecutor) this);
        plugin.getCommand("Test").setExecutor((CommandExecutor) this);
        this.main = plugin;
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("Test")) {


            return true;
        }
      /* if (command.getName().equalsIgnoreCase("KM_admin")) {
            if (args.length == 0) {
                return true;
            }
            if (!p.isOp()) {
                return true;
            }
            if (args[0].equalsIgnoreCase("pomoc")) {
                p.sendMessage(ChatColor.DARK_GREEN + "=================================");
                p.sendMessage(ChatColor.GREEN + " /zabij (nick) " + ChatColor.WHITE + "zabijasz gracza");
                p.sendMessage(ChatColor.GREEN + " /odrodz  " + ChatColor.WHITE + "odradzasz sie");
                p.sendMessage(ChatColor.GREEN + " /smierci (nick)  " + ChatColor.WHITE + "ustawia liczbe smierci");
                p.sendMessage(ChatColor.DARK_GREEN + "=================================");
            }
            if (args[0].equalsIgnoreCase("zabij")) {
                if (args.length == 1) {
                    Gracz_fun.Gracz_ustaw_zycia(p.getName(), "0");
                    p.setHealth(0.0);
                    return true;
                }
                if (args.length == 2) {
                    Gracz_fun.Gracz_ustaw_zycia(args[1], "0");
                    Bukkit.getPlayer(args[1]).setHealth(0.0);
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("smierci")) {
                if (args.length == 1) {
                    Gracz_fun.Gracz_ustaw_smierci(p.getName(), Integer.parseInt(args[1]));
                    return true;
                }
                if (args.length == 2) {
                    Gracz_fun.Gracz_ustaw_smierci(args[1], Integer.parseInt(args[2]));
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("odrodz")) {
                if (args.length == 1) {
                    Gracz_fun.Gracz_odrodz(p.getName());
                    return true;
                }
                if (args.length == 2) {
                    Gracz_fun.Gracz_odrodz(args[1]);
                    return true;
                }
            }
        } */
        if (command.getName().equalsIgnoreCase("KM")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.GREEN + " /km pomoc " + ChatColor.WHITE + "Wpisz ,aby uzykac wiecej informacji");
                return true;
            }
            if (args[0].equalsIgnoreCase("pomoc")) {
                Messages.HelpMessage(p);
            }
            if (args[0].equalsIgnoreCase("ulecz")) {
                if (!p.getInventory().getItemInMainHand().getType().equals((Object) Material.DIAMOND)) {
                    p.sendMessage(ChatColor.WHITE + "Musisz trzymac " + ChatColor.AQUA + " Diament " + ChatColor.WHITE + " w dloni");
                    return false;
                }
                final int ilosc = p.getInventory().getItemInMainHand().getAmount();
                if (ilosc == 0) {
                    return false;
                }
                if (args.length == 2) {

                    p.getInventory().getItemInMainHand().setAmount(ilosc - 1);
                    p.sendMessage("Gracz " + ChatColor.GREEN + args[1] + ChatColor.WHITE + " otrzymal od ciebie jedno" + ChatColor.RED + " \u2764 zycie \u2764");
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Bukkit.getPlayer(args[1]).sendMessage("Gracz " + ChatColor.GREEN + p.getName() + ChatColor.WHITE + " podarowal ci jedno" + ChatColor.RED + " \u2764 zycie \u2764");
                    }
                }
            }


        if (args[0].equalsIgnoreCase("gracz") && args.length == 2) {

            String uuid = Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString();

            HTTP_Manager.Request(HTTP_Type.GET, Main.URL+"/"+uuid,"[]", new Callback<Response>() {
                @Override
                public void OnSucess(Response data) {

                      if(data.Message.equalsIgnoreCase("[]"))
                         p.sendMessage(ChatColor.RED + " Nie znaleziono gracza " + ChatColor.WHITE + args[1]);
                      else
                      {
                          JsonParser jsonParser = new JsonParser();
                          JsonArray jsonArray = (JsonArray) jsonParser.parse(data.Message);
                          jsonParser = new JsonParser();
                          JsonObject json = (JsonObject) jsonParser.parse(jsonArray.get(0).toString());
                          Messages.PlayerStatsMsg(p,args[1],json.get("PD_Deaths").toString(),json.get("PD_lifes").toString());
                      }



            }});

        }


    }
        return false;
}}
