package org.wilson.springsecurity.base_on_url_dy.config;

import org.wilson.springsecurity.base_on_url_dy.model.Menu;
import org.wilson.springsecurity.base_on_url_dy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private MenuService menuService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestURI = ((FilterInvocation) object) .getRequest().getRequestURI();
        List<Menu> allMenu = menuService.getAllMenu();
        Optional<Menu> menu = allMenu.stream().filter(m -> antPathMatcher.match(m.getPattern(), requestURI)).findAny();
        if (menu.isPresent()) {
            return SecurityConfig.createList(menu.get().getRoles().stream().map(r -> r.getName()).toArray(String[]::new));
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
