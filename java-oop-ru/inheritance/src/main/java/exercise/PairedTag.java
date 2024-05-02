package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    String value;
    List<Tag> childs;

    public PairedTag(String name, Map<String, String> attributes, String value, List<Tag> childs) {
        super(name, attributes);
        this.value = value;
        this.childs = childs;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<").append(name);
        for (var attribute : attributes.entrySet()) {
            builder.append(" ").append(attribute.getKey()).append("=\"")
                    .append(attribute.getValue()).append("\"");
        }
        builder.append(">").append(value);

        childs.forEach(child -> builder.append(child.toString()));

        builder.append("</").append(name).append(">");

        return builder.toString();
    }
}
// END
