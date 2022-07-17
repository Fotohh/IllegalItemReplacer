package me.Xaxis.replace;

import me.Xaxis.replace.File.BannedItems;
import me.Xaxis.replace.Listener.onInventoryClick;
import me.Xaxis.replace.Listener.onInventoryOpen;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.commands.ReplaceCommand;
import me.Xaxis.replace.commands.SetPanicChestCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class IIR extends JavaPlugin {

    private static IIR IIR;

    public static IIR getInstance() {
        return IIR;
    }

    BannedItems itemsFile = new BannedItems();

    BannedItemManager bannedItems;

    @Override
    public void onEnable() {

        bannedItems = new BannedItemManager(itemsFile);
        itemsFile.run(this, "ItemData");
        bannedItems.loadItems();

        getCommand("replaceItem").setExecutor(new ReplaceCommand(this, itemsFile));
        getCommand("setPanicChestLocation").setExecutor(new SetPanicChestCommand(this));
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onInventoryClick(this, bannedItems), this);
        getServer().getPluginManager().registerEvents(new onInventoryOpen(this, bannedItems), this);


    }
}
