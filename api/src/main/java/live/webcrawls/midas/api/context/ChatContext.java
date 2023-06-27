package live.webcrawls.midas.api.context;

import live.webcrawls.midas.api.sender.ChatSender;
import net.kyori.adventure.text.Component;

/**
 * {@code ChatContext};
 */
public interface ChatContext {

    static ChatContext immutable(ChatSender sender,
                                 Component message,
                                 String rawMessage) {
        return new ImmutableChatContext(sender, message, rawMessage);
    }

    ChatSender sender();

    Component message();

    String rawMessage();

}
