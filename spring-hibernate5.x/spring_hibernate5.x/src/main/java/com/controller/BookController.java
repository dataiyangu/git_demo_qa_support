package com.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.BookVO;
import com.service.IBookService;


@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @RequestMapping(value = "/newBook", method = RequestMethod.GET)
    public ModelAndView newBook(ModelAndView model) {
    	BookVO book = new BookVO();
//        model.addObject("book", book);
    	book.setBookAuthor("Sergio Leone");
    	book.setBookName("Once Upon a Time in America");
        bookService.saveOrUpdateBook(book);
        model.setViewName("operationSuccess");
        return model;
    }

}