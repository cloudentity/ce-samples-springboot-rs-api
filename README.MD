## Overview

This repo holds reference applications for spring boot based API applications using Spring Security to protect API endpoints. API endpoint access is enforced as per the Bearer Token Usage specification for the The [OAuth 2.0 Authorization Framework as defined in RFC6750](./https://datatracker.ietf.org/doc/html/rfc6750). Any party in
possession of a bearer token (a "bearer") can use it to get access to the associated resources.

## Reference projects

* [springboot-rs-single-auth-server](./springboot-rs-single-auth-server)

This project showcases a spring boot application configuration that validates the Bearer token against a single OAuth server. In this case Cloudentity will be 
the authorization server that issues the OAuth access token
and associated scopes within the Bearer. Any API access
will be protected by verifying that Cloudentity is the
actual issuer of the token with Spring Security configurations.

* [springboot-rs-multi-auth-server](./springboot-rs-multi-auth-server)

This project showcases a spring boot application configuration that validates the Bearer token against multiple OAuth authorization servers. This model is particulary useful when there is multiple trust domains within a single platform with multiple authorization server provider. In this application, we will use the built in multitenancy at authorization server level of Cloudentity to showcase usage of OAuth access token
and associated scopes within the Bearer by any of the Cloudentity authorization server. Any API access
will be protected by verifying that Cloudentity is the
actual issuer of the token with Spring Security configurations. Authorization server multitenancy is a powerful feature within Cloudentity for application integrations


