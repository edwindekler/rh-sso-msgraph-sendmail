package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is derived from Microsoft Graph API v1.0 reference:
 * <a href="https://learn.microsoft.com/en-us/graph/api/resources/recipient?view=graph-rest-1.0">recipient resource type</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipient {

    @JsonProperty("emailAddress")
    public EmailAddress emailAddress;

    public Recipient() {
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "emailAddress=" + emailAddress +
                '}';
    }
}
