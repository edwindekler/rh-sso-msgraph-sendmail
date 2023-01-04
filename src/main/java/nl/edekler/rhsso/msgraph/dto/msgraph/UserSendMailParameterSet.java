package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSendMailParameterSet {

    @JsonProperty("message")
    public Message message;

    @JsonProperty("saveToSentItems")
    public Boolean saveToSentItems;

    public UserSendMailParameterSet() {
    }

    protected UserSendMailParameterSet(final UserSendMailParameterSet.UserSendMailParameterSetBuilder builder) {
        this.message = builder.message;
        this.saveToSentItems = builder.saveToSentItems;
    }

    public static UserSendMailParameterSet.UserSendMailParameterSetBuilder newBuilder() {
        return new UserSendMailParameterSet.UserSendMailParameterSetBuilder();
    }

    @Override
    public String toString() {
        return "UserSendMailParameterSet{" +
                "message=" + message +
                ", saveToSentItems=" + saveToSentItems +
                '}';
    }

    public static final class UserSendMailParameterSetBuilder {
        protected Message message;
        protected Boolean saveToSentItems;

        public UserSendMailParameterSet.UserSendMailParameterSetBuilder withMessage(final Message val) {
            this.message = val;
            return this;
        }

        public UserSendMailParameterSet.UserSendMailParameterSetBuilder withSaveToSentItems(final Boolean val) {
            this.saveToSentItems = val;
            return this;
        }

        protected UserSendMailParameterSetBuilder() {
        }

        public UserSendMailParameterSet build() {
            return new UserSendMailParameterSet(this);
        }
    }
}
