package live.webcrawls.midas.common.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import live.webcrawls.midas.common.MidasPlatform;
import live.webcrawls.midas.common.sender.ChatSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;

import static live.webcrawls.midas.common.constants.Messages.*;

public class CommandService {

    private MidasPlatform platform;
    private CommandManager<ChatSender> manager;

    public CommandService(MidasPlatform platform,
                          CommandManager<ChatSender> manager) {
        this.platform = platform;
        this.manager = manager;
    }

    public void registerCommands() {
        Command.Builder<ChatSender> root = this.manager.commandBuilder("midas");
        Command.Builder<ChatSender> main = root.handler(this::handleHelp);
        Command.Builder<ChatSender> modules = root.literal("modules")
                .handler(this::handleModules);

        this.manager.command(main);
        this.manager.command(modules);
    }

    private void handleHelp(CommandContext<ChatSender> sender) {
        var messages = List.of(
                defaultStyle("version 1.0.0 - @webcrawls"),
                Component.empty(),
                defaultStyle("Commands:"),
                defaultStyle("- /midas help"),
                defaultStyle("- /midas reload"),
                defaultStyle("- /midas modules")
        );
        sender.getSender().sendMessage(pluginText(messages));
    }

    private void handleModules(CommandContext<ChatSender> sender) {
        var modules = this.platform.modules().stream().map(module -> defaultStyle(module.id()).color(NamedTextColor.GREEN)).toList();
        sender.getSender().sendMessage(pluginText(defaultStyle("Modules: " + modules.size())));
        sender.getSender().sendMessage(pluginText(modules));
    }

}
