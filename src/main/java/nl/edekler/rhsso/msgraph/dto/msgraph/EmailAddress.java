package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is derived from Microsoft Graph API v1.0 reference:
 * <a href="https://learn.microsoft.com/en-us/graph/api/resources/emailaddress?view=graph-rest-1.0">emailAddress resource type</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailAddress {

    @JsonProperty("address")
    public String address;

    @JsonProperty("name")
    public String name;

    public EmailAddress() {
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
