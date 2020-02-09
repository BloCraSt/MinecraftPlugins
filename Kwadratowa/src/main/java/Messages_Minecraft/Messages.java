package Messages_Minecraft;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
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


        TextComponent napis = new MessageBuilder().Header("Witaj na serverze","*", ChatColor.DARK_GREEN, ChatColor.GREEN).Get_Message();

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

    public static void RespawnMessage (Player player)
    {

        String [] msgs= new String[]{"Masz dwie opcje ,aby wrocic na bozy swiat",
                "Odczekaj bana",
                "- Popros znajomego aby podarowal ci "+org.bukkit.ChatColor.AQUA + "Diament",
                "- Kupic zycie na naszej "+org.bukkit.ChatColor.ITALIC+"Stronie Internetowej "
        };

        TextComponent napis = new MessageBuilder().Header("!! Umarles !!","=", ChatColor.DARK_GREEN, ChatColor.GREEN).List(msgs,"* ",ChatColor.GREEN).Get_Message();
        napis.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://pl.namemc.com/skin/cfec983c354670ae" ) );
        napis.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage(napis);
    }

    public static void HelpMessageADMIN(Player player)
    {

        String [] msgs= new String[]{ChatColor.GREEN + " /km zabij (nick) " + ChatColor.WHITE + "zabijasz gracza",
                ChatColor.GREEN + " /km odrodz  " + ChatColor.WHITE + "odradzasz sie",
                ChatColor.GREEN + " /km smierci (nick)  " + ChatColor.WHITE + "ustawia liczbe smierci",
        };

        TextComponent napis = new MessageBuilder().Header("","=", ChatColor.DARK_GREEN, ChatColor.GREEN).List(msgs,"* ",ChatColor.GREEN).Get_Message();
        napis.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://pl.namemc.com/skin/cfec983c354670ae" ) );
        napis.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage(napis);
    }
    public static void HelpMessage(Player player)
    {
        String [] msgs= new String[]{
                org.bukkit.ChatColor.GREEN + " /km ulecz (nick) " + org.bukkit.ChatColor.WHITE + "Musisz trzymac diament ,aby uleczy gracza",
                org.bukkit.ChatColor.GREEN + " /km gracz (nick) " + org.bukkit.ChatColor.WHITE + "Wyswital statystyki gracza",
        };

        TextComponent napis = new MessageBuilder().Header("","=", ChatColor.DARK_GREEN, ChatColor.GREEN).List(msgs,"* ",ChatColor.GREEN).Get_Message();
        napis.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://pl.namemc.com/skin/cfec983c354670ae" ) );
        napis.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage(napis);
    }
    public static void PlayerStatsMsg(Player player,String name,String lifes,String Deaths)
    {

        String [] msgs= new String[]{
                org.bukkit.ChatColor.GREEN + " Gracz " + org.bukkit.ChatColor.WHITE + name,
                org.bukkit.ChatColor.DARK_GREEN + "",
                org.bukkit.ChatColor.GREEN + " - Zycia   : " + org.bukkit.ChatColor.GRAY + lifes + org.bukkit.ChatColor.RED + " \u2764",
                org.bukkit.ChatColor.GREEN + " - Smierci : " + org.bukkit.ChatColor.GRAY + Deaths + org.bukkit.ChatColor.WHITE + " \u2620"
        };

        TextComponent napis = new MessageBuilder().Header("","=", ChatColor.DARK_GREEN, ChatColor.GREEN).List(msgs,"* ",ChatColor.GREEN).Get_Message();
        napis.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://pl.namemc.com/skin/cfec983c354670ae" ) );
        napis.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the Spigot website!").create() ) );
        player.spigot().sendMessage(napis);
    }








}
