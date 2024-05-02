package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<").append(name);
        for (var attribute : attributes.entrySet()) {
            builder.append(" ").append(attribute.getKey()).append("=\"")
                    .append(attribute.getValue()).append("\"");
        }
        builder.append(">");

        return builder.toString();
    }
}
// END
