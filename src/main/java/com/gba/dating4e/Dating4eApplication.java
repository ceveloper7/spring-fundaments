package com.gba.dating4e;

import com.gba.dating4e.core.FileSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class Dating4eApplication {

	private static final Logger log = LoggerFactory.getLogger(Dating4eApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Dating4eApplication.class, args);
/**
		FileSystem fs = ctx.getBean(FileSystem.class);
		System.out.println(DataSize.ofBytes(fs.getFreeDiskSpace()).toBytes() + " GB");
 **/
	}

}
