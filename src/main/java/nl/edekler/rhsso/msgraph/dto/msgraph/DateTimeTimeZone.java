package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is derived from Microsoft Graph API v1.0 reference:
 * <a href="https://learn.microsoft.com/en-us/graph/api/resources/datetimetimezone?view=graph-rest-1.0">dateTimeTimeZone resource type</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DateTimeTimeZone {

    @JsonProperty("dateTime")
    public String dateTime;

    @JsonProperty("timeZone")
    public String timeZone;

    public DateTimeTimeZone() {
    }

    @Override
    public String toString() {
        return "DateTimeTimeZone{" +
                "dateTime='" + dateTime + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
