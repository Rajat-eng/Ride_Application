package com.rajat.uber.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import com.rajat.uber.dto.PointDto;

public class GeometryUtil {
    public static Point createPoint(PointDto pointDto) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        if (pointDto == null || pointDto.getCoordinates() == null || pointDto.getCoordinates().length != 2) {
            return null;
        }
        Coordinate coordinate =
                new Coordinate(pointDto.getCoordinates()[0], pointDto.getCoordinates()[1]);
        return geometryFactory.createPoint(coordinate);
    }
}
