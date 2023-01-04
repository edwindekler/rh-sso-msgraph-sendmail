package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is derived from Microsoft Graph API v1.0 reference:
 * <a href="https://learn.microsoft.com/en-us/graph/api/resources/internetmessageheader?view=graph-rest-1.0">internetMessageHeader resource type</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternetMessageHeader {

    @JsonProperty("name")
    public String name;

    @JsonProperty("value")
    public String value;

    public InternetMessageHeader() {
    }

    @Override
    public String toString() {
        return "InternetMessageHeader{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
