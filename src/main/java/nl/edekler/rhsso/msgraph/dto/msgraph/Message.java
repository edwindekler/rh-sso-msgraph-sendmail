package nl.edekler.rhsso.msgraph.dto.msgraph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * This class is derived from Microsoft Graph API v1.0 reference:
 * <a href="https://learn.microsoft.com/en-us/graph/api/resources/message?view=graph-rest-1.0">message resource type</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    @JsonProperty("bccRecipients")
    public List<Recipient> bccRecipients;

    @JsonProperty("body")
    public ItemBody body;

    @JsonProperty("bodyPreview")
    public String bodyPreview;

    @JsonProperty("ccRecipients")
    public List<Recipient> ccRecipients;

    @JsonProperty("conversationId")
    public String conversationId;

    @JsonProperty("conversationIndex")
    public byte[] conversationIndex;

    @JsonProperty("flag")
    public FollowupFlag flag;

    @JsonProperty("from")
    public Recipient from;

    @JsonProperty("hasAttachments")
    public Boolean hasAttachments;

    @JsonProperty("importance")
    public Importance importance;

    @JsonProperty("inferenceClassification")
    public InferenceClassificationType inferenceClassification;

    @JsonProperty("internetMessageHeaders")
    public List<InternetMessageHeader> internetMessageHeaders;

    @JsonProperty("internetMessageId")
    public String internetMessageId;

    @JsonProperty("isDeliveryReceiptRequested")
    public Boolean isDeliveryReceiptRequested;

    @JsonProperty("isDraft")
    public Boolean isDraft;

    @JsonProperty("isRead")
    public Boolean isRead;

    @JsonProperty("isReadReceiptRequested")
    public Boolean isReadReceiptRequested;

    @JsonProperty("parentFolderId")
    public String parentFolderId;

    @JsonProperty("receivedDateTime")
    public OffsetDateTime receivedDateTime;

    @JsonProperty("replyTo")
    public List<Recipient> replyTo;

    @JsonProperty("sender")
    public Recipient sender;

    @JsonProperty("sentDateTime")
    public OffsetDateTime sentDateTime;

    @JsonProperty("subject")
    public String subject;

    @JsonProperty("toRecipients")
    public List<Recipient> toRecipients;

    @JsonProperty("uniqueBody")
    public ItemBody uniqueBody;

    @JsonProperty("webLink")
    public String webLink;

//    @Nullable
//    @JsonProperty("attachments")
//    public AttachmentCollectionPage attachments;
//
//    @Nullable
//    @JsonProperty("extensions")
//    public ExtensionCollectionPage extensions;
//
//    @Nullable
//    @JsonProperty("multiValueExtendedProperties")
//    public MultiValueLegacyExtendedPropertyCollectionPage multiValueExtendedProperties;
//
//    @Nullable
//    @JsonProperty("singleValueExtendedProperties")
//    public SingleValueLegacyExtendedPropertyCollectionPage singleValueExtendedProperties;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "bccRecipients=" + bccRecipients +
                ", body=" + body +
                ", bodyPreview='" + bodyPreview + '\'' +
                ", ccRecipients=" + ccRecipients +
                ", conversationId='" + conversationId + '\'' +
                ", conversationIndex=" + Arrays.toString(conversationIndex) +
                ", flag=" + flag +
                ", from=" + from +
                ", hasAttachments=" + hasAttachments +
                ", importance=" + importance +
                ", inferenceClassification=" + inferenceClassification +
                ", internetMessageHeaders=" + internetMessageHeaders +
                ", internetMessageId='" + internetMessageId + '\'' +
                ", isDeliveryReceiptRequested=" + isDeliveryReceiptRequested +
                ", isDraft=" + isDraft +
                ", isRead=" + isRead +
                ", isReadReceiptRequested=" + isReadReceiptRequested +
                ", parentFolderId='" + parentFolderId + '\'' +
                ", receivedDateTime=" + receivedDateTime +
                ", replyTo=" + replyTo +
                ", sender=" + sender +
                ", sentDateTime=" + sentDateTime +
                ", subject='" + subject + '\'' +
                ", toRecipients=" + toRecipients +
                ", uniqueBody=" + uniqueBody +
                ", webLink='" + webLink + '\'' +
                '}';
    }
}
