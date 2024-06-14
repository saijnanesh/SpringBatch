package com.example.spring.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class Write implements ItemWriter<String> {

	@Override
	public void write(Chunk<? extends String> chunk) throws Exception {
		System.out.println("Inside writer");
		System.out.println("Wrirting data" + chunk);
	}

}
