package com.uab.gr10.news.repository;

import com.uab.gr10.news.models.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, UUID> {

    // select distinct * where p.name = ?1
    Notice findDistinctByTitle(String name);
}
