package live.webcrawls.midas.common;

import cloud.commandframework.CommandManager;
import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.formatter.ChatModule;
import live.webcrawls.midas.api.formatter.FormatResult;
import live.webcrawls.midas.api.sender.ChatSender;
import live.webcrawls.midas.common.command.CommandService;
import live.webcrawls.midas.common.module.GreentextModule;
import live.webcrawls.midas.common.module.URLModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MidasPlatform {

    private final File dataDirectory;
    private final List<ChatModule> modules;
    private final CommandService commands;

    public MidasPlatform(final File dataDirectory,
                         final CommandManager<ChatSender> commandManager) {
        this.dataDirectory = dataDirectory;
        this.modules = new ArrayList<>();
        this.commands = new CommandService(commandManager);
    }

    public void load() {
        // todo stub
        this.modules.add(new GreentextModule());
        this.modules.add(new URLModule());
        this.commands.registerCommands();
    }

    public ChatContext formatContext(ChatContext ctx) {
        for (var module : this.modules) {
            FormatResult format = module.apply(ctx);

            if (!format.applied()) continue;
            ctx = ChatContext.immutable(ctx.sender(), format.result(), ctx.rawMessage());
        }

        return ctx;
    }

}
