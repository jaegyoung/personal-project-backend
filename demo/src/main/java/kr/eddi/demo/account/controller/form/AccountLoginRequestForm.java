package kr.eddi.demo.account.controller.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountLoginRequestForm {

    final private String email;
    final private String password;
}
