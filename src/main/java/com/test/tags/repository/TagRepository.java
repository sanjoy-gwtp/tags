package com.test.tags.repository;

import com.test.tags.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sanjoy
 * on 5/27/21
 */
@Repository
public interface TagRepository extends JpaRepository<Tag,String> {

    Tag findByKey(String key);
}
