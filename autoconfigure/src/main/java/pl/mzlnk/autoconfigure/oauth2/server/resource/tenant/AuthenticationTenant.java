package pl.mzlnk.autoconfigure.oauth2.server.resource.tenant;

import pl.mzlnk.autoconfigure.oauth2.server.resource.properties.AuthenticationTenantDetails;
import pl.mzlnk.autoconfigure.oauth2.server.resource.tenant.matcher.AuthenticationTenantMatcher;
import pl.mzlnk.autoconfigure.oauth2.server.resource.properties.TokenType;
import pl.mzlnk.autoconfigure.oauth2.server.resource.tenant.matcher.AuthenticationTenantMatcherFactory;

import java.util.Collections;
import java.util.List;

public abstract class AuthenticationTenant {

    private final String providerId;
    private final TokenType tokenType;
    private final List<AuthenticationTenantMatcher> matchers;

    public AuthenticationTenant(String providerId, TokenType tokenType, List<AuthenticationTenantMatcher> matchers) {
        this.providerId = providerId;
        this.tokenType = tokenType;
        this.matchers = matchers;
    }

    public String getProviderId() {
        return providerId;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public List<AuthenticationTenantMatcher> getMatchers() {
        return Collections.unmodifiableList(matchers);
    }

    public void addMatcher(AuthenticationTenantMatcher matcher) {
        this.matchers.add(matcher);
    }

    public void addMatchers(List<AuthenticationTenantMatcher> matchers) {
        this.matchers.addAll(matchers);
    }

    public boolean isRelatedToOpaqueToken() {
        return this.tokenType == TokenType.OPAQUE;
    }

    public boolean isRelatedToJwtToken() {
        return this.tokenType == TokenType.JWT;
    }

    public interface Factory {

        TokenType getType();
        AuthenticationTenant fromDetails(AuthenticationTenantDetails tenant, AuthenticationTenantMatcherFactory matcherFactory);

    }

}