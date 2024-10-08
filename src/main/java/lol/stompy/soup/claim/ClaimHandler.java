package lol.stompy.soup.claim;

import lol.stompy.soup.Soup;

import java.util.HashMap;
import java.util.Map;

public class ClaimHandler {

    private final Map<String, Claim> claims;
    private final Soup soup;

    /**
     * handles all claim related procedures
     *
     * @param soup instance of main
     */

    public ClaimHandler(Soup soup) {
        this.soup = soup;
        this.claims = new HashMap<>();
    }

}
