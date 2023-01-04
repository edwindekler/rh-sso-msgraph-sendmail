package nl.edekler.rhsso.msgraph.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.edekler.rhsso.msgraph.dto.msgraph.UserSendMailParameterSet;
import nl.edekler.rhsso.msgraph.utils.HttpRequestInfo;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Http request handling for Microsoft Graph API.<br/>
 *
 * @author <a href="mailto:edwin.de.kler@gmail.com">Edwin de Kler</a>
 * @since 1.0.0
 */
public class MsGraphApiService {

    private static final Logger LOG = Logger.getLogger(MsGraphApiService.class);
    private static final String serviceRoot = "https://graph.microsoft.com/v1.0";

    private final MsAzureAuthService msAzureAuthService;

    /**
     * Default constructor.<br/>
     *
     * @param msAzureAuthService Microsoft Azure authentication service
     */
    public MsGraphApiService(final MsAzureAuthService msAzureAuthService) {
        this.msAzureAuthService = msAzureAuthService;
    }

    /**
     * Send mail.<br/>
     *
     * @param userEmail User account email address
     * @param body Request body (json)
     * @return
     * @throws Exception When someting went wrong during sending email
     */
    public HttpRequestInfo sendMail(final String userEmail, final UserSendMailParameterSet body) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpRequestInfo httpRequestInfo = new HttpRequestInfo();

        try {

            HttpPost request = new HttpPost(String.format(this.serviceRoot + "/users/%s/sendMail", userEmail));
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", String.format("Bearer %s", this.msAzureAuthService.authenticate().accessToken()));

            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(body), ContentType.create(ContentType.APPLICATION_JSON.getMimeType(), "UTF-8"));
            request.setEntity(entity);

            httpRequestInfo.setRequestLine(request.getRequestLine());
            httpRequestInfo.setRequestHeaders(flattenHeaders(request.getAllHeaders()));

            long startTime = System.currentTimeMillis();
            httpclient.execute(request, response -> {
                httpRequestInfo.setDuration(System.currentTimeMillis() - startTime);
                httpRequestInfo.setStatusLine(response.getStatusLine());
                httpRequestInfo.setResponseHeaders(flattenHeaders(response.getAllHeaders()));
                httpRequestInfo.setResponseEntity(response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : "empty");
                return null;
            });

        } finally {
            LOG.infof("%s", httpRequestInfo);
            try {
                httpclient.close();
            } catch (IOException e) {
                //NOOP
            }
        }
        return httpRequestInfo;
    }

    /**
     * Flatten headers to list of name:value
     *
     * @param headers
     * @return
     */
    private final List<String> flattenHeaders(Header[] headers) {
        return Arrays.stream(headers)
                .map(header -> String.format("%s:%s", header.getName(), header.getValue()))
                .collect(Collectors.toList());
    }
}
