package lol.stompy.soup.kits;

import lol.stompy.soup.Soup;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class KitHandler {

    private final Map<UUID, Kit> kits;
    private final Soup soup;

    /**
     * kit handler, handles all kit related stuff
     *
     * @param soup instance of main
     */

     public KitHandler(Soup soup) {
        this.kits = new HashMap<>();
        this.soup = soup;
    }

    /**
     * finds a kit by its name
     *
     * @param name name of the kit
     * @return {@link Optional<Kit>}
     */

    public final Optional<Kit> getKit(String name) {
         return kits.values().stream().filter(kit -> kit.getName().equalsIgnoreCase(name)).findFirst();
    }

    /**
     * finds a kit by its uuid
     *
     * @param uuid uuid of the kit
     * @return {@link Optional<Kit>}
     */

    public final Optional<Kit> getKit(UUID uuid) {
         return Optional.ofNullable(kits.get(uuid));
    }

}
