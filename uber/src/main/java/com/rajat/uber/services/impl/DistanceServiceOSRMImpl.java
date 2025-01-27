package com.rajat.uber.services.impl;

import org.springframework.stereotype.Service;
import com.rajat.uber.services.DistanceService;
import org.locationtech.jts.geom.Point;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        return 0;
    }
}
