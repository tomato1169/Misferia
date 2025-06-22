package azerot.azerot.command;

import azerot.azerot.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadCast extends AbstractCommand {
    public BroadCast() {
        super("broadcast");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        try {
            if (!sender.hasPermission("")) {
                sender.sendMessage(WorldUtils.worldName() + " §cНедостаточно прав!" );
                return;
            }
            if (args.length == 0) {
                sender.sendMessage(WorldUtils.worldName() + " §cНедостаточно аргументов!" );
                return;
            }
            if (args.length > 0) {
                String message = "";
                for (int i = 0; i < args.length; i++)
                    message = message + " " + args[i];
                message = message.replaceAll("&", "§");
                        Bukkit.broadcastMessage(WorldUtils.broadcast() + "§f"+ message);
                return;
            }
        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + " §cВозможно, вы ошиблись в написании команды!" );
        }
    }
}
