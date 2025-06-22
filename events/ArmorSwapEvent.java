package azerot.azerot.events;

import azerot.azerot.ItemStat;
import azerot.azerot.RPGItemContainer;
import azerot.azerot.WorldUtils;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorSwapEvent implements Listener {


    @EventHandler
    public void onSwap(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
        int id = ItemStat.getID(hand);
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        int mark = ItemStat.getMark(hand);
        int enchant = ItemStat.getEnchant(hand);
        int markpower = ItemStat.getMarkpower(hand);
        int heatlh = RPGI.getHealth(mark,markpower,enchant);
        int armor = RPGI.getArmor(mark,markpower,enchant);
        int resistance = RPGI.getResistance(mark,markpower,enchant);
        int regeneration = RPGI.getRegeneration(mark,markpower,enchant);
        int magicarmor = RPGI.getMagicArmor(mark, markpower, enchant);
        int returnDamage = RPGI.getReturnDamage(mark, markpower, enchant);
        int dodge = RPGI.getDodge(mark, markpower, enchant);
        int dopdamage = RPGI.getDopDamage(mark, markpower, enchant);
        int trueregeneration = RPGI.getTrueRegeneration(mark, markpower, enchant);
        int agility = RPGI.getAgility(mark, markpower, enchant);
        int strength = RPGI.getStrength(mark, markpower, enchant);
        int intelligence = RPGI.getIntelligence(mark, markpower, enchant);
        if(hand.getType().equals(Material.LEATHER_HELMET) || hand.getType().equals(Material.CHAINMAIL_HELMET) || hand.getType().equals(Material.IRON_HELMET) || hand.getType().equals(Material.DIAMOND_HELMET) ||hand.getType().equals(Material.GOLD_HELMET) ||
        hand.getType().equals(Material.LEATHER_CHESTPLATE) || hand.getType().equals(Material.CHAINMAIL_CHESTPLATE) || hand.getType().equals(Material.IRON_CHESTPLATE) || hand.getType().equals(Material.DIAMOND_CHESTPLATE) ||hand.getType().equals(Material.GOLD_CHESTPLATE) ||
                hand.getType().equals(Material.LEATHER_LEGGINGS) || hand.getType().equals(Material.CHAINMAIL_LEGGINGS) || hand.getType().equals(Material.IRON_LEGGINGS) || hand.getType().equals(Material.DIAMOND_LEGGINGS) ||hand.getType().equals(Material.GOLD_LEGGINGS) || hand.getType().equals(Material.LEATHER_BOOTS) || hand.getType().equals(Material.CHAINMAIL_BOOTS) || hand.getType().equals(Material.IRON_BOOTS) || hand.getType().equals(Material.DIAMOND_BOOTS) ||hand.getType().equals(Material.GOLD_BOOTS)){
            if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){
               e.setCancelled(true);
                return;
            }
        }

        if(hand.getType().equals(Material.LEATHER_HELMET) || hand.getType().equals(Material.CHAINMAIL_HELMET) || hand.getType().equals(Material.IRON_HELMET) || hand.getType().equals(Material.DIAMOND_HELMET) ||hand.getType().equals(Material.GOLD_HELMET)){
            if(player.getInventory().getHelmet() == null){
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage( WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + heatlh );
                if(armor != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + armor);
                }
                if(magicarmor != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + magicarmor );
                }
                if(resistance != 0){
                    player.sendMessage(WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + resistance );
                }
                if(regeneration != 0){
                    player.sendMessage(WorldUtils.worldName() + " §7Регенерация: " + "§2+" + regeneration );
                }
                if(trueregeneration != 0 ){
                    player.sendMessage( WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + trueregeneration );
                }
                if(returnDamage != 0){
                    player.sendMessage( WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + returnDamage  + "%");
                }
                if(dopdamage != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + dopdamage + "%");
                }
                if(dodge != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + dodge + "%");
                }
                if(agility != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + agility );
                }
                if(strength != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + strength);
                }
                if(intelligence != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + intelligence );
                }
            }else{
                ItemStack item  = player.getInventory().getHelmet();
                int id1 = ItemStat.getID(item);
                RPGItemContainer RPGI1 = RPGItemContainer.getRPGItemContainer(id1);
                int mark1 = ItemStat.getMark(item);
                int enchant1 = ItemStat.getEnchant(item);
                int markpower1 = ItemStat.getMarkpower(item);
                int heatlh1 = RPGI1.getHealth(mark1,markpower1,enchant1);
                int armor1 = RPGI1.getArmor(mark1,markpower1,enchant1);
                int resistance1 = RPGI1.getResistance(mark1,markpower1,enchant1);
                int regeneration1 = RPGI1.getRegeneration(mark1,markpower1,enchant1);
                int magicarmor1 = RPGI1.getMagicArmor(mark1, markpower1, enchant1);
                int returnDamage1 = RPGI1.getReturnDamage(mark1, markpower1, enchant1);
                int dodge1 = RPGI1.getDodge(mark1, markpower1, enchant1);
                int dopdamage1 = RPGI1.getDopDamage(mark1, markpower1, enchant1);
                int trueregeneration1 = RPGI1.getTrueRegeneration(mark1, markpower1, enchant1);
                int agility1 = RPGI1.getAgility(mark1, markpower1, enchant1);
                int strength1 = RPGI1.getStrength(mark1, markpower1, enchant1);
                int intelligence1 = RPGI1.getIntelligence(mark1, markpower1, enchant1);
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage(heatlh1 > heatlh ? WorldUtils.worldName() +" §4❤ §7Здоровье: " + "§4-" + (heatlh1 - heatlh): WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + (heatlh - heatlh1));
                if(armor != 0 || armor1 != 0){
                    player.sendMessage(armor1 > armor ? WorldUtils.worldName() + " §c☫ §7Броня: " + "§4-" + (armor1 - armor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (armor - armor1));
                }
                if(magicarmor1 != 0 || magicarmor != 0){
                    player.sendMessage(magicarmor1 > magicarmor ? WorldUtils.worldName() + " §c☫ §7Магическая Броня: " + "§4-" + (magicarmor1 - magicarmor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (magicarmor - magicarmor1));
                }
                if(resistance != 0 || resistance1 != 0){
                    player.sendMessage(resistance1 > resistance ? WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§4-" + (resistance1 - resistance) : WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + (resistance - resistance1));

                }
                if(regeneration1 != 0 || regeneration != 0){
                    player.sendMessage(regeneration1 > regeneration ? WorldUtils.worldName() +" §a⚸ §7Регенерация: " + "§4-" + (regeneration1 - regeneration) : WorldUtils.worldName() + " §7Регенерация: " + "§2+" + (regeneration - regeneration1));

                }
                if(trueregeneration != 0 || trueregeneration1 != 0){
                    player.sendMessage(trueregeneration1 > trueregeneration ? WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§4-" + (trueregeneration1 - trueregeneration) : WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + (trueregeneration - trueregeneration1));
                }
                if(returnDamage1 != 0 || returnDamage != 0){
                    player.sendMessage(returnDamage1 > returnDamage ? WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§4-" + (returnDamage1 - returnDamage) + "%" : WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + (returnDamage - returnDamage1) + "%");
                }
                if(dopdamage1 != 0 || dopdamage != 0){
                    player.sendMessage(dopdamage1 > dopdamage ? WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§4-" + (dopdamage1 - dopdamage) + "%" : WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + (dopdamage - dopdamage1) + "%");
                }
                if(dodge1 != 0 || dodge != 0){
                    player.sendMessage(dodge1 > dodge ? WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§4-" + (dodge1 - dodge) + "%" : WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + (dodge - dodge1) + "%");
                }
                if(agility != 0 || agility1 != 0){
                    player.sendMessage(agility1 > agility ? WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§4-" + (agility1 - agility) : WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + (agility - agility1));
                }
                if(strength != 0 || strength1 != 0){
                    player.sendMessage(strength1 > strength ? WorldUtils.worldName() + " §2⟿ §7Сила: " + "§4-" + (strength1 - strength) : WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + (strength - strength1));
                }
                if(intelligence != 0 || intelligence1 != 0){
                    player.sendMessage(intelligence1 > intelligence ? WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§4-" + (intelligence1 - intelligence) : WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + (intelligence - intelligence1));
                }
                player.getInventory().setHelmet(hand);
                player.getInventory().setItemInMainHand(item);
            }
        }
        if(hand.getType().equals(Material.LEATHER_CHESTPLATE) || hand.getType().equals(Material.CHAINMAIL_CHESTPLATE) || hand.getType().equals(Material.IRON_CHESTPLATE) || hand.getType().equals(Material.DIAMOND_CHESTPLATE) ||hand.getType().equals(Material.GOLD_CHESTPLATE)){
            if(player.getInventory().getChestplate() == null){
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage( WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + heatlh );
                if(armor != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + armor);
                }
                if(magicarmor != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + magicarmor );
                }
                if(resistance != 0){
                    player.sendMessage(WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + resistance );
                }
                if(regeneration != 0){
                    player.sendMessage(WorldUtils.worldName() + " §7Регенерация: " + "§2+" + regeneration );
                }
                if(trueregeneration != 0 ){
                    player.sendMessage( WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + trueregeneration );
                }
                if(returnDamage != 0){
                    player.sendMessage( WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + returnDamage  + "%");
                }
                if(dopdamage != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + dopdamage + "%");
                }
                if(dodge != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + dodge + "%");
                }
                if(agility != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + agility );
                }
                if(strength != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + strength);
                }
                if(intelligence != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + intelligence );
                }
            }else{
                ItemStack item  = player.getInventory().getChestplate();
                int id1 = ItemStat.getID(item);
                RPGItemContainer RPGI1 = RPGItemContainer.getRPGItemContainer(id1);
                int mark1 = ItemStat.getMark(item);
                int enchant1 = ItemStat.getEnchant(item);
                int markpower1 = ItemStat.getMarkpower(item);
                int heatlh1 = RPGI1.getHealth(mark1,markpower1,enchant1);
                int armor1 = RPGI1.getArmor(mark1,markpower1,enchant1);
                int resistance1 = RPGI1.getResistance(mark1,markpower1,enchant1);
                int regeneration1 = RPGI1.getRegeneration(mark1,markpower1,enchant1);
                int magicarmor1 = RPGI1.getMagicArmor(mark1, markpower1, enchant1);
                int returnDamage1 = RPGI1.getReturnDamage(mark1, markpower1, enchant1);
                int dodge1 = RPGI1.getDodge(mark1, markpower1, enchant1);
                int dopdamage1 = RPGI1.getDopDamage(mark1, markpower1, enchant1);
                int trueregeneration1 = RPGI1.getTrueRegeneration(mark1, markpower1, enchant1);
                int agility1 = RPGI1.getAgility(mark1, markpower1, enchant1);
                int strength1 = RPGI1.getStrength(mark1, markpower1, enchant1);
                int intelligence1 = RPGI1.getIntelligence(mark1, markpower1, enchant1);
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage(heatlh1 > heatlh ? WorldUtils.worldName() +" §4❤ §7Здоровье: " + "§4-" + (heatlh1 - heatlh): WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + (heatlh - heatlh1));
                if(armor != 0 || armor1 != 0){
                    player.sendMessage(armor1 > armor ? WorldUtils.worldName() + " §c☫ §7Броня: " + "§4-" + (armor1 - armor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (armor - armor1));
                }
                if(magicarmor1 != 0 || magicarmor != 0){
                    player.sendMessage(magicarmor1 > magicarmor ? WorldUtils.worldName() + " §c☫ §7Магическая Броня: " + "§4-" + (magicarmor1 - magicarmor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (magicarmor - magicarmor1));
                }
                if(resistance != 0 || resistance1 != 0){
                    player.sendMessage(resistance1 > resistance ? WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§4-" + (resistance1 - resistance) : WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + (resistance - resistance1));

                }
                if(regeneration1 != 0 || regeneration != 0){
                    player.sendMessage(regeneration1 > regeneration ? WorldUtils.worldName() +" §a⚸ §7Регенерация: " + "§4-" + (regeneration1 - regeneration) : WorldUtils.worldName() + " §7Регенерация: " + "§2+" + (regeneration - regeneration1));

                }
                if(trueregeneration != 0 || trueregeneration1 != 0){
                    player.sendMessage(trueregeneration1 > trueregeneration ? WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§4-" + (trueregeneration1 - trueregeneration) : WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + (trueregeneration - trueregeneration1));
                }
                if(returnDamage1 != 0 || returnDamage != 0){
                    player.sendMessage(returnDamage1 > returnDamage ? WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§4-" + (returnDamage1 - returnDamage) + "%" : WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + (returnDamage - returnDamage1) + "%");
                }
                if(dopdamage1 != 0 || dopdamage != 0){
                    player.sendMessage(dopdamage1 > dopdamage ? WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§4-" + (dopdamage1 - dopdamage) + "%" : WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + (dopdamage - dopdamage1) + "%");
                }
                if(dodge1 != 0 || dodge != 0){
                    player.sendMessage(dodge1 > dodge ? WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§4-" + (dodge1 - dodge) + "%" : WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + (dodge - dodge1) + "%");
                }
                if(agility != 0 || agility1 != 0){
                    player.sendMessage(agility1 > agility ? WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§4-" + (agility1 - agility) : WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + (agility - agility1));
                }
                if(strength != 0 || strength1 != 0){
                    player.sendMessage(strength1 > strength ? WorldUtils.worldName() + " §2⟿ §7Сила: " + "§4-" + (strength1 - strength) : WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + (strength - strength1));
                }
                if(intelligence != 0 || intelligence1 != 0){
                    player.sendMessage(intelligence1 > intelligence ? WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§4-" + (intelligence1 - intelligence) : WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + (intelligence - intelligence1));
                }
                player.getInventory().setChestplate(hand);
                player.getInventory().setItemInMainHand(item);
            }
        }
        if(hand.getType().equals(Material.LEATHER_LEGGINGS) || hand.getType().equals(Material.CHAINMAIL_LEGGINGS) || hand.getType().equals(Material.IRON_LEGGINGS) || hand.getType().equals(Material.DIAMOND_LEGGINGS) ||hand.getType().equals(Material.GOLD_LEGGINGS)){
            if(player.getInventory().getLeggings() == null){
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage( WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + heatlh );
                if(armor != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + armor);
                }
                if(magicarmor != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + magicarmor );
                }
                if(resistance != 0){
                    player.sendMessage(WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + resistance );
                }
                if(regeneration != 0){
                    player.sendMessage(WorldUtils.worldName() + " §7Регенерация: " + "§2+" + regeneration );
                }
                if(trueregeneration != 0 ){
                    player.sendMessage( WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + trueregeneration );
                }
                if(returnDamage != 0){
                    player.sendMessage( WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + returnDamage  + "%");
                }
                if(dopdamage != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + dopdamage + "%");
                }
                if(dodge != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + dodge + "%");
                }
                if(agility != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + agility );
                }
                if(strength != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + strength);
                }
                if(intelligence != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + intelligence );
                }
            }else{
                ItemStack item  = player.getInventory().getLeggings();
                int id1 = ItemStat.getID(item);
                RPGItemContainer RPGI1 = RPGItemContainer.getRPGItemContainer(id1);
                int mark1 = ItemStat.getMark(item);
                int enchant1 = ItemStat.getEnchant(item);
                int markpower1 = ItemStat.getMarkpower(item);
                int heatlh1 = RPGI1.getHealth(mark1,markpower1,enchant1);
                int armor1 = RPGI1.getArmor(mark1,markpower1,enchant1);
                int resistance1 = RPGI1.getResistance(mark1,markpower1,enchant1);
                int regeneration1 = RPGI1.getRegeneration(mark1,markpower1,enchant1);
                int magicarmor1 = RPGI1.getMagicArmor(mark1, markpower1, enchant1);
                int returnDamage1 = RPGI1.getReturnDamage(mark1, markpower1, enchant1);
                int dodge1 = RPGI1.getDodge(mark1, markpower1, enchant1);
                int dopdamage1 = RPGI1.getDopDamage(mark1, markpower1, enchant1);
                int trueregeneration1 = RPGI1.getTrueRegeneration(mark1, markpower1, enchant1);
                int agility1 = RPGI1.getAgility(mark1, markpower1, enchant1);
                int strength1 = RPGI1.getStrength(mark1, markpower1, enchant1);
                int intelligence1 = RPGI1.getIntelligence(mark1, markpower1, enchant1);
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage(heatlh1 > heatlh ? WorldUtils.worldName() +" §4❤ §7Здоровье: " + "§4-" + (heatlh1 - heatlh): WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + (heatlh - heatlh1));
                if(armor != 0 || armor1 != 0){
                    player.sendMessage(armor1 > armor ? WorldUtils.worldName() + " §c☫ §7Броня: " + "§4-" + (armor1 - armor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (armor - armor1));
                }
                if(magicarmor1 != 0 || magicarmor != 0){
                    player.sendMessage(magicarmor1 > magicarmor ? WorldUtils.worldName() + " §c☫ §7Магическая Броня: " + "§4-" + (magicarmor1 - magicarmor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (magicarmor - magicarmor1));
                }
                if(resistance != 0 || resistance1 != 0){
                    player.sendMessage(resistance1 > resistance ? WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§4-" + (resistance1 - resistance) : WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + (resistance - resistance1));

                }
                if(regeneration1 != 0 || regeneration != 0){
                    player.sendMessage(regeneration1 > regeneration ? WorldUtils.worldName() +" §a⚸ §7Регенерация: " + "§4-" + (regeneration1 - regeneration) : WorldUtils.worldName() + " §7Регенерация: " + "§2+" + (regeneration - regeneration1));

                }
                if(trueregeneration != 0 || trueregeneration1 != 0){
                    player.sendMessage(trueregeneration1 > trueregeneration ? WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§4-" + (trueregeneration1 - trueregeneration) : WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + (trueregeneration - trueregeneration1));
                }
                if(returnDamage1 != 0 || returnDamage != 0){
                    player.sendMessage(returnDamage1 > returnDamage ? WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§4-" + (returnDamage1 - returnDamage) + "%" : WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + (returnDamage - returnDamage1) + "%");
                }
                if(dopdamage1 != 0 || dopdamage != 0){
                    player.sendMessage(dopdamage1 > dopdamage ? WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§4-" + (dopdamage1 - dopdamage) + "%" : WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + (dopdamage - dopdamage1) + "%");
                }
                if(dodge1 != 0 || dodge != 0){
                    player.sendMessage(dodge1 > dodge ? WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§4-" + (dodge1 - dodge) + "%" : WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + (dodge - dodge1) + "%");
                }
                if(agility != 0 || agility1 != 0){
                    player.sendMessage(agility1 > agility ? WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§4-" + (agility1 - agility) : WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + (agility - agility1));
                }
                if(strength != 0 || strength1 != 0){
                    player.sendMessage(strength1 > strength ? WorldUtils.worldName() + " §2⟿ §7Сила: " + "§4-" + (strength1 - strength) : WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + (strength - strength1));
                }
                if(intelligence != 0 || intelligence1 != 0){
                    player.sendMessage(intelligence1 > intelligence ? WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§4-" + (intelligence1 - intelligence) : WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + (intelligence - intelligence1));
                }
                player.getInventory().setLeggings(hand);
                player.getInventory().setItemInMainHand(item);
            }
        }
        if(hand.getType().equals(Material.LEATHER_BOOTS) || hand.getType().equals(Material.CHAINMAIL_BOOTS) || hand.getType().equals(Material.IRON_BOOTS) || hand.getType().equals(Material.DIAMOND_BOOTS) ||hand.getType().equals(Material.GOLD_BOOTS)){
            if(player.getInventory().getBoots() == null){
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage( WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + heatlh );
                if(armor != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + armor);
                }
                if(magicarmor != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + magicarmor );
                }
                if(resistance != 0){
                    player.sendMessage(WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + resistance );
                }
                if(regeneration != 0){
                    player.sendMessage(WorldUtils.worldName() + " §7Регенерация: " + "§2+" + regeneration );
                }
                if(trueregeneration != 0 ){
                    player.sendMessage( WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + trueregeneration );
                }
                if(returnDamage != 0){
                    player.sendMessage( WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + returnDamage  + "%");
                }
                if(dopdamage != 0){
                    player.sendMessage(WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + dopdamage + "%");
                }
                if(dodge != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + dodge + "%");
                }
                if(agility != 0){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + agility );
                }
                if(strength != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + strength);
                }
                if(intelligence != 0 ){
                    player.sendMessage(WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + intelligence );
                }
            }else{
                ItemStack item  = player.getInventory().getBoots();
                int id1 = ItemStat.getID(item);
                RPGItemContainer RPGI1 = RPGItemContainer.getRPGItemContainer(id1);
                int mark1 = ItemStat.getMark(item);
                int enchant1 = ItemStat.getEnchant(item);
                int markpower1 = ItemStat.getMarkpower(item);
                int heatlh1 = RPGI1.getHealth(mark1,markpower1,enchant1);
                int armor1 = RPGI1.getArmor(mark1,markpower1,enchant1);
                int resistance1 = RPGI1.getResistance(mark1,markpower1,enchant1);
                int regeneration1 = RPGI1.getRegeneration(mark1,markpower1,enchant1);
                int magicarmor1 = RPGI1.getMagicArmor(mark1, markpower1, enchant1);
                int returnDamage1 = RPGI1.getReturnDamage(mark1, markpower1, enchant1);
                int dodge1 = RPGI1.getDodge(mark1, markpower1, enchant1);
                int dopdamage1 = RPGI1.getDopDamage(mark1, markpower1, enchant1);
                int trueregeneration1 = RPGI1.getTrueRegeneration(mark1, markpower1, enchant1);
                int agility1 = RPGI1.getAgility(mark1, markpower1, enchant1);
                int strength1 = RPGI1.getStrength(mark1, markpower1, enchant1);
                int intelligence1 = RPGI1.getIntelligence(mark1, markpower1, enchant1);
                player.sendMessage(WorldUtils.worldName() + " Вы одели: " + hand.getItemMeta().getDisplayName());
                player.sendMessage(heatlh1 > heatlh ? WorldUtils.worldName() +" §4❤ §7Здоровье: " + "§4-" + (heatlh1 - heatlh): WorldUtils.worldName() + " §4❤ §7Здоровье: " + "§2+" + (heatlh - heatlh1));
                if(armor != 0 || armor1 != 0){
                    player.sendMessage(armor1 > armor ? WorldUtils.worldName() + " §c☫ §7Броня: " + "§4-" + (armor1 - armor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (armor - armor1));
                }
                if(magicarmor1 != 0 || magicarmor != 0){
                    player.sendMessage(magicarmor1 > magicarmor ? WorldUtils.worldName() + " §c☫ §7Магическая Броня: " + "§4-" + (magicarmor1 - magicarmor) : WorldUtils.worldName() + " §c☫ §7Броня: " + "§2+" + (magicarmor - magicarmor1));
                }
                if(resistance != 0 || resistance1 != 0){
                    player.sendMessage(resistance1 > resistance ? WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§4-" + (resistance1 - resistance) : WorldUtils.worldName() + " §6❂ §7Сопротивление: " + "§2+" + (resistance - resistance1));

                }
                if(regeneration1 != 0 || regeneration != 0){
                    player.sendMessage(regeneration1 > regeneration ? WorldUtils.worldName() +" §a⚸ §7Регенерация: " + "§4-" + (regeneration1 - regeneration) : WorldUtils.worldName() + " §7Регенерация: " + "§2+" + (regeneration - regeneration1));

                }
                if(trueregeneration != 0 || trueregeneration1 != 0){
                    player.sendMessage(trueregeneration1 > trueregeneration ? WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§4-" + (trueregeneration1 - trueregeneration) : WorldUtils.worldName() + " §2⚸ §7Боевая реген.: " + "§2+" + (trueregeneration - trueregeneration1));
                }
                if(returnDamage1 != 0 || returnDamage != 0){
                    player.sendMessage(returnDamage1 > returnDamage ? WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§4-" + (returnDamage1 - returnDamage) + "%" : WorldUtils.worldName() + " §7❖ §7Возвращение урона: " + "§2+" + (returnDamage - returnDamage1) + "%");
                }
                if(dopdamage1 != 0 || dopdamage != 0){
                    player.sendMessage(dopdamage1 > dopdamage ? WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§4-" + (dopdamage1 - dopdamage) + "%" : WorldUtils.worldName() + " §c⚔ §7Дополнительный урон: " + "§2+" + (dopdamage - dopdamage1) + "%");
                }
                if(dodge1 != 0 || dodge != 0){
                    player.sendMessage(dodge1 > dodge ? WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§4-" + (dodge1 - dodge) + "%" : WorldUtils.worldName() + " §2⟿ §7Уклонение: " + "§2+" + (dodge - dodge1) + "%");
                }
                if(agility != 0 || agility1 != 0){
                    player.sendMessage(agility1 > agility ? WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§4-" + (agility1 - agility) : WorldUtils.worldName() + " §2⟿ §7Ловкость: " + "§2+" + (agility - agility1));
                }
                if(strength != 0 || strength1 != 0){
                    player.sendMessage(strength1 > strength ? WorldUtils.worldName() + " §2⟿ §7Сила: " + "§4-" + (strength1 - strength) : WorldUtils.worldName() + " §2⟿ §7Сила: " + "§2+" + (strength - strength1));
                }
                if(intelligence != 0 || intelligence1 != 0){
                    player.sendMessage(intelligence1 > intelligence ? WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§4-" + (intelligence1 - intelligence) : WorldUtils.worldName() + " §2⟿ §7Интеллект: " + "§2+" + (intelligence - intelligence1));
                }
                player.getInventory().setBoots(hand);
                player.getInventory().setItemInMainHand(item);
            }
        }

    }
}
