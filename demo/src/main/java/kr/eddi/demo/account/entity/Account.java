package kr.eddi.demo.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String memberType;

    @Column(unique = true)
    private String nickname;
    private String totalAddress;


    public Account(String email, String password, String memberType, String nickname, String totalAddress) {
        this.email = email;
        this.password = password;
        this.memberType= memberType;
        this.nickname=nickname;
        this.totalAddress=totalAddress;
    }
}
