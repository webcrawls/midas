package live.webcrawls.midas.paper;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.sender.ChatSender;
import live.webcrawls.midas.common.MidasPlatform;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MidasPlugin extends JavaPlugin implements Listener, ChatRenderer {

    private MidasPlatform platform;

    @Override
    public void onEnable() {
        this.platform = new MidasPlatform(this.getDataFolder());
        this.platform.loadConfiguration();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        event.renderer(this);
    }

    @Override
    public @NotNull Component render(@NotNull Player source, @NotNull Component sourceDisplayName, @NotNull Component message, @NotNull Audience viewer) {
        ChatSender sender = ChatSender.immutable(source.getUniqueId(), source.getName(), Map.of());
        return this.platform.formatContext(ChatContext.immutable(sender, message, PlainTextComponentSerializer.plainText().serialize(message))).message();
    }
}
