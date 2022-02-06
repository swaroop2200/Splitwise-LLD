package service.validation;

import model.User;

import java.util.Map;

public interface SplitValidation {
    boolean validate(int totAmount, Map<User, Double> userContribution);
}
