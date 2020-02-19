package loginjw.loginjw;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;




public class MessageBuilder
{
    int bar_lenght = 45;
    int title_lenght =0;
    String pattern;
    ChatColor Bar_color;
    TextComponent Ret_Message;

    public MessageBuilder()
    {
        Ret_Message = new TextComponent();
    }


    public MessageBuilder Header (String title, String pattern, ChatColor Bar_Color, ChatColor Title_color)
    {

          this.pattern = pattern;
          this.Bar_color = Bar_Color;
          this.title_lenght = this.bar_lenght/2;
        String str_title="";
        for(int i=0;i<this.title_lenght;i++)
        {
            if(i<this.title_lenght)
            {
                str_title+=" ";
            }

        }


        str_title +=title;

        StringBuilder myName = new StringBuilder(str_title);
        myName.setCharAt(this.title_lenght-5, '!');


        TextComponent header_title =new TextComponent(myName.toString()+"  !"+"\n");
        header_title.setColor(Title_color);
        header_title.setItalic(true);

        this.Bar(pattern,Bar_Color);
        Ret_Message.addExtra(header_title);

        return  this;
    }
    public MessageBuilder Bar(String pattern, ChatColor color)
    {
        String str_bar="";
        for(int i=0;i<45;i++)
        {
            if(i>8 && i < 45-8)
            str_bar+=pattern;
            else
                str_bar +=" ";
        }
        TextComponent header =new TextComponent(str_bar+"\n");
        header.setColor(color);
        header.setBold(true);
        Ret_Message.addExtra(header);

        return  this;
    }
    public  TextComponent Get_Message()
    {
        this.Bar(pattern,Bar_color);
        return Ret_Message;
    }


    public TextComponent Get_Message(String msg,MessageType type)
    {
        ChatColor light = TypeColor(type)[0];
        ChatColor dark = TypeColor(type)[1];
        this.Header(msg,"=",dark,light);
        return Get_Message();
    }


    public  ChatColor[] TypeColor(MessageType type)
    {
        switch (type)
        {
            case OK:
                return new ChatColor[] {ChatColor.GREEN,ChatColor.DARK_GREEN};
            case Error:
                return new ChatColor[] {ChatColor.RED,ChatColor.DARK_RED};
            case Info:
                return new ChatColor[] {ChatColor.GRAY,ChatColor.DARK_GRAY};
            case Warning:
                return new ChatColor[] {ChatColor.YELLOW,ChatColor.GOLD};
        }
        return new ChatColor[] {ChatColor.GREEN,ChatColor.DARK_GREEN};
    }

}
