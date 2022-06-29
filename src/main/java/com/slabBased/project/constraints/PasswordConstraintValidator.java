package com.slabBased.project.constraints;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        List<Rule> rules = new ArrayList<>();

        rules.add(new LengthRule(6,20));

        rules.add(new WhitespaceRule());

        rules.add(new UsernameRule());

        rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));

        rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));

        rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));

        rules.add(new CharacterRule(EnglishCharacterData.Special, 1));
        PasswordValidator validator = new PasswordValidator(rules);

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = String.join(",", messages);

        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
