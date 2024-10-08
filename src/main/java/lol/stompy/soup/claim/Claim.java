package lol.stompy.soup.claim;

import lol.stompy.soup.util.sFile;
import lol.stompy.soup.util.sLocation;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Claim {

    private final String name;
    private final Location locationA, locationB;

    private final List<ClaimTrait> claimTraits;

    /**
     * creates a claim from two locations
     *
     * @param locationA first location
     * @param locationB second location
     */

    public Claim(String name, Location locationA, Location locationB) {
        this.name = name;

        this.locationA = locationA;
        this.locationB = locationB;

        this.claimTraits = new LinkedList<>();
    }

    /**
     * adds a claim trait to the claim
     *
     * @param claimTrait claim trait to add
     */

    public final void addClaimTrait(ClaimTrait claimTrait) {
        claimTraits.add(claimTrait);
    }

    /**
     * removes a claim trait from the claim
     *
     * @param claimTrait claim trait to remove
     */

    public final void removeClaimTrait(ClaimTrait claimTrait) {
        if (!claimTraits.contains(claimTrait))
            return;

        claimTraits.remove(claimTrait);
    }

    /**
     * checks if a claim trait is in the list
     *
     * @param claimTrait claim trait to check if its in the list
     * @return {@link Boolean}
     */

    public final boolean hasClaimTrait(ClaimTrait claimTrait) {
        return claimTraits.contains(claimTrait);
    }

    /**
     * checks if a location is in the claim
     *
     * @param location location to check
     * @return {@link Boolean}
     */

    public final boolean isInClaim(Location location) {
        double x1 = Math.min(locationA.getX(), locationB.getX());
        double y1 = Math.min(locationA.getY(), locationB.getY());
        double z1 = Math.min(locationA.getZ(), locationB.getZ());

        double x2 = Math.max(locationA.getX(), locationB.getX());
        double y2 = Math.max(locationA.getY(), locationB.getY());
        double z2 = Math.max(locationA.getZ(), locationB.getZ());

        return location.getX() >= x1 && location.getX() <= x2
                && location.getY() >= y1 && location.getY() <= y2
                && location.getZ() >= z1 && location.getZ() <= z2;
    }

    /**
     * saves the data into the config
     *
     * @param file file to save data in
     */

    @SneakyThrows
    public final void saveConfig(sFile file) {
        final FileConfiguration fileConfiguration = file.getConfig();

        final List<String> strings = new LinkedList<>();
        claimTraits.forEach(claimTrait -> strings.add(claimTrait.toString()));

        fileConfiguration.set(name + ".locationA", sLocation.locationToString(locationA));
        fileConfiguration.set(name + ".locationB", sLocation.locationToString(locationB));
        fileConfiguration.set(name + ".claimTraits", strings);

        file.save();
    }

    /**
     * puts the data of the claim into a document
     *
     * @return {@link Document}
     */

    public Document toBson() {
        final List<String> strings = new LinkedList<>();
        claimTraits.forEach(claimTrait -> strings.add(claimTrait.toString()));

        return new Document("_id", name)
                .append("locationA", sLocation.locationToString(locationA))
                .append("locationB", sLocation.locationToString(locationB))
                .append("claimTraits", strings);
    }

}
