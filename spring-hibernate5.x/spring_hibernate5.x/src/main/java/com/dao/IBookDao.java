package com.dao;
import java.util.List;

import com.model.BookVO;


public interface IBookDao {
    public void saveOrUpdateBook(BookVO book);

    public List<BookVO> getBooks();

    public BookVO getBookById(int id);

    public void deleteBookById(int id);
}