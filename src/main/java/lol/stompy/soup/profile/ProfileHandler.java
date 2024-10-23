package lol.stompy.soup.profile;

import com.mongodb.Mongo;
import lol.stompy.soup.Soup;
import lol.stompy.soup.profile.impl.MongoProfileStorage;
import lol.stompy.soup.profile.impl.YamlProfileStorage;

import java.util.*;

public class ProfileHandler {

    private final Map<UUID, Profile> profiles;
    private final Soup soup;

    private final ProfileStorage profileStorage;

    /**
     * profile handler, handles all soup related activites
     *
     * @param soup instance of main
     */

    public ProfileHandler(Soup soup) {
        this.soup = soup;
        this.profiles = new HashMap<>();

        if (soup.getConfig().getBoolean("config"))
            profileStorage = new YamlProfileStorage();
        else
            profileStorage = new MongoProfileStorage();

        this.loadAll(true);
    }

    /**
     * Loads all profile from database
     *
     * @param async to do async
     */

    private void loadAll(boolean async) {

        if (async) {
            soup.getServer().getScheduler().runTaskAsynchronously(soup, () -> loadAll(false));
            return;
        }

        profileStorage.loadAll(profiles, soup);
    }

    /**
     * Handles the removal of a profile
     *
     * @param profile profile to remove
     * @param async to do task async or not
     */

    public final void handleRemoval(Profile profile, boolean async) {

        if (async) {
            soup.getServer().getScheduler().runTaskAsynchronously(soup, () -> handleRemoval(profile, false));
            return;
        }

        profileStorage.handleRemoval(profiles, soup, profile);
    }

    /**
     * Gets an optional of the profile of a target
     *
     * @param uuid uuid oof target
     * @return {@link Optional<Profile>}
     */

    public final Optional<Profile> get(UUID uuid) {
        return Optional.ofNullable(profiles.get(uuid));
    }

    /**
     * Gets all profiles
     *
     * @return {@link Collection<Profile>}
     */

    public final Collection<Profile> getAll() {
        return profiles.values();
    }


}
