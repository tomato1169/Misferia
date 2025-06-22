package azerot.azerot;

import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.questScript;
import azerot.azerot.regions.regionManager;
import azerot.azerot.wheel.firstWheel;
import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;
import com.sk89q.worldguard.bukkit.event.block.BreakBlockEvent;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class Handler implements Listener {
    private static final int PLAYER_CRAFT_INV_SIZE = 5 ;



    @EventHandler
    public void onSandFalling(BlockSpreadEvent e){
       if(e.getBlock().getType() == Material.DAYLIGHT_DETECTOR){
           e.setCancelled(false);
       }

    }
    @EventHandler
    public void playerAchiement(PlayerAchievementAwardedEvent e){
            e.setCancelled(true);
    }

    @EventHandler
    public void onMap(MapInitializeEvent e ){
        for(MapRenderer mr : e.getMap().getRenderers()) e.getMap().removeRenderer(mr);

        e.getMap().addRenderer(new MapRenderer() {
            @Override
            public void render(MapView map, MapCanvas canvas, Player player) {
                map.setCenterX(-4021);
                map.setCenterZ(3408);
                map.setScale(MapView.Scale.FARTHEST);
                map.setUnlimitedTracking(true);
            }
        });

    }
    @EventHandler
    public void tntOff(BlockExplodeEvent e){
        e.setCancelled(true);
    }


    public static Vector getDirection(final Location location, final Location destination) {
        double x1, y1, z1;
        double x0, y0, z0;

        x1 = destination.getX();
        y1 = destination.getY();
        z1 = destination.getZ();

        x0 = location.getX();
        y0 = location.getY();
        z0 = location.getZ();

        return new Vector(x1 - x0, y1 - y0, z1 - z0);
    }
        @EventHandler
    public void pickUpItem(EntityPickupItemEvent e) throws SQLException {
        if (!e.getItem().getItemStack().hasItemMeta()) {
            e.setCancelled(true);
            return;
        }
        Player p = (Player) e.getEntity();
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        int id = ItemStat.getID(e.getItem().getItemStack());
        int enchant = ItemStat.getEnchant(e.getItem().getItemStack());
        RPGItemContainer rpgItemContainer = RPGItemContainer.getRPGItemContainer(id);
        int quality = rpgItemContainer.getQuality();
        int count = e.getItem().getItemStack().getAmount();
        int mark = ItemStat.getMark(e.getItem().getItemStack());
        int strength = ItemStat.getScaleAtStrength(e.getItem().getItemStack());
        int agility = ItemStat.getScaleAtAgility(e.getItem().getItemStack());
        int intelligence = ItemStat.getScaleAtIntelligence(e.getItem().getItemStack());
        int markpower = ItemStat.getMarkpower(e.getItem().getItemStack());
        if(id == 1){
            player.setMoney(player.getMoney() + (e.getItem().getItemStack().getAmount()));
            e.setCancelled(true);
            e.getItem().remove();
        }
        if(id == 2){
            player.setMoney(player.getMoney() + (e.getItem().getItemStack().getAmount() * 64));
            e.setCancelled(true);
            e.getItem().remove();
        }
        if(id == 3){
            player.setMoney(player.getMoney() + (e.getItem().getItemStack().getAmount() * 64 * 64));
            e.setCancelled(true);
            e.getItem().remove();
        }
        if(id == 4){
                player.setMoney(player.getMoney() + (e.getItem().getItemStack().getAmount() * 64 * 64 * 64));
            e.setCancelled(true);
            e.getItem().remove();
            }
        if(id == 5){
                player.setMoney(player.getMoney() + (e.getItem().getItemStack().getAmount() * 64 * 64 * 64 * 64));
            e.setCancelled(true);
            e.getItem().remove();
            }




            if(id > 5) {
                if(quality == 1 && player.getSettings().get("liftingWhite")){
                    e.setCancelled(true);
                    return;
                }
                if(quality == 2 && player.getSettings().get("liftingGreen")){
                    e.setCancelled(true);
                    return;
                }
                if(quality == 3 && player.getSettings().get("liftingBlue")){
                    e.setCancelled(true);
                    return;
                }
                if(quality == 4 && player.getSettings().get("liftingPurple")){
                    e.setCancelled(true);
                    return;
                }

    e.getItem().getItemStack().setItemMeta((new RPGItem()).getRPGItem(id, enchant, 1, mark, markpower, strength,agility, intelligence, rpgplayer.getRPGPlayer((Player) e.getEntity())).getItemMeta());
    if(quality == 1 && player.getSettings().get("dropWhite")){
        player.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы подобрали: " + e.getItem().getItemStack().getItemMeta().getDisplayName() + " x" + e.getItem().getItemStack().getAmount());
    }
                if(quality == 2 && player.getSettings().get("dropGreen")){
                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы подобрали: " + e.getItem().getItemStack().getItemMeta().getDisplayName() + " x" + e.getItem().getItemStack().getAmount());
                }
                if(quality == 3 && player.getSettings().get("dropBlue")){
                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы подобрали: " + e.getItem().getItemStack().getItemMeta().getDisplayName() + " x" + e.getItem().getItemStack().getAmount());
                }
                if(quality == 4 && player.getSettings().get("dropPurple")){
                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы подобрали: " + e.getItem().getItemStack().getItemMeta().getDisplayName() + " x" + e.getItem().getItemStack().getAmount());
                }
                if (player.getRCEnabled() && rpgItemContainer.getRecipe() > 0 && player.getSettings().get("autoRC")){
                    player.getRC().addItem(e.getItem().getItemStack());
                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §2Рецепт был перемещен в хранилище!");
                    e.setCancelled(true);
                    e.getItem().remove();
                }
}
            if(id == 231 || id == 232 || id == 233 || id == 234){
                player.getFlowers().clear();
            }
        Bukkit.getLogger().info(e.getEntity().getName() + ": подобрал " + id + "-" + enchant + "-" + mark + "-" + markpower + " x" + e.getItem().getItemStack().getAmount());
            (new BukkitRunnable() {
                public void run() {
                    for (Map.Entry<String, Integer> i : player.getQuests().entrySet()){
                        String quest = i.getKey();
                        int questInfo = i.getValue();
                        if(questInfo == 1){
                            try {
                                questScript.ScriptsQuest(p, quest, "0", false, 0);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }).runTaskLater((Plugin)azerot.getInstance(), 20L);
    }


    @EventHandler
    public void onEat(FoodLevelChangeEvent event){
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        ItemStack cursor = e.getCursor();
        if (clicked == null || clicked.getType() == Material.AIR || !clicked.hasItemMeta())
            return;
        if (clicked.getItemMeta().getDisplayName().equals("§5Информация о персонаже")) {
                e.setCancelled(true);
        return;
    }
}

    @EventHandler
    public void teleport(PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE)
            e.setCancelled(true);
    }

    @EventHandler
    public void explode(EntityExplodeEvent e) {
        if (e.getEntity() instanceof Fireball) {
            List<Block> blocks = e.blockList();
            blocks.clear();
            //проверить потом будет ли урон наносится
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL || ItemStat.getType(e.getPlayer().getInventory().getItemInMainHand()) == 2)
            e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL)
            e.setCancelled(true);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        int id = ItemStat.getID(e.getItemDrop().getItemStack());
        int enchant = ItemStat.getEnchant(e.getItemDrop().getItemStack());
        int strength = ItemStat.getScaleAtStrength(e.getItemDrop().getItemStack());
        int agility = ItemStat.getScaleAtAgility(e.getItemDrop().getItemStack());
        int intelligence = ItemStat.getScaleAtIntelligence(e.getItemDrop().getItemStack());
        int mark = ItemStat.getMark(e.getItemDrop().getItemStack());
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        Bukkit.getLogger().info(e.getPlayer().getName() + ": выкинул" + id + "-" + enchant + "-" + mark );

        if (RPGI.getSoulBound() == 1)
            if (!e.getItemDrop().getItemStack().equals(rpgplayer.getRPGPlayer(e.getPlayer()).getSBItem())) {
                e.getPlayer().sendMessage(WorldUtils.worldName() + " §cВы попытались выкинуть привязанный к персонажу предмет! Выкиньте его еще раз, чтобы удалить!" );
                        rpgplayer.getRPGPlayer(e.getPlayer()).setSBItem(e.getItemDrop().getItemStack());
                e.setCancelled(true);
            } else {
                e.getItemDrop().remove();
                e.getPlayer().sendMessage(WorldUtils.worldName() + " §aПремет удален!" );
                        rpgplayer.getRPGPlayer(e.getPlayer()).setSBItem(null);
            }
        if (RPGI.getSoulBound() == 2) {
            e.getPlayer().sendMessage(WorldUtils.worldName() + " §cКвестовые предметы нельзя выкинуть!" );
                    e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§5Информация о персонаже"))
                e.setCancelled(true);

        rpgplayer pl = rpgplayer.getRPGPlayer(e.getPlayer());
        if(id == 232 || id == 233 || id == 231 || id == 234){
            if(id == 231){
                if(e.getItemDrop().getItemStack().getAmount() >= 2){
                    pl.getFlowers().put(0, 2);
                }else {
                    pl.getFlowers().merge(0,1, Integer::sum);
                }
            }
            if(id == 232){
                if(e.getItemDrop().getItemStack().getAmount() >= 2){
                    pl.getFlowers().put(1, 2);
                }else {
                    pl.getFlowers().merge(1,1, Integer::sum);
                }
            }
            if(id == 233){
                if(e.getItemDrop().getItemStack().getAmount() >= 2){
                    pl.getFlowers().put(2, 2);
                }else {
                    pl.getFlowers().merge(2,1, Integer::sum);
                }
            }
            if(id == 234){
                if(e.getItemDrop().getItemStack().getAmount() >= 2){
                    pl.getFlowers().put(3, 2);
                }else {
                    pl.getFlowers().merge(3,1, Integer::sum);
                }
            }
            if(pl.getFlowers().get(0) != null && pl.getFlowers().get(1) != null && pl.getFlowers().get(2) != null && pl.getFlowers().get(3) != null) {

                if (pl.getFlowers().get(0) == 2 && pl.getFlowers().get(1) == 2 && pl.getFlowers().get(2) == 2 && pl.getFlowers().get(3) == 2) {
                    (new BukkitRunnable() {
                        public void run() {
                            for (Entity entity : e.getItemDrop().getLocation().getWorld().getNearbyEntities(e.getItemDrop().getLocation(), 5D, 5D, 5D)) {
                                if (entity.getType().equals(EntityType.DROPPED_ITEM)) {
                                    Bukkit.broadcastMessage("всего существ: " + e.getItemDrop().getLocation().getWorld().getNearbyEntities(e.getItemDrop().getLocation(), 5D, 5D, 5D).size());
                                    Item itemFor = (Item) entity;
                                    int id1 = ItemStat.getID(itemFor.getItemStack());
                                    if (id1 == 231) {
                                        pl.getFlowers().put(0, pl.getFlowers().get(0) + itemFor.getItemStack().getAmount());
                                        Bukkit.broadcastMessage("231: " + pl.getFlowers().get(0));
                                    }
                                    if (id1 == 232) {
                                        pl.getFlowers().put(1, pl.getFlowers().get(1) + itemFor.getItemStack().getAmount());
                                        Bukkit.broadcastMessage("232 : " + pl.getFlowers().get(1));
                                    }
                                    if (id1 == 233) {
                                        pl.getFlowers().put(2, pl.getFlowers().get(2) + itemFor.getItemStack().getAmount());
                                        Bukkit.broadcastMessage("233 : " + pl.getFlowers().get(2));
                                    }
                                    if (id1 == 234) {
                                        pl.getFlowers().put(3, pl.getFlowers().get(3) + itemFor.getItemStack().getAmount());
                                        Bukkit.broadcastMessage("234 : " + pl.getFlowers().get(3));
                                    }
                                    Bukkit.broadcastMessage("работает2");
                                    Bukkit.broadcastMessage(" " + pl.getFlowers().toString());
                                    if (pl.getFlowers().get(0) == 4 && pl.getFlowers().get(1) == 4 && pl.getFlowers().get(2) == 4 && pl.getFlowers().get(3) == 4) {
                                        Bukkit.broadcastMessage("работает3");
                                        for (Entity entity1 : e.getItemDrop().getLocation().getWorld().getNearbyEntities(e.getItemDrop().getLocation(), 3D, 3D, 3D)) {
                                            if (entity1.getType().equals(EntityType.DROPPED_ITEM)) {
                                                Item itemFor1 = (Item) entity1;
                                                int id2 = ItemStat.getID(itemFor1.getItemStack());
                                                if (id2 == 232 || id2 == 233 || id2 == 231 || id2 == 234) {
                                                    itemFor1.remove();
                                                }
                                            }
                                        }
                                        e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(), new RPGItem().getRPGItem(235, 0, 1, 0, 0, 0, 0, 0,null));
                                    }
                                }

                            }
                        }
                    }).runTaskLater((Plugin) azerot.getInstance(), 20L);

                }
            }

        }
    }


/*
    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) throws Exception {

        Player p = e.getPlayer();
        String msg = e.getMessage();
        rpgplayer rpgp = rpgplayer.getRPGPlayer(p);
        boolean command = false;
        if (msg.equalsIgnoreCase("#рука") || msg.equalsIgnoreCase("#hand")) {
                ItemStack hand = p.getInventory().getItemInMainHand();
        if (hand == null || hand.getType() == Material.AIR || !hand.hasItemMeta()) {
            e.setCancelled(true);
            p.sendMessage(WorldUtils.worldName() + " §cЭтот предмет нельзя отобразить!" );
            return;
        }
        ItemStack item = null;
        int amount = hand.getAmount();
        if (!hand.getItemMeta().getDisplayName().equals("§5Информация о персонаже")) {
                item = hand;
        CustomChat.customChatVisual("§7[§6" + rpgp.getPLevel() + "§7] " + p.getDisplayName(), item, p);
    } else {
        item = ItemsForGui.BlockCharacterInfo(rpgp);
        CustomChat.customChatVisual("§7[§6" + rpgp.getPLevel() + "§7]] "+ p.getDisplayName(), item, p);
    }
    command = true;
}
    if (!command) {

            CustomChat.customChat("§7[§6" + rpgp.getPLevel() + "§7] " + p.getDisplayName(), msg, p);
            e.setCancelled(true);
            } else {
            e.setCancelled(true);
            }
            }

 */

@EventHandler
public void invclose(InventoryCloseEvent e) {
        Player player = (Player)e.getPlayer();
        if (!player.isOnline())
        return;
        rpgplayer p = rpgplayer.getRPGPlayer(player);
        p.resetArmor();//тут
        }

        @EventHandler
        public void weatherClear(WeatherChangeEvent e){
            e.setCancelled(e.toWeatherState());
        }
    @EventHandler
    public void onTradeClick(InventoryClickEvent e) {
        rpgplayer rp = rpgplayer.getRPGPlayer((Player) e.getWhoClicked());
        RpgTrade trade = rp.getTrade();
        if (trade != null)
            trade.handleClick(e);
    }

@EventHandler
public void noUproot(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL)
        event.setCancelled(true);
        }
        @EventHandler
        public void Bottle(PlayerInteractEvent event){
        Player p = event.getPlayer();

        if(p.getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE){
            event.setCancelled(true);

        }
        }


        @EventHandler
        public void onSpawn(CreatureSpawnEvent e){
        if(e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM)
            e.setCancelled(true);
        }

        @EventHandler
        public void onRost(BlockGrowEvent e){
        e.setCancelled(true);
        }

    @EventHandler
    public void onEntity(EntityChangeBlockEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBurn(BlockBurnEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPhysics(BlockPhysicsEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onStructure(StructureGrowEvent e){
        e.setCancelled(true);
    }


    @EventHandler
    public void onBlockFrom(BlockFromToEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityBlock(EntityBlockFormEvent e){
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onSpread(BlockSpreadEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onFade(BlockFadeEvent e){
        e.setCancelled(true);
    }



@EventHandler
public void onEggSpawnEvent(ItemSpawnEvent event) {
        Item ITEM = event.getEntity();
        if (ITEM.getItemStack().getType() == Material.EGG && !ITEM.getItemStack().hasItemMeta())
        for (Entity entity : ITEM.getNearbyEntities(0.5D, 1.0D, 0.5D)) {
        if (entity.getType() == EntityType.CHICKEN) {
        event.setCancelled(true);
        return;
        }
        }
        }

public static void unmount(Player p) {
        if (p.getVehicle() != null)
        p.getVehicle().remove();
        }

@EventHandler
public void onVehicleExit(VehicleExitEvent e) {
        e.getVehicle().remove();
        }

@EventHandler
public void craft(CraftItemEvent e) {
        e.setCancelled(true);
        }

@EventHandler
public void onBurn(EntityCombustEvent event) {
        event.setCancelled(true);
        }

@EventHandler
public void ignite(BlockIgniteEvent e) {
        e.setCancelled(true);
        }

@EventHandler
public void leafdecay(LeavesDecayEvent e) {
        e.setCancelled(true);
        }

@EventHandler
public void itemFrameItemRemoval(EntityDamageEvent e) {
        if (e.getEntity() instanceof org.bukkit.entity.ItemFrame)
        e.setCancelled(true);
        }

@EventHandler
public void onFrameBrake(HangingBreakByEntityEvent event) {
        event.setCancelled(true);
        }

@EventHandler
public void saveEC(InventoryCloseEvent e) {
        if (!e.getInventory().getName().equals("Хранилище"))
        return;
        rpgplayer p = rpgplayer.getRPGPlayer((Player)e.getPlayer());
        p.setEC(e.getInventory());
        }



@EventHandler
public void openEC(PlayerInteractEvent e) throws SQLException {
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;
        if (!e.getClickedBlock().getType().equals(Material.ENDER_CHEST))
            return;
    if (e.getClickedBlock().getLocation().getX() == -4058 && e.getClickedBlock().getLocation().getY() == 103 && e.getClickedBlock().getLocation().getZ() == 3419 ) {
        if(rpgplayer.getRPGPlayer(e.getPlayer()).getQuestsStage().get("5") == 15){
            e.setCancelled(true);
            e.getPlayer().sendMessage("§eХмм, тяжелое... Надо бы отнести его");
            e.getPlayer().getInventory().addItem(new RPGItem().getRPGItem(1001, 0, 1, 0, 0, 0, 0, 0, rpgplayer.getRPGPlayer(e.getPlayer())));
        }
    }else {
        if(e.getClickedBlock().getLocation().getX() == -4675 && e.getClickedBlock().getLocation().getY() == 112 && e.getClickedBlock().getLocation().getZ() == 3307){
            if(WorldUtils.checkItems(rpgplayer.getRPGPlayer(e.getPlayer()), 1038, 0) >= 1){
                if(rpgplayer.getRPGPlayer(e.getPlayer()).getSettings().get("openCases")){
                    if(!rpgplayer.getRPGPlayer(e.getPlayer()).isPlayAnimation()){
                        firstWheel.start(e.getPlayer(), e.getPlayer().getLocation());
                        WorldUtils.removeItems(rpgplayer.getRPGPlayer(e.getPlayer()), 1038, 1);
                    }
                }
            }
        }else{
            if(rpgplayer.getRPGPlayer(e.getPlayer()).getRCEnabled()){
                e.getPlayer().openInventory(azerot.getInventorys().get("ECorRC"));
            }else {
                rpgplayer p = rpgplayer.getRPGPlayer(e.getPlayer());
                p.toPlayer().openInventory(p.getEC());
            }
        }
        e.setCancelled(true);
    }

}

@EventHandler
public void saveRC(InventoryCloseEvent e) {
        if (!e.getInventory().getName().equals("Хранилище рецептов"))
        return;
        rpgplayer p = rpgplayer.getRPGPlayer((Player)e.getPlayer());
        p.setRC(e.getInventory());
        }

    @EventHandler
    public void handleDeathEvent(EntityDamageEvent entityDamageEvent) {
        Entity damagedEntity = entityDamageEvent.getEntity();
        if (damagedEntity.getClass().isAssignableFrom(Player.class)) {
            Player player = (Player) damagedEntity;
            if (entityDamageEvent.getFinalDamage() >= player.getHealth()) {
                entityDamageEvent.setCancelled(false);
                RPGDamageble.death(player, null, null);
            }
        }
    }
        }
