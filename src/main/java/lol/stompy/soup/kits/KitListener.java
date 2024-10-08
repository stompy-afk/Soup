package lol.stompy.soup.kits;

import lol.stompy.soup.util.CC;
import lombok.AllArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

@AllArgsConstructor
public class KitListener implements Listener {

    private final KitHandler kitHandler;

    @EventHandler
    public final void onSignClickEvent(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;

        final Block block = event.getClickedBlock();

        if (!(block instanceof Sign))
            return;

        final Sign sign = (Sign) block;

        if (!sign.getLine(0).equalsIgnoreCase("[Kit]"))
            return;

        final String s = sign.getLine(1);
        final Optional<Kit> kitOptional = kitHandler.getKit(s);

        if (kitOptional.isEmpty()) {
            player.sendMessage(CC.translate("&cThis kit does not exist!"));
            return;
        }

        final Kit kit = kitOptional.get();
        kit.apply(player);
    }

}
