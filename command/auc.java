package azerot.azerot.command;

import azerot.azerot.*;
import azerot.azerot.Cutscene.DeadIsland;
import azerot.azerot.Cutscene.FinalSecondPatch;
import azerot.azerot.message.Clas;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class auc extends AbstractCommand {
    public auc() {
        super("auc");
    }

    public void execute(CommandSender sender, String label, String[] args) throws SQLException {
        Player p = (Player) sender;
        p.setGameMode(GameMode.CREATIVE);
        p.teleport(new Location(Bukkit.getWorld("realm2"), -1500, 100, 1500));
    }
}