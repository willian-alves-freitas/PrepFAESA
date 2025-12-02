package com.company.quizopedia.security;

import com.company.quizopedia.entity.Opcao;
import com.company.quizopedia.entity.Questao;
import com.company.quizopedia.entity.Questionario;
import com.company.quizopedia.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Aluno", code = AlunoRole.CODE)
public interface AlunoRole {
    String CODE = "aluno";

    @MenuPolicy(menuIds = "Questionario.list")
    @ViewPolicy(viewIds = {"Questionario.list", "QuestionarioView"})
    void screens();

    @EntityAttributePolicy(entityClass = Questao.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Questao.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void questao();

    @EntityAttributePolicy(entityClass = Questionario.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Questionario.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void questionario();

    @EntityAttributePolicy(entityClass = Opcao.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Opcao.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void opcao();

    @EntityPolicy(entityClass = User.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void user();
}