# Sending e-mail with Microsoft Graph API

# Introduction
Microsoft is disabling SMTP Basic authentication as of 31-12-2022, see [Deprecation of Basic authentication in Exchange Online](https://learn.microsoft.com/en-us/exchange/clients-and-mobile-in-exchange-online/deprecation-of-basic-authentication-exchange-online).
That's where this project comes in. In this project we replace the default SMTP client with the [Microsoft Graph API - user: sendMail](https://learn.microsoft.com/en-us/graph/api/user-sendmail?view=graph-rest-1.0&tabs=http).

Red Hat Single Sign-On is designed to cover most use-cases without requiring custom code, but we also want it to be customizable.
To achieve this Red Hat Single Sign-On has a number of Service Provider Interfaces (SPI) for which you can implement your own providers.
See [Service Provider Interfaces (SPI)](https://access.redhat.com/documentation/en-us/red_hat_single_sign-on/7.3/html-single/server_developer_guide/index#providers) for more info regarding how to implement your own SPI.

# Install and Configure Red Hat Single Sign-On
If you want to test this jar, you can install your own copy of the Red Hat SSO on your local machine.

## Install On Windows 10
Install RHSSO locally by processing the following steps:
- Download [Red Hat Single Sign-On v7.3.0](https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=core.service.rhsso&version=7.3)
- Unpack `rh-sso-7.3.0.GA.zip` to your preferred installation folder
- Add jBoss user (optional)
    - Run `C:\<rhsso_install_map>\bin\add-user.bat`
        - Choose `a` Management User (mgmt-users.properties)
        - Username `myadmin`
        - Password `Jan2010!`
        - What groups... leave empty and choose `yes` to add user `myadmin` for realm `ManagementRealm`
        - User used for AS process... choose `no`
- Run `C:\<rhsso_install_map>\bin\standalone.bat`
- Run the URL http://127.0.0.1:8080/auth in your browser
    - Add a new username `admin`
    - Add a new password `Jan2010!` and confirm the new password
- You will be redirected to the login page

The URL for login to the RHSSO Admin Console is `http://127.0.0.1:8080/auth/admin`.
The URL for EAP Console is `http://127.0.0.1:9990/console`.

> JDK 11.0.10 works fine. The experience with 11.0.15 is that org.xnio.channels.Channels returns NoClassDefFound for EAP 7.
> See https://access.redhat.com/solutions/6953431

## Update standalone.bat
To use JDK 11.0.10 for Red Hat SSO you have to download [JDK 11.0.10](https://www.oracle.com/nl/java/technologies/javase/jdk11-archive-downloads.html) and update `standalone.bat`. Set the environment variable `JAVA_HOME` to JDK install map.

```shell
@echo off
rem -------------------------------------------------------------------------
rem JBoss Bootstrap Script for Windows
rem -------------------------------------------------------------------------

rem Set JAVA_HOME to JDK 11
set JAVA_HOME=C:/Program Files/Java/jdk-11.0.10
```

# Building JAR file with Maven
Here is how to build and deploy this project to Red Hat SSO.

```shell script
mvn clean package -DskipTests
cp ./target/rh-sso-msgraph-sendmail.jar C:/<rhsso_install_map>/standalone/deployments
```

# Microsoft Graph API
Settings for the ` MsGraph API`  are set in the Maven `settings.xml` in your local Maven configuration.
These are for local development only and prevents secrets in your sourcecontrol repository.

```xml
<settings>
  <profile>
    <id>msgraph</id>
    <properties>
      <msgraph.token-url>https://login.microsoftonline.com/[tenant-id]/oauth2/v2.0/token</msgraph.token-url>
      <msgraph.client.id>[client-id]</msgraph.client.id>
      <msgraph.client.secret>[client-secret]</msgraph.client.secret>
    </properties>
  </profile>

  </profiles>
  <activeProfiles>
    <activeProfile>msgraph</activeProfile>
  </activeProfiles>
</settings>
```
Now you can refer to these properties above in your ` *.properties`  files like this:

```
# Refer to maven property which are derived from local settings.xml
azure.oidc.auth.url=${msgraph.token-url}
azure.oidc.auth.client.id=${msgraph.client.id}
azure.oidc.auth.client.secret=${msgraph.client.secret}
```

After cmd `mvn clean package -DskipTests` you can check your ` *.properties` files in the `target/classes` map
to see if these properties have been replaced in good order.

# Links
- [Microsoft - Deprecation of Basic authentication in Exchange Online](https://learn.microsoft.com/en-us/exchange/clients-and-mobile-in-exchange-online/deprecation-of-basic-authentication-exchange-online)
- [Microsoft - Graph API - user: sendMail](https://learn.microsoft.com/en-us/graph/api/user-sendmail?view=graph-rest-1.0&tabs=http)
- [OWASP - Injection Prevention Cheat Sheet in Java](https://cheatsheetseries.owasp.org/cheatsheets/Injection_Prevention_in_Java_Cheat_Sheet.html)
- [OWASP - Java HTML Sanitizer](https://github.com/owasp/java-html-sanitizer)
- [Keycloak - Documentation Archive - 4.8.3](https://www.keycloak.org/archive/documentation-4.8.html)
- [Keycloak - Java Docs Distribution - 4.8.3.Final](https://www.keycloak.org/docs-api/4.8/javadocs/index.html)
- [Keycloak - Github Sourcecode](https://github.com/keycloak/keycloak)
- [Keycloak - Extension Playground](https://github.com/thomasdarimont/keycloak-extension-playground)

---
Copyright &copy; 2022-2023 - [Edwin's Technical Blog](https://www.edekler.nl/) - All rights reserved.