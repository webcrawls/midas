package live.webcrawls.midas.common;

import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.formatter.ChatFormatter;
import live.webcrawls.midas.api.formatter.FormatResult;
import live.webcrawls.midas.common.module.GreentextChatFormatter;
import live.webcrawls.midas.common.module.URLFormatter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MidasPlatform {

    private final File dataDirectory;
    private final List<ChatFormatter> formatters;

    public MidasPlatform(final File dataDirectory) {
        this.dataDirectory = dataDirectory;
        this.formatters = new ArrayList<>();
    }

    public void loadConfiguration() {
        // todo stub
        this.formatters.add(new GreentextChatFormatter());
        this.formatters.add(new URLFormatter());
    }

    public ChatContext formatContext(ChatContext ctx) {
        for (var formatter : this.formatters) {
            FormatResult format = formatter.apply(ctx);

            if (!format.applied()) continue;
            ctx = ChatContext.immutable(ctx.sender(), format.result(), ctx.rawMessage());
        }

        return ctx;
    }

}
