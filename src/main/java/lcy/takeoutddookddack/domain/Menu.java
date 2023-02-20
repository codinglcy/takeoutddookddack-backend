package lcy.takeoutddookddack.domain;

import jakarta.persistence.*;

public class Menu {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private Integer price;
}
