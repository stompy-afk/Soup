package lol.stompy.soup.profile;

import lol.stompy.soup.Soup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface ProfileStorage {

    /**
     * Loads all profiles
     *
     * @param profiles profiles map
     * @param soup instance of main
     */

    void loadAll(Map<UUID, Profile> profiles, Soup soup);

    /**
     * Handles the removal of a singular profile
     *
     * @param profiles profiles map
     * @param soup instance of main
     * @param profile instance of the profile
     */

    void handleRemoval(Map<UUID, Profile> profiles, Soup soup, Profile profile);


}
