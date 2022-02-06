package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// represent a split as to who paid what
@Setter
@Getter
@AllArgsConstructor
public class Split {
    private User user;
    private double amount;
}
