package com.rabin.departmentservice.config;

import com.rabin.departmentservice.client.EmployeeClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
class WebClientConfigTest {

    @Mock
    private LoadBalancedExchangeFilterFunction mockFilterFunction;

    @InjectMocks
    private WebClientConfig webClientConfigUnderTest;

    @Test
    void testEmployeeWebClient() {
        // Setup
        // Run the test
        final WebClient result = webClientConfigUnderTest.employeeWebClient();

        // Verify the results
    }

    @Test
    void testEmployeeClient() {
        // Setup
        // Run the test
        final EmployeeClient result = webClientConfigUnderTest.employeeClient();

        // Verify the results
    }
}
