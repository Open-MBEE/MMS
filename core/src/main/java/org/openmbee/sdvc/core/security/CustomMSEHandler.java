package org.openmbee.sdvc.core.security;

import org.aopalliance.intercept.MethodInvocation;
import org.openmbee.sdvc.core.services.PermissionService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMSEHandler extends DefaultMethodSecurityExpressionHandler {

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    private ObjectFactory<PermissionService> permissionService;

    public CustomMSEHandler(ObjectFactory<PermissionService> permissionService) {
        super();
        this.permissionService = permissionService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
        Authentication authentication, MethodInvocation invocation) {
        CustomMSERoot root =
            new CustomMSERoot(authentication, permissionService.getObject());
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}