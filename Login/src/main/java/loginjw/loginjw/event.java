// 
// Decompiled by Procyon v0.5.36
// 

package loginjw.loginjw;

import Http_minecraft.Callback;
import Http_minecraft.HTTP_Manager;
import Http_minecraft.HTTP_Type;
import Http_minecraft.Response;
import com.google.gson.JsonElement;
import net.minecraft.server.v1_15_R1.ChatMessageType;
import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class event implements Listener
{

    @EventHandler(priority = EventPriority.HIGHEST)
    public boolean Player_login(final PlayerJoinEvent e) {



         String URL = Main.URL + "/players/" + e.getPlayer().getUniqueId();
        HTTP_Manager.Request(HTTP_Type.GET, URL, "[]", new Callback<Response>() {
            @Override
            public void OnSucess(Response data) {

                if (data.Message.equalsIgnoreCase("[]")) {
                    Messages.OnFirstJoin(e.getPlayer());
                }
                else {
                    Messages.OnJoin(e.getPlayer());
                }
                Main.LoginQueue.add(e.getPlayer().getName());
            }
        });
        return false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public boolean Player_Move(final PlayerMoveEvent e) {
        if (Main.LoginQueue.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public boolean Player_Exit(final PlayerQuitEvent e)
    {
        String URL = Main.URL + "/plugins/login/Exit/" + e.getPlayer().getUniqueId();

        HTTP_Manager.Request(HTTP_Type.POST, URL, "[]", new Callback<Response>() {
            @Override
            public void OnSucess(Response data) {
            }
        });
        return false;
    }
}
