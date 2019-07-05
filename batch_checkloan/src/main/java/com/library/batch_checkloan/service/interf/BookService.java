package com.library.batch_checkloan.service.interf;

import com.library.batch_checkloan.model.Book;

public interface BookService {

    /**
     * On récupère un livre à partir de son ID.
     * @param book_id L'ID du livre.
     * @return le livre recherché sauf erreur.
     */
    Book getBook(int book_id);
}
