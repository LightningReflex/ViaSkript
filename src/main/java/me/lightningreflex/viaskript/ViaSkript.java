package me.lightningreflex.viaskript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

@SuppressWarnings("unused")
public final class ViaSkript extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        SkriptAddon addonInstance = Skript.registerAddon(this);
        try {
            addonInstance.loadClasses("me.lightningreflex.viaskript", "expressions");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
