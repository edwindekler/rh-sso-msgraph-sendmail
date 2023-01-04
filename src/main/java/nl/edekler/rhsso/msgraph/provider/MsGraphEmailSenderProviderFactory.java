package nl.edekler.rhsso.msgraph.provider;

import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.email.EmailSenderProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * Microsoft Graph email sender provider.<br/>
 * <p>
 *     Overrides the <code>default</code> emailSender provider. If you use another name, some default email functions
 *     aren't working because it uses the default provider instead!
 * </p>
 *
 * @author <a href="mailto:edwin.de.kler@gmail.com">Edwin de Kler</a>
 */
public class MsGraphEmailSenderProviderFactory implements EmailSenderProviderFactory {

    private static final Logger LOG = Logger.getLogger(MsGraphEmailSenderProviderFactory.class);

    @Override
    public EmailSenderProvider create(KeycloakSession session) {
        return new MsGraphEmailSenderProvider(session);
    }

    @Override
    public void init(Config.Scope config) {
        LOG.infof("init()");
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        LOG.infof("postInit()");
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return "default";
    }

}
