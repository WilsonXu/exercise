package org.wilson.springbootinaction.ch02;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wilson on 2020/4/8.
 */
public interface ReadingListRepository extends JpaRepository<Book, Integer> {
    List<Book> findByReader(String reader);
}
