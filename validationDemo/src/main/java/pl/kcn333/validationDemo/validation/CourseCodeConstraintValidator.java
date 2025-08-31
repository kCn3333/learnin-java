package pl.kcn333.validationDemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode,String> {

    private String coursePrefix;

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        if(code!=null)
            return code.startsWith(coursePrefix);
        else return true;
    }

    @Override
    public void initialize(CourseCode courseCode) {
        this.coursePrefix=courseCode.value();
    }
}
