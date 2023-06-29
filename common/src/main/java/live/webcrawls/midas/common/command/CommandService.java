package live.webcrawls.midas.common.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import live.webcrawls.midas.common.MidasPlatform;
import live.webcrawls.midas.common.constants.Messages;
import live.webcrawls.midas.common.module.MidasModule;
import live.webcrawls.midas.common.sender.ChatSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
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
        if (this.manager == null) {
            MidasPlatform.LOGGER.warning("null command manager");
            return;
        }
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
        List<MidasModule> modules = this.platform.modules().all();
        List<Component> messages = new ArrayList<>();

        messages.add(defaultStyle(modules.size() + " modules loaded."));

        for (var module : modules) {
            messages.add(defaultStyle("- " + module.id()));
        }

        sender.getSender().sendMessage(Messages.pluginText(messages));
    }

}
