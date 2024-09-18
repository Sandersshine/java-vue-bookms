package com.gaoliang.bookms.controller;

import com.gaoliang.bookms.Mapper.BookMapper;
import com.gaoliang.bookms.entity.Book;
import com.gaoliang.bookms.entity.DeleteReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET,
	RequestMethod.POST, RequestMethod.HEAD, RequestMethod.PUT})
public class BookController
{
	@Autowired
	private BookMapper bookMapper;

	@RequestMapping("/all")
	public List<Book> query()
	{
		return bookMapper.getall();
	}

	@RequestMapping("/insert")
	public int insert(@RequestBody Book book)
	{
		return bookMapper.insertOne(book);
		// System.out.println(book);
		// return 1;
	}

	@RequestMapping("/delete")
	public boolean delete(@RequestParam int id)
	{
		System.out.println("删除id：" + id);
		// return bookMapper.deleteOne(id);
		return true;
	}

	@RequestMapping("/update")
	public boolean update(@RequestBody Book book)
	{
		System.out.println("更新后的书：" + book);
		return bookMapper.update(book);
		// return true;
	}
}
