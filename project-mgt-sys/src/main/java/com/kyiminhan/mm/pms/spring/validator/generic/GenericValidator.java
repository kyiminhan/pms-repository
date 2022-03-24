package com.kyiminhan.mm.pms.spring.validator.generic;

import java.io.Serializable;

import com.kyiminhan.mm.pms.spring.validator.BaseValidator;

/**
 * The Interface GenericValidator.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @param <T> the generic type
 * @since Mar 8, 2019 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.spring.validator </BR>
 *        GenericValidator.java </BR>
 */
public interface GenericValidator<T extends Serializable> extends BaseValidator {
}