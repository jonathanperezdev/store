package com.aurora.store.handlers;

import com.aurora.store.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JakartaValidatorHandler {

    //Injects the Jakarta Validation who validate annotations like @NotNull
    private final Validator validator;

    public  <T> T validateModel(T model){
        Errors errors = new BeanPropertyBindingResult(model, model.getClass().getSimpleName());
        List<String> errorsMsg;
        validator.validate(model, errors);
        if (Objects.nonNull(errors) && !CollectionUtils.isEmpty(errors.getAllErrors())){
            errorsMsg = errors.getAllErrors().stream().map(this::getErrorOf).collect(Collectors.toList());
            throw new BadRequestException(errorsMsg);
        }

        return model;
    }

    private String getErrorOf(ObjectError error){
        FieldError field = (FieldError) error;
        return field.getField()+" "+field.getDefaultMessage();
    }
}
