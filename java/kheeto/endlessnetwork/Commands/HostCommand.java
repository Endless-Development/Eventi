package kheeto.endlessnetwork.Commands;

import kheeto.endlessnetwork.EventiBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class HostCommand extends Command {

    public HostCommand() {
        super("Control");
    }

    public void execute(CommandSender sender, String[] args) {
        Configuration config = EventiBungee.getConfig();

        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            if(!(p.hasPermission("eventi.host"))) {
                p.sendMessage(new ComponentBuilder(config.get("No_Permission").toString()).create());
                return;
            }

            if(args.length > 0) {
                String uhc = config.get("uhc").toString();
                String sumo = config.get("sumo").toString();
                String spleef = config.get("spleef").toString();
                String parkour = config.get("parkour").toString();

                if(args[0].toString().toLowerCase() == uhc) {
                    p.connect(ProxyServer.getInstance().getServerInfo(config.get("UHC_Server").toString()));
                    p.sendMessage(new ComponentBuilder(config.get("Sender_Message").toString()).create());
                    return;
                } else if(args[0].toString().toLowerCase() == sumo) {
                    p.connect(ProxyServer.getInstance().getServerInfo(config.get("Sumo_Server").toString()));
                    p.sendMessage(new ComponentBuilder(config.get("Sender_Message").toString()).create());
                    return;
                } else if(args[0].toString().toLowerCase() == spleef) {
                    p.connect(ProxyServer.getInstance().getServerInfo(config.get("Spleef_Server").toString()));
                    p.sendMessage(new ComponentBuilder(config.get("Sender_Message").toString()).create());
                    return;
                } else if(args[0].toString().toLowerCase() == parkour) {
                    p.connect(ProxyServer.getInstance().getServerInfo(config.get("Parkour_Server").toString()));
                    p.sendMessage(new ComponentBuilder(config.get("Sender_Message").toString()).create());
                    return;
                }
            } else {
                p.sendMessage(new ComponentBuilder(config.get("No_Event").toString()).create());
            }
        }
    }
}
