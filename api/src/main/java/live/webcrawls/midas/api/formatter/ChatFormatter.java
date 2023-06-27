package live.webcrawls.midas.api.formatter;

import live.webcrawls.midas.api.context.ChatContext;

/**
 * {@code ChatModule} defines common methods a chat module should implement.
 */
public interface ChatFormatter {

    String id();

    FormatResult apply(ChatContext context);

}
