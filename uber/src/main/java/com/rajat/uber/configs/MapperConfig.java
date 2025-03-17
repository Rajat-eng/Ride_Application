package com.rajat.uber.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rajat.uber.dto.PointDto;
import com.rajat.uber.utils.GeometryUtil;

import lombok.extern.slf4j.Slf4j;

import org.locationtech.jts.geom.Point;

@Configuration
@Slf4j
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

          // Convert PointDto → Point (Entity)
        mapper.typeMap(PointDto.class, Point.class).setConverter(context -> {
            PointDto pointDto = context.getSource();
            log.info("point DTO to point Entity", pointDto);
            if (pointDto == null || pointDto.getCoordinates() == null || pointDto.getCoordinates().length != 2) {
                return null;  // Handle null cases properly
            }
            return GeometryUtil.createPoint(pointDto);
        });

        // Convert Point (Entity) → PointDto
        mapper.typeMap(Point.class, PointDto.class).setConverter(context -> {
            Point point = context.getSource();
            log.info("point Entity to point DTO",point);
            if (point == null) {
                return null;
            }
            return new PointDto(new double[]{point.getX(), point.getY()});
        });

        return mapper;
    }
}
