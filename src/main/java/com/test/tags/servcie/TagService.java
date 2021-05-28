package com.test.tags.servcie;



import com.test.tags.entities.Tag;

import java.util.List;

/**
 * @author sanjoy
 * on 5/27/21
 */

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTagByKey(String key);

    void deleteTag(String key);

    List<Tag> getAll();

    boolean exist(String key);
}
