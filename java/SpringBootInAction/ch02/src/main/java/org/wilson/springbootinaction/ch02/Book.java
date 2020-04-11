package org.wilson.springbootinaction.ch02;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wilson on 2020/4/8.
 */
@Entity
public class Book {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    private String reader;
    @Getter
    @Setter
    private String isbn;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String author;
    @Getter
    @Setter
    private String description;
}
