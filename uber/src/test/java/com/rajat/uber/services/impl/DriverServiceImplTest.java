package com.rajat.uber.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.enums.Role;
import com.rajat.uber.repositories.DriverRepository;
import com.rajat.uber.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class DriverServiceImplTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DriverServiceImpl driverService;

    private Driver driver;
    private User user;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
        
        user = User.builder().id(1L).email("rv@gmail.com").password("rajat").roles(Set.of(Role.RIDER, Role.DRIVER))
                .build();
       
        driver = Driver.builder().user(user).available(true).vehicleId("UP25CA9696").build();
       
    }

    @Test
    public void getCurrentDriver_whenAuthenticated_Success() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        when(driverRepository.findByUser(user)).thenReturn(Optional.of(driver));
        Driver currentDriver = driverService.getCurrentDriver();
        verify(driverRepository).findByUser(user);
        assertEquals(currentDriver.getUser().getId(), driver.getUser().getId());
    }
}
