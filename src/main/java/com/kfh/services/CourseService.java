package com.kfh.services;

import com.kfh.dtos.CourseDto;
import com.kfh.mappers.CourseMapper;
import com.kfh.repos.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    public Flux<CourseDto> getAllCoursesReactive() {

        return Flux.fromIterable(courseRepository.findAll())
                .map(courseMapper::toDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
