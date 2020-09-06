package com.demo.model;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostBody {

    @NotNull
    private String source;
    @NotNull
    private String destination;
    @NotNull
    private Integer price;

    public String toString(){
        return source + "," + destination + "," + price;
    }
}
