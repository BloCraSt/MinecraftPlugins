package loginjw.loginjw;

import com.google.gson.JsonElement;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.chat.TextComponentSerializer;
import net.minecraft.server.v1_15_R1.ChatMessageType;
import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Messages
{

   /*e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "====================================");
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Witamy nowego gracza na serverze");
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Aby z nami pograc musisz zalorzyc konto");
                    e.getPlayer().sendMessage(ChatColor.GREEN + "/haslo (haslo) (haslo)");
                    e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "====================================");*/

 /*   e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "====================================================");
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Witamy cie " + ChatColor.GRAY + e.getPlayer().getName() + ChatColor.GREEN + " ponownie na serverze");
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Wpisz /login (haslo) ,aby sie zalogowac");
                    e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "====================================================");*/

    public static TextComponent OnJoin (Player player)
    {


        TextComponent napis = new MessageBuilder().Header("Witaj na serverze","*", ChatColor.DARK_GREEN,ChatColor.GREEN).Get_Message();

        napis.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://pl.namemc.com/skin/cfec983c354670ae" ) );
        napis.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage(napis);
        return  null;
    }

    public static TextComponent OnFirstJoin (Player player)
    {
        BaseComponent c = new TextComponent("Witam");
        TextComponent message = new TextComponent( "Click meeeee" );
        message.addExtra(c);
        message.addExtra("DSdadasdasa");
        message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://spigotmc.org" ) );
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage( message );
        return  message;
    }



    public static void AlreadyLogin(Player player)
    {
        TextComponent message = new MessageBuilder().Get_Message("Juz jestes zarejestrowany",MessageType.Warning);
        player.spigot().sendMessage( message );
    }

    public static TextComponent CreatedAccount (Player player)
    {
        BaseComponent c = new TextComponent("Witam");
        TextComponent message = new TextComponent( "Click meeeee" );
        message.addExtra(c);
        message.addExtra("DSdadasdasa");
        message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://spigotmc.org" ) );
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage( message );
        return  message;
    }
    public static TextComponent Password_bad (Player player)
    {
        BaseComponent c = new TextComponent("Witam");
        TextComponent message = new TextComponent( "Click meeeee" );
        message.addExtra(c);
        message.addExtra("DSdadasdasa");
        message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://spigotmc.org" ) );
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage( message );
        return  message;
    }
    public static TextComponent Password_changed (Player player)
    {
        BaseComponent c = new TextComponent("Witam");
        TextComponent message = new TextComponent( "Click meeeee" );
        message.addExtra(c);
        message.addExtra("DSdadasdasa");
        message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://spigotmc.org" ) );
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage( message );
        return  message;
    }
    public static TextComponent Password_Need_to_set (Player player)
    {
        BaseComponent c = new TextComponent("Witam");
        TextComponent message = new TextComponent( "Click meeeee" );
        message.addExtra(c);
        message.addExtra("DSdadasdasa");
        message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://spigotmc.org" ) );
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage( message );
        return  message;
    }
    public static TextComponent Already_Login (Player player)
    {
       /* BaseComponent c = new TextComponent("Witam");
        TextComponent message = new TextComponent( "Click meeeee" );
        message.addExtra(c);
        message.addExtra("DSdadasdasa");
        p.sendMessage(org.bukkit.ChatColor.DARK_GREEN + "=================================");
        p.sendMessage(org.bukkit.ChatColor.GREEN + "!       Juz jestes zalogowany     !");
        p.sendMessage(org.bukkit.ChatColor.DARK_GREEN + "=================================");
        message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://spigotmc.org" ) );
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage( message );
        return  message;*/return null;
    }

    public static void Wiadomosc_Test(Player p)
    {
        TextComponent message = new TextComponent( ChatColor.BLACK+"Moja Wiadomosc \n" );
        message.addExtra(ChatColor.RED+"A to będzie pod spotem \n");
        message.addExtra(ChatColor.GREEN+"A to jeszcze bardziej \n");
        message.addExtra(ChatColor.BOLD+"Pogrubiona wiadomosc \n");
        message.addExtra(ChatColor.ITALIC+"Pochulona ");
        message.addExtra(ChatColor.MAGIC+"magiczna ");
        //po najechaniu myszka pojawi sie link
        message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://google.com" ) );

        //po najechaniu myszka na wiadomsc pojawi sie wpisany tekst
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Ustaw własny teks").create() ) );
        //wysyla wiadomsc do gracza
        p.spigot().sendMessage( message );

    }










}
