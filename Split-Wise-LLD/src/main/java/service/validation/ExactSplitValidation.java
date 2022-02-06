package service.validation;

import model.User;

import java.util.Map;

public class ExactSplitValidation implements SplitValidation {

    public boolean validate(int totAmount, Map<User, Double> userContribution) {
        return true;
    }
}
