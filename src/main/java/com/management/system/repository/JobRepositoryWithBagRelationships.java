package com.management.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.management.system.model.Job;

public interface JobRepositoryWithBagRelationships {
    Optional<Job> fetchBagRelationships(Optional<Job> job);

    List<Job> fetchBagRelationships(List<Job> jobs);

    Page<Job> fetchBagRelationships(Page<Job> jobs);
}
