package cn.whaifree.designPattern.ChainPattern.rule;

import cn.hutool.core.lang.Validator;

/**
 *
 * 责任链模式
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/31 17:33
 * @注释
 */
public class NoIfElse {
    public static void main(String[] args) {
        VerifyEmailRule verifyEmailRule = new VerifyEmailRule();
        verifyEmailRule.appendRuleChain(new VerifyPhoneRule());

        System.out.println(verifyEmailRule.processLogic("13648765326"));
    }
}


interface IRuleChain {
    IRuleChain appendRuleChain(IRuleChain ruleChain);
    IRuleChain getNextRuleChain();
    Object processLogic(Object inputData);
}

abstract class AbstractRuleChain implements IRuleChain {


    IRuleChain next;

    @Override
    public IRuleChain appendRuleChain(IRuleChain ruleChain) {
        next = ruleChain;
        return next;
    }

    @Override
    public IRuleChain getNextRuleChain() {
        return next;
    }


    @Override
    public Object processLogic(Object inputData) {
        if (next != null) {
            return next.processLogic(inputData);
        }else {
            return inputData;
        }
    }
}

class VerifyEmailRule extends AbstractRuleChain{



    @Override
    public Object processLogic(Object inputData) {
        // 具体执行方法
        boolean email = Validator.isEmail((CharSequence) inputData);
        if (email) {
            System.out.println("email:" + email);
        }else {
            System.out.println("no email:" + email);
        }

        return super.processLogic(inputData);
    }
}

class VerifyPhoneRule extends AbstractRuleChain{

    @Override
    public Object processLogic(Object inputData) {
        // 具体执行方法
        boolean phone = Validator.isMobile((CharSequence) inputData);
        if (phone) {
            System.out.println("phone:" + phone);
        }else {
            System.out.println("no phone:" + phone);
        }
        return super.processLogic(inputData);
    }
}
