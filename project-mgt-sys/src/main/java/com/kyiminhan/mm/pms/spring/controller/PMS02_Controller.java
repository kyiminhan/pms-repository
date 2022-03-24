package com.kyiminhan.mm.pms.spring.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyiminhan.mm.pms.common.constant.ControllerConstant;
import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.web.model.Day;
import com.kyiminhan.mm.pms.web.model.Month;
import com.kyiminhan.mm.pms.web.model.Plan;
import com.kyiminhan.mm.pms.web.model.SubTask;
import com.kyiminhan.mm.pms.web.model.Task;

/**
 * The Class PMS01_Controller.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 7, 2019 </BR>
 *        project-mgt-sys system </BR>
*        com.kyiminhan.mm.pms.spring.controller </BR>
 *        PMS01_Controller.java </BR>
 */
@Controller
public class PMS02_Controller {

	/**
	 * Inits the.
	 *
	 * @param model the model
	 * @return String
	 */
	@GetMapping(value = { URLConstant.DEFAULT_URL, URLConstant.HOME })
	public String init(final Model model) {
		final LocalDate currentDate = LocalDate.now();
		final Collection<Day> days = new ArrayList<>();

		final Collection<Plan> taskPlans = new ArrayList<>();
		final Collection<Plan> subTaskPlans = new ArrayList<>();

		for (int i = 1; i <= currentDate.getMonth().maxLength(); i++) {
			final int year = currentDate.getYear();
			final int monthValue = currentDate.getMonthValue();
			days.add(Day.builder().date(LocalDate.of(year, monthValue, i)).build());
			taskPlans.add(Plan.builder().date(LocalDate.of(year, monthValue, i)).planEnable(true).build());
			subTaskPlans.add(Plan.builder().date(LocalDate.of(year, monthValue, i)).planEnable(true).build());
		}

		final Collection<Month> months = new ArrayList<>();
		months.add(Month.builder().monthName(currentDate.getMonth().name()).days(days).build());

		final Collection<SubTask> subTasks = new ArrayList<>();
		subTasks.add(SubTask.builder().subTaskPlans(subTaskPlans).subTaskName("TODO").build());

		final Collection<Task> tasks = new ArrayList<>();
		tasks.add(Task.builder().taskName("PMS02").taskPlans(taskPlans).subTasks(subTasks).build());

		model.addAttribute("months", months);
		model.addAttribute("tasks", tasks);

		return ControllerConstant.PMS02_VIEW;
	}
}