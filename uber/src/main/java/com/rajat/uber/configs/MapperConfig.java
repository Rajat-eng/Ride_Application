package com.rajat.uber.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rajat.uber.dto.PointDto;
import com.rajat.uber.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // dto to entity
        mapper.typeMap(PointDto.class, Point.class).setConverter(context -> {
            PointDto pointDto = context.getSource();
            return GeometryUtil.createPoint(pointDto);
        });

        mapper.typeMap(Point.class, PointDto.class).setConverter(context -> {
            Point point = context.getSource();
            double coordinates[] = {point.getX(), point.getY()};
            return new PointDto(coordinates);
        });


        return mapper;
    }
}
