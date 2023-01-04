package nl.edekler.rhsso.msgraph.utils;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Property resource handler, for reading properties from file in default resource location.<br/>
 *
 * @author <a href="mailto:edwin.de.kler@gmail.com">Edwin de Kler</a>
 * @since 1.0.0
 */
public class PropertyResourceHandler {

    private static final Logger LOG = Logger.getLogger(PropertyResourceHandler.class);

    /**
     * Get properties from file from default resource location.<br/>
     *
     * @param filename
     * @return
     */
    public static final Properties getPropertiesFromResource(final String filename) {
        Properties properties = new Properties();

        InputStream is = null;
        try {
            is = PropertyResourceHandler.class.getClassLoader().getResourceAsStream(filename);
            if (is == null) {
                throw new IOException(String.format("File %s not found", filename));
            }
            properties.load(is);
        } catch (IOException ioe) {
            LOG.error(ioe);
        } finally {
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException ioe) {
                LOG.error(ioe);
            }
        }
        return properties;
    }

    /**
     * Check for properties that should exist and not empty values.<br/>
     *
     * @param properties
     * @param propertyNames
     * @throws IllegalArgumentException
     */
    public static final void checkExistAndNotEmpty(final Properties properties, final String... propertyNames) throws IllegalArgumentException {
        for (String property : propertyNames) {
            if (StringUtils.isEmpty(properties.getProperty(property))) {
                throw new IllegalArgumentException(String.format("Property '%s' is missing or has empty value", property));
            }
        }
    }
}
