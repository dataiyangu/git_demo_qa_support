package com.dao;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.model.BookVO;


@Repository
public class BookDaoImpl implements IBookDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdateBook(BookVO book) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
    }

    @Override
    public List<BookVO> getBooks() {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select * from book").addEntity(BookVO.class);
        List<BookVO> list = query.list();

        //List<BookVO> list = session.createQuery(" from BookVO ").list();

        return list;
    }

    @Override
    public BookVO getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        BookVO book = new BookVO();

//      SQLQuery query = session.createSQLQuery("select * from book where id = ?").addEntity(BookVO.class);
//      query.setInteger(0, id);
//      List<BookVO> result = query.list();
//      if(result != null && result.size() > 0) {
//          book = result.get(0);
//      }

        book = (BookVO) session.get(BookVO.class, id);

        return book;
    }

    @Override
    public void deleteBookById(int id) {
        Session session = sessionFactory.getCurrentSession();

//      SQLQuery query = session.createSQLQuery(" delete from book where id = ? ").addEntity(BookVO.class);
//      query.setInteger(0, id);
//      query.executeUpdate();

        BookVO book = (BookVO) session.get(BookVO.class, id);
        session.delete(book);
    }
}