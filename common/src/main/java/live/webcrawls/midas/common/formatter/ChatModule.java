package live.webcrawls.midas.common.formatter;

import live.webcrawls.midas.common.context.ChatContext;

/**
 * {@code ChatModule} defines common methods a chat module should implement.
 */
public interface ChatModule {

    String id();

    FormatResult apply(ChatContext context);

}
