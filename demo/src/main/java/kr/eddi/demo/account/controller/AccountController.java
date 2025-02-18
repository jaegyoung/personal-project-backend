package kr.eddi.demo.account.controller;

import kr.eddi.demo.account.controller.form.AccountLoginRequestForm;
import kr.eddi.demo.account.controller.form.AccountRegisterForm;
import kr.eddi.demo.account.controller.form.PasswordCheckForm;
import kr.eddi.demo.account.controller.form.UsertokenRequestForm;
import kr.eddi.demo.account.entity.Account;
import kr.eddi.demo.account.service.AccountService;
import kr.eddi.demo.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

   final private AccountService accountService;
    final private RedisService redisService;

    @PostMapping("/register")
    public Boolean accountRegister(@RequestBody AccountRegisterForm accountRegisterForm){
        log.info("register(): "+accountRegisterForm);
        return accountService.register(accountRegisterForm.toAccountRequest());
    }
    @PostMapping("/login")
    public List<String> accountLogin(@RequestBody AccountLoginRequestForm accountLoginRequestForm) {
        List<String> responseList= new ArrayList<>();
        String userToken= accountService.login(accountLoginRequestForm);
        Long accountID= accountService.findAccountIdByEmail(accountLoginRequestForm.getEmail());
        String nickname= accountService.findNicknameByAccountId(accountID);
        redisService.setKeyAndValue(userToken,accountID);
        responseList.add(userToken);
        responseList.add(nickname);
        return responseList;
    }
    @PostMapping("/passwordCheck")
    public Boolean passwordCheck (@RequestBody PasswordCheckForm form){
        return accountService.checkPassword(form);
    }
    @PostMapping("/myAccount")
    public Account account (@RequestBody UsertokenRequestForm usertokenRequestForm){
        log.info(usertokenRequestForm.getUserToken());
        return accountService.findAccountByUsertoken(redisService.getValueByKey(usertokenRequestForm.getUserToken()));
    }
}
