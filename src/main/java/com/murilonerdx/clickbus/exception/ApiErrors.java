package com.murilonerdx.clickbus.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.validation.BindingResult;

public class ApiErrors {
  private List<String> errors;

  public ApiErrors(BindingResult bindingResult){
    this.errors = new ArrayList<>();
    bindingResult.getAllErrors().forEach(error->this.errors.add(error.getDefaultMessage()));
  }

  public ApiErrors(IllegalArgumentException e) {
    this.errors = (Collections.singletonList(e.getMessage()));
  }

  public ApiErrors(PlaceNotFound e) {
    this.errors = (Collections.singletonList(e.getMessage()));
  }

  public List<String> getErrors() {
    return errors;
  }
}
