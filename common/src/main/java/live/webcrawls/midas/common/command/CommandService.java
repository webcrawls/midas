package live.webcrawls.midas.common.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import live.webcrawls.midas.api.sender.ChatSender;

public class CommandService {

    private CommandManager<ChatSender> manager;

    public CommandService(CommandManager<ChatSender> manager) {
        this.manager = manager;
    }

    public void registerCommands() {
        Command.Builder<ChatSender> root = this.manager.commandBuilder("midas");
    }

    private void handleHelp(CommandContext<ChatSender> sender) {

    }

}
