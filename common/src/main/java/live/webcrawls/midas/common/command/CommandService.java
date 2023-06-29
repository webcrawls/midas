package live.webcrawls.midas.common.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import live.webcrawls.midas.common.sender.ChatSender;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

public class CommandService {

    private CommandManager<ChatSender> manager;

    public CommandService(CommandManager<ChatSender> manager) {
        this.manager = manager;
    }

    public void registerCommands() {
        Command.Builder<ChatSender> root = this.manager.commandBuilder("midas");
        Command.Builder<ChatSender> main = root.handler(this::handleHelp);

        this.manager.command(main);
    }

    private void handleHelp(CommandContext<ChatSender> sender) {
        sender.getSender().sendMessage(Component.text("LMAOO"));
    }

}
