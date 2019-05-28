package com.vasylieva.elective.repository;

import com.vasylieva.elective.model.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchCourseRepository extends ElasticsearchRepository<CourseDocument, Long> {

}
