package com.board.board.config;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.board.board.domain.UserAccount;
import com.board.board.repository.UserAccountRepository;
import java.util.Optional;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@Import(SecurityConfig.class)
public class TestSecurityConfig {
  @MockBean
  private UserAccountRepository userAccountRepository;

  @BeforeTestMethod
  public void securitySetUp() {
    given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
        "godoriTest",
        "pw",
        "godori-test@email.com",
        "godori-test",
        "test memo"
    )));
  }

}
