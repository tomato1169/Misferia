package azerot.azerot.events;

import azerot.azerot.GenerateInventory;
import azerot.azerot.ItemsForGui;
import azerot.azerot.azerot;
import azerot.azerot.rpgplayer;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetSlot;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;


public class CraftingMenu implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        (new BukkitRunnable() {
            public void run() {
                InventoryView view = player.getOpenInventory();
                if (CraftingMenu.this.isPlayerCraftingInv(view)) {
                    if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
                        return;
                    if (!player.isOnline()) {
                        cancel();
                        return;
                    }
                    setItems(player, ItemsForGui.CharacterInfo(),ItemsForGui.PlayerNPC(rpgplayer.getRPGPlayer(player)), ItemsForGui.quest(), ItemsForGui.bestiary());
                }
            }
        }).runTaskTimerAsynchronously((Plugin)azerot.getPlugin(azerot.class), 0L, 5L);
    }

    public final int PLAYER_CRAFT_INV_SIZE = 5;
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        InventoryView view = player.getOpenInventory();
        if (isPlayerCraftingInv(view)) {
            view.getTopInventory().clear();
        }
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        InventoryView view = event.getPlayer().getOpenInventory();
        if (isPlayerCraftingInv(view)) {
            view.getTopInventory().clear();
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryView view = event.getView();
        if (isPlayerCraftingInv(view)) {
            view.getTopInventory().clear();
        }
    }


    private boolean isPlayerCraftingInv(InventoryView view) {
        return view.getTopInventory().getSize() == PLAYER_CRAFT_INV_SIZE;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getClickedInventory().getSize() != PLAYER_CRAFT_INV_SIZE) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        rpgplayer rpgPlayer = rpgplayer.getRPGPlayer(player);

        int slot = event.getSlot();

        if (player.getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        switch (slot) {
            case 1:
                player.openInventory(GenerateInventory.playerInfo(rpgPlayer));
                event.setCancelled(true);
                break;
            case 3:
                player.openInventory(GenerateInventory.allPlayerQuest(rpgPlayer));
                event.setCancelled(true);
                break;
            case 2:
                event.setCancelled(true);
                break;
            case 4:
                player.openInventory(azerot.getInventorys().get("InvBestiary"));
                event.setCancelled(true);
                break;
        }
    }
    void setItems(Player player, ItemStack... itemStacks) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();

        for (int i = 1; i <= 4; i++) {
            ItemStack itemStack = itemStacks[i - 1];
            PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(entityPlayer.defaultContainer.windowId, i, CraftItemStack.asNMSCopy(itemStack));

            entityPlayer.playerConnection.sendPacket(packet);
        }
    }
}
