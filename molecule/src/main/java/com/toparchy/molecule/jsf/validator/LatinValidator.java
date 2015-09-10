package com.toparchy.molecule.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("latinValidator")
public class LatinValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Matcher matcher = Pattern.compile("^[A-Z]+_*[A-Z]+$").matcher((String) value);
		if (!matcher.matches()) {
			throw new ValidatorException(new FacesMessage("只能是大写英文字母及下划线，且下划线不能在开头与结尾处"));
		}
	}

}
