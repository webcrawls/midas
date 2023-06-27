package live.webcrawls.midas.common.module;

import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.formatter.ChatFormatter;
import live.webcrawls.midas.api.formatter.FormatResult;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class URLFormatter implements ChatFormatter {

    // https://stackoverflow.com/a/163398
    // thoughts: todo
    private static String URL_REGEX_1 = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    @Override
    public String id() {
        return "url";
    }

    @Override
    public FormatResult apply(ChatContext context) {
        String input = context.rawMessage();
        Pattern pattern = Pattern.compile(URL_REGEX_1);

        List<String> urls = new ArrayList<>();

        for (var part : input.split(" ")) {
            // 1. detect if part is a url
            if (pattern.matcher(part).matches()) {
                urls.add(part);
            }
        }

        if (urls.isEmpty()) {
            return FormatResult.immutable(context.message(), false);
        }

        Component message = context.message();

        for (var url : urls) {
            Component hoverText = Component.text("Click to visit URL", NamedTextColor.GRAY);
            Component text = Component
                    .text(url, NamedTextColor.BLUE)
                    .hoverEvent(HoverEvent.showText(hoverText))
                    .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, url));
            message = message.replaceText(builder -> builder
                    .match(url)
                    .replacement(text));
        }

        return FormatResult.immutable(message, true);

        // 2. generate a cute component if it is
        // 3. update the context's component and continue
        // 4. return result
    }
}
