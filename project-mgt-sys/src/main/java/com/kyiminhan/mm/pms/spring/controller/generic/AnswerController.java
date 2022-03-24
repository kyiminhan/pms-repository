package com.kyiminhan.mm.pms.spring.controller.generic;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kyiminhan.mm.pms.common.constant.ControllerConstant;
import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.constant.MessageConstant;
import com.kyiminhan.mm.pms.common.constant.ViewConstant;
import com.kyiminhan.mm.pms.spring.entity.Answer;
import com.kyiminhan.mm.pms.spring.entity.Question;
import com.kyiminhan.mm.pms.spring.service.AnswerService;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.service.QuestionService;

import lombok.NonNull;
import lombok.Setter;

@Controller
@RequestMapping(path = "/question/{id}")
public class AnswerController extends GenericBaseController<Answer> {

	private static final long serialVersionUID = 1L;
	private final AnswerService answerService;
	@Setter(onMethod = @__(@Autowired))
	private QuestionService questionService;

	@Autowired
	public AnswerController(@Qualifier("answerServiceImpl") final BaseService<Answer> baseService) {
		super(baseService);
		this.answerService = (AnswerService) baseService;

	}

	@ModelAttribute(value = "questions")
	public Collection<Question> getProjects() {
		Collection<Question> questions = new HashSet<>();
		questions = this.answerService.findAllQuestions();
		return questions;
	}

	@GetMapping(value = "/{page}/answer/create")
	public String create(@NonNull @PathVariable("page") final String page, @PathVariable("id") final String id,
			final Model model) throws Exception {
		final Question question = this.questionService
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, id));
		model.addAttribute(ControllerConstant.PAGE, page);
		model.addAttribute(this.createGenericObjName(), Answer.builder().question(question).build());
		return this.forward(ViewConstant.CREATE);
	}

	@PostMapping(value = "/{page}/answer/create")
	public String create(@NonNull @PathVariable("page") final String page, @NonNull @PathVariable("id") final String id,
			final Answer object, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectAttributes) throws Exception {
		object.setQuestion(this.questionService.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, id)));
		this.genericValidator.validate(object, bindingResult);
		model.addAttribute(ControllerConstant.PAGE, page);
		model.addAttribute(this.createGenericObjName(), object);
		if (bindingResult.hasErrors()) {
			return this.forward(ViewConstant.CREATE);
		}
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.MESSAGES, this.answerService.save(object));
		return "redirect:/question/" + id + "/" + page + "/answer/create";
	}

	@GetMapping(value = "/{page}/answer/{aid}/edit")
	public String edit(@NonNull @PathVariable("page") final String page, @NonNull @PathVariable("id") final String qId,
			@NonNull @PathVariable("aid") final String aId, final Model model) throws Exception {
		final Answer answer = this.answerService.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, aId));
		final Question question = this.questionService
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, qId));
		answer.setQuestion(question);
		model.addAttribute(ControllerConstant.PAGE, page);
		model.addAttribute(this.createGenericObjName(), answer);
		return this.forward(ViewConstant.EDIT);
	}

	@PostMapping(value = "/{page}/answer/{aid}/edit")
	public String edit(@NonNull @PathVariable("page") final String page, @NonNull @PathVariable("id") final String qId,
			@NonNull @PathVariable("aid") final String aId, final Answer object, final BindingResult bindingResult,
			final Model model, final RedirectAttributes redirectAttributes) {
		object.setQuestion(this.questionService.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, qId)));
		this.genericValidator.validate(object, bindingResult);
		model.addAttribute(ControllerConstant.PAGE, page);
		model.addAttribute(this.createGenericObjName(), object);
		if (bindingResult.hasErrors()) {
			return this.forward(ViewConstant.EDIT);
		}
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.MESSAGES, this.answerService.update(object));
		return "redirect:/question/" + qId + "/" + page + "/answer/" + aId + "/edit";
	}

	@GetMapping(value = "/{page}/answer/{uuid}/delete")
	public String delete(@NonNull @PathVariable("page") final String page,
			@NonNull @PathVariable("id") final String qId, @NonNull @PathVariable("uuid") final String uuid,
			final RedirectAttributes redirectAttributes) {
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.MESSAGES, this.answerService.delete(uuid));
		return "redirect:/question/list/" + page;
	}
}