package me.rootdeibis.bedwars.common.utils;

import me.rootdeibis.bedwars.BedwarsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LoggerUtils {

    private final String prefix;
    private static final String LINE_SEPARATOR = String.join("=",  Arrays.stream(new String[40]).map(d -> "=").collect(Collectors.joining()));

    public LoggerUtils(Class clazz) {
        this.prefix = "["+ BedwarsPlugin.getInstance().getDescription().getName() + "|"+ clazz.getSimpleName()+"]";
    }

    public void debug(String message) {
        this.print(String.format("&9%s &7> &9" + message, prefix));
    }

    public void error(String message) {
        this.print(String.format("&c%s &7> &c" + message, prefix));
    }

    public void error(Exception e) {
        this.error(LINE_SEPARATOR);
        this.error(e.getLocalizedMessage());
        for(StackTraceElement t : e.getStackTrace()) {
            this.error("&8" + t.toString());
        }
        this.error(LINE_SEPARATOR);
    }

    public void success(String message) {
        this.print(String.format("&a%s &7> &a" + message, prefix));
    }


    private void print(String messages) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
    }
}
