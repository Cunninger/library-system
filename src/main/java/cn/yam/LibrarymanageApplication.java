package cn.yam;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("cn.yam.mapper")
@SpringBootApplication
@Slf4j
public class LibrarymanageApplication {

    public static void main(String[] args) {
        log.info("xxxxxxxxxxxxx程序启动成功！");
        SpringApplication.run(LibrarymanageApplication.class, args);
    }

}
// int main()