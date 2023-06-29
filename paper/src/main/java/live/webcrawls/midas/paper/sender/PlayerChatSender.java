package live.webcrawls.midas.paper.sender;

import live.webcrawls.midas.common.MidasPlatform;
import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.sender.ChatSender;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.identity.Identity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerChatSender implements ChatSender, ForwardingAudience {

    private final UUID uuid;
    private final String name;
    private final Map<String, String> meta;
    private Player playerRef;


    public PlayerChatSender(final Player player) {
        this.playerRef = player;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.meta = Map.of();
    }

    @Override
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public String username() {
        return this.name;
    }

    @Override
    public Map<String, String> meta() {
        return this.meta;
    }

    @Override
    public void sendMessage(ChatContext ctx) {
        if (this.playerRef != null && this.playerRef.isOnline()) {
            this.playerRef.sendMessage(Identity.identity(ctx.sender().uuid()), ctx.message());
        } else {
            MidasPlatform.LOGGER.warning("Tried to send message offline player " + ctx.sender().username());
        }
    }

    @Override
    public @NotNull Iterable<? extends Audience> audiences() {
        if (this.playerRef != null) {
            return List.of(this.playerRef);
        }

        return List.of();
    }
}
