package lol.stompy.soup.profile.impl;

import lol.stompy.soup.Soup;
import lol.stompy.soup.profile.Profile;
import lol.stompy.soup.profile.ProfileStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MongoProfileStorage implements ProfileStorage {

    /**
     * Loads all profiles
     *
     * @param profiles profiles map
     * @param soup     instance of main
     */
    @Override
    public void loadAll(Map<UUID, Profile> profiles, Soup soup) {

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

    }
}
