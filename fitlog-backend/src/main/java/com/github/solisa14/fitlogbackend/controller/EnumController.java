package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.EnumDto;
import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enums")
public class EnumController {

    @GetMapping("/muscle-groups")
    public ResponseEntity<List<EnumDto>> getAllMuscleGroups() {
        List<EnumDto> muscleGroups = Arrays.stream(MuscleGroup.values())
                .map(mg -> new EnumDto(mg.name(), mg.toString()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(muscleGroups);
    }

    @GetMapping("/tracking-types")
    public ResponseEntity<List<EnumDto>> getAllTrackingTypes() {
        List<EnumDto> trackingTypes = Arrays.stream(TrackingType.values())
                .map(tt -> new EnumDto(tt.name(), tt.toString()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(trackingTypes);
    }
}
