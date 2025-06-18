package com.example.database.domain.OnlineBookStore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StocksID implements Serializable {
    private int isbn;
    private String code;
}
