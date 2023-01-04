package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is derived from Microsoft Graph API v1.0 reference:
 * <a href="https://learn.microsoft.com/en-us/graph/api/resources/itembody?view=graph-rest-1.0">itemBody resource type</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemBody {

    @JsonProperty("content")
    public String content;

    @JsonProperty("contentType")
    public BodyType contentType;

    public ItemBody() {
    }

    @Override
    public String toString() {
        return "ItemBody{" +
                "content='" + content + '\'' +
                ", contentType=" + contentType +
                '}';
    }
}
