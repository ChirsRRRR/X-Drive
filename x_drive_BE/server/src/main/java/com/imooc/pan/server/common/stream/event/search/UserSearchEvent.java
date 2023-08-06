package com.imooc.pan.server.common.stream.event.search;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UserSearchEvent implements Serializable {

    private static final long serialVersionUID = -9102948089281244619L;
    private String keyword;

    private Long userId;


    public UserSearchEvent(String keyword, Long userId) {
        this.keyword = keyword;
        this.userId = userId;
    }
}
