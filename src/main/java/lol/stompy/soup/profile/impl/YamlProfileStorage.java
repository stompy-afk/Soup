package lol.stompy.soup.profile.impl;

import lol.stompy.soup.Soup;
import lol.stompy.soup.profile.Profile;
import lol.stompy.soup.profile.ProfileStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class YamlProfileStorage implements ProfileStorage {
    /**
     * Loads all profiles
     *
     * @param profiles profiles map
     * @param soup     instance of main
     */
    @Override
    public void loadAll(Map<UUID, Profile> profiles, Soup soup) {
        soup.getProfiles().getConfig().getConfigurationSection("profiles").getKeys(false).forEach(s -> {
            final Profile profile = new Profile(Objects.requireNonNull(soup.getConfig().getConfigurationSection("profiles." + s)));
            profiles.put(profile.getUuid(), profile);
        });
    }

    /**
     * Handles the removal of a singular profile
     *
     * @param profiles profiles map
     * @param soup     instance of main
     * @param profile  instance of the profile
     */
    @Override
    public void handleRemoval(Map<UUID, Profile> profiles, Soup soup, Profile profile) {
        profile.saveConfig(soup.getProfiles());
        profiles.remove(profile.getUuid());
    }
}
