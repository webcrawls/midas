package live.webcrawls.midas.paper;

import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.sender.ChatSender;
import live.webcrawls.midas.common.MidasPlatform;
import live.webcrawls.midas.paper.sender.ConsoleChatSender;
import live.webcrawls.midas.paper.sender.PlayerChatSender;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MidasPlugin extends JavaPlugin implements Listener, ChatRenderer {

    private MidasPlatform platform;

    @Override
    public void onEnable() {
        PaperCommandManager<ChatSender> manager;
        try {
            manager = new PaperCommandManager<>(this, AsynchronousCommandExecutionCoordinator.simpleCoordinator(), this::mapPlayer, this::mapSender);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.platform = new MidasPlatform(this.getDataFolder(), manager);
        this.platform.load();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        event.renderer(this);
    }

    @Override
    public @NotNull Component render(@NotNull Player source, @NotNull Component sourceDisplayName, @NotNull Component message, @NotNull Audience viewer) {
        ChatSender sender = mapPlayer(source);
        return this.platform.formatContext(ChatContext.immutable(sender, message, PlainTextComponentSerializer.plainText().serialize(message))).message();
    }

    private ChatSender mapPlayer(final CommandSender sender) {
        if (sender instanceof Player player) {
            return new PlayerChatSender(player);
        } else if (sender instanceof ConsoleCommandSender console) {
            return ConsoleChatSender.SENDER;
        } else {
            // todo something better :|
            return ChatSender.immutable(null, null, null);
        }
    }

    private @Nullable Player mapSender(final ChatSender sender) {
        return Bukkit.getPlayer(sender.uuid());
    }
}
