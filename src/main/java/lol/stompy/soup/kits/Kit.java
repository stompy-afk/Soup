package lol.stompy.soup.kits;

import lol.stompy.soup.util.CC;
import lol.stompy.soup.util.Serializer;
import lol.stompy.soup.util.sFile;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Kit {

    private final UUID uuid;

    private String name, permission;
    private ItemStack[] contents, armorContents;

    /**
     * creating a new kit
     *
     * @param name name of my kit
     */

    public Kit(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.permission = "null";
    }

    /**
     * creating a new kit with the player's inventory
     *
     * @param name name of the kit
     * @param player player to create the kit off
     */

    public Kit(String name, Player player) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.permission = "null";

        this.contents = player.getInventory().getContents();
        this.armorContents = player.getInventory().getArmorContents();
    }

    /**
     * creates a kit from a document
     *
     * @param document document to create kit from
     */

    @SneakyThrows
    public Kit(Document document) {
        this.uuid = UUID.fromString(document.getString("_id"));
        this.name = document.getString("name");
        this.permission = document.getString("permission");

        final String contents = document.getString("contents");
        final String armorContents = document.getString("armorContents");

        if (!contents.equalsIgnoreCase("null"))
            this.contents = Serializer.itemStackArrayFromBase64(contents);

        if (!armorContents.equalsIgnoreCase("null"))
            this.armorContents = Serializer.itemStackArrayFromBase64(armorContents);
    }

    /**
     * creates a kit from the config
     *
     * @param fileConfiguration file to get data from
     * @param path path to reach
     */

    @SneakyThrows
    public Kit(FileConfiguration fileConfiguration, String path) {
        this.uuid = UUID.fromString(Objects.requireNonNull(fileConfiguration.getString(path + "id")));
        this.name = Objects.requireNonNull(fileConfiguration.getString(path + "name"));
        this.permission = fileConfiguration.getString(path + "permission");

        final String contents = fileConfiguration.getString(path + "contents");
        final String armorContents = fileConfiguration.getString(path + "armorContents");

        if (!Objects.requireNonNull(contents).equalsIgnoreCase("null"))
            this.contents = Serializer.itemStackArrayFromBase64(contents);

        if (!Objects.requireNonNull(armorContents).equalsIgnoreCase("null"))
            this.armorContents = Serializer.itemStackArrayFromBase64(armorContents);
    }

    /**
     * applies the kit to a player
     *
     * @param player player to apply a kit to
     */

    public void apply(Player player) {
        if (!permission.equalsIgnoreCase("null") && !player.hasPermission(permission)) {
            player.sendMessage(CC.translate("&cYou don't have permission to use this kit!"));
            return;
        }

        if (contents != null)
            player.getInventory().setContents(contents);

        if (armorContents != null)
            player.getInventory().setArmorContents(armorContents);

        player.sendMessage(CC.translate("&aApplied kit " + name));
    }

    /**
     * saves the kit into a config
     *
     * @param file file configuration to save the kit into
     */

    @SneakyThrows
    public void saveConfig(sFile file) {
        final FileConfiguration fileConfiguration = file.getConfig();
        final String uuidString = uuid.toString();

        fileConfiguration.set(uuidString + ".name", name);
        fileConfiguration.set(uuidString + ".permission", permission);

        fileConfiguration.set(uuidString + ".contents", contents == null ? "null" : Serializer.itemStackArrayToBase64(contents));
        fileConfiguration.set(uuidString + ".armorContents", armorContents == null ? "null" : Serializer.itemStackArrayToBase64(armorContents));

        file.save();
    }

    /**
     * puts all the kits info in a document
     *
     * @return {@link Document}
     */

    public Document toBson() {
        return new Document("_id", uuid.toString())
                .append("name", name)
                .append("permission", permission)
                .append("contents", contents == null ? "null" : Serializer.itemStackArrayToBase64(contents))
                .append("armorContents", armorContents == null ? "null" : Serializer.itemStackArrayToBase64(armorContents));
    }

}
