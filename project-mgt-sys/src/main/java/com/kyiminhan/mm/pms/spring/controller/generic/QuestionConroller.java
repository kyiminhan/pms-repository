package com.kyiminhan.mm.pms.spring.controller.generic;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kyiminhan.mm.pms.common.constant.MessageConstant;
import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.constant.ViewConstant;
import com.kyiminhan.mm.pms.spring.entity.Question;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.service.QuestionService;

@Controller
@RequestMapping(path = "/question")
public class QuestionConroller extends GenericBaseController<Question> {

	private static final long serialVersionUID = 1L;
	private final QuestionService questionService;

	@Autowired
	public QuestionConroller(@Qualifier("questionServiceImpl") final BaseService<Question> baseService) {
		super(baseService);
		this.questionService = (QuestionService) baseService;

	}

	@GetMapping(value = URLConstant.DELETE_PARAM_PAGE_NO)
	public String delete(@PathVariable("uuid") final String uuid, @PathVariable("page") final String page,
			final RedirectAttributes redirectAttributes) {
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.MESSAGES, this.questionService.delete(uuid));
		return this.redirect(URLConstant.LIST + "/" + page);

	}

	@GetMapping(value = { URLConstant.LIST })
	@Override
	public String list(final Model model) {
		return this.redirect(URLConstant.LIST + "/1");
	}

	@GetMapping(value = { URLConstant.LIST_PAGEING })
	public String list(@PathVariable("page") final String page, final Model model) {
		final Map<String, Object> map = this.questionService
				.findAllWithPaging((null != page) ? Integer.valueOf(page) : 0);
		model.addAttribute(this.createGenericListName(), map);
		return this.forward(ViewConstant.LIST);

	}
}