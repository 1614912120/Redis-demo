package com.example.redisdemo.poko;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: User
 * Package: com.example.redisdemo.poko
 * Description:
 *
 * @Author R
 * @Create 2024/4/6 18:35
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
}
