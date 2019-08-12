package com.haodf.service.paramcheck.domain;

import com.haodf.service.paramcheck.annotation.Check;
import com.haodf.service.paramcheck.annotation.activeannotation.Min;
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

}
