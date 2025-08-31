package pl.kcn333.validationDemo;

import jakarta.validation.constraints.*;
import pl.kcn333.validationDemo.validation.CourseCode;

public class Customer {

    @NotNull( message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull( message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull( message = "is required")
    @Min(value = 1, message = "min 1")
    @Max(value = 10, message = "max 10")
    private Integer passes;

    @NotNull
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "invalid format")
    private String postalCode;


    @CourseCode(value = "K", message = "has to start with K")
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getPasses() {
        return passes;
    }

    public void setPasses(Integer passes) {
        this.passes = passes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer() {
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
