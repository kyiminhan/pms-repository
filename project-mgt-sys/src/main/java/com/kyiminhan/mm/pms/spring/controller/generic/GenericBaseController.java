package com.kyiminhan.mm.pms.spring.controller.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.constant.MessageConstant;
import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.constant.ViewConstant;
import com.kyiminhan.mm.pms.spring.controller.BaseController;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.validator.generic.GenericValidator;

import lombok.NonNull;
import lombok.Setter;

@Controller
public abstract class GenericBaseController<T extends Serializable> extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Class<T> clazz;
	private final BaseService<T> baseService;
	@Setter(onMethod = @__(@Autowired))
	protected GenericValidator<T> genericValidator;

	@SuppressWarnings("unchecked")
	public GenericBaseController(final BaseService<T> baseService) {
		super();
		this.baseService = baseService;
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	protected String createGenericObjName() {
		return new StringBuilder(this.clazz.getSimpleName().substring(0, 1).toLowerCase())
				.append(this.clazz.getSimpleName().substring(1)).toString();

	}

	protected String createGenericListName() {
		return new StringBuilder(this.createGenericObjName()).append("s").toString();
	}

	protected String getPath() {
		return this.clazz.getSimpleName().substring(0).toLowerCase();
	}

	protected String forward(@NonNull final String viewName) {
		return new StringBuilder(this.getPath()).append(URLConstant.DEFAULT_URL).append(viewName).toString();
	}

	protected String redirect(@NonNull final String url) {
		return new StringBuilder(URLConstant.REDIRECT).append(URLConstant.DEFAULT_URL).append(this.getPath())
				.append(url).toString();
	}

	protected String redirect(@NonNull final String url, @NonNull final String pathVariable) {
		return new StringBuilder(URLConstant.REDIRECT).append(URLConstant.DEFAULT_URL).append(this.getPath())
				.append(URLConstant.DEFAULT_URL).append(pathVariable).append(url).toString();
	}

	@GetMapping(value = { "", "/" })
	public String genericHome() {
		return this.redirect(URLConstant.LIST);
	}

	@GetMapping(value = URLConstant.CREATE)
	public String create(final Model model) throws Exception {
		model.addAttribute(this.createGenericObjName(), this.clazz.newInstance());
		return this.forward(ViewConstant.CREATE);
	}

	@PostMapping(value = URLConstant.CREATE)
	public String create(@ModelAttribute() final T object, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectAttributes) throws Exception {
		this.genericValidator.validate(object, bindingResult);
		if (bindingResult.hasErrors()) {
			return this.forward(ViewConstant.CREATE);
		}
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.MESSAGES, this.baseService.save(object));
		return this.redirect(URLConstant.CREATE);
	}

	@GetMapping(value = URLConstant.EDIT_PARAM)
	public String edit(@NonNull @PathVariable("uuid") final String uuid, final Model model) throws Exception {
		final T t = this.baseService.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, uuid));
		model.addAttribute(this.createGenericObjName(), t);
		return this.forward(ViewConstant.EDIT);
	}

	@PostMapping(value = URLConstant.EDIT_PARAM)
	public String edit(@NonNull @PathVariable("uuid") final String uuid, @ModelAttribute() final T object,
			final BindingResult bindingResult, final Model model, final RedirectAttributes redirectAttributes) {
		this.genericValidator.validate(object, bindingResult);
		if (bindingResult.hasErrors()) {
			return this.forward(ViewConstant.EDIT);
		}
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.MESSAGES, this.baseService.update(object));
		return this.redirect(URLConstant.EDIT, uuid);
	}

	@GetMapping(value = URLConstant.DETAIL_PARAM)
	public String detail(@NonNull @PathVariable("uuid") final String uuid, final Model model,
			final RedirectAttributes redirectAttribute) {
		final T t = this.baseService.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, uuid));
		model.addAttribute(this.createGenericObjName(), t);
		return this.forward(ViewConstant.DETAIL);
	}

	@GetMapping(value = URLConstant.DELETE_PARAM)
	public String delete(@NonNull @PathVariable("uuid") final String uuid,
			final RedirectAttributes redirectAttributes) {
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.MESSAGES, this.baseService.delete(uuid));
		return this.redirect(URLConstant.LIST);

	}

	@GetMapping(value = URLConstant.LIST)
	public String list(@NonNull final Model model) {
		model.addAttribute(this.createGenericListName(), this.baseService.findAll());
		return this.forward(ViewConstant.LIST);
	}
}