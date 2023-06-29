package live.webcrawls.midas.common.sender;

import live.webcrawls.midas.common.context.ChatContext;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

import java.util.Map;
import java.util.UUID;

/**
 * {@code ChatSender} defines the sender of a chat message.
 */
public interface ChatSender extends Audience {

    /**
     * Returns an immutable {@code ChatSender} instance.
     *
     * @param uuid     the uuid of the sender
     * @param username the username of the sender
     * @param meta     the sender meta
     * @return the {@code ChatSender} instance
     */
    static ChatSender immutable(UUID uuid,
                                String username,
                                Map<String, String> meta) {
        return new ImmutableChatSender(uuid, username, meta);
    }

    /**
     * Returns the {@code UUID} of this sender.
     *
     * @return the {@code UUID} of the sender
     * @implNote the all-zeros {@code UUID} is reserved for console/server senders
     */
    UUID uuid();

    /**
     * Returns the raw username of the sender.
     *
     * @return the raw username without formatting
     */
    String username();

    /**
     * Returns the metadata map for this sender.
     *
     * @return the sender's metadata
     */
    Map<String, String> meta();

    /**
     * Sends a message to the recipient.
     *
     * @param ctx the chat context
     */
    void sendMessage(ChatContext ctx);

}
