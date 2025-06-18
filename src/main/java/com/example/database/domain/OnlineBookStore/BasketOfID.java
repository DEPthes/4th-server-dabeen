package com.example.database.domain.OnlineBookStore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketOfID implements Serializable {
    private String email;
    private String basketid;
}
