package com.lanfangyi.service.paramcheck.domain;

import com.lanfangyi.service.paramcheck.annotation.Check;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Min(2)
    private long a;

    @Check
    private User2 user2;

    @Valid
    public void get(@NotNull Integer a) {
        System.out.println("=========");
    }

    public static void main(String[] args) {
        new User().get(null);
    }

}
