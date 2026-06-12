package com.vm.guesthouse.security.auditor;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vm.guesthouse.entity.base.CustomUserDetails;

public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
    public Optional<Long> getCurrentAuditor() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated())
            return Optional.empty();

        CustomUserDetails user =
                (CustomUserDetails) auth.getPrincipal();

        return Optional.of(user.getUserId());
    }
}
