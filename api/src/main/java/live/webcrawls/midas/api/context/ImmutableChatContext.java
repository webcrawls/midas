package live.webcrawls.midas.api.context;

import live.webcrawls.midas.api.sender.ChatSender;
import net.kyori.adventure.text.Component;

public record ImmutableChatContext(ChatSender sender,
                                   Component message,
                                   String rawMessage) implements ChatContext {

    @Override
    public ChatSender sender() {
        return this.sender;
    }

    @Override
    public Component message() {
        return this.message;
    }

    @Override
    public String rawMessage() {
        return this.rawMessage;
    }
}
