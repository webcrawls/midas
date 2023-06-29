package live.webcrawls.midas.common;

import cloud.commandframework.CommandManager;
import live.webcrawls.midas.common.config.ConfigurationService;
import live.webcrawls.midas.common.config.MidasConfiguration;
import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.module.MidasModule;
import live.webcrawls.midas.common.module.ModuleRegistry;
import live.webcrawls.midas.common.module.format.FormatModule;
import live.webcrawls.midas.common.module.format.FormatResult;
import live.webcrawls.midas.common.module.markdown.MarkdownModule;
import live.webcrawls.midas.common.module.minimessage.MiniMessageModule;
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
    private final CommandService commands;
    private final ConfigurationService configuration;
    private final ModuleRegistry modules;

    public MidasPlatform(final File dataDirectory,
                         final CommandManager<ChatSender> commandManager) {
        LOGGER.info("Loading MidasPlatform");
        this.dataDirectory = dataDirectory;

        // load module, command, and configuration services
        this.modules = new ModuleRegistry();
        this.commands = new CommandService(this, commandManager);
        this.configuration = new ConfigurationService(this.dataDirectory);
    }

    public ModuleRegistry modules() {
        return this.modules;
    }

    public void load() {
        // todo stub
        this.modules.registerModule(new URLModule());
        this.modules.registerModule(new FormatModule(null));
        this.modules.registerModule(new MarkdownModule());
        this.modules.registerModule(new MiniMessageModule());

        MidasConfiguration config = this.configuration.getConfiguration();

        for (String moduleId : config.enabledModules) {
            this.modules.enableModule(moduleId);
        }

        this.commands.registerCommands();
    }

    public ChatContext formatContext(ChatContext ctx) {
        for (var module : this.modules.all()) {
            FormatResult format = module.apply(ctx);

            if (!format.applied()) continue;
            ctx = ChatContext.immutable(ctx.sender(), format.result(), ctx.rawMessage());
        }

        return ctx;
    }

}
