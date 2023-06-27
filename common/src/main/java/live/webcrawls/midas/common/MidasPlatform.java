package live.webcrawls.midas.common;

import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.formatter.ChatFormatter;
import live.webcrawls.midas.api.formatter.FormatResult;

import java.util.ArrayList;
import java.util.List;

public class MidasPlatform {

    private final List<ChatFormatter> formatters;

    public MidasPlatform() {
        this.formatters = new ArrayList<>();
    }

    public MidasPlatform(final List<ChatFormatter> formatters) {
        this.formatters = formatters;
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
