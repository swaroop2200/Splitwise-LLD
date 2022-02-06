package service;

import model.Split;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitService {

    public  List<Split> getExactSplits(double totalAmount, Map<User, Double> userContributions, User paidBy){
        // do validations first
        List<Split> splits = new ArrayList<Split>();
        for (Map.Entry<User, Double> entry : userContributions.entrySet()) {
            splits.add(new Split(entry.getKey(), entry.getValue()));
        }
        return splits;
    }

    public  List<Split> getPercentageSplits(double totalAmount, Map<User, Double> userContributions, User paidBy){
        //new PercentageSplitValidation().validate(totalAmount, userContributions);
        List<Split> splits = new ArrayList<Split>();
        for (Map.Entry<User, Double> entry : userContributions.entrySet()) {
            splits.add(new Split(entry.getKey(), (totalAmount* entry.getValue())/100.0));
        }
        return splits;
    }

    public  List<Split> getEqualSplits(double totalAmount, List<User> users, User paidBy){
        // do validations first
        double splitAmount = ((double) Math.round(totalAmount*100/ users.size()))/100.0;
        List<Split> splits = new ArrayList<Split>();
        for(User user : users){
            splits.add(new Split(user, splitAmount));
        }
        splits.get(0).setAmount(splitAmount + (totalAmount - splitAmount* users.size()));
        return splits;
    }

}
