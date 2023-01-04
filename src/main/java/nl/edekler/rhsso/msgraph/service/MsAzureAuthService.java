package nl.edekler.rhsso.msgraph.service;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import nl.edekler.rhsso.msgraph.utils.PropertyResourceHandler;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Service for Microsoft Azure OAuth2/OIDC authentication.<br/>
 *
 * @author <a href="mailto:edwin.de.kler@gmail.com">Edwin de Kler</a>
 * @since 1.0.0
 */
public final class MsAzureAuthService {
    private static MsAzureAuthService INSTANCE;
    private final ConfidentialClientApplication cca;
    private final Set<String> scopes;

    /**
     *
     * @throws MalformedURLException
     */
    private MsAzureAuthService() throws MalformedURLException {

        final Properties appProps = PropertyResourceHandler.getPropertiesFromResource("application.properties");

        final String propScopes = "azure.oidc.auth.scopes";
        final String propClientId = "azure.oidc.auth.client.id";
        final String propClientSecret = "azure.oidc.auth.client.secret";
        final String propUrl = "azure.oidc.auth.url";

        PropertyResourceHandler.checkExistAndNotEmpty(appProps,
                propScopes,
                propClientId,
                propClientSecret,
                propUrl
        );

        scopes = new HashSet<>();
        scopes.add(appProps.getProperty(propScopes));

        this.cca = ConfidentialClientApplication
                .builder(
                        appProps.getProperty(propClientId),
                        ClientCredentialFactory.createFromSecret(
                                appProps.getProperty(propClientSecret)))
                .authority(appProps.getProperty(propUrl))
                .build();
    }

    /**
     * Get instance of class.<br/>
     *
     * @return
     * @throws MalformedURLException
     */
    public synchronized static MsAzureAuthService getInstance() throws MalformedURLException {
        if (INSTANCE == null) {
            INSTANCE = new MsAzureAuthService();
        }
        return INSTANCE;
    }

    /**
     * Authenticate with OIDC to Azure.<br/>
     *
     * @return
     */
    public synchronized IAuthenticationResult authenticate() {
        return cca.acquireToken(
                ClientCredentialParameters
                        .builder(scopes)
                        .build())
                .join();
    }

}
