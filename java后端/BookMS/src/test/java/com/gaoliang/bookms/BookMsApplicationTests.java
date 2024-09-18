package com.gaoliang.bookms;

import com.gaoliang.bookms.Mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

@SpringBootTest
class BookMsApplicationTests
{
	@Autowired
	private BookMapper bookMapper;

	@Test
	void test1()
	{
		System.out.println(bookMapper.getall());
	}

	@Test
	void test2()
	{
		ArrayList<Integer> arr =
			new ArrayList<>(Arrays.asList(1, 2));

		arr.removeIf(val -> val.equals(2));

		System.out.println(arr);
	}
}

