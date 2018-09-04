package be.vdab.groenetenen.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.groenetenen.web.VanTotPostcodeForm;

public class VanTotPostcodeValidator implements ConstraintValidator<VanTotPostCodeFormVanIsKleinerDanTot, VanTotPostcodeForm>{

	@Override
public void initialize(VanTotPostCodeFormVanIsKleinerDanTot vanTotPosstCodeForm) {
		
	}
	
	@Override
	public boolean isValid(VanTotPostcodeForm form, ConstraintValidatorContext context) {
		if(form.getVan() == null || form.getTot() == null) {
			return true;
		}
		return form.getVan() < form.getTot();
	}

}
