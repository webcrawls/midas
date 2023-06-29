package live.webcrawls.midas.common.module;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.formatter.ChatModule;
import live.webcrawls.midas.common.formatter.FormatResult;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class URLModule implements ChatModule {

    // https://stackoverflow.com/a/163398
    // requires protocol :(
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
            if (pattern.matcher(part).matches()) {
                urls.add(part);
            }
        }

        if (urls.isEmpty()) {
            return FormatResult.immutable(context.message(), false);
        }

        Component message = context.message();

        for (var url : urls) {
            String cleanedUrl = url
                    .replace("https://", "")
                    .replace("http://", "")
                    .replace("www.", "");

            Component text = createUrlComponent(url, cleanedUrl);

            message = message.replaceText(builder -> builder
                    .match(url)
                    .replacement(text));
        }

        return FormatResult.immutable(message, true);
    }

    private Component createUrlComponent(String original, String cleaned) {
        Component hoverText = Component.text("Click to visit URL", NamedTextColor.GRAY);
        Component text = Component
                .text(cleaned, TextColor.color(80, 180, 255))
                .decorate(TextDecoration.UNDERLINED)
                .hoverEvent(HoverEvent.showText(hoverText))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, original));
        return text;
    }

}
