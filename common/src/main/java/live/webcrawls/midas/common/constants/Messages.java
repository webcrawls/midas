package live.webcrawls.midas.common.constants;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.List;

public interface Messages {


    MiniMessage FORMATTER = MiniMessage.miniMessage();
    Component NAME = midasStyle("Midas");
    Component PREFIX = Component.text()
            .append(NAME)
            .append(Component.text(" | ", TextColor.color(168, 216, 255), TextDecoration.BOLD))
            .build();

    static Component midasStyle(String input) {
        return FORMATTER.deserialize("<gradient:#f59342:#f5c242><bold>" + input + "</bold></gradient>");
    }

    static Component defaultStyle(String input) {
        return Component.text(input, TextColor.color(184, 202, 217));
    }

    static Component pluginText(Component text) {
        return Component.text()
                .append(PREFIX)
                .append(text)
                .build();
    }

    static Component pluginText(List<Component> text) {
        var builder = Component.text()
                .append(PREFIX);

        text.forEach(t -> builder.append(pluginText(t).append(Component.newline())));

        return builder.build();
    }

    static Component pluginText(Component... text) {
        return pluginText(List.of(text));
    }


}
