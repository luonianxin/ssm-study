package com.learn.ssm.chapter15.validator;

import com.learn.ssm.chapter15.pojo.Transition;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TransitaionValidator implements Validator {
    /**
     * Can this {@link Validator} {@link #validate(Object, Errors) validate}
     * instances of the supplied {@code clazz}?
     * <p>This method is <i>typically</i> implemented like so:
     * <pre class="code">return Foo.class.isAssignableFrom(clazz);</pre>
     * (Where {@code Foo} is the class (or superclass) of the actual
     * object instance that is to be {@link #validate(Object, Errors) validated}.)
     *
     * @param clazz the {@link Class} that this {@link Validator} is
     *              being asked if it can {@link #validate(Object, Errors) validate}
     * @return {@code true} if this {@link Validator} can indeed
     * {@link #validate(Object, Errors) validate} instances of the
     * supplied {@code clazz}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Transition.class.equals(clazz);
    }

    /**
     * Validate the supplied {@code target} object, which must be
     * of a {@link Class} for which the {@link #supports(Class)} method
     * typically has (or would) return {@code true}.
     * <p>The supplied {@link Errors errors} instance can be used to report
     * any resulting validation errors.
     *
     * @param target the object that is to be validated
     * @param errors contextual state about the validation process
     * @see
     */
    @Override
    public void validate(Object target, Errors errors) {
            Transition trans = (Transition) target;
            double dis = trans.getAmount() - (trans.getPrice() * trans.getQuantity());
            if(Math.abs(dis)>0.01d){
                errors.rejectValue("amount","1002","交易金额和购买数量与价格不匹配");
            }
    }
}
