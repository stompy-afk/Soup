package lol.stompy.soup.profile;

import lol.stompy.soup.util.Serializer;
import lol.stompy.soup.util.sFile;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private final UUID uuid;

    private int kills, deaths, coins;

    /**
     * making a profile for the player
     *
     * @param uuid uuid of the player
     */

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * saves a profile to a config
     *
     * @param file file to save to
     */

    @SneakyThrows
    public void saveConfig(sFile file) {
        final FileConfiguration fileConfiguration = file.getConfig();
        final String uuidString = uuid.toString();

        fileConfiguration.set(uuidString + ".kills", kills);
        fileConfiguration.set(uuidString + ".deaths", deaths);
        fileConfiguration.set(uuidString + ".coins", coins);

        file.save();
    }

    /**
     * Turns the data in the profile to a document
     *
     * @return {@link Document}
     */

    public Document toBson() {
        return new Document("_id", uuid.toString())
                .append("kills", kills)
                .append("deaths", deaths)
                .append("coins", coins);
    }


}
