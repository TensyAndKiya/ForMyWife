package com.clei.D20190617;

import com.clei.Y2019.M06.D15.DateUtils;
import java.time.LocalDateTime;

public class LocalJarTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(DateUtils.format(localDateTime));
    }
}
