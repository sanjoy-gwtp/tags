package com.test.tags.exception;

/**
 * @author sanjoy
 * on 5/28/21
 */

public class TagNotFoundException extends RuntimeException {

    TagNotFoundException(String key) {
        super("Could not find Tag " + key);
    }

}
