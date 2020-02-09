// 
// Decompiled by Procyon v0.5.36
// 

package kwadratowa_jw.kw_jw;

import Http_minecraft.Callback;
import Http_minecraft.HTTP_Manager;
import Http_minecraft.HTTP_Type;
import Http_minecraft.Response;
import Messages_Minecraft.Messages;
import com.google.gson.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.xml.parsers.ParserConfigurationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Events implements Listener
{
    Main main;
    
    Events(final Main main) {
        this.main = main;
    }
    
    @EventHandler
    public boolean P_Chat(final AsyncPlayerChatEvent e) {
        String uuid =e.getPlayer().getUniqueId().toString();
        if(Main.DeadPlayers.containsKey(uuid))
        {
            e.setFormat(ChatColor.WHITE + "\u2620 [Martwy] \u2620 " + ChatColor.GRAY + "<" + e.getPlayer().getName() + "> " + e.getMessage());
        }
        return true;
    }
    @EventHandler
    public boolean P_Login(final PlayerLoginEvent e) {

        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();

        HTTP_Manager.Request(HTTP_Type.PUT, Main.URL+"/"+uuid,"[]", new Callback<Response>() {
            @Override
            public void OnSucess(Response data) {

            }
        });
        return false;
    }
    @EventHandler
    public boolean P_Death(final PlayerDeathEvent e) {

        Player p = e.getEntity();
        String uuid = p.getUniqueId().toString();
        String cause = e.getDeathMessage();

        Location loc = p.getLocation();
         int x = loc.getBlockX();
         int y =loc.getBlockY();
         int z =loc.getBlockZ();
         String poz = String.format("(%1$d,%2$d,%3$d)",x,y,z);

        JsonObject object = new JsonObject();
        object.addProperty("PlayerID",uuid);
        object.addProperty("D_cause",cause);
        object.addProperty("D_Location",poz);
        String jsonobject = "["+object.toString()+"]";

        if(Main.PlayersMaxHealths.contains(uuid))
        {
            Main.PlayersMaxHealths.remove(uuid);
        }

        HTTP_Manager.Request(HTTP_Type.PUT, Main.URL+"/deaths/"+uuid,jsonobject, new Callback<Response>() {
            @Override
            public void OnSucess(Response data) {
                //Gracz_fun.PlayerDeath(p);



            }
        });
        return true;
    }
    @EventHandler
    public boolean P_Move(final PlayerMoveEvent e) {

          String uuid =e.getPlayer().getUniqueId().toString();
        if (e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockZ() == e.getFrom().getBlockZ()) return true; //The player hasn't moved
            if(Main.DeadPlayers.containsKey(uuid))
            {
                Date date = Main.DeadPlayers.get(uuid);
                e.getPlayer().sendMessage(ChatColor.RED + "Jestes martwy ,data odrodzenia : " + ChatColor.WHITE +date.toString());
                e.setCancelled(true);
            }


              return true;
    }
    @EventHandler
    public boolean P_Respawn(final PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();

        HTTP_Manager.Request(HTTP_Type.GET, Main.URL+"/"+uuid,"[]", new Callback<Response>() {
            @Override
            public void OnSucess(Response data)
            {
                           JsonParser jsonParser = new JsonParser();
                           JsonArray jsonArray = (JsonArray) jsonParser.parse(data.Message);
                           jsonParser = new JsonParser();
                           JsonObject json = (JsonObject) jsonParser.parse(jsonArray.get(0).toString());

                if (json.get("PD_IsDeath").toString().equalsIgnoreCase("1")) {
                    Messages.RespawnMessage(p);
                    Effekty.Set_Ghost(p);
                    Effekty.Set_head(p);
                    String Strdate = json.get("PD_UnbanDate").toString().replaceAll("\"","").replace("T"," ").replace("Z","");
                    int dot = Strdate.indexOf(".");
                    Strdate = Strdate.substring(0,dot);
                   try
                    {
                          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                          Date date = formatter.parse(Strdate);
                          Main.DeadPlayers.put(p.getUniqueId().toString(),date);
                    }catch (ParseException ex){p.sendMessage(ex.toString());}
                }
                else
                {
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Ilosc zyc : " + ChatColor.AQUA + json.get("PD_life").toString() + ChatColor.GREEN);
                }
            }
        });
        return true;
    }
    @EventHandler
    public boolean P_UseHealth(final PlayerInteractEvent e) {

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.DIAMOND))
            {
                Player p = e.getPlayer();
                String uuid = p.getUniqueId().toString();

                if(Main.PlayersMaxHealths.contains(uuid))
                {
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Masz maksymalna ilosc zyc");
                    Effekty.Heart_Sound_Limit(p);
                    return  true;
                }

                Location loc = p.getLocation();
                int x = loc.getBlockX();
                int y =loc.getBlockY();
                int z =loc.getBlockZ();
                String poz = String.format("(%1$d,%2$d,%3$d)",x,y,z);

                JsonObject object = new JsonObject();
                object.addProperty("PlayerID",uuid);
                object.addProperty("L_healer",uuid);
                object.addProperty("L_Location",poz);
                String jsonobject = "["+object.toString()+"]";

                HTTP_Manager.Request(HTTP_Type.PUT, Main.URL+"/Lifes/"+uuid,jsonobject, new Callback<Response>() {
                    @Override
                    public void OnSucess(Response data) {

                        if(data.Status.equalsIgnoreCase("405"))
                        {
                            e.getPlayer().sendMessage(ChatColor.GREEN + "Masz maksymalna ilosc zyc");
                            Effekty.Heart_Sound_Limit(p);
                            Main.PlayersMaxHealths.add(uuid);
                        }
                        if(data.Status.equalsIgnoreCase("201"))
                        {
                            Effekty.Spawn_Heath_rotate(p);
                            Effekty.Heart_Sound_Add(p);
                            final int ItemAmount = e.getPlayer().getInventory().getItemInMainHand().getAmount();
                            e.getPlayer().getInventory().getItemInMainHand().setAmount(ItemAmount - 1);
                            e.getPlayer().sendMessage(ChatColor.GREEN + "Kolejne " + ChatColor.RED + " \u2764 zycie \u2764 " + ChatColor.GREEN + " zostalo dodane");
                        }

                    }
                });
            }
            return true;
    }
}
