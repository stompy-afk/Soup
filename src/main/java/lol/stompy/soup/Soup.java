package lol.stompy.soup;

import lol.stompy.soup.claim.ClaimHandler;
import lol.stompy.soup.kits.KitHandler;
import lol.stompy.soup.profile.ProfileHandler;
import lol.stompy.soup.util.sFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Soup extends JavaPlugin {

    @Getter
    private static Soup instance;

    private KitHandler kitHandler;
    private ProfileHandler profileHandler;
    private ClaimHandler claimHandler;

    private sFile profiles, kits;

    /**
     * plugin loading logic
     */

    @Override
    public void onLoad() {
        instance = this;
        this.saveDefaultConfig();
    }

    /**
     * plugin enabling logic
     */

    @Override
    public void onEnable() {
        this.profiles = new sFile(getDataFolder(), "profiles.yml");
        this.kits = new sFile(getDataFolder(), "kits.yml");

        this.kitHandler = new KitHandler(this);
        this.profileHandler = new ProfileHandler(this);
        this.claimHandler = new ClaimHandler(this);

    }

    /**
     * plugin disabling logic
     */

    @Override
    public void onDisable() {
        profileHandler.getAll().forEach(profile -> profileHandler.handleRemoval(profile, false));
    }

}
