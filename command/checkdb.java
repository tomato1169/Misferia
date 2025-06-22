package azerot.azerot.command;

import azerot.azerot.GenerateInventory;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class checkdb extends AbstractCommand{
    public checkdb() {
        super("checkdb");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        try {
            String db = (args.length > 0) ? args[0] : null;
            int id = (args.length > 1) ? Integer.parseInt(args[1]) : 1;
            Player p = (Player) sender;
            if(db.equals("mobs")){
                p.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            } else if(db.equals("items")){
                if(azerot.getInventorys().get("InvForItemInMobs: " + id) == null){
                    p.sendMessage(WorldUtils.worldName() + " §4Предмета с таким номером не существует!");
                }else{
                    p.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
                }
            }

        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + "3" );
            exception.printStackTrace();
        }
    }
}
