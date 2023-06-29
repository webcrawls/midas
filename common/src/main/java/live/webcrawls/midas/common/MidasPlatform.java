package live.webcrawls.midas.common;

import cloud.commandframework.CommandManager;
import live.webcrawls.midas.common.config.ConfigurationService;
import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.module.MidasModule;
import live.webcrawls.midas.common.module.format.FormatResult;
import live.webcrawls.midas.common.sender.ChatSender;
import live.webcrawls.midas.common.command.CommandService;
import live.webcrawls.midas.common.module.greentext.GreentextModule;
import live.webcrawls.midas.common.module.url.URLModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MidasPlatform {

    public static final Logger LOGGER = Logger.getLogger("midas");

    private final File dataDirectory;
    private final List<MidasModule> modules;
    private final CommandService commands;
    private final ConfigurationService configuration;

    public MidasPlatform(final File dataDirectory,
                         final CommandManager<ChatSender> commandManager) {
        this.dataDirectory = dataDirectory;
        this.modules = new ArrayList<>();
        this.commands = new CommandService(this, commandManager);
        this.configuration = new ConfigurationService(this.dataDirectory);
    }

    public List<MidasModule> modules() {
        return List.copyOf(this.modules);
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
