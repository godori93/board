package com.board.board.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

  @Bean
  public AuditorAware<String> auditorAware() {
    return () -> Optional.of("godori"); // Todo: 스프링 시큐리티로 인증 기능을 붙일 때, 수정하기
  }

}
