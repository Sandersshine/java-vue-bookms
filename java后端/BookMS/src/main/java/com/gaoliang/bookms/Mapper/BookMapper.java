package com.gaoliang.bookms.Mapper;

import com.gaoliang.bookms.entity.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface BookMapper
{
	@Select("select * from bookms.book")
	List<Book> getall();

	// 是#{name} 不是#{book.name}
	@Insert("insert into bookms.book (name, author, press) " +
		"values (#{name}, #{author}, #{press})")
	int insertOne(Book book);

	@Delete("delete from bookms.book where id = #{1}")
	boolean deleteOne(int id);

	@Update("update book " +
		"set name=#{name}, author=#{author}, press=#{press} " +
		"where id = #{id}")
	boolean update(Book book);
}
