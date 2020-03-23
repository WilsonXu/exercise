package org.wilson.corejava.v1.ch08.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by wilson on 2020/3/23.
 */
@AllArgsConstructor
@NoArgsConstructor
public class Pair<T> {
    @Getter
    @Setter
    private T first;
    @Getter
    @Setter
    private T second;
}
