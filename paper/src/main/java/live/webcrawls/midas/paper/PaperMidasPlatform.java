package live.webcrawls.midas.paper;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.formatter.ChatFormatter;
import live.webcrawls.midas.api.sender.ChatSender;
import live.webcrawls.midas.common.MidasPlatform;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class PaperMidasPlatform extends MidasPlatform implements Listener, ChatRenderer {

    private final JavaPlugin plugin;
    private final List<ChatFormatter> formatters;

    public PaperMidasPlatform(final JavaPlugin plugin, final List<ChatFormatter> formatters) {
        this.plugin = plugin;
        this.formatters = formatters;
    }

    public void registerListeners() {
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public void unregisterListeners() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        event.renderer(this);
    }

    @Override
    public @NotNull Component render(@NotNull Player source,
                                     @NotNull Component sourceDisplayName,
                                     @NotNull Component message,
                                     @NotNull Audience viewer) {
        String rawMessage = PlainTextComponentSerializer.plainText().serialize(message);

        ChatSender sender = ChatSender.immutable(source.getUniqueId(), source.getName(), Map.of());
        ChatContext context = ChatContext.immutable(sender, message, rawMessage);

        ChatContext formattedContext = this.formatContext(context);

        return formattedContext.message();
    }
}
