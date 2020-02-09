// 
// Decompiled by Procyon v0.5.36
// 

package kwadratowa_jw.kw_jw;

import org.bukkit.plugin.PluginManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import java.io.FileWriter;
import java.io.File;

public class Save
{
    public static void Zapisz() {
        final File file1 = new File(Main.Path + "/Gracze_zycia.json");
        if (!file1.exists()) {
            try {
                final File file2 = new File(Main.Path, "Gracze_zycia.json");
                file2.createNewFile();
                final FileWriter w = new FileWriter(file2);
                w.write("[]");
                w.flush();
                w.close();
            }
            catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
            }
        }
        try {
            final File f = new File(Main.Path + "/Gracze_zycia.json");
            final Gson g = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            final FileWriter w2 = new FileWriter(f);
            final PluginManager Pm = Bukkit.getPluginManager();
           // final String temp = g.toJson((Object)Main.Gracze);
           // w2.write(temp);
            w2.flush();
            w2.close();
        }
        catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
        }
    }
}
