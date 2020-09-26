package com.learn.ssm.chapter11.aop.verifier.impl;

import com.learn.ssm.chapter11.aop.verifier.RoleVerifier;
import com.learn.ssm.chapter11.game.pojo.Role;

public class RoleVerifierImpl implements RoleVerifier {
    public boolean verify(Role role) {
        return role != null;
    }
}
