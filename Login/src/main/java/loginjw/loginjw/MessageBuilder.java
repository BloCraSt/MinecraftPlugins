package loginjw.loginjw;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;

public class MessageBuilder
{

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
          this.title_lenght = title.length()*2+4;
        String str_title="";
        for(int i=0;i<this.title_lenght-title.length();i++)
        {
            if(i<this.title_lenght)
            {
                str_title+=" ";
            }
        }
        str_title +=title;



        TextComponent header_title =new TextComponent(str_title+"\n");
        header_title.setColor(Title_color);
        header_title.setItalic(true);

        this.Bar(pattern,Bar_Color);
        Ret_Message.addExtra(header_title);

        return  this;
    }
    public MessageBuilder Bar(String pattern, ChatColor color)
    {
        String str_bar="";
        for(int i=0;i<this.title_lenght;i++)
        {
            str_bar+=pattern;
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

}
