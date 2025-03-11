package com.rajat.uber.repositories;

import com.rajat.uber.TestContainerConfiguration;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.enums.Role;
import org.junit.jupiter.api.*;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import org.locationtech.jts.geom.Coordinate;

@DataJpaTest
@Testcontainers
@Import(TestContainerConfiguration.class)  // 🏗️ Use the test database
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;

    private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @BeforeEach
    void setUp() {
        driverRepository.deleteAll(); // Clean DB before each test
        
        User user1 = User.builder().id(1L).name("Rajat").email("test1@example.com").password("password").roles(Set.of(Role.RIDER)).build();
        User user2 = User.builder().id(1L).name("Dikshant").email("test2@example.com").password("password").roles(Set.of(Role.RIDER)).build();
        
        // Mock drivers
        driverRepository.save(
            Driver.builder().user(user1).vehicleId("UP25CA9696").available(true).currentLocation(geometryFactory.createPoint(new Coordinate(12.9716, 77.5946))).build()
        );

        driverRepository.save(
            Driver.builder().user(user2).vehicleId("UP25CA9697").available(true).currentLocation(geometryFactory.createPoint(new Coordinate(15.9716, 81.5946))).build()
        );
        
    }

    @Test
    void testFindTenNearestDrivers() {
        Point pickupLocation = createPoint(12.9716, 77.5946);
        List<Driver> drivers = driverRepository.findTenNearestDrivers(pickupLocation);

        assertEquals(2, drivers.size());
        assertEquals("rajat", drivers.get(0).getUser().getName());

        System.out.println("✅ testFindTenNearestDrivers Passed");
    }

    private static Point createPoint(double lat, double lon) {
        return geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(lon, lat));
    }
}

