package azerot.azerot.events;

import azerot.azerot.*;
import azerot.azerot.mobs.RPGMobsContainer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateInventory
{

    public static void createInvenotory() {

        Inventory invTraininng = Bukkit.createInventory(null, 9 , "§6Обучение");
        invTraininng.setItem(0, ItemsForGui.rejim());
        invTraininng.setItem(1, ItemsForGui.mobs());
        invTraininng.setItem(2, ItemsForGui.mechanika());
        invTraininng.setItem(3, ItemsForGui.zatochka());

        for (int j = 0; j < invTraininng.getSize(); j++) {
            if (invTraininng.getItem(j) == null)
                invTraininng.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("invTraining", invTraininng);

        Inventory inv = Bukkit.createInventory(null, 54, "§5Информация о существах");
        List<Integer> mobs = new ArrayList<>();
        int sizeMobs = azerot.getInstance().getAllMobsIdFirstPatch().size();

        mobs = azerot.getInstance().getAllMobsIdFirstPatch().subList(0, sizeMobs);

        int slot = 0;
        for (Integer i : mobs) {
            RPGMobsContainer rpgMobsContainer = RPGMobsContainer.getRPGMobsContainer(i);
            int level= rpgMobsContainer.getLevel();
            String name = rpgMobsContainer.getName();
            String type = rpgMobsContainer.getType();
            double respawn = rpgMobsContainer.getRespawn();
            String drop = rpgMobsContainer.getDrop();
            int typeMob = rpgMobsContainer.getTypeMob();
            int patch = rpgMobsContainer.getPatch();
            if(patch  == 1){
                inv.setItem(slot, ItemsForGui.BlockDataBase(i, level, name, type, typeMob, respawn, drop));
                slot++;
            }

        }
        inv.setItem(inv.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
        inv.setItem(49, ItemsForGui.OpenAllItems());
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("InvMobsInFirstPatch", inv);


        Inventory inv1 = Bukkit.createInventory(null, 54, "§5Информация о существах");
        List<Integer> mobs1 = new ArrayList<>();
        int sizeMobs1 = azerot.getInstance().getAllMobsIdSecondPatch().size();

        mobs1 = azerot.getInstance().getAllMobsIdSecondPatch().subList(0, sizeMobs1);

        int slot1 = 0;
        for (Integer j : mobs1) {
            RPGMobsContainer rpgMobsContainer = RPGMobsContainer.getRPGMobsContainer(j);
            int level= rpgMobsContainer.getLevel();
            String name = rpgMobsContainer.getName();
            String type = rpgMobsContainer.getType();
            double respawn = rpgMobsContainer.getRespawn();
            String drop = rpgMobsContainer.getDrop();
            int typeMob = rpgMobsContainer.getTypeMob();
            int patch = rpgMobsContainer.getPatch();
            if(patch  == 2){
                inv1.setItem(slot1, ItemsForGui.BlockDataBase(j, level, name, type, typeMob, respawn, drop));
                slot1++;
            }

        }
        inv1.setItem(inv1.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
        inv1.setItem(49, ItemsForGui.OpenAllItems());
        for (int j = 0; j < inv1.getSize(); j++) {
            if (inv1.getItem(j) == null)
                inv1.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("InvMobsInSecondPatch", inv1);





       /* Inventory invMobsThirdPatch = Bukkit.createInventory(null, 54, "§5Информация о существах");
        List<Integer> mobsThirdPatch = new ArrayList<>();
        int sizeMobsThirdPatch = azerot.getInstance().getAllMobsIdThirdPatch().size();

        mobsThirdPatch = azerot.getInstance().getAllMobsIdThirdPatch().subList(0, sizeMobsThirdPatch);

        int slotMobsThirdPatch = 0;
        for (Integer j : mobsThirdPatch) {
            RPGMobsContainer rpgMobsContainer = RPGMobsContainer.getRPGMobsContainer(j);
            int level= rpgMobsContainer.getLevel();
            String name = rpgMobsContainer.getName();
            String type = rpgMobsContainer.getType();
            double respawn = rpgMobsContainer.getRespawn();
            String drop = rpgMobsContainer.getDrop();
            int typeMob = rpgMobsContainer.getTypeMob();
            int patch = rpgMobsContainer.getPatch();

                invMobsThirdPatch.setItem(slotMobsThirdPatch, ItemsForGui.BlockDataBase(j, level, name, type, typeMob, respawn, drop));
                slotMobsThirdPatch++;

        }
        invMobsThirdPatch.setItem(invMobsThirdPatch.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
        invMobsThirdPatch.setItem(49, ItemsForGui.OpenAllItems());
        for (int j = 0; j < invMobsThirdPatch.getSize(); j++) {
            if (invMobsThirdPatch.getItem(j) == null)
                invMobsThirdPatch.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("InvMobsInThirdPatch", invMobsThirdPatch);

        */



        Inventory invPatchSelection = Bukkit.createInventory(null, 54, "§6Список дополнений");

        invPatchSelection.setItem(1, ItemsForGui.firstPatch());
        invPatchSelection.setItem(3, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(5, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(7, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(19, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(21, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(23, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(25, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(37, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(39, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(41, ItemsForGui.unavailablePatch());
        invPatchSelection.setItem(43, ItemsForGui.unavailablePatch());


        if(azerot.patch >= 2){
            invPatchSelection.setItem(3, ItemsForGui.secondPatch());
        }
        if(azerot.patch >= 3){
            invPatchSelection.setItem(5, ItemsForGui.thirdPatch());
        }
        for (int j = 0; j < invPatchSelection.getSize(); j++) {
            if (invPatchSelection.getItem(j) == null)
                invPatchSelection.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("InvPatchSelection", invPatchSelection);

        Inventory invBestiary = Bukkit.createInventory(null, 9, "§5Бестиарий");
        invBestiary.setItem(0, ItemsForGui.tags());
        invBestiary.setItem(1, ItemsForGui.bosses());
        invBestiary.setItem(2, ItemsForGui.mechanics());
        for (int j = 0; j < invBestiary.getSize(); j++) {
            if (invBestiary.getItem(j) == null)
                invBestiary.setItem(j, ItemsForGui.corner());
        }

        azerot.getInventorys().put("InvBestiary", invBestiary);

        Inventory invBosses = Bukkit.createInventory(null, 9, "§5Боссы");
        for (int j = 0; j < invBosses.getSize(); j++) {
            if (invBosses.getItem(j) == null)
                invBosses.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("InvBosses", invBosses);

        Inventory ECorRC = Bukkit.createInventory(null, 27, "§5Хранилища");
        ECorRC.setItem(12, ItemsForGui.EC());
        ECorRC.setItem(14, ItemsForGui.RCIn());
        for (int j = 0; j < ECorRC.getSize(); j++) {
            if (ECorRC.getItem(j) == null)
                ECorRC.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("ECorRC", ECorRC);

        Inventory invMechanics = Bukkit.createInventory(null, 9, "§5Механики");
        invMechanics.setItem(0, ItemsForGui.spells());
        for (int j = 0; j < invMechanics.getSize(); j++) {
            if (invMechanics.getItem(j) == null)
                invMechanics.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("InvMechanics", invMechanics);


      for(int i = 1; i < 6; i++){
            Inventory invSecondPacthItems = Bukkit.createInventory(null, 54, "§5Информация о предметах. Страница: " + i);
            List<Integer> items = new ArrayList<>();
            if(i == 1){
                items = azerot.getInstance().getAllItemsIdSecondPatch().subList(0,45);
            }
            if(i == 2){
                items = azerot.getInstance().getAllItemsIdSecondPatch().subList(45,90);
            }
            if(i == 3){
                items = azerot.getInstance().getAllItemsIdSecondPatch().subList(90,135);
            }
            if(i == 4){
                items = azerot.getInstance().getAllItemsIdSecondPatch().subList(135,180);
            }
          if(i == 5){
              items = azerot.getInstance().getAllItemsIdSecondPatch().subList(180,azerot.getInstance().getAllItemsIdSecondPatch().size());
          }
            int slotSecondPacthItems = 0;
            for (Integer j : items) {
                ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
                invSecondPacthItems.setItem(slotSecondPacthItems, rpgitem);
                slotSecondPacthItems++;
            }

            if(i != 5){
                invSecondPacthItems.setItem(invSecondPacthItems.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));
            }
            if(i == 1){
                invSecondPacthItems.setItem(invSecondPacthItems.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                invSecondPacthItems.setItem(49, ItemsForGui.FilterItems(0));
            }else{
                invSecondPacthItems.setItem(invSecondPacthItems.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(45));
            }
            for (int j = 0; j < invSecondPacthItems.getSize(); j++) {
                if (invSecondPacthItems.getItem(j) == null)
                    invSecondPacthItems.setItem(j, ItemsForGui.corner());
            }
            azerot.getInventorys().put("InvItemsForSecondPatch: " + i, invSecondPacthItems);
        }


       for(int i = 1; i < 4; i++){
            Inventory invFirstPacthItems = Bukkit.createInventory(null, 54, "§5Информация о предметах. Страница: " + i);
            List<Integer> items = new ArrayList<>();
            if(i == 1){
                items = azerot.getInstance().getAllItemsIdFirstPatch().subList(0,45);
            }
            if(i == 2){
                items = azerot.getInstance().getAllItemsIdFirstPatch().subList(45,90);
            }
            if(i == 3){
                items = azerot.getInstance().getAllItemsIdFirstPatch().subList(90, azerot.getInstance().getAllItemsIdFirstPatch().size());
            }
            int slotFirstPacthItems = 0;
            for (Integer j : items) {
                ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0,0,null));
                invFirstPacthItems.setItem(slotFirstPacthItems, rpgitem);
                slotFirstPacthItems++;
            }

            if(i <= 2)
                invFirstPacthItems.setItem(invFirstPacthItems.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));

            if(i == 1){
                invFirstPacthItems.setItem(invFirstPacthItems.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                invFirstPacthItems.setItem(49, ItemsForGui.FilterItems(0));
            }else{
                invFirstPacthItems.setItem(invFirstPacthItems.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(45));
            }
            for (int j = 0; j < invFirstPacthItems.getSize(); j++) {
                if (invFirstPacthItems.getItem(j) == null)
                    invFirstPacthItems.setItem(j, ItemsForGui.corner());
            }
            azerot.getInventorys().put("InvItemsForFirstPatch: " + i, invFirstPacthItems);
        }

        for(int i = 1; i <= RPGRecipesContainer.getRpgrecipes().size(); i++){
            Inventory invReciped = Bukkit.getServer().createInventory(null, 54, "§6Рецепт §c#" + i);
            RPGRecipesContainer rpgRecipesContainer = RPGRecipesContainer.RPGRecipesContainer(i);
            for (int j = 0; j < invReciped.getSize(); j++)
                invReciped.setItem(j, ItemsForGui.corner());
            Bukkit.broadcastMessage("рецепт: " + i);
            String items1 = rpgRecipesContainer.getItems1();
            String[] items11 = items1.split(";");
            String items2 = rpgRecipesContainer.getItems2();
            String[] items22 = new String[0];
            if(items2 != null)
                items22 = items2.split(";");
            List<String> craft = Collections.singletonList(rpgRecipesContainer.getCraft());
            int slotReciped = 10;
            for (String j : items11) {
                String[] ip = j.trim().split(" ");
                invReciped.setItem(slotReciped, ItemsForGui.GUIItem((new RPGItem()).getRPGItem(Integer.parseInt(ip[0]), 0, Integer.parseInt(ip[1]), 0, 0, 0, 0, 0,null)));
                slotReciped += 2;
            }
            slotReciped = 37;
            if(items2 != null) {
                for (String j : items22) {
                    String[] ip = j.trim().split(" ");
                    invReciped.setItem(slotReciped, ItemsForGui.GUIItem((new RPGItem()).getRPGItem(Integer.parseInt(ip[0]), 0, Integer.parseInt(ip[1]),  0, 0,0, 0, 0,null)));
                    slotReciped += 2;
                }
            }
            for (String j : craft) {
                String[] it = j.trim().split(" ");
                invReciped.setItem(16, ItemsForGui.GUIItem((new RPGItem()).getRPGItem(Integer.valueOf(Integer.valueOf(it[0]).intValue()).intValue(), 0, Integer.valueOf(Integer.valueOf(it[1]).intValue()).intValue(),  0, 0, 0, 0, 0,null)));
                invReciped.setItem(43, ItemsForGui.craftcomplete());
            }
            azerot.getInventorys().put("InvReciped: " + i, invReciped);
        }


        Inventory invTypeOneFirstPatch = Bukkit.createInventory(null, 54, "§5Информация о предметах");
        List<Integer> items = new ArrayList<>();
        int slotInvTypeOneFirstPatch = 0;

        items = azerot.getInstance().getItemsForType().get("typeOneFirstPatch");

        for (Integer j : items) {
            ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
            invTypeOneFirstPatch.setItem(slotInvTypeOneFirstPatch, rpgitem);
            slotInvTypeOneFirstPatch++;
        }

        invTypeOneFirstPatch.setItem(invTypeOneFirstPatch.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
        invTypeOneFirstPatch.setItem(49, ItemsForGui.FilterItems(1));
        for (int j = 0; j < invTypeOneFirstPatch.getSize(); j++) {
            if (invTypeOneFirstPatch.getItem(j) == null)
                invTypeOneFirstPatch.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("typeOneFirstPatch", invTypeOneFirstPatch);

        Inventory invTypeTwoFirstPatch = Bukkit.createInventory(null, 54, "§5Информация о предметах");
        List<Integer> itemsTypeTwoFirstPatch = new ArrayList<>();
        int slotInvTypeTwoFirstPatch = 0;

        items = azerot.getInstance().getItemsForType().get("typeTwoFirstPatch");

        for (Integer j : items) {
            ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
            invTypeTwoFirstPatch.setItem(slotInvTypeTwoFirstPatch, rpgitem);
            slotInvTypeTwoFirstPatch++;
        }

        invTypeTwoFirstPatch.setItem(invTypeTwoFirstPatch.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
        invTypeTwoFirstPatch.setItem(49, ItemsForGui.FilterItems(2));
        for (int j = 0; j < invTypeTwoFirstPatch.getSize(); j++) {
            if (invTypeTwoFirstPatch.getItem(j) == null)
                invTypeTwoFirstPatch.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("typeTwoFirstPatch", invTypeTwoFirstPatch);


        Inventory invTypeThreeFirstPatch = Bukkit.createInventory(null, 54, "§5Информация о предметах");
        List<Integer> itemsTypeThreeFirstPatch = new ArrayList<>();
        int slotInvTypeThreeFirstPatch = 0;

        itemsTypeThreeFirstPatch = azerot.getInstance().getItemsForType().get("typeThreeFirstPatch");

        for (Integer j : itemsTypeThreeFirstPatch) {
            ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
            invTypeThreeFirstPatch.setItem(slotInvTypeThreeFirstPatch, rpgitem);
            slotInvTypeThreeFirstPatch++;
        }

        invTypeThreeFirstPatch.setItem(invTypeThreeFirstPatch.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
        invTypeThreeFirstPatch.setItem(49, ItemsForGui.FilterItems(3));
        for (int j = 0; j < invTypeThreeFirstPatch.getSize(); j++) {
            if (invTypeThreeFirstPatch.getItem(j) == null)
                invTypeThreeFirstPatch.setItem(j, ItemsForGui.corner());
        }
        azerot.getInventorys().put("typeThreeFirstPatch", invTypeThreeFirstPatch);


        for(int i = 1; i <= 2; i++){
            Inventory invTypeOneSecondPatch = Bukkit.createInventory(null, 54, "§5Информация о предметах. Страница " + i);
            List<Integer> itemsTypeOneSecondPatch = new ArrayList<>();

            if(i == 1){
                itemsTypeOneSecondPatch = azerot.getInstance().getItemsForType().get("typeOneSecondPatch").subList(0, 45);
            } else {
                itemsTypeOneSecondPatch = azerot.getInstance().getItemsForType().get("typeOneSecondPatch").subList(45, azerot.getInstance().getItemsForType().get("typeOneSecondPatch").size());
            }
            int slotInvTypeOneSecondPatch = 0;
            for (Integer j : itemsTypeOneSecondPatch) {
                ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
                invTypeOneSecondPatch.setItem(slotInvTypeOneSecondPatch, rpgitem);
                slotInvTypeOneSecondPatch++;
            }

            if(i == 1){
                invTypeOneSecondPatch.setItem(invTypeOneSecondPatch.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                invTypeOneSecondPatch.setItem(invTypeOneSecondPatch.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));
                invTypeOneSecondPatch.setItem(49, ItemsForGui.FilterItems(1));
            }else{
                invTypeOneSecondPatch.setItem(invTypeOneSecondPatch.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(45));
            }
            for (int j = 0; j < invTypeOneSecondPatch.getSize(); j++) {
                if (invTypeOneSecondPatch.getItem(j) == null)
                    invTypeOneSecondPatch.setItem(j, ItemsForGui.corner());
            }
            azerot.getInventorys().put("typeOneSecondPatch: " + i, invTypeOneSecondPatch);
        }

        for(int i = 1; i < 2; i++){
            Inventory invTypeTwoSecondPatch = Bukkit.createInventory(null, 54, "§5Информация о предметах. Страница " + i);
            List<Integer> itemsTypeTwoSecondPatch = new ArrayList<>();
            int slotInvTypeTwoSecondPatch = 0;

            itemsTypeTwoSecondPatch = azerot.getInstance().getItemsForType().get("typeTwoSecondPatch").subList(0, azerot.getInstance().getItemsForType().get("typeTwoSecondPatch").size());

            for (Integer j : itemsTypeTwoSecondPatch) {
                ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
                invTypeTwoSecondPatch.setItem(slotInvTypeTwoSecondPatch, rpgitem);
                slotInvTypeTwoSecondPatch++;
            }

            if(i == 1){
                invTypeTwoSecondPatch.setItem(invTypeTwoSecondPatch.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                invTypeTwoSecondPatch.setItem(invTypeTwoSecondPatch.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));
                invTypeTwoSecondPatch.setItem(49, ItemsForGui.FilterItems(2));
            }else{
                invTypeTwoSecondPatch.setItem(invTypeTwoSecondPatch.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(45));
            }
            for (int j = 0; j < invTypeTwoSecondPatch.getSize(); j++) {
                if (invTypeTwoSecondPatch.getItem(j) == null)
                    invTypeTwoSecondPatch.setItem(j, ItemsForGui.corner());
            }
            azerot.getInventorys().put("typeTwoSecondPatch: " + i, invTypeTwoSecondPatch);
        }

        for(int i = 1; i < 3; i++){
            Inventory invTypeThreeSecondPatch = Bukkit.createInventory(null, 54, "§5Информация о предметах. Страница " + i);
            List<Integer> itemsTypeThreeSecondPatch = new ArrayList<>();
            int slotInvTypeTwoSecondPatch = 0;

            if(i == 1){
                itemsTypeThreeSecondPatch = azerot.getInstance().getItemsForType().get("typeThreeSecondPatch").subList(0, 45);
            } else if (i == 2) {
                itemsTypeThreeSecondPatch = azerot.getInstance().getItemsForType().get("typeThreeSecondPatch").subList(45, azerot.getInstance().getItemsForType().get("typeThreeSecondPatch").size());
            }

            for (Integer j : itemsTypeThreeSecondPatch) {
                ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
                invTypeThreeSecondPatch.setItem(slotInvTypeTwoSecondPatch, rpgitem);
                slotInvTypeTwoSecondPatch++;
            }

            if(i == 1){
                invTypeThreeSecondPatch.setItem(invTypeThreeSecondPatch.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                invTypeThreeSecondPatch.setItem(invTypeThreeSecondPatch.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));
                invTypeThreeSecondPatch.setItem(49, ItemsForGui.FilterItems(3));
            }else{
                invTypeThreeSecondPatch.setItem(invTypeThreeSecondPatch.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(45));
            }
            for (int j = 0; j < invTypeThreeSecondPatch.getSize(); j++) {
                if (invTypeThreeSecondPatch.getItem(j) == null)
                    invTypeThreeSecondPatch.setItem(j, ItemsForGui.corner());
            }
            azerot.getInventorys().put("typeThreeSecondPatch: " + i, invTypeThreeSecondPatch);


        }

        /*
        for(int i = 1; i <= 3; i++){
            Inventory invThirdPatchItems = Bukkit.createInventory(null, 54, "§5Информация о предметах. Страница: " + i);
            List<Integer> itemsThirdPatch = new ArrayList<>();
            if(i == 1){
                itemsThirdPatch = azerot.getInstance().getAllItemsIdThirdPatch().subList(0,45);
            }
            if(i == 2){
                itemsThirdPatch = azerot.getInstance().getAllItemsIdThirdPatch().subList(45,90);
            }
            if(i == 3){
                itemsThirdPatch = azerot.getInstance().getAllItemsIdThirdPatch().subList(90, 135);
            }


            int slotThirdPacthItems = 0;
            for (Integer j : itemsThirdPatch) {
                ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0, 0,null));
                invThirdPatchItems.setItem(slotThirdPacthItems, rpgitem);
                slotThirdPacthItems++;
            }

            if(i == 1 || i == 2){
                invThirdPatchItems.setItem(invThirdPatchItems.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));
            }
            if(i == 1){
                invThirdPatchItems.setItem(invThirdPatchItems.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                invThirdPatchItems.setItem(49, ItemsForGui.FilterItems(0));
            }else{
                invThirdPatchItems.setItem(invThirdPatchItems.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(45));
            }
            for (int j = 0; j < invThirdPatchItems.getSize(); j++) {
                if (invThirdPatchItems.getItem(j) == null)
                    invThirdPatchItems.setItem(j, ItemsForGui.corner());
            }
            azerot.getInventorys().put("InvItemsForThirdPatch: " + i, invThirdPatchItems);
        }

         */


        for(int iID = 1; iID < RPGItemContainer.getAllItems(); iID++) {
            int id = azerot.getInstance().getItems().get(iID);
            try {
                if (id >= 1) {

                    Inventory invForItemInMob = Bukkit.createInventory(null, 54, "§5Информация о предмете");
                    ItemStack item = null;
                    RPGItemContainer itemID = RPGItemContainer.getRPGItemContainer(id);
                    int slotinvForItemInMob = 0;
                    for (int j = 1; j <= RPGMobsContainer.getRpgmobs().size(); j++) {
                        RPGMobsContainer rpgMobsContainer = RPGMobsContainer.getRPGMobsContainer(j);
                        if (rpgMobsContainer != null) {

                        String drop = rpgMobsContainer.getDrop();
                        String type = rpgMobsContainer.getType();

                        List<String> lore = new ArrayList<>();
                        String[] drops = drop.split(";");

                        for (String i : drops) {
                            String[] it = i.split(" ");

                            int dropid = Integer.parseInt(it[0]);
                            int maxamount = Integer.valueOf(it[1]);
                            double chance = Double.valueOf(it[2]);
                            if (dropid == id) {
                                if(id != 1 || id != 2 || id != 3 || id != 4 || id != 5){
                                    item = ItemsForGui.getCustomSkull(azerot.getEntitySkinsURL().get(id));
                                    ItemMeta meta = item.getItemMeta();
                                    meta.setDisplayName(rpgMobsContainer.getName());
                                    lore.add("§7Количество: " + maxamount);
                                    lore.add("§7Шанс: " + chance);
                                    meta.setLore(lore);
                                    item.setItemMeta(meta);
                                    if(slotinvForItemInMob <= 44){
                                        invForItemInMob.setItem(slotinvForItemInMob, item);
                                        slotinvForItemInMob++;
                                    }
                                }
                            }
                        }
                    }
                }
                    invForItemInMob.setItem(45, ItemsForGui.BlockDataBaseLeave());
                    if (itemID.getType() == 2 || itemID.getType() == 3) {
                        if (itemID.getHasMark() > 0) {
                            invForItemInMob.setItem(46, new RPGItem().getRPGItem(id, 0, 1, 1, 1, 0, 0, 0,null));
                            invForItemInMob.setItem(47, new RPGItem().getRPGItem(id, 0, 1, 2, 1, 0, 0, 0,null));
                            invForItemInMob.setItem(48, new RPGItem().getRPGItem(id, 0, 1, 3, 1, 0, 0, 0,null));
                            invForItemInMob.setItem(49, new RPGItem().getRPGItem(id, 0, 1, 4, 1, 0, 0, 0,null));
                            invForItemInMob.setItem(50, new RPGItem().getRPGItem(id, 0, 1, 5, 1, 0, 0, 0,null));
                            invForItemInMob.setItem(51, new RPGItem().getRPGItem(id, 0, 1, 6, 1, 0, 0, 0,null));
                            invForItemInMob.setItem(52, new RPGItem().getRPGItem(id, 0, 1, 7, 1, 0, 0, 0,null));
                            invForItemInMob.setItem(53, new RPGItem().getRPGItem(id, 0, 1, 8, 1, 0, 0, 0,null));
                        } else {
                            invForItemInMob.setItem(49, new RPGItem().getRPGItem(id, 0, 1, 0, 1, 0, 0, 0,null));
                        }
                    } else {
                        invForItemInMob.setItem(49, new RPGItem().getRPGItem(id, 0, 1, 0, 0, 0, 0, 0,null));
                    }
                    azerot.getInventorys().put("InvForItemInMobs: " + id, invForItemInMob);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            // Инвентарь предмета(короче понял)

        }


        for(int id = 1; id <= RPGMobsContainer.getRpgmobs().size(); id++){
            RPGMobsContainer rpgMobsContainer = RPGMobsContainer.getRPGMobsContainer(id);
            if(rpgMobsContainer != null){
                Inventory invItemsInMobs = Bukkit.createInventory(null, 54, "§5Информация о монстре");
                Inventory invItemsInMobs1 = Bukkit.createInventory(null, 54, "§5Информация о монстре");
                String drop = rpgMobsContainer.getDrop();
                String[] drops = drop.split(";");
                int slotinvItemsInMobs = 0;
                int slotinvItemsInMobs1 = 0;
                invItemsInMobs.setItem(invItemsInMobs.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                if(drops.length > 45){
                    invItemsInMobs.setItem(invItemsInMobs.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));
                    invItemsInMobs1.setItem(invItemsInMobs1.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(5));
                    invItemsInMobs1.setItem(49, ItemsForGui.EntityStats(id));
                }
                for (String i : drops) {
                    String[] it = i.trim().split(" ");
                    int dropid = Integer.parseInt(it[0]);
                    int maxamount = Integer.valueOf(it[1]);
                    double chance = Double.valueOf(it[2]);
                    ItemStack rpgitem = null;
                    if (RPGItemContainer.getRPGItemContainer(dropid).getHasMark() == 0) {
                        rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(dropid, 0, 1,  0, 1, 0,0, 0,null));
                    } else {
                        rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(dropid, 0, 1,  100, 100,0,0, 0,null));
                    }
                    ItemMeta meta = rpgitem.getItemMeta();
                    if (maxamount == 1)
                        meta.setDisplayName(meta.getDisplayName() + " §e" + chance + "%");
                    if (maxamount > 1)
                        meta.setDisplayName(meta.getDisplayName() + " §71-" + maxamount + " §e" + chance + "%");

                    rpgitem.setItemMeta(meta);
                    invItemsInMobs.setItem(49, ItemsForGui.EntityStats(id));

                    if(slotinvItemsInMobs < 45){
                        invItemsInMobs.setItem(slotinvItemsInMobs, rpgitem);
                        slotinvItemsInMobs++;
                    }else{
                        invItemsInMobs1.setItem(slotinvItemsInMobs1, rpgitem);
                        slotinvItemsInMobs1++;
                    }

                }

                for (int j = 0; j < invItemsInMobs.getSize(); j++) {
                    if (invItemsInMobs.getItem(j) == null)
                        invItemsInMobs.setItem(j, ItemsForGui.corner());
                }
                for (int j = 0; j < invItemsInMobs1.getSize(); j++) {
                    if (invItemsInMobs1.getItem(j) == null)
                        invItemsInMobs1.setItem(j, ItemsForGui.corner());
                }
                if(drops.length <= 45){
                    azerot.getInventorys().put("InvForItemsInMob: " + id, invItemsInMobs);
                }else{
                    azerot.getInventorys().put("InvForItemsInMob: " + id, invItemsInMobs);
                    azerot.getInventorys().put("InvForItemsInMob: " + id + "/2", invItemsInMobs1);
                }
            }
        }


        for(int h = 1; h <= 10; h++){
            Inventory invShop1 = Bukkit.getServer().createInventory(null, 54, "§6Торгаш §с#" + h);
            File file = new File(azerot.getInstance().getDataFolder() + File.separator + "shops.yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            for (int j = 0; j < invShop1.getSize(); j++)
                invShop1.setItem(j, ItemsForGui.corner());
            List<String> itemsforShop = yamlConfiguration.getStringList(String.valueOf(h) + ".items");
            int slotShop1 = 0;
            for (String i : itemsforShop) {
                String[] it = i.trim().split(" ");
                ItemStack rpgitem = (new RPGItem()).getRPGItem(Integer.valueOf(it[0]).intValue(), 0, Integer.valueOf(Integer.valueOf(it[1]).intValue()).intValue(),  0, 0,0,0, 0,null);
                invShop1.setItem(slotShop1, ItemsForGui.GUIItem(rpgitem));
                slotShop1++;
            }
            azerot.getInventorys().put("invShop: " + h, invShop1);
        }


    }
}
