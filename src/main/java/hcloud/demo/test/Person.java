package hcloud.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Person {

    private String firstName;
    private String lastName;
    private String age;
}
