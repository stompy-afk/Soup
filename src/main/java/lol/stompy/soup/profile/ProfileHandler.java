package lol.stompy.soup.profile;

import lol.stompy.soup.Soup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileHandler {

    private final Map<UUID, Profile> profiles;
    private final Soup soup;

    /**
     * profile handler, handles all soup related activites
     *
     * @param soup instance of main
     */

    public ProfileHandler(Soup soup) {
        this.soup = soup;
        this.profiles = new HashMap<>();
    }

}
