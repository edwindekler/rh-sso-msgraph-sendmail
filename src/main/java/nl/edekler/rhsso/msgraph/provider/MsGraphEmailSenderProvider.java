package nl.edekler.rhsso.msgraph.provider;

import nl.edekler.rhsso.msgraph.service.MsAzureAuthService;
import nl.edekler.rhsso.msgraph.service.MsGraphApiService;
import nl.edekler.rhsso.msgraph.utils.HttpRequestInfo;
import nl.edekler.rhsso.msgraph.utils.PropertyResourceHandler;
import nl.edekler.rhsso.msgraph.dto.msgraph.*;
import org.jboss.logging.Logger;
import org.keycloak.email.EmailException;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.services.ServicesLogger;

import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

/**
 * Email sender provider based on Microsoft Graph API.<br/>
 *
 * @author <a href="mailto:edwin.de.kler@gmail.com">Edwin de Kler</a>
 */
public class MsGraphEmailSenderProvider implements EmailSenderProvider {

    private static final Logger LOG = Logger.getLogger(MsGraphEmailSenderProvider.class);

    private final String graphUserEmail;

    public MsGraphEmailSenderProvider(KeycloakSession session) {
        final Properties appProps = PropertyResourceHandler.getPropertiesFromResource("application.properties");
        final String propGraphUserEmail = "api.graph.user.email";
        PropertyResourceHandler.checkExistAndNotEmpty(appProps, propGraphUserEmail);
        graphUserEmail = appProps.getProperty(propGraphUserEmail);
    }

    @Override
    public void send(Map<String, String> config, UserModel user, String subject, String textBody, String htmlBody) throws EmailException {

        try {
            Message message = new Message();
            message.subject = subject;
            ItemBody body = new ItemBody();
            if (textBody != null) {
                body.contentType = BodyType.TEXT;
                body.content = textBody;
            }
            if (htmlBody != null) {
                body.contentType = BodyType.HTML;
                body.content = htmlBody;
            }
            message.body = body;
            LinkedList<Recipient> toRecipientsList = new LinkedList<>();
            Recipient toRecipients = new Recipient();
            EmailAddress emailAddress = new EmailAddress();
            emailAddress.address = user.getEmail();
            toRecipients.emailAddress = emailAddress;
            toRecipientsList.add(toRecipients);
            message.toRecipients = toRecipientsList;

            MsGraphApiService msGraphApiService = new MsGraphApiService(MsAzureAuthService.getInstance());

            UserSendMailParameterSet userSendMailParameterSet = UserSendMailParameterSet.newBuilder()
                    .withMessage(message)
                    .withSaveToSentItems(false)
                    .build();

            HttpRequestInfo httpRequestInfo = msGraphApiService.sendMail(graphUserEmail, userSendMailParameterSet);
            if (httpRequestInfo.getStatusLine().getStatusCode() == 202) {
                LOG.infof("Sent Email from %s to %s", graphUserEmail, message.toRecipients);
            } else {
                throw new Exception(String.format("Failed to send Email from %s to %s: %s%n%s",
                        graphUserEmail,
                        message.toRecipients,
                        httpRequestInfo.getStatusLine().toString(),
                        httpRequestInfo.getResponseEntity()));
            }

        } catch (Exception e) {
            ServicesLogger.LOGGER.failedToSendEmail(e);
            throw new EmailException(e);
        }
    }

    @Override
    public void close() {

    }
}
