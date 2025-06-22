package azerot.azerot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import azerot.azerot.azerot;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import java.sql.SQLException;

public abstract class AbstractCommand implements CommandExecutor {
    public AbstractCommand(String command) {
        PluginCommand pluginCommand = azerot.getInstance().getCommand(command);
        if (pluginCommand != null)
            pluginCommand.setExecutor(this);
    }

    public abstract void execute(CommandSender paramCommandSender, String paramString, String[] paramArrayOfString) throws SQLException;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            execute(sender, label, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
