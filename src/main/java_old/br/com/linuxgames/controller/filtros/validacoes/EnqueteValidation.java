package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class EnqueteValidation  extends ValidationFilter {

	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("titulo", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("opt1", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("qt1", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("opt2", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("qt2", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("ativo", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}
