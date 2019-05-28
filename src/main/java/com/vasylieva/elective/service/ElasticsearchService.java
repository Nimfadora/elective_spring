package com.vasylieva.elective.service;

import com.vasylieva.elective.model.CourseDocument;
import org.apache.commons.lang3.tuple.Pair;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticsearchService {

    private static final String[] FULL_TEXT_SEARCHFIELDS = new String[]{
            "author", "category", "title", "description", "level", "skills"
    };

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void indexCourse(String indexName, CourseDocument course) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(indexName)
                .withId(course.getId().toString())
                .withObject(course)
                .build();
        elasticsearchTemplate.index(indexQuery);
    }

    public List<CourseDocument> findCourses(String indexName, String searchString, Pageable pageable) {


        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(searchString, FULL_TEXT_SEARCHFIELDS))
                .withIndices(indexName)
                .withPageable(pageable)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, CourseDocument.class);
    }

    public List<CourseDocument> findCourses(String indexName, String searchString, String skill,
                                            String level, String language, Pair<String, String> duration,
                                            SortBuilder sortBuilder, Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(searchString, FULL_TEXT_SEARCHFIELDS))
                .withIndices(indexName)
                .withSort(sortBuilder)
                .withPageable(pageable)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, CourseDocument.class);
    }
}
