package live.webcrawls.midas.paper;

import io.papermc.paper.event.player.AsyncChatEvent;
import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.formatter.FormatResult;
import live.webcrawls.midas.api.formatter.ChatFormatter;
import live.webcrawls.midas.api.sender.ChatSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class PaperChatService implements Listener {

    private final JavaPlugin plugin;
    private final List<ChatFormatter> formatters;

    public PaperChatService(final JavaPlugin plugin, final List<ChatFormatter> formatters) {
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
        Player player = event.getPlayer();
        Component message = event.message();
        String rawMessage = PlainTextComponentSerializer.plainText().serialize(message);

        ChatSender sender = ChatSender.immutable(player.getUniqueId(), player.getName(), Map.of());
        ChatContext context = ChatContext.immutable(sender, event.originalMessage(), rawMessage);

        for (var formatter : this.formatters) {
            FormatResult format = formatter.apply(context);

            if (!format.applied()) continue;
            context = ChatContext.immutable(sender, format.result(), rawMessage);
        }

        event.message(context.message());
    }


}
