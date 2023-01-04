package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is derived from Microsoft Graph API v1.0 reference:
 * <a href="https://learn.microsoft.com/en-us/graph/api/resources/followupflag?view=graph-rest-1.0">followupFlag resource type</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowupFlag {

    @JsonProperty("completedDateTime")
    public DateTimeTimeZone completedDateTime;

    @JsonProperty("dueDateTime")
    public DateTimeTimeZone dueDateTime;

    @JsonProperty("flagStatus")
    public FollowupFlagStatus flagStatus;

    @JsonProperty("startDateTime")
    public DateTimeTimeZone startDateTime;

    public FollowupFlag() {
    }

    @Override
    public String toString() {
        return "FollowupFlag{" +
                "completedDateTime=" + completedDateTime +
                ", dueDateTime=" + dueDateTime +
                ", flagStatus=" + flagStatus +
                ", startDateTime=" + startDateTime +
                '}';
    }
}
