// 
// Decompiled by Procyon v0.5.36
// 

package kwadratowa_jw.kw_jw;

import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;

public class Effekty
{
    public static void Spawn_Heath(final Player p) {
        new BukkitRunnable() {
            float i = 0.0f;
            
            public void run() {
                final double j = this.i / 10.0f;
                final double x = Math.cos(j * 3.141592653589793);
                final double y = Math.sin(j * 3.141592653589793);
                Location l = new Location(Bukkit.getWorld("world"), p.getLocation().getX() + 1.0, p.getLocation().getY() + 1.0, p.getLocation().getZ());
                Bukkit.getWorld("world").spawnParticle(Particle.HEART, l, 100);
                l = new Location(Bukkit.getWorld("world"), p.getLocation().getX() - 1.0, p.getLocation().getY() + 1.0, p.getLocation().getZ());
                Bukkit.getWorld("world").spawnParticle(Particle.HEART, l, 100);
                l = new Location(Bukkit.getWorld("world"), p.getLocation().getX(), p.getLocation().getY() + 1.0, p.getLocation().getZ() - 1.0);
                Bukkit.getWorld("world").spawnParticle(Particle.HEART, l, 100);
                l = new Location(Bukkit.getWorld("world"), p.getLocation().getX(), p.getLocation().getY() + 1.0, p.getLocation().getZ() + 1.0);
                Bukkit.getWorld("world").spawnParticle(Particle.HEART, l, 100);
                ++this.i;
                if (this.i >= 5.0f) {
                    this.cancel();
                }
            }
        }.runTaskTimerAsynchronously((Plugin)Main.plugin, 15L, 20L);
    }
    
    public static void Spawn_Heath_rotate(final Player p) {
        new BukkitRunnable() {
            float i = 0.0f;
            Location l;
            
            public void run() {
                final double j = this.i / 10.0f;
                final double x = Math.cos(j * 3.141592653589793);
                final double z = Math.sin(j * 3.141592653589793);
                final double y = Math.tan(j / 15.0 * 3.141592653589793);
                this.l = new Location(Bukkit.getWorld("world"), p.getLocation().getX() + x, p.getLocation().getY() + y, p.getLocation().getZ() + z);
                Bukkit.getWorld("world").spawnParticle(Particle.HEART, this.l, 100);
                ++this.i;
                if (this.i >= 60.0f) {
                    this.l = new Location(Bukkit.getWorld("world"), p.getLocation().getX(), p.getLocation().getY() + 2.0, p.getLocation().getZ());
                    Bukkit.getWorld("world").spawnParticle(Particle.HEART, this.l, 100);
                    Bukkit.getWorld("world").playSound(p.getLocation(), Sound.ENTITY_PLAYER_SWIM, 1.0f, 1.0f);
                    this.cancel();
                }
            }
        }.runTaskTimerAsynchronously((Plugin)Main.plugin, 5L, 1L);
    }
    
    public static void Heart_Sound_Limit(final Player p) {
        Bukkit.getWorld("world").playSound(p.getLocation(), Sound.ENTITY_WANDERING_TRADER_NO, 1.0f, 1.0f);
    }

    public static void Heart_Sound_Add(final Player p) {
        Bukkit.getWorld("world").playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0f, 1.0f);
    }
    public static void Set_Ghost(final  Player p )
    {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 15));
    }
    public static void Set_head(final Player p)
    {
       p.getInventory ().setHelmet(new ItemStack(Material.JACK_O_LANTERN, 1));
    }

}
