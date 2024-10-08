package lol.stompy.soup;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Soup extends JavaPlugin {

    @Getter
    private static Soup instance;

    /**
     * plugin loading concept
     */

    @Override
    public void onLoad() {
        instance = this;
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
