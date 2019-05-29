package com.library.client.services.impl;

import com.library.client.model.Author;
import com.library.client.services.AbstractService;
import com.library.client.services.interf.AuthorService;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class AuthorServiceImpl extends AbstractService implements AuthorService {

    @Override
    public List<Author> getAuthors() {
        Author[] authors = restTemplate.getForObject(properties.getServiceURL() + "Authors", Author[].class);
        return authors == null ? null : Arrays.asList(authors);
    }

    @Override
    public Author getAuthor(int author_id) {
        return restTemplate.getForObject(properties.getServiceURL() + "Authors/" + author_id, Author.class);
    }
}
